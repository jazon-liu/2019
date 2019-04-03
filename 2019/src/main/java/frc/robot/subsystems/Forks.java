package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Forks extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private static Forks m_instance;

  private boolean m_ForksReleased = false;

  private final Solenoid m_Forks = new Solenoid(RobotMap.FORKS_SOLENOID);

  public Forks() {

    super();

    this.setForks(false);

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public static Forks getInstance() { 
    if (m_instance == null) {
      m_instance = new Forks();
    }
    return m_instance;
  }

  public void setForks (boolean on) {
    if (on) {
      m_Forks.set(true);
      m_ForksReleased = true;
    }
    else {
      m_Forks.set(false);
      m_ForksReleased = false;
    }
  }
}