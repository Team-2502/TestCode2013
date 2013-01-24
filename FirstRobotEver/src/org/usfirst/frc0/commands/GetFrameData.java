package org.usfirst.frc0.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GetFrameData extends CommandBase {

	protected void initialize() {
		// TODO Auto-generated method stub
		requires(simpleVision);
	}

	protected void execute() {
		// TODO Auto-generated method stub
		
		ParticleAnalysisReport[] reports = simpleVision.getFrame();
		 for (int i = 0; i < reports.length; i++) {                                // print results
             ParticleAnalysisReport r = reports[i];
             SmartDashboard.putString("Particle" + i, r.center_mass_x + ", " + r.center_mass_y);
		 }
		
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
