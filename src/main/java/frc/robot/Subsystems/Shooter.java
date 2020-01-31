package frc.robot.Subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.Commands.LiftCommand;
import frc.robot.Commands.ShooterCommand;

public class Shooter extends Subsystem {
    private CANSparkMax spark;
    private CANEncoder sparkEncoder;
    private boolean shooterEnabled;
    private NetworkTable shooterTable;
    private NetworkTableEntry speedEntry;

    public Shooter() {
        System.out.println("Shooter intialized...");
        shooterEnabled = false;
        spark = new CANSparkMax(RobotMap.SHOOTER_SPARK, MotorType.kBrushless);
        sparkEncoder = new CANEncoder(spark);
        spark.restoreFactoryDefaults();

        shooterTable = Robot.table.getSubTable("shooter");

        speedEntry = shooterTable.getEntry("speed");
        speedEntry.setDefaultNumber((int) 0);
    }

    public void updateNetworkTable(){
        speedEntry.setNumber(sparkEncoder.getVelocity());
    }

    public void toggle() {
        shooterEnabled = !shooterEnabled;
    }


    public void updateShooter() {
        if (shooterEnabled) {

        }
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new ShooterCommand());

    }
}