// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

<<<<<<< Updated upstream
import java.util.Optional;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
=======
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
>>>>>>> Stashed changes
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class DriveSubsystem extends SubsystemBase {

  private DifferentialDrive drivetrain;
  private final CANSparkMax frontleft = new CANSparkMax(2, MotorType.kBrushless);
  private final CANSparkMax frontright = new CANSparkMax(3, MotorType.kBrushless); 
  private final CANSparkMax backleft = new CANSparkMax(4, MotorType.kBrushless);
  private final CANSparkMax backright = new CANSparkMax(5, MotorType.kBrushless);














  /** Creates a new ExampleSubsystem. */
  public DriveSubsystem() {
<<<<<<< Updated upstream
  

  }

  public double getGyroAngle(){
      return 1;
  }

  public Optional<Pose2d> getPose(){
    return;
  }

  private ChassisSpeeds getChassisSpeeds(){
    return;
    
  }

  public void resetOdometry(Pose2d pose){

  }

  public void drive(double xSpeed, double ySpeed, double rot, boolean fieldRelative, boolean rateLimit){

  }

  public void setX(){

  }

  public setModuleStates(SwerveModuleState[] desiredStates){

  }

  public void resetEnoders(){
    
  }

  public void zeroHeading(){

  }

  public void breakMode(){

  }

  public Optional<Double> getHeading(){

  }

  public void addVisionData(Pose2d visionEstimatePose, double timeStamp){

  }

  
=======
    frontleft.follow(backleft);
    frontright.follow(backright);
    drivetrain = new DifferentialDrive(backleft, backright);
  }
>>>>>>> Stashed changes

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void tankdrive(double left, double right){
    drivetrain.tankDrive(left, right);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
