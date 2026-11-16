package org.firstinspires.ftc.teamcode.main.mechanism;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ramp {
    private final Servo left_ramp, right_ramp;
//    TODO : Tune These Vaule
    private double idle = 0.5;
    private double work = 0.1;

    public ramp(HardwareMap m) {
        left_ramp = m.get(Servo.class, "left_ramp");
        right_ramp = m.get(Servo.class, "right_ramp");

        left_ramp.setDirection(Servo.Direction.REVERSE);
        right_ramp.setDirection(Servo.Direction.FORWARD);
    }
}
