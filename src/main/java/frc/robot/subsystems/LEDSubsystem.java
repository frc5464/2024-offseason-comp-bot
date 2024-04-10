package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import entech.subsystems.EntechSubsystem;
import frc.robot.OI.UserPolicy;

public class LEDSubsystem extends EntechSubsystem{

    int totalLED = 91;

    AddressableLED ledstrip = new AddressableLED(1);
    AddressableLEDBuffer ledbuffer = new AddressableLEDBuffer(totalLED);
    private static final boolean ENABLED = true;

    public static String color_choice;

    private static final String kBlueAmplified = "BlueAmplified";
    private static final String kYellowCoop = "YellowCoop";
    private static final String kPinkIntake = "PinkIntake";
    private static final String kWhite = "White";
    private static final String kClear = "Clear";
    private static final String kCloseToggle = "CloseToggle";

    private Timer blinkyTimer = new Timer();  // timer for blinks
    private boolean blinkingActive = false;   // flag for blinking state
    private boolean blinkRunState = false;    // keeps track of the LEDs being on/off
    private double blinkTime = 0.2;              // seconds that we blink
    private int blinkReps = 5;                // number of blinks that we want
    private double blinkRunReps = 0;

    // STUFF THAT WE WANT TO ADD TO THE REAL CODE
    private Timer led_timer = new Timer();
    private double led_tick_speed = 0.05;

    enum AnimationColorMode{
        Static,
        Rainbow,
        AllianceColor
    }

    // Animation configuration
    int ani_index = 0;
    int ani_r = 0;
    int ani_g = 0;
    int ani_b = 255;
    int ani_rainbow_index=0;
    int ani_trl_len = 15; // how many pixels trail with the fade?
    AnimationColorMode aniColorMode = AnimationColorMode.AllianceColor;

    @Override
    public void initialize(){
        ledstrip.setLength(ledbuffer.getLength());
        ledstrip.setData(ledbuffer);
        ledstrip.start();
        White();
        blinkyTimer.stop();
        blinkyTimer.reset();
        led_timer.reset();
        led_timer.start();
        color_choice = kWhite;
        startBlinking();
    }

    public void periodic(){
        SmartDashboard.putBoolean("blinking", blinkingActive);
        // ANIMATION TICK
        if (RobotState.isDisabled()) {
        if(led_timer.get() > led_tick_speed){
            AnimationUpdateColor();
            AnimationRun();
            led_timer.restart();
        }
        }
        else{
        if(blinkingActive){
            handleBlinking();
        }
        else{
            color_choice = UserPolicy.LEDselected;
        }
        // if(!RobotState.isDisabled()){
        switch (color_choice) {
            case kYellowCoop:
                YellowCoop();
                break;

            case kBlueAmplified:
                BlueAmplified();
                break;

            case kPinkIntake:
                PinkIntake();
                break;

            case kWhite:
                White();
                break;
        
            case kClear:
                Clear();
                break;

            case kCloseToggle:
                CloseToggle();               
                break;

            default:
                // White();
                break;
        }
        // }
        }
    }

    @Override
    public boolean isEnabled() {
        return ENABLED;
    }
        
    public void YellowCoop(){
            for(int i = 1; i < totalLED; i++){
            ledbuffer.setRGB(i, 200, 80, 0);
        }
        ledstrip.setData(ledbuffer); 
    }

    public void BlueAmplified(){
            for(int i = 1; i < totalLED; i++){
                ledbuffer.setRGB(i, 0, 0, 60);
        }
        ledstrip.setData(ledbuffer);
    }

    public void PinkIntake(){
            for(int i = 1; i < totalLED; i++){
            ledbuffer.setRGB(i, 255, 25, 25);
        }
        ledstrip.setData(ledbuffer);
    }

    public void White(){
            for(int i = 1; i < totalLED; i++){
                ledbuffer.setRGB(i, 100, 100, 100);
        }
        ledstrip.setData(ledbuffer);
    }

    public void CloseToggle(){
        for(int i = 1; i < totalLED; i ++){
            ledbuffer.setRGB(i, 0, 200, 0);
        }
        ledstrip.setData(ledbuffer);
    }

    public void Clear(){
        for(int i = 1; i < totalLED; i++){
            ledbuffer.setRGB(i, 0, 0, 0);
        }
        ledstrip.setData(ledbuffer);
    }

    public void startBlinking(){
        blinkyTimer.reset();
        blinkyTimer.start();
        blinkingActive = true;
        blinkRunState = true; //start with the LEDs ON
        blinkRunReps = 0;
    }

    private void handleBlinking(){
        
        // check the timer, flip if it's time
        if(blinkyTimer.get() >= blinkTime){
            System.out.println("blink");
            // show a color if the state is true
            if(blinkRunState){
                color_choice = UserPolicy.LEDselected;
            }
            // show nothing if the state is false
            else{
                color_choice = kClear;
            }
            // reset the timer, restart timer
            blinkyTimer.reset();
            blinkyTimer.start();

            // increment our reps, then flip state for next time
            blinkRunReps += 0.5;
            
            blinkRunState = !blinkRunState;
            System.out.println(color_choice);    
        }

        // check the blink reps. Quit blinking if reps are done.
        if(blinkRunReps>=blinkReps){
            blinkyTimer.stop();
            blinkRunState = false;
            blinkingActive = false;
            blinkRunReps = 0;
        }
    }

        public void AnimationRun(){ // we could put other animations here if needed
            if(RobotState.isDisabled()){
                AnimationChaseFade();
            }      
        }

        private void AnimationUpdateColor(){
            switch(aniColorMode){
                case AllianceColor:
                    if(DriverStation.getAlliance().isPresent()){ // If Driver Station is connected,
                        DriverStation.Alliance color= DriverStation.getAlliance().get(); // get alliance
                        if(color == DriverStation.Alliance.Blue){ // set to blue if Driver station says so
                            ani_r = 0;
                            ani_g = 0;
                            ani_b = 255;
                        }
                        if(color == DriverStation.Alliance.Red){
                            ani_r = 255;
                            ani_g = 0;
                            ani_b = 0;
                        }                
                    }
                    else{   // This is the occurs when Driver Station is not connected
                        ani_r = 255;
                        ani_g = 0;
                        ani_b = 255;
                    }
                    break;
                case Rainbow:
                    if(ani_rainbow_index < 255){ 
                        ani_r = ani_rainbow_index; // red increasing
                        ani_g = 255-ani_rainbow_index; // green decreasing
                    }
                    else if(ani_rainbow_index < 510){
                        ani_b = ani_rainbow_index; // blue increasing
                        ani_r = 255-ani_rainbow_index; // red decreasing        
                    }
                    else{
                        ani_g = ani_rainbow_index; // green increasing
                        ani_b = 255-ani_rainbow_index; // blue decreasing
                    }
                    ani_rainbow_index++;
                    if(ani_rainbow_index>=755){
                        ani_rainbow_index = 0;
                    }
                    break;
                case Static: // don't change a thing here
                    break;
            }
        }

        private void AnimationChaseFade(){   // moving pixel with a fading trail behind
            for(int i=0;i<ani_trl_len;i++){ // act on a member of the fading tail
                int x = ani_index - i; // move to a new pixel past the first one                
                int trl_r = ani_r - (i*(ani_r/ani_trl_len)); // find brightness vals
                int trl_g = ani_g - (i*(ani_g/ani_trl_len)); 
                int trl_b = ani_b - (i*(ani_b/ani_trl_len));                              
                if(x <0){x = totalLED + x;} // wrap around if needed   
                ledbuffer.setRGB(x, trl_r, trl_g, trl_b); // set that pixel value 
            }
            ledstrip.setData(ledbuffer); // send the data over
            ani_index++; // increase the index of the brightest pixel
            if(ani_index>=totalLED){
                ani_index=0; // wrap that index around if it has reached the strip end
            }
        }

}
