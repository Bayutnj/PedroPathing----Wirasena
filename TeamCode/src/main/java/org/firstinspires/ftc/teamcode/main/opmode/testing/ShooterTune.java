package org.firstinspires.ftc.teamcode.main.opmode.testing;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.JoinedTelemetry;
import com.bylazar.telemetry.PanelsTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

@TeleOp
@Configurable
public class ShooterTune extends OpMode {
    private DcMotorEx ls;
    public static double kP = 0.0, kI =0.0, kD = 0.0, kF =0.0;
    public static double target = 1800.0;
    public JoinedTelemetry joinedTelemetry;

    @Override
    public void init() {
        ls = hardwareMap.get(DcMotorEx.class, "shooter");
        ls.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ls.setDirection(DcMotorSimple.Direction.REVERSE);
        ls.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(kP, kI,kD,kF);
        ls.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);

        joinedTelemetry = new JoinedTelemetry(PanelsTelemetry.INSTANCE.getFtcTelemetry(), telemetry);
    }

    @Override
    public void loop() {
        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(kP, kI,kD,kF);
        ls.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);

//        if (gamepad1.y) {
        ls.setVelocity(target);
//        } else {
//            ls.setPower(0);
//        }

        joinedTelemetry.addData("VELOCITY", ls.getVelocity());
        joinedTelemetry.addData("TARGET", target);
        joinedTelemetry.update();
    }
}
