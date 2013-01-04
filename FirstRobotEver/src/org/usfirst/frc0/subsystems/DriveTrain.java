package org.usfirst.frc0.subsystems;

import org.usfirst.frc0.OI;
import org.usfirst.frc0.commands.DriveWithJoystick;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;


public class DriveTrain extends Subsystem {

	
	RobotDrive robotDrive;
	private int driveType;
	private Jaguar jaguarLeft,jaguarRight;
	
	public static final int MECANUM_DRIVE = 0;
	public static final int TANK_DRIVE = 1;
	
	public DriveTrain(int driveType)
	{
		this.driveType = driveType;
		if(driveType == MECANUM_DRIVE)
		{
			robotDrive = new RobotDrive(1, 2, 3, 4);
		}
		else if(driveType == TANK_DRIVE)
		{
			jaguarLeft= new Jaguar(1); // left
			jaguarRight = new Jaguar(2); // right
		}
	}
	
	
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new DriveWithJoystick());
	}
	
	
	public void drive(OI oi)
	{
		switch(driveType)
		{
			case MECANUM_DRIVE:
				robotDrive.mecanumDrive_Polar(oi.joy1.getMagnitude(), oi.joy1.getDirectionRadians(), oi.joy1.getTwist());
			break;
			
			case TANK_DRIVE:
				robotDrive.tankDrive(oi.joy1, oi.joy2);
			break;
		}
	}
	
	// Stop the motors because we crashed
	public void stop()
	{
		robotDrive.stopMotor();
	
	}
	
	
}
