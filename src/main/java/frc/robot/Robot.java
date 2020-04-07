/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DigitalInput;;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */

   //Make this a singleton class
  private final RobotConfig config = new RobotConfig();
  private final PWMSparkMax m_leftMotor = new PWMSparkMax(config.getLeftMotor());
  private final PWMSparkMax m_rightMotor = new PWMSparkMax(config.getRighMotor());
  private final PWMSparkMax m_intakeMotor = new PWMSparkMax(config.getIntakeMotor());
  private final PWMSparkMax m_intakeRotationMotor = new PWMSparkMax(config.getIntakeRotationMotor());
  private final PWMSparkMax m_ShooterMotor = new PWMSparkMax(config.getShooterMotor());
  private final PWMSparkMax m_hoodMotor = new PWMSparkMax(config.getHoodMotor());
  private final PWMSparkMax m_transferMotor = new PWMSparkMax(config.getTransferMotor());
  private final PWMTalonSRX m_funnel = new PWMTalonSRX(config.getFunnel());
  private final PWMSparkMax m_ClimbMotor = new PWMSparkMax(config.getClimbMotor());
  private final DigitalInput beamBreakFront = new DigitalInput(config.getFrontBeamBreak());
  private final DigitalInput beamBreakBack = new DigitalInput(config.getBackBeamBreak());
  private final DigitalOutput s_Latch = new DigitalOutput(config.getLatch());
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);
  private final XboxController m_driverController = new XboxController(0);
  private final Shooter m_Shooter = new Shooter();
  private final Climb m_Climb = new Climb();
  private int shooterConfig = -1;
  private boolean isLatched = false;
  private boolean isPositioning = false;

  @Override
  public void robotInit() 
  {
    
  }

  @Override
  public void robotPeriodic() 
  {
    if(isPositioning)
    {
      m_Climb.checkControlPanelPositioning(m_funnel);
    }
    m_Shooter.transferController(m_transferMotor, m_funnel, beamBreakBack, beamBreakFront);
  }

  @Override
  public void autonomousInit() 
  {

  }

  @Override
  public void autonomousPeriodic() 
  {

  }

  @Override
  public void teleopInit() {
    m_intakeRotationMotor.setPosition(0);
    m_intakeMotor.setSpeed(0);
    m_Shooter.shooterConfig(-1, m_ShooterMotor, m_hoodMotor);
    m_funnel.setSpeed(0);
  }

  @Override
  public void teleopPeriodic() {
    m_Shooter.periodicSpeed(shooterConfig, m_ShooterMotor, m_hoodMotor);
    m_robotDrive.arcadeDrive(m_driverController.getY(Hand.kLeft), m_driverController.getX(Hand.kRight));

    if(m_driverController.getXButtonPressed())
    {
      m_intakeRotationMotor.setPosition(.25);
      m_intakeMotor.setSpeed(.8);
      m_funnel.setSpeed(.8);
    }

    else if(m_driverController.getXButtonReleased())
    {
      m_intakeRotationMotor.setPosition(0);
      m_intakeMotor.setSpeed(0);
    }

    if(m_driverController.getAButtonPressed())
    {
      shooterConfig++;
      if(shooterConfig==3)
        shooterConfig=0;
      m_Shooter.shooterConfig(shooterConfig, m_ShooterMotor, m_hoodMotor);
    }

    if(m_driverController.getBackButtonPressed())
    {
      m_Shooter.fire(m_transferMotor);
    }

    if(m_driverController.getBButtonReleased())
    {
      m_Shooter.stopFire(m_transferMotor);
    }

    if(m_driverController.getRawButtonPressed(8))
    {
      m_Climb.leftBarRunner(m_funnel);
    }

    if(m_driverController.getRawButtonPressed(10))
    {
      m_Climb.rightBarRunner(m_funnel);
    }

    if(m_driverController.getRawButtonReleased(8))
    {
      m_Climb.stopBarRunner(m_funnel);
    }

    if(m_driverController.getRawButtonReleased(10))
    {
      m_Climb.stopBarRunner(m_funnel);
    }

    if(m_driverController.getBumperPressed(Hand.kLeft))
    {
      try 
      {
		    m_Climb.climb(m_ClimbMotor, s_Latch);
      } 
      catch (InterruptedException e) 
      {
	    	e.printStackTrace();
	    }
    }

    if(m_driverController.getBumperPressed(Hand.kRight))
    {
      try 
      {
		    m_Climb.retract(m_ClimbMotor,s_Latch);
      } 
      catch (InterruptedException e) 
      {
	    	e.printStackTrace();
	    }
    }

    if(m_driverController.getBumperReleased(Hand.kLeft))
    {
      m_Climb.stopClimb(m_ClimbMotor);
    }

    if(m_driverController.getBumperReleased(Hand.kRight))
    {
      m_Climb.stopClimb(m_ClimbMotor);
    }

    if(m_driverController.getBButtonPressed())
    {
      try 
      {
        if(isLatched)
        {
          m_Climb.unlatch(m_ClimbMotor,s_Latch);
        }
        else if(!isLatched)
        {
          m_Climb.latch(m_ClimbMotor,s_Latch);
        }
      } 
      catch (InterruptedException e) 
      {
	    	e.printStackTrace();
	    }
    }

    if(m_driverController.getYButtonPressed())
    {
      try 
      {
		    m_Climb.controlPanelRotation(m_funnel);
      } 
      catch (InterruptedException e) 
      {
		    e.printStackTrace();
	    }
    }

    if(m_driverController.getRawButtonPressed(13) && m_driverController.getAButtonPressed())
    {
      m_Climb.controlPanelPosition("green", m_funnel, m_colorSensor);
      isPositioning = true;
    }

    if(m_driverController.getRawButtonPressed(13) && m_driverController.getBButtonPressed())
    {
      m_Climb.controlPanelPosition("red", m_funnel, m_colorSensor);
      isPositioning = true;
    }

    if(m_driverController.getRawButtonPressed(13) && m_driverController.getYButtonPressed())
    {
      m_Climb.controlPanelPosition("yellow", m_funnel, m_colorSensor);
      isPositioning = true;
    }

    if(m_driverController.getRawButtonPressed(13) && m_driverController.getXButtonPressed())
    {
      m_Climb.controlPanelPosition("blue",m_funnel, m_colorSensor);
      isPositioning = true;
    }
  }

  @Override
  public void disabledInit() 
  {
    m_Shooter.shooterConfig(-1, m_ShooterMotor, m_hoodMotor);
  }

  @Override
  public void disabledPeriodic() 
  {

  }

  @Override
  public void testInit() 
  {

  }

  @Override
  public void testPeriodic() 
  {

  }

}