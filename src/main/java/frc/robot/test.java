package frc.robot;

import java.io.FileNotFoundException;

public class test {
    public static void main(String[] args) throws FileNotFoundException
    {
        RobotConfig config = new RobotConfig();
        config.readFile();
        config.printArray();
    }
}