package frc.robot.Commands;

import frc.robot.RobotMap;

public class RevolverCommand extends BaseCommand {

  public RevolverCommand() {
    System.out.println("RevolverCommand Initialization...");
    requires(revolver);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    super.initialize();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      if(oi.getButton(5) || oi.getButton(3)){
          revolver.click(oi.getButton(5));
      }

      if(oi.getButton(6) || oi.getButton(4)){
          revolver.halfClick(oi.getButton(6));
      }
      revolver.updateNetworkTable();
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