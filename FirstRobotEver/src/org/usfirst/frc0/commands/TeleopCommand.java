
package org.usfirst.frc0.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author asdf
 */
public class TeleopCommand extends CommandBase {

	private Command driveTrainCommand = new DriveWithJoystick();
	
    public TeleopCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driveTrainCommand.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
