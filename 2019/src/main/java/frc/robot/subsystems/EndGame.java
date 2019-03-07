/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Constants;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class EndGame extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private static EndGame m_instance;


  private boolean m_AnklesReleased = false;


 
  private final DoubleSolenoid m_endGameAnkles = new DoubleSolenoid(RobotMap.PCM_ONE, RobotMap.ENDGAME_FEET_FORWARD, RobotMap.ENDGAME_FEET_REVERSE);

  private final WPI_TalonSRX m_EndGameDrive = new WPI_TalonSRX(RobotMap.ENDGAME_DRIVE);

  public EndGame() {

    super();

    this.configureMotors();
    this.setAnklesReleased(false);

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public static EndGame getInstance() {
    if (m_instance == null) {
      m_instance = new EndGame();
    }
    return m_instance;


  }

  private void setFactoryDefault() {
    m_EndGameDrive.configFactoryDefault();
  }

  private void configureMotors() {

    setFactoryDefault();    

    m_EndGameDrive.set(ControlMode.PercentOutput, 0.0);

    m_EndGameDrive.configOpenloopRamp(0.5, 0);

    m_EndGameDrive.configVoltageCompSaturation(10.0, 0);
    m_EndGameDrive.enableVoltageCompensation(true);

    m_EndGameDrive.configNominalOutputForward(0.0, 0);
    m_EndGameDrive.configNominalOutputReverse(0.0, 0);
    m_EndGameDrive.setInverted(true);
    
  }


  public void setEndGameDriveSpeed(double speed){

    //note only positive values

      m_EndGameDrive.set(ControlMode.PercentOutput, speed);
    // if(on)
    //   m_EndGameDrive.set(ControlMode.PercentOutput, -Constants.ENDGAME_DRIVE_SPEED);
    // else
    //   m_EndGameDrive.set(ControlMode.PercentOutput, 0);
  }

  public void setAnklesReleased (boolean isBroken) {

    if (isBroken) {
      m_endGameAnkles.set(Value.kForward);
      m_AnklesReleased = true;
    }
    else {
      m_endGameAnkles.set(Value.kReverse);
      m_AnklesReleased = false;
    }
  }

  public boolean getAnklesReleased(){
    return m_AnklesReleased;
  }

}
