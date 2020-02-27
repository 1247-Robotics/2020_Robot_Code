package frc.robot.Commands;

import frc.robot.RobotMap;

public class LiftCommand extends BaseCommand {
    private int loopTime;
    private int minWaitTime;

    public LiftCommand() {
        System.out.println("LiftCommand Initialization...");
        requires(lift);
        loopTime = 0;
        minWaitTime = 100;
    }

    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        if (loopTime < minWaitTime) {
            loopTime++;
        } else {
            if (oi.getButton(RobotMap.LIFT_BUTTON)) {
                loopTime = 0;
                lift.changeState();
            }
        }
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