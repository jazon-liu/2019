package frc.auto.modes;

import frc.auto.AutoModeBase;
import frc.auto.AutoModeEndedException;
import frc.auto.actions.*;
import frc.paths.TrajectoryGenerator;
import lib.geometry.Translation2d;

import java.util.Arrays;

public class CargoShipModeHard extends AutoModeBase {

    private static final TrajectoryGenerator mTrajectoryGenerator = TrajectoryGenerator.getInstance();

    final boolean mStartedLeft;

    private DriveTrajectory mLevel2ToCargoThree;
    private DriveTrajectory mCargoThreeTo90Turn;
    private DriveTrajectory mEndTurnToLoadingStation;
    private DriveTrajectory mLoadingStationToCargoFour;

    public CargoShipModeHard(boolean driveToLeftCargo) {
        mStartedLeft = driveToLeftCargo;

        mLevel2ToCargoThree = new DriveTrajectory(mTrajectoryGenerator.getTrajectorySet().levelTwoToCargoTwoLineup.get(mStartedLeft), true);

        // mLevel2ToCargoTwo = new DriveTrajectory(mTrajectoryGenerator.getTrajectorySet().level2ToCargoTwo.get(mStartedLeft), true);
        // mLevel2ToCargoOne = new DriveTrajectory(mTrajectoryGenerator.getTrajectorySet().level2ToCargoThree.get(mStartedLeft), true);
        // mCargoThreeTo90Turn = new DriveTrajectory(mTrajectoryGenerator.getTrajectorySet().cargoThreeTo90Turn.get(mStartedLeft));
        // mEndTurnToLoadingStation = new DriveTrajectory(mTrajectoryGenerator.getTrajectorySet().endTurnToLoadingStation.get(mStartedLeft));
        // mLoadingStationToCargoFour = new DriveTrajectory(mTrajectoryGenerator.getTrajectorySet().loadingStationToCargoFour.get(mStartedLeft));
    }

    @Override
    protected void routine() throws AutoModeEndedException {
        System.out.println("Running Cargo Mode");

        //Score First Hatch
        runAction(new ParallelAction (
            Arrays.asList(
                mLevel2ToCargoThree,
                new SeriesAction(
                    Arrays.asList(
                        new WaitAction(2.0),
                        new ExtendRetract4Bar(true)
                    )
                )
            )
        ));

        // runAction(new SeriesAction (
        //     Arrays.asList(
        //         // new TurnToTarget(0.5),
        //         new OpenLoopDrive(0.5, 0.5, 0.5)
        //     )
        // ));

        // runAction(new SeriesAction (
        //     Arrays.asList(
        //         new OpenCloseBeak(true),
        //         new SeriesAction(
        //             Arrays.asList(
        //                 new WaitAction(0.25),
        //                 new OpenLoopDrive(-0.5, -0.5, 0.5)
        //             )
        //         )
        //     )
        // ));

        // //Turn 90 degrees
        // runAction(new ParallelAction (
        //     Arrays.asList(
        //         mCargoThreeTo90Turn
        //     )
        // ));

        // //Get Second Hatch
        // runAction(new ParallelAction (
        //     Arrays.asList(
        //         mEndTurnToLoadingStation
        //     )
        // ));

        // //Score Second Hatch
        // runAction(new ParallelAction (
        //     Arrays.asList(
        //         mLoadingStationToCargoFour
        //     )
        // ));

        // //Score Second Hatch
        // runAction(new ParallelAction (
        //     Arrays.asList(
        //         new OpenLoopDrive(0.5, 0.5, 0.2)
        //     )
        // ));
    }
}