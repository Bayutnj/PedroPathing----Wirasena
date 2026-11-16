package org.firstinspires.ftc.teamcode.main.mechanism;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class lifter {
    private final DcMotorEx l1;
//    TODO : Tune These Value
    private final double min = 0; // ticks unit
    private final double max = 1500; // ticks unit

    public lifter(HardwareMap m) {
        l1 = m.get(DcMotorEx.class, "l1");
//        l1.setDirection(DcMotorSimple.Direction.REVERSE);
        l1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        l1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        l1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}
