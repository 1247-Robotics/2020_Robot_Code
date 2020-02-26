package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.Commands.LiftCommand;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;


public class Lift extends Subsystem {
    private Solenoid leftSolenoid;
    private Solenoid rightSolenoid;
    private Compressor compressor;
    public boolean liftState;
    public Lift() {
        System.out.println("Lift intialized...");
        liftState = false;
        compressor =  new Compressor();
        compressor.start();
        leftSolenoid = new Solenoid(RobotMap.LEFT_SOLENOID);
        rightSolenoid = new Solenoid(RobotMap.RIGHT_SOLENOID);
    }

    public void changeState() {
        if (liftState) {
            leftSolenoid.set(false);
            rightSolenoid.set(false);
            liftState = !liftState;
          } else {
            leftSolenoid.set(true);
            rightSolenoid.set(true);
            liftState = !liftState;
          }
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new LiftCommand());

    }
}