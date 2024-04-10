package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import entech.subsystems.EntechSubsystem;
import frc.robot.OI.UserPolicy;

public class ClimbSubsystem extends EntechSubsystem{
    CANSparkMax leftarm = new CANSparkMax(8, MotorType.kBrushless);
    CANSparkMax rightarm = new CANSparkMax(7, MotorType.kBrushless);  

    public RelativeEncoder leftEncoder;
    public RelativeEncoder rightEncoder;

    public double rightUp = -110;
    public double rightDown = -1;

    public double leftUp = -17;
    public double leftDown = -0.4;

    private static final boolean ENABLED = true;

    @Override
    public void initialize(){
        leftEncoder = leftarm.getEncoder();
        rightEncoder = rightarm.getEncoder();

        leftEncoder.setPosition(0);
        rightEncoder.setPosition(0);
    }

    public void periodic(){
        SmartDashboard.putNumber("left encoder", leftEncoder.getPosition());
        SmartDashboard.putNumber("right encoder", rightEncoder.getPosition());

        SmartDashboard.putBoolean("Auto up", UserPolicy.autoUp);
        SmartDashboard.putBoolean("Auto down", UserPolicy.autoDown);
    }

    @Override
    public boolean isEnabled() {
        return ENABLED;
    }

    public void LeftUp(){
        if(UserPolicy.leftUp){
            if(UserPolicy.climbOverride){
                leftarm.set(-1);
            }
            else if(leftEncoder.getPosition() < leftUp){
                leftarm.set(0);
            }
            else{
                leftarm.set(-1);
            }
        }
        else{
            ClimbLeftDisable();
        }
    }

    
    public void LeftDown(){
        if(UserPolicy.leftDown){
            if(UserPolicy.climbOverride){
                leftarm.set(1);
            }
            else if(leftEncoder.getPosition() > leftDown){
                leftarm.set(0);
            }
            else{
                leftarm.set(1);
            }
        }
        else{
            ClimbLeftDisable();
        }
    }

    // public void ClimbOverride(){
    //     if(UserPolicy.climbOverride == true){
    //     leftarm.set(1);
    //     rightarm.set(1);
    //     }
    // }


    public void RightUp(){
        if(UserPolicy.rightUp){
            if(UserPolicy.climbOverride){
                rightarm.set(-1);
            }
            else if(rightEncoder.getPosition() < rightUp){
                rightarm.set(0);
            }
            else{
                rightarm.set(-1);
            }
        }
        else{
            ClimbRightDisable();        
        }
    }
    
    public void RightDown(){
        if(UserPolicy.rightDown){
            if(UserPolicy.climbOverride){
                rightarm.set(1);
            }
            else if(rightEncoder.getPosition() > rightDown){
                rightarm.set(0);
            }
            else{
                rightarm.set(1);
            }
        }
        else{
            ClimbRightDisable();      
        }
    }

    //TODO: if there is extra time then create an automatic climbing method using the NAVX
    
    public void AutoUp(){
        if(UserPolicy.autoUp){
            UserPolicy.leftUp = true;
            UserPolicy.rightUp = true;
            RightUp();
            LeftUp();

            // rightarm.set(-1);
            // if(rightEncoder.getPosition() < rightUp){
            //     rightarm.set(0);
            //     UserPolicy.autoUp = false;
            // }
            // leftarm.set(-1);
            // if(leftEncoder.getPosition() < leftUp){
            //     leftarm.set(0);
            //     UserPolicy.autoUp = false;
            // }
        }
        else{
            ClimbLeftDisable();
            ClimbRightDisable();
        }
    }

    public void AutoDown(){
        if(UserPolicy.autoDown){
            UserPolicy.leftDown = true;
            UserPolicy.rightDown = true;
            RightDown();
            LeftDown();

            // rightarm.set(1);
            // if(rightEncoder.getPosition() > rightDown){
            //     rightarm.set(0);
            //     UserPolicy.autoUp = false;
            // }

            // leftarm.set(1);
            // if(leftEncoder.getPosition() > leftDown){
            //     leftarm.set(0);
            //     UserPolicy.autoUp = false;
            // }
        }
        else{
            ClimbLeftDisable();
            ClimbRightDisable();
        }
    }

    public void ClimbLeftDisable(){
        leftarm.set(0);
    }

    public void ClimbRightDisable(){
        rightarm.set(0);
    }

    public void zeroEncoders(){
        leftEncoder.setPosition(0);
        rightEncoder.setPosition(0);        
    }
}
