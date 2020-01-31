package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Commands.IntakeCommand;
import frc.robot.Commands.LiftCommand;


public class Intake extends Subsystem {
    public Intake() {
        System.out.println("Intake intialized...");
    }

    public void spin(){

    }
    
    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new IntakeCommand());

    }
}