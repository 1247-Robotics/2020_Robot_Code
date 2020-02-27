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
    
      if(oi.getButton(RobotMap.REVOLVER_LEFT_CLICK_BUTTON)){
          revolver.click(false);
      } else if(oi.getButton(RobotMap.REVOLVER_RIGHT_CLICK_BUTTON)){
          revolver.click(true);
      }else if(oi.getButton(RobotMap.REVOLVER_LEFT_HALF_CLICK_BUTTON)){
        revolver.halfClick(false);
      }else if(oi.getButton(RobotMap.REVOLVER_RIGHT_HALF_CLICK_BUTTON)){
        revolver.halfClick(true);
      }else{
        revolver.setSpeed(0);
      }

      if(oi.getButton(7)) revolver.trimLeft();
      else if(oi.getButton(9)) revolver.trimRight();
      /*
      if(oi.getButton(3)) revolver.setSpeed(.4);
      else if(oi.getButton(4)) revolver.setSpeed(-.4);
      else revolver.setSpeed(0);
      */
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