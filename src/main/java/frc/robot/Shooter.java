package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PWMSparkMax;
//import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import java.util.*;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

public class Shooter {

    public void transferController(PWMSparkMax m_transferMotor, PWMTalonSRX m_funnel, 
    DigitalInput beamBreakBack, DigitalInput beamBreakFront) 
    {
        int pos = 0;
        if (beamBreakFront.get() == true && beamBreakBack.get() == false)
        {
            m_funnel.setSpeed(.8);
            m_transferMotor.setPosition(pos += 1.4);
        } 
        else if (beamBreakFront.get() == false && beamBreakBack.get() == false) 
        {
            m_transferMotor.setPosition(0);
        } 
        else if (beamBreakBack.get() == false && beamBreakFront.get() == true) 
        {
            m_funnel.setSpeed(.8);
            m_transferMotor.setPosition(6);
        } 
        else if (beamBreakFront.get() == true && beamBreakBack.get() == true) 
        {
            if (pos > 6)
                pos = 6;
            m_transferMotor.setPosition(4);
        }
    }

    public void fire(PWMSparkMax m_transferMotor) 
    {
        m_transferMotor.setSpeed(.8);
    }

    public void stopFire(PWMSparkMax m_transferMotor)
    {
        m_transferMotor.setSpeed(0);
    }

    public void shooterConfig(int config, SpeedControllerGroup m_ShooterMotor, PWMSparkMax m_hoodMotor)
    {
        if(config==0)
        {
            setShooterSpeed(2750, m_ShooterMotor);
            m_hoodMotor.setPosition(-.8);
        }
        if(config==1)
        {
            setShooterSpeed(3250, m_ShooterMotor);
            m_hoodMotor.setPosition(-0.5);
        }
        if(config==2)
        {
            setShooterSpeed(4500, m_ShooterMotor);
            m_hoodMotor.setPosition(-.5);
        }
        if(config==3)
        {
            setShooterSpeed(3500, m_ShooterMotor);
            m_hoodMotor.setPosition(-14);
        }
        if(config==-2)
        {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter Speed: ");
            int speed = scan.nextInt();
            setShooterSpeed(speed, m_ShooterMotor);
            System.out.println("Enter hood angle: ");
            int hood = scan.nextInt();
            m_hoodMotor.setPosition(hood);
            scan.close();
        }
        if(config==-1)
        {
            setShooterSpeed(0, m_ShooterMotor);
            m_hoodMotor.setPosition(0);
        }
    }

    public void setShooterSpeed(int speed, SpeedControllerGroup m_ShooterMotor)
    {
        int power = speed/3784;
        m_ShooterMotor.set(power);
    }

    public void periodicSpeed(int config, SpeedControllerGroup m_ShooterMotor, PWMSparkMax m_hoodMotor)
    {
        if(config==0)
        {
            setShooterSpeed(2750, m_ShooterMotor);
            m_hoodMotor.setPosition(-.8);
        }
        if(config==1)
        {
            setShooterSpeed(3250, m_ShooterMotor);
            m_hoodMotor.setPosition(-0.5);
        }
        if(config==2)
        {
            setShooterSpeed(4500, m_ShooterMotor);
            m_hoodMotor.setPosition(-.5);
        }
        if(config==3)
        {
            setShooterSpeed(3500, m_ShooterMotor);
            m_hoodMotor.setPosition(-14);
        }
        if(config==-1)
        {
            setShooterSpeed(0, m_ShooterMotor);
            m_hoodMotor.setPosition(0);
        }
    }
}