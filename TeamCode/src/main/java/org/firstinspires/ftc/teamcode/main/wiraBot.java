package org.firstinspires.ftc.teamcode.main;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.main.mechanism.intake;
import org.firstinspires.ftc.teamcode.main.mechanism.shooter;

public class wiraBot{
    public wiraBot(HardwareMap m) {
        shooter = new shooter(m);
    }
    public Follower follower;
    public final shooter shooter;
}
