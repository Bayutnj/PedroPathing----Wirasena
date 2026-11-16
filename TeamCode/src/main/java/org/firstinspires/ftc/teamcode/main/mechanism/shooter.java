package org.firstinspires.ftc.teamcode.main.mechanism;

import com.pedropathing.ivy.CommandBuilder;
import com.pedropathing.ivy.commands.Commands;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class shooter {
    private final DcMotorEx l, r;
    private boolean activated = true;
    public static double target;
//    TODO : Tune These Value
    public static double kS = 0;
    public static double kV = 0;
    public static double kP = 0;
    public shooter(HardwareMap m) {
        l = m.get(DcMotorEx.class, "l");
        r = m.get(DcMotorEx.class, "r");
        l.setDirection(DcMotorSimple.Direction.REVERSE);
        r.setDirection(DcMotorSimple.Direction.FORWARD);
    }
    public void setPower(double p) {
        l.setPower(p);
        r.setPower(p);
    }
    public CommandBuilder shooterToggle() {
        return Commands.instant(() -> {
            activated = !activated;
            if (!activated) {
                setPower(0);
            }
        });
    }
}
