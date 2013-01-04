package org.usfirst.frc0.commands;


public class DriveWithJoystick extends CommandBase {

	public DriveWithJoystick()
	{
		requires(driveTrain);
	}
	
	protected void initialize() {
		// TODO Auto-generated method stub
		//requires(driveTrain);
	}

	
	protected void execute() {
		// TODO Auto-generated method stub
		driveTrain.drive(oi);
		//System.out.println("setting motors " + oi.joy1.getY() + oi.joy2.getY());
	}

	
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	
	protected void interrupted() {
		// TODO Auto-generated method stub
	}

}
