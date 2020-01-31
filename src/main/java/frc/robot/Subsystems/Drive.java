package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.Commands.DriveCommand;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.ExternalFollower;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Drive extends Subsystem {
  private CANSparkMax sparkLeft1, sparkRight1, sparkLeft2, sparkLeft3, sparkRight2, sparkRight3;
  private CANEncoder sparkLeft1Encoder, sparkLeft2Encoder, sparkLeft3Encoder, sparkRight1Encoder, sparkRight2Encoder,
      sparkRight3Encoder;
  private RobotDrive driveTrain;
  private SpeedControllerGroup left, right;
  public int speed;
  private NetworkTable driveTable;
  private NetworkTableEntry speedEntry, sparkLeft1Entry, sparkLeft2Entry, sparkLeft3Entry, sparkRight1Entry, sparkRight2Entry, sparkRight3Entry;

  public Drive() {
    System.out.println("Drive initialization...");
    sparkLeft1 = new CANSparkMax(RobotMap.SPARK_CHANNEL_LEFT1, MotorType.kBrushless);
    sparkLeft2 = new CANSparkMax(RobotMap.SPARK_CHANNEL_LEFT2, MotorType.kBrushless);
    sparkLeft3 = new CANSparkMax(RobotMap.SPARK_CHANNEL_LEFT3, MotorType.kBrushless);

    sparkRight1 = new CANSparkMax(RobotMap.SPARK_CHANNEL_RIGHT1, MotorType.kBrushless);
    sparkRight2 = new CANSparkMax(RobotMap.SPARK_CHANNEL_RIGHT2, MotorType.kBrushless);
    sparkRight3 = new CANSparkMax(RobotMap.SPARK_CHANNEL_RIGHT3, MotorType.kBrushless);

    sparkLeft1Encoder = new CANEncoder(sparkLeft1);
    sparkLeft2Encoder = new CANEncoder(sparkLeft2);
    sparkLeft3Encoder = new CANEncoder(sparkLeft3);

    sparkRight1Encoder = new CANEncoder(sparkRight1);
    sparkRight2Encoder = new CANEncoder(sparkRight2);
    sparkRight3Encoder = new CANEncoder(sparkRight3);

    sparkLeft1.restoreFactoryDefaults();
    sparkLeft2.restoreFactoryDefaults();
    sparkLeft3.restoreFactoryDefaults();

    sparkRight1.restoreFactoryDefaults();
    sparkRight2.restoreFactoryDefaults();
    sparkRight3.restoreFactoryDefaults();

    sparkLeft2.follow(sparkLeft1);
    sparkLeft3.follow(sparkLeft1);

    sparkRight2.follow(sparkRight1);
    sparkRight3.follow(sparkRight1);

    driveTrain = new RobotDrive(sparkLeft1, sparkRight1);
    
    driveTable = Robot.table.getSubTable("drive");

    speedEntry = driveTable.getEntry("speed");
    speedEntry.setDefaultNumber((int) 0);

    sparkLeft1Entry = driveTable.getEntry("sparkLeft1");
    sparkLeft1Entry.setDefaultNumber((int) 0);

    sparkLeft2Entry = driveTable.getEntry("sparkLeft2");
    sparkLeft2Entry.setDefaultNumber((int) 0);

    sparkLeft3Entry = driveTable.getEntry("sparkLeft3");
    sparkLeft3Entry.setDefaultNumber((int) 0);

    sparkRight1Entry = driveTable.getEntry("sparkRight1");
    sparkRight1Entry.setDefaultNumber((int) 0);

    sparkRight2Entry = driveTable.getEntry("sparkRight2");
    sparkRight2Entry.setDefaultNumber((int) 0);

    sparkRight3Entry = driveTable.getEntry("sparkRight3");
    sparkRight3Entry.setDefaultNumber((int) 0);
  }


  public void updateNetworkTable() {
    speedEntry.setNumber(speed);
    sparkLeft1Entry.setNumber(sparkLeft1Encoder.getVelocity());
    sparkLeft2Entry.setNumber(sparkLeft2Encoder.getVelocity());
    sparkLeft3Entry.setNumber(sparkLeft3Encoder.getVelocity());
    sparkRight1Entry.setNumber(sparkRight1Encoder.getVelocity());
    sparkRight2Entry.setNumber(sparkRight2Encoder.getVelocity());
    sparkRight3Entry.setNumber(sparkRight3Encoder.getVelocity());
  }

  public void driveBasic(double x, double y) {
    int sign = (y < 0)? 1:-1;
    driveTrain.arcadeDrive(Math.pow(y, 2) * sign, -x*0.5);
    speed = rpmToMph();
    System.out.println("Speed = " + speed);
  }

  private double getAvg(boolean left) {
    if (left)
      return (sparkLeft1Encoder.getVelocity() + sparkLeft2Encoder.getVelocity() + sparkLeft3Encoder.getVelocity()) / 3;
    else
      return (sparkRight1Encoder.getVelocity() + sparkRight2Encoder.getVelocity() + sparkRight3Encoder.getVelocity())
          / 3;
  }

  private int rpmToMph() {

    double avgOfWheels = (Math.abs(getAvg(false)) + Math.abs(getAvg(true))) / 2;
    double axleSpeed = avgOfWheels / 7.66;
    double feetPerSecond = (axleSpeed * (6 * Math.PI)) / 12;
    double milesPerHour = (feetPerSecond * 60) / 5280;
    return (int) milesPerHour;
  }

  private int rpmToFps() {

    double avgOfWheels = (Math.abs(getAvg(false)) + Math.abs(getAvg(true))) / 2;
    double axleSpeed = avgOfWheels / 7.66;
    double feetPerSecond = (axleSpeed * (6 * Math.PI)) / 12;
    return (int) feetPerSecond;
  }

  private double getDev(boolean left) {
    if (left) {
      double avg = getAvg(true);
      return -Math
          .sqrt((Math.pow(sparkLeft1Encoder.getVelocity() - avg, 2) + Math.pow(sparkLeft2Encoder.getVelocity() - avg, 2)
              + Math.pow(sparkLeft3Encoder.getVelocity() - avg, 2)) / 3);
    } else {
      double avg = getAvg(false);
      return Math.sqrt(
          (Math.pow(sparkRight1Encoder.getVelocity() - avg, 2) + Math.pow(sparkRight2Encoder.getVelocity() - avg, 2)
              + Math.pow(sparkRight3Encoder.getVelocity() - avg, 2)) / 3);
    }
  }

  public void autonomousDrive(double x, double y) {
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveCommand());
  }
}