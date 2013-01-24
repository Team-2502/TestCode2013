
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author bradmiller
 */
public class ExampleCommand extends CommandBase {

    public ExampleCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(simpleVision);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    int i = 0;
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        
        if(i==20)
        {
            ParticleAnalysisReport[] reports = simpleVision.getFrame();
            for (int i = 0; i < reports.length; i++) {                                // print results
                ParticleAnalysisReport r = reports[i];
                SmartDashboard.putString("Particle" + i, r.center_mass_x + ", " + r.center_mass_y);
            }
            
            i = 0;
        }
        
        i++;
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
