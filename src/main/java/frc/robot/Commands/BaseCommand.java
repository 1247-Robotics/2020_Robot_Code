package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Subsystems.Drive;
import frc.robot.Subsystems.Intake;
import frc.robot.Subsystems.Lift;
import frc.robot.Subsystems.Revolver;
import frc.robot.Subsystems.Shooter;

public abstract class BaseCommand extends Command {
  public static OI oi;
  public static Drive drive;
  public static Lift lift;
  public static Revolver revolver;
  public static Intake intake;
  public static Shooter shooter;

  public BaseCommand() {
    super();
    System.out.println("BaseCommand initialization...");

  }

  public static void init() {
    drive = new Drive();
    lift = new Lift();
    oi = new OI();
    revolver = new Revolver();
    intake = new Intake();
    //shooter = new Shooter();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}