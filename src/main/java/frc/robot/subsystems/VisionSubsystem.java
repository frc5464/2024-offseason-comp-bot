package frc.robot.subsystems;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;
import edu.wpi.first.net.PortForwarder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import entech.subsystems.EntechSubsystem;
import frc.robot.Robot;
import frc.robot.OI.RobotStatus;
import frc.robot.OI.UserPolicy;

public class VisionSubsystem extends EntechSubsystem{
    private static final boolean ENABLED = true;

    @Override
    public boolean isEnabled() {
        return ENABLED;
    }

    //robot catches targets
    public double cameraX;
    public double cameraY;
    public double aprilTagSkew;

    //best target
    public double bestX;
    public double bestY;
    public boolean targetsPresent;

    //all tags (example, for now)
    public double tag8x;
    public double tag8y;

    public double note;
    public boolean notesPresent;
    public double noteX;
    public double noteY;

    private PhotonCamera AprilTagCamera = new PhotonCamera("AprilTagCamera");
    private PhotonCamera NoteCamera = new PhotonCamera("NoteCamera");

    public double yValueSpeaker = 6.67;
    public double yValueAmp = 1.00;

    @Override
    public void initialize(){
        NoteCamera.setPipelineIndex(0);
        AprilTagCamera.setPipelineIndex(0);
        PortForwarder.add(5800, "photonvision.local", 5800);
    }

    public void periodic(){
        NoteUpdate();
        VisionUpdate();
        DisplayStats();
    }

    public void NoteUpdate(){
        var noteResult = NoteCamera.getLatestResult();
        notesPresent = noteResult.hasTargets();

        if(notesPresent){
            PhotonTrackedTarget bestTarget = noteResult.getBestTarget();
            noteX = bestTarget.getYaw();
            noteY = bestTarget.getPitch();
            RobotStatus.noteIsDetected = true;
            RobotStatus.noteVisionX = noteX;
            RobotStatus.noteVisionY = noteY;
        }
        else{
            RobotStatus.noteIsDetected = false;
        }
    }

    public void VisionUpdate(){
        var result = AprilTagCamera.getLatestResult();
        targetsPresent = result.hasTargets();

        if(cameraY < yValueSpeaker) {
            UserPolicy.closetospeaker = false;
        }

        if(cameraY < yValueAmp) {
            UserPolicy.closetoamp = false;
        }

        if(targetsPresent){
            UserPolicy.aprilTagsAreDetected = true;
            //listing the targets
            List<PhotonTrackedTarget> targets = result.getTargets();



            //looking in the grocery store
            for(int i = 0; i < targets.size(); i++){
                //check grocery aisle i
                int id = targets.get(i).getFiducialId();

                if(id == 8){
                    cameraX = targets.get(i).getYaw();
                    cameraY = targets.get(i).getPitch();
                    aprilTagSkew = targets.get(i).getSkew();
                    RobotStatus.skew = aprilTagSkew;
                    RobotStatus.AprilTagX = cameraX;
                    RobotStatus.AprilTagY = cameraY;
                    if(cameraY > yValueSpeaker) {
                        UserPolicy.closetospeaker = true;
                    }
                }

                if(id == 4){
                    cameraX = targets.get(i).getYaw();
                    cameraY = targets.get(i).getPitch();
                    RobotStatus.AprilTagX = cameraX;
                    RobotStatus.AprilTagY = cameraY;
                    if(cameraY > yValueSpeaker) {
                        UserPolicy.closetospeaker = true;
                    }
                }

                if(UserPolicy.closetospeaker){
                    if(UserPolicy.LEDselected != "CloseToggle"){
                        UserPolicy.LEDselected = "CloseToggle";
                    }
                }
                else{
                    if(UserPolicy.LEDselected == "CloseToggle"){
                        if(UserPolicy.noteDetected){
                            UserPolicy.LEDselected = "PinkIntake";
                        }
                        else{
                            UserPolicy.LEDselected = "White";
                    }                 
                }
                }

                // TODO: fix this user policy check to be something else....... ????
                if (id == 6) {
                    cameraX = targets.get(i).getYaw();
                    cameraY = targets.get(i).getPitch();          
                    RobotStatus.AprilTagX = cameraX;
                    RobotStatus.AprilTagY = cameraY;
                    if(cameraY > yValueAmp) {
                        UserPolicy.closetoamp = true;
                    }      
                }

                if (id == 5) {
                    cameraX = targets.get(i).getYaw();
                    cameraY = targets.get(i).getPitch();          
                    RobotStatus.AprilTagX = cameraX;
                    RobotStatus.AprilTagY = cameraY;
                    if(cameraY > yValueAmp) {
                        UserPolicy.closetoamp = true;
                    }      
                }
            }
            
            PhotonTrackedTarget bestTarget = result.getBestTarget();
            bestX = bestTarget.getYaw();
            bestY = bestTarget.getPitch();
        }
        else{
            UserPolicy.aprilTagsAreDetected = false;
        }
    }
    public void DisplayStats(){
        SmartDashboard.putBoolean("Camera Target Detection", targetsPresent);
        SmartDashboard.putNumber("Camera X", cameraX);
        SmartDashboard.putNumber("Camera Y", cameraY);
        SmartDashboard.putBoolean("speakerclose", UserPolicy.closetospeaker);
        SmartDashboard.putNumber("AprilTagSkew", aprilTagSkew);
        SmartDashboard.putNumber("Note Y", RobotStatus.noteVisionY);
    }
    //TODO: detect april tags with this code and be able to use this code for futher systems in the commands 
}