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
        robot.replace("M_LEFTMOTOR", i);
    }
    
    public void setRightMotor(int i)
    {
        robot.replace("M_RIGHTMOTOR", i);
    }

    public void setIntakeMotor(int i)
    {
        robot.replace("M_INTAKEMOTOR", i);
    }

    public void setIntakeRotationMotor(int i)
    {
        robot.replace("M_INTAKEROTATIONMOTOR", i);
    }

    public void setShooterMotor(int i)
    {
        robot.replace("M_SHOOTERMOTOR", i);
    }

    public void setHoodMotor(int i)
    {
        robot.replace("M_HOODMOTOR", i);
    }

    public void setTransferMotor(int i)
    {
        robot.replace("M_TRANSFERMOTOR", i);
    }

    public void setFunnel(int i)
    {
        robot.replace("M_FUNNEL", i);
    }

    public void setClimbMotor(int i)
    {
        robot.replace("M_CLIMBMOTOR", i);
    }

    public void setFrontBeamBreak(int i)
    {
        robot.replace("BEAMBREAKFRONT", i);
    }

    public void setBackBeamBreak(int i)
    {
        robot.replace("BEAMBREAKBACK", i);
    }

    public void setLatch(int i)
    {
        robot.replace("S_LATCH", i);
    }

    public int getLeftMotor1()
    {
        return robot.get("M_LEFTMOTOR_A");
    }

    public int getLeftMotor2()
    {
        return robot.get("M_LEFTMOTOR_B");
    }

    public int getLeftMotor3()
    {
        return robot.get("M_LEFTMOTOR_C");
    }
    
    public int getRightMotor1()
    {
        return robot.get("M_RIGHTMOTOR_A");
    }
    
    public int getRightMotor2()
    {
        return robot.get("M_RIGHTMOTOR_B");
    }

    public int getRightMotor3()
    {
        return robot.get("M_RIGHTMOTOR_C");
    }

    public int getIntakeMotor()
    {
        return robot.get("M_INTAKEMOTOR");
    }

    public int getIntakeRotationMotor()
    {
        return robot.get("M_INTAKEROTATIONMOTOR");
    }

    public int getShooterMotor1()
    {
        return robot.get("M_SHOOTERMOTOR_A");
    }

    public int getShooterMotor2()
    {
        return robot.get("M_SHOOTERMOTOR_B");
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

    public int getTurretMotor()
    {
        return robot.get("M_TURRETMOTOR");
    }
}