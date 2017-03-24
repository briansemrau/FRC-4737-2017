package org.usfirst.frc.team4737.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team4737.robot.commands.camera.ShowGearCam;

/**
 * @author Brian Semrau
 * @version Mar. 03, 2017
 */
public class CameraDisplay extends Subsystem {

//    public final UsbCamera frontCam;
//    public final UsbCamera gearCam;

    private UsbCamera current;

    public CameraDisplay() {
//        frontCam = new UsbCamera("cam0", 0);
//        gearCam = new UsbCamera("cam1", 1);
    }

    public void startGearCam() {
//        CameraServer.getInstance().startAutomaticCapture(gearCam);
//        current = gearCam;
    }

    public void startFrontCam() {
//        CameraServer.getInstance().startAutomaticCapture(frontCam);
//        current = frontCam;
    }

    public boolean isShowingGearCam() {
        return false;//current != frontCam;
    }

    public void initDefaultCommand() {
        setDefaultCommand(new ShowGearCam());
    }

}

