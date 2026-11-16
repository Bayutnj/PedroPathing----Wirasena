package org.firstinspires.ftc.teamcode.main.opmode.testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.JoinedTelemetry;
import com.bylazar.telemetry.PanelsTelemetry;
import com.pedropathing.controllers.Controller;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Configurable
@TeleOp(name = "ShooterV2")
public class shootertest extends LinearOpMode {
    private DcMotorEx shooter;
    private Controller controller;
    public JoinedTelemetry joinedTelemetry;

    // Bisa diubah live lewat Panels / Dashboard
    public static double kP = 0;
    public static double kI = 0;
    public static double kD = 0;
    public static double kF = 0;      // dipakai sebagai kV (proportional feedforward)
    public static double target = 1800;

    // Simpan gain terakhir supaya controller cuma dibangun ulang saat gain berubah
    private double lastKP, lastKI, lastKD, lastKF;

    private Controller buildController() {
        return Controller.sum(
                Controller.pid(kP, kI, kD),
                Controller.proportionalFeedforward(kF)
        );
    }

    private boolean gainsChanged() {
        return kP != lastKP || kI != lastKI || kD != lastKD || kF != lastKF;
    }

    private void rememberGains() {
        lastKP = kP;
        lastKI = kI;
        lastKD = kD;
        lastKF = kF;
    }

    @Override
    public void runOpMode() throws InterruptedException {
        shooter = hardwareMap.get(DcMotorEx.class, "shooter");

        shooter.setDirection(DcMotorSimple.Direction.REVERSE);
        shooter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // Karena PID dihitung sendiri, motor harus RUN_WITHOUT_ENCODER
        shooter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        shooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        controller = buildController();
        rememberGains();

        joinedTelemetry = new JoinedTelemetry(PanelsTelemetry.INSTANCE.getFtcTelemetry(), telemetry);

        waitForStart();

        while (opModeIsActive()) {
            // Bangun ulang controller hanya kalau gain berubah (tuning live)
            if (gainsChanged()) {
                controller = buildController();
                rememberGains();
            }

            double velocity = shooter.getVelocity(); // ticks/sec
            double error = target - velocity;

            double power = controller.calculate(error, target);
            power = Math.max(-1.0, Math.min(1.0, power));

            shooter.setPower(power);

            joinedTelemetry.addData("Velocity", velocity);
            joinedTelemetry.addData("Target", target);
            joinedTelemetry.addData("Error", error);
            joinedTelemetry.addData("Power", power);
            joinedTelemetry.addData("POS", shooter.getCurrentPosition());
            joinedTelemetry.addData("kP", kP);
            joinedTelemetry.addData("kI", kI);
            joinedTelemetry.addData("kD", kD);
            joinedTelemetry.addData("kF", kF);
            joinedTelemetry.update();

            TelemetryPacket packet = new TelemetryPacket();
            packet.put("CURRENT", velocity);
            packet.put("TARGET", target);
            packet.put("POWER", power);
            FtcDashboard.getInstance().sendTelemetryPacket(packet);
        }

        shooter.setPower(0);
    }
}