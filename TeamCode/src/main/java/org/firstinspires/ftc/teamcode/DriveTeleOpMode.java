package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@TeleOp
public class DriveTeleOpMode extends LinearOpMode {
    private Gyroscope imu;
    private DcMotor motorTest;
    private DigitalChannel digitalTouch;
    private DistanceSensor sensorColorRange;
    private Servo servoTest;
    private lMotor leftMotor;
    private rMotor rightMotor;

    @Override
    public void runOpMode() {
        imu = hardwareMap.get(Gyroscope.class, "imu");
        motorTest = hardwareMap.get(DcMotor.class, "motorTest");
        digitalTouch = hardwareMap.get(DigitalChannel.class, "digitalTouch");
        sensorColorRange = hardwareMap.get(DistanceSensor.class, "sensorColorRange");
        servoTest = hardwareMap.get(Servo.class, "servoTest");
        leftMotor = hardwareMap.dcMotor.get(lMotor.class, "leftMotor");
        rightMotor = hardwareMap.dcMotor.get(rMotor.class, "rightMotor");
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //Drive Code
        boolean drivemode = true;
        double throttle = gamepad1.left_stick_y;
        double turn =  gamepad1.left_stick_x;

        double leftspeed = throttle - turn;
        double rightspeed = throttle + turn;

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
       
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            
            //Drive Mode Switching
            if (gamepad1.dpad_down){
                drivemode = (drivemode) ? false : true;
            }
            
            if(drivemode) {
                leftspeed = throttle - turn;
                rightspeed = throttle + turn;
            }
            else{
                leftspeed = gamepad1.left_stick_y;
                rightspeed = gamepad1.right_stick_y;
            }
            
            //Motor Power
            leftMotor.setPower(leftspeed);
            rightMotor.setPower(rightspeed);
            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}
