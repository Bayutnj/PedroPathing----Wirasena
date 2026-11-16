package org.firstinspires.ftc.teamcode.main.opmode;

import com.pedropathing.ivy.Scheduler;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.main.wiraBot;

public class TeleG extends OpMode {
    private wiraBot bot;

    @Override
    public void init() {
        bot = new wiraBot(hardwareMap);
        Scheduler.reset();
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void loop() {
    }
}
