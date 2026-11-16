package org.firstinspires.ftc.teamcode.main.mechanism;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class intake {
    private final DcMotorEx i1;

    public intake(HardwareMap m) {
        i1 = m.get(DcMotorEx.class, "i1");
//        i1.setDirection(DcMotorSimple.Direction.REVERSE);
    }

}
