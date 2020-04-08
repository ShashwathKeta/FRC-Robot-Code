package frc.robot;

import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class RobotConfig 
{
    private HashMap<String,Integer> robot = new HashMap<String,Integer>();

    //Config file should be found using relative path, not full path
    private File file = new File("D:\\Work\\FRC Code\\Java\\FRCJavaCode\\config\\RobotConfig.cfg");

    public RobotConfig()
    {
        try 
        {
            readFile();
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }
    }

    public void readFile() throws FileNotFoundException
    {
        Scanner scan = new Scanner(file);
        String device = "";
        int port = 0;
        while(scan.hasNextLine())
        {
            device = scan.next();
            port = scan.nextInt();
            robot.put(device, port);
        }
        scan.close();
    }

    public void printArray()
    {
        System.out.print(robot);
    }

    public void setLeftMotor(int i)
    {
        robot.replace("m_leftMotor", i);
    }
    
    public void setRightMotor(int i)
    {
        robot.replace("m_rightMotor", i);
    }

    public void setIntakeMotor(int i)
    {
        robot.replace("m_intakeMotor", i);
    }

    public void setIntakeRotationMotor(int i)
    {
        robot.replace("m_intakeRotationMotor", i);
    }

    public void setShooterMotor(int i)
    {
        robot.replace("m_ShooterMotor", i);
    }

    public void setHoodMotor(int i)
    {
        robot.replace("m_hoodMotor", i);
    }

    public void setTransferMotor(int i)
    {
        robot.replace("m_transferMotor", i);
    }

    public void setFunnel(int i)
    {
        robot.replace("m_funnel", i);
    }

    public void setClimbMotor(int i)
    {
        robot.replace("m_ClimbMotor", i);
    }

    public void setFrontBeamBreak(int i)
    {
        robot.replace("beamBreakFront", i);
    }

    public void setBackBeamBreak(int i)
    {
        robot.replace("beamBreakBack", i);
    }

    public void setLatch(int i)
    {
        robot.replace("s_Latch", i);
    }

    public int getLeftMotor()
    {
        return robot.get("M_LEFTMOTOR");
    }
    
    public int getRighMotor()
    {
        return robot.get("M_RIGHTMOTOR");
    }

    public int getIntakeMotor()
    {
        return robot.get("M_INTAKEMOTOR");
    }

    public int getIntakeRotationMotor()
    {
        return robot.get("M_INTAKEROTATIONMOTOR");
    }

    public int getShooterMotor()
    {
        return robot.get("M_SHOOTERMOTOR");
    }

    public int getHoodMotor()
    {
        return robot.get("M_HOODMOTOR");
    }

    public int getTransferMotor()
    {
        return robot.get("M_TRANSFERMOTOR");
    }

    public int getFunnel()
    {
        return robot.get("M_FUNNEL");
    }

    public int getClimbMotor()
    {
        return robot.get("M_CLIMBMOTOR");
    }

    public int getFrontBeamBreak()
    {
        return robot.get("BEAMBREAKFRONT");
    }

    public int getBackBeamBreak()
    {
        return robot.get("BEAMBREAKBACK");
    }

    public int getLatch()
    {
        return robot.get("S_LATCH");
    }
}