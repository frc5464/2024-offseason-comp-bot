package frc.robot.OI;

public final class UserPolicy {
    public static boolean twistable = false;
    public static boolean xLocked = false;

    public static boolean ampShoot = false;
    public static boolean speakerShoot = false;
    public static boolean disableShoot = false;
    public static boolean shootReverse = false;
    public static boolean shootUptoSpeed = false;

    public static boolean intaking = false;
    public static boolean feeding = false; 
    public static boolean intakeReverse = false;

    public static boolean leftUp = false;
    public static boolean leftDown = false;
    public static boolean rightUp = false;
    public static boolean rightDown = false;
    public static boolean autoUp = false;
    public static boolean autoDown = false;

    public static boolean climbOverride = false;

    public static String LEDselected = "White";

    public static boolean snapAprilSpeaker = false;
    // TODO: Make snapping to the amp a possiblity
    
    public static boolean closetospeaker = false;
    public static boolean closetoamp = false;

    public static boolean noteHoming = false;
    public static boolean noteDetected = false;

    public static boolean aprilTagsAreDetected = false;
    
    public static boolean dummyIntake = false;

    public static boolean homingPathToNote = false;

    public static boolean isWackR = false;
    public static boolean isWackL = false;

    public static double wait;

    public static double XComponent;
    public static double YComponent;

    public static boolean translateToNote = true;

    private UserPolicy() {
    }
}
