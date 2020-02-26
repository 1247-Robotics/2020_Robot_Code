package frc.robot.Subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.Commands.ShooterCommand;

public class Shooter extends Subsystem {
    private CANSparkMax shooterMotor;
    private CANEncoder shooterMotorEncoder;
    private boolean shooterEnabled;
    private NetworkTable shooterTable;
    private NetworkTableEntry speedEntry;

    public Shooter() {
        System.out.println("Shooter intialized...");
        shooterEnabled = false;
        shooterMotor = new CANSparkMax(RobotMap.SHOOTER_MOTOR, MotorType.kBrushless);
        shooterMotorEncoder = new CANEncoder(shooterMotor);
        shooterMotor.restoreFactoryDefaults();

        shooterTable = Robot.table.getSubTable("shooter");

        speedEntry = shooterTable.getEntry("speed");
        speedEntry.setDefaultNumber((int) 0);
    }

    public void updateNetworkTable() {
        speedEntry.setNumber(shooterMotorEncoder.getVelocity());
    }

    public void toggle() {
        shooterEnabled = !shooterEnabled;
        if (shooterEnabled)
            shooterMotor.set(1);
        else
            shooterMotor.set(1);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new ShooterCommand());

    }
}