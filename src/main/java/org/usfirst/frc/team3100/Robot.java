package org.usfirst.frc.team3100;

    import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Encoder;

public class Robot extends IterativeRobot {
    private static final int controllerPort = 1;
    private static final int leftDriveChannel = 5;
    private static final int rightDriveChannel = 6;
    private static final int shooterChannel = 1;
    private static final int turntableChannel = 3;
    private static final int ballLiftChannel = 2;
    private static final int ballPickupChannel = 4;
    private static final int hoodChannel = 0;
    private int testShooter = 0;

    private Encoder rotationEncoder;
    private Encoder angleEncoder;
    private XBoxController controller;
    private SpeedController rightDrive;
    private SpeedController leftDrive;
    private SpeedController shooter;
    private SpeedController turnTable;
    private SpeedController ballLift;
    private SpeedController ballPickup;
    private SpeedController hood;

    private RobotDrive driveTrain;

    public void robotInit() {
        controller = new XBoxController(controllerPort);
        rightDrive = new Victor(rightDriveChannel);
        rightDrive.setInverted(true);
        leftDrive = new Victor(leftDriveChannel);
        leftDrive.setInverted(true);
        driveTrain = new RobotDrive(leftDrive, rightDrive);
        shooter = new Victor(shooterChannel);
        turnTable = new Victor(turntableChannel);
        ballLift = new Victor(ballLiftChannel);
        ballPickup = new Victor(ballPickupChannel);
        hood = new Victor(hoodChannel);
        rotationEncoder = new Encoder(0,0);
        angleEncoder = new Encoder(0,0);

    }

    public void autonomousInit() {

    }

    public void autonomousPeriodic() {

    }

    public void teleopPeriodic() {

        driveTrain.arcadeDrive(controller.getLeftStickY(), controller.getRightStickX());
        if(controller.getLeftBumper()) {
            if(testShooter == 0) {
                shooter.set(1);
                ballLift.set(1);
                testShooter = 1;
            }
            else {
                shooter.set(0);
                ballLift.set(0);
                testShooter = 0;
            }
        }
        if(controller.getRightBumper()) {
            ballPickup.set(1);
        } else {
            ballPickup.set(0);
        }
        if(controller.getDPad() == 90) {
            turnTable.set(1);
        } else if (controller.getDPad() == 270) {
            turnTable.set(-1);
        } else if (controller.getDPad() == 0) {
            hood.set(-1);
        } else if (controller.getDPad() == 180) {
            hood.set(1);
        } else {
            turnTable.set(0);
            hood.set(0);
        }
        System.out.println(rotationEncoder.get() + " " + angleEncoder.get());
    }

    public void testPeriodic() {

    }
}
