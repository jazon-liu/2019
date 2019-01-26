package frc.robot;

import frc.auto.AutoModeBase;
import frc.auto.creators.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.Optional;

public class NewAutoModeSelector {

    enum StartingHeight {
        LEVEL_ONE,
        LEVEL_TWO
    }

    ;

    enum StartingPosition {
        LEFT,
        CENTER,
        RIGHT
    }

    ;

    enum DesiredMode {
        DO_NOTHING,
        CROSS_AUTO_LINE,
        CARGO_SHIP,
        CARGO_SHIP_AND_ROCKET,
        ROCKET
    }

    ;

    private StartingHeight mCachedStartingHeight = null;
    private StartingPosition mCachedStartingPosition = null;
    private DesiredMode mCachedDesiredMode = null;

    private Optional<AutoModeCreator> mCreator = Optional.empty();

    private SendableChooser<StartingHeight> mStartHeightChooser;
    private SendableChooser<StartingPosition> mStartPositionChooser;
    private SendableChooser<DesiredMode> mModeChooser;

    public NewAutoModeSelector() {

        mStartHeightChooser = new SendableChooser<>();
        mStartHeightChooser.setDefaultOption("Level 1", StartingHeight.LEVEL_ONE);
        mStartHeightChooser.addOption("Level 2", StartingHeight.LEVEL_TWO);
        SmartDashboard.putData("Start Height", mStartHeightChooser);

        mStartPositionChooser = new SendableChooser<>();
        mStartPositionChooser.setDefaultOption("Right", StartingPosition.RIGHT);
        mStartPositionChooser.addOption("Center", StartingPosition.CENTER);
        mStartPositionChooser.addOption("Right", StartingPosition.RIGHT);

        mModeChooser = new SendableChooser<>();
        mModeChooser.setDefaultOption("Cross Auto Line ", DesiredMode.CROSS_AUTO_LINE);
        mModeChooser.addOption("Do Nothing", DesiredMode.DO_NOTHING);
        mModeChooser.addOption("Cargo Ship", DesiredMode.CARGO_SHIP);
        mModeChooser.addOption("Cargo Ship AND Rocket", DesiredMode.CARGO_SHIP_AND_ROCKET);
        mModeChooser.addOption("Only Rocket", DesiredMode.ROCKET);
    }

    public void updateModeCreator() {

        StartingHeight startingHeight = mStartHeightChooser.getSelected();
        StartingPosition startingPosition = mStartPositionChooser.getSelected();
        DesiredMode desiredMode = mModeChooser.getSelected();

        if (mCachedDesiredMode != desiredMode || mCachedStartingHeight != startingHeight || mCachedStartingPosition != startingPosition) {
            System.out.println("Auto selection changed, updating creator! Desired Mode->" + desiredMode.name() + ", Starting Height->" + startingHeight.name() + ", Starting Position->" + startingPosition.name());
            mCreator = getCreatorForParams(desiredMode, startingHeight, startingPosition);
        }

        mCachedDesiredMode = desiredMode;
        mCachedStartingHeight = startingHeight;
        mCachedStartingPosition = startingPosition;
    }

    private Optional<AutoModeCreator> getCreatorForParams(DesiredMode mode, StartingHeight height, StartingPosition position) {
        boolean startOnOne = StartingHeight.LEVEL_ONE == height;
        boolean startOnLeft = StartingPosition.LEFT == position;

        switch (mode) {
            case CROSS_AUTO_LINE:
                return Optional.of(new CrossAutoLineCreator());
            case CARGO_SHIP:
                return Optional.of(new CargoShipModeCreator(startOnOne, startOnLeft));
            case CARGO_SHIP_AND_ROCKET:
                return Optional.of(new CargoShipANDRocketModeCreator(startOnOne, startOnLeft));
            case ROCKET:
                return Optional.of(new RocketModeCreator(startOnOne, startOnLeft));
            default:
                break;
        }

        System.err.println("No valid auto mode found for  " + mode);
        return Optional.empty();
    }

    public void reset() {
        mCreator = Optional.empty();
        mCachedDesiredMode = null;
    }

    public void outputToSmartDashboard() {
        SmartDashboard.putString("AutoModeSelected", mCachedDesiredMode.name());
        SmartDashboard.putString("StartingHeightSelected", mCachedStartingHeight.name());
        SmartDashboard.putString("StartingPositionSelected", mCachedStartingPosition.name());
    }

    public Optional<AutoModeBase> getAutoMode() {
        if (!mCreator.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(mCreator.get().getStateDependentAutoMode(fieldState));
    }
}