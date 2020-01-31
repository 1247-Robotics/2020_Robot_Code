package frc.robot.Commands;

import frc.robot.RobotMap;

public class DriveCommand extends BaseCommand {

  public DriveCommand() {
    System.out.println("DriveCommand Initialization...");
    requires(drive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    super.initialize();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double deadZone = 0.05;
    drive.driveBasic((oi.getAxis("Z") > deadZone || oi.getAxis("Z") < -deadZone)?oi.getAxis("Z"):0, (oi.getAxis("Y") > deadZone || oi.getAxis("Y") < -deadZone)?oi.getAxis("Y"):0);
    drive.updateNetworkTable();
    oi.updateNetworkTable();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    super.end();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    super.interrupted();
  }
}