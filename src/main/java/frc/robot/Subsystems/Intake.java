package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.Commands.IntakeCommand;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;



public class Intake extends Subsystem {
    TalonSRX intakeMotor;
    public Intake() {
        System.out.println("Intake intialized...");
        intakeMotor = new TalonSRX(RobotMap.INTAKE_MOTOR);
    }

    public void spin(double percent){
        intakeMotor.set(ControlMode.PercentOutput, percent);
    }
    
    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new IntakeCommand());

    }
}