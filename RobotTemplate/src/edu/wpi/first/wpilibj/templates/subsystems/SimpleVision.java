package org.usfirst.frc2502.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.CriteriaCollection;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import edu.wpi.first.wpilibj.image.RGBImage;
import edu.wpi.first.wpilibj.image.NIVision.MeasurementType;

public class SimpleVision extends Subsystem {

    AxisCamera camera;          // the axis camera object (connected to the switch)
    CriteriaCollection[] cc = new CriteriaCollection[3];      // the criteria for doing the particle filter operation
	
    public SimpleVision()
    {
        camera = AxisCamera.getInstance("10.25.2.11");  // get an instance of the camera
        cc[0] = new CriteriaCollection();      // create the criteria for the particle filter
        cc[0].addCriteria(MeasurementType.IMAQ_MT_EQUIVALENT_RECT_LONG_SIDE, 100, 240, false);

        cc[1] = new CriteriaCollection();
        cc[1].addCriteria(MeasurementType.IMAQ_MT_RATIO_OF_EQUIVALENT_RECT_SIDES, 3, 10, false);

        cc[2] = new CriteriaCollection();
        cc[2].addCriteria(MeasurementType.IMAQ_MT_ORIENTATION, 45, 135, false);
    }

    protected void initDefaultCommand() {
            // TODO Auto-generated method stub

    }
	
    public ParticleAnalysisReport[] getFrame()
    {
        try {
            /**
             * Do the image capture with the camera and apply the algorithm described above. This
             * sample will either get images from the camera or from an image file stored in the top
             * level directory in the flash memory on the cRIO. The file name in this case is "10ft2.jpg"
             * 
             */
            //ColorImage image = camera.getImage();     // comment if using stored images
            ColorImage image;                           // next 2 lines read image from flash on cRIO
            image = camera.getImage();
            BinaryImage thresholdImage = image.thresholdRGB(0, 100, 230, 255, 230, 255);   // keep only mainly green object
            BinaryImage bigObjectsImage = thresholdImage.removeSmallObjects(false, 2);  // remove small artifacts
            BinaryImage convexHullImage = bigObjectsImage.convexHull(false);          // fill in occluded rectangles
            
            // Apply all the filters in succession
            BinaryImage filteredImage = convexHullImage;
            for(int i = 0; i < cc.length; i++)
            {
                BinaryImage filtered = filteredImage.particleFilter(cc[i]);
                filteredImage.free();
                filteredImage = filtered;
                System.out.println(filteredImage.getNumberParticles());
            }
            
            ParticleAnalysisReport[] reports = filteredImage.getOrderedParticleAnalysisReports();  // get list of results
           /*
            for (int i = 0; i < reports.length; i++) {                                // print results
                ParticleAnalysisReport r = reports[i];
                System.out.println("Particle: " + i + ":  Center of mass x: " + r.center_mass_x);
            }
            System.out.println(filteredImage.getNumberParticles() + "  " + Timer.getFPGATimestamp());
            * */

            /**
             * all images in Java must be freed after they are used since they are allocated out
             * of C data structures. Not calling free() will cause the memory to accumulate over
             * each pass of this loop.
             */
            filteredImage.free();
            convexHullImage.free();
            bigObjectsImage.free();
            thresholdImage.free();
            image.free();

            return reports;

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("camera error");
            return new ParticleAnalysisReport[0]; // return a blank image since we have nothing
        }
	
    }
}
