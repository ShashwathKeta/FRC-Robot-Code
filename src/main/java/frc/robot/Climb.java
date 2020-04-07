package frc.robot;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import com.revrobotics.ColorSensorV3;

public class Climb
{
    boolean isLatched = true;
    boolean isPositioned = false;

    public void unlatch(PWMSparkMax m_ClimbMotor, DigitalOutput s_Latch) throws InterruptedException
    {
        m_ClimbMotor.setSpeed(-.25);
        s_Latch.setPWMRate(-1);
        Thread.sleep(250);
        s_Latch.setPWMRate(0);
        m_ClimbMotor.setSpeed(0);
        isLatched = false;
    }

    public void climb(PWMSparkMax m_ClimbMotor, DigitalOutput s_Latch) throws InterruptedException
    {
        unlatch(m_ClimbMotor, s_Latch);
        m_ClimbMotor.setSpeed(.8);
    }

    public void stopClimb(PWMSparkMax m_ClimbMotor)
    {
        m_ClimbMotor.setSpeed(0);
    }

    public void retract(PWMSparkMax m_ClimbMotor,DigitalOutput s_Latch) throws InterruptedException
    {
        unlatch(m_ClimbMotor,s_Latch);
        m_ClimbMotor.setSpeed(-.8);
    }

    public void latch(PWMSparkMax m_ClimbMotor, DigitalOutput s_Latch) throws InterruptedException
    {
        m_ClimbMotor.setSpeed(.25);
        s_Latch.setPWMRate(1);
        Thread.sleep(250);
        s_Latch.setPWMRate(0);
        m_ClimbMotor.setSpeed(0);
        isLatched=true;
    }

    public void leftBarRunner(PWMTalonSRX m_barRunner)
    {
        m_barRunner.setSpeed(.8);
    }

    public void rightBarRunner(PWMTalonSRX m_barRunner)
    {
        m_barRunner.setSpeed(-.8);
    }

    public void stopBarRunner(PWMTalonSRX m_BarRunner)
    {
        m_BarRunner.setSpeed(0);
    }

    public void controlPanelRotation(PWMTalonSRX m_barRunner) throws InterruptedException
    {
        m_barRunner.setSpeed(.8);
        Thread.sleep(3000);
        m_barRunner.setSpeed(0);
    }

    public void controlPanelPosition(String color, PWMTalonSRX m_barRunner, ColorSensorV3 m_colorSensor)
    {
        m_barRunner.setSpeed(.5);
        if(m_colorSensor.getColor().equals(color))
        {
            isPositioned = true;
        }
    }

    public void checkControlPanelPositioning(PWMTalonSRX m_barRunner)
    {
        if(isPositioned)
        {
            m_barRunner.setSpeed(0);
        }
    }
}