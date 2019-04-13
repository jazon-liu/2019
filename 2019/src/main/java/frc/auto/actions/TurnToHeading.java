package frc.auto.actions;

import frc.robot.subsystems.Drivetrain;
import lib.geometry.Rotation2d;
import lib.util.DriveSignal;

/**
 * Turns the robot to a specified heading
 * 
 * @see Action
 */
public class TurnToHeading implements Action {

    private Rotation2d mTargetHeading;
    private Drivetrain mDrive = Drivetrain.getInstance();

    public TurnToHeading(Rotation2d heading) {
        mTargetHeading = heading;
    }

    @Override
    public boolean isFinished() {
        return mDrive.isDoneWithTurn();
    }

    @Override
    public void update() {
    }

    @Override
    public void done() {
        System.out.println("Finished TurnToHeading!!!");
        mDrive.setOpenLoop(new DriveSignal(0.0, 0.0));
        mDrive.setBrakeMode(false);
    }

    @Override
    public void start() {
        mDrive.setWantTurnToHeading(mTargetHeading);
    }
}