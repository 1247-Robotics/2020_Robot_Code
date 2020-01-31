package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Commands.LiftCommand;


public class Lift extends Subsystem {
    public Lift() {
        System.out.println("Lift intialized...");
    }

    public void Move(double speed) {
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new LiftCommand());

    }
}