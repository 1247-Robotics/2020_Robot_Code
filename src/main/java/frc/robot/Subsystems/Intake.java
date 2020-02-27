package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.Commands.IntakeCommand;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;



public class Intake extends Subsystem {
    TalonSRX intakeMotor;
    public double setSpeed;
    public Intake() {
        System.out.println("Intake intialized...");
        setSpeed = .8;
        intakeMotor = new TalonSRX(RobotMap.INTAKE_MOTOR);
    }

    public void increment(){
        setSpeed += 0.01;
    }

    public void decrement(){
        setSpeed -= 0.01;
    }

    public void spin(double percent){
        intakeMotor.set(ControlMode.PercentOutput, percent);
    }
    
    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new IntakeCommand());

    }
}