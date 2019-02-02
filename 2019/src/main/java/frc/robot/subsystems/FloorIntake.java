/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import javax.lang.model.util.ElementScanner6;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class FloorIntake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private static FloorIntake m_instance;

  private final WPI_TalonSRX m_Floor1 = new WPI_TalonSRX(RobotMap.FLOOR_INTAKE);

  private final DoubleSolenoid m_FloorIntakeShort = new DoubleSolenoid(RobotMap.PCM_ZERO, RobotMap.FLOOR_INTAKE_SHORT_FORWARD, RobotMap.FLOOR_INTAKE_SHORT_REVERSE);
  private final DoubleSolenoid m_FloorIntakeLong = new DoubleSolenoid(RobotMap.PCM_ZERO, RobotMap.FLOOR_INTAKE_LONG_FORWARD, RobotMap.FLOOR_INTAKE_LONG_REVERSE);

  private final DoubleSolenoid m_DiscIntake = new DoubleSolenoid(RobotMap.PCM_ONE, RobotMap.DISC_INTAKE_FORWARD, RobotMap.DISC_INTAKE_REVERSE);

  

  public FloorIntake() {

    super();

    setFactoryDefault();
    setBrakeMode(true);
    configureMotor();
    setMotorSafeties();

  }

  @Override
  public void initDefaultCommand() {

    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public static FloorIntake getInstance() {
    if (m_instance == null) {
      m_instance = new FloorIntake();
    }
    return m_instance;
  }

  public void setFactoryDefault(){
    m_Floor1.configFactoryDefault();
    m_Floor1.configFactoryDefault();

  }
  public void setBrakeMode(boolean mode){
    if (mode == true) {
      m_Floor1.setNeutralMode(NeutralMode.Brake);
      m_Floor1.setNeutralMode(NeutralMode.Brake);
    }
    else {
      m_Floor1.setNeutralMode(NeutralMode.Coast);
      m_Floor1.setNeutralMode(NeutralMode.Coast);
    }
  }
  public void configureMotor(){
    m_Floor1.configOpenloopRamp(1.0, 0);
    m_Floor1.configOpenloopRamp(1.0, 0);
  }
  public void setMotorSafeties(){
    m_Floor1.setSafetyEnabled(false);
    m_Floor1.setSafetyEnabled(false);
  }

  public void setFloorIntakeMotor(double percent) {

    m_Floor1.set(ControlMode.PercentOutput, percent);
  }

  public double getAverageCurrent() {

    return m_Floor1.getOutputCurrent();
  }

  public void setFloorIntakeShort (boolean on) {
    if (on) {
      m_FloorIntakeShort.set(Value.kForward);
    }
    else {
      m_FloorIntakeShort.set(Value.kReverse);
    }
  }
  public void setFloorIntakeLong (boolean on) {
    if (on) {
      m_FloorIntakeLong.set(Value.kForward);
    }
    else {
      m_FloorIntakeLong.set(Value.kReverse);
    }
  }
  public void setDiscIntake (boolean on) {
    if (on) {
      m_DiscIntake.set(Value.kForward);
    }
    else {
      m_DiscIntake.set(Value.kReverse);
    }
  }
  public void setFloorIntake(double percent) {
    m_Floor1.set(percent);
  }
}
