package frc.robot.Commands;

import frc.robot.RobotMap;

public class ShooterCommand extends BaseCommand {

  public ShooterCommand() {
    System.out.println("ShooterCommand Initialization...");
    requires(shooter);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    super.initialize();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(oi.getButton(3)) shooter.toggle();
    shooter.updateShooter();
    shooter.updateNetworkTable();
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