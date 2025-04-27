package com.example.commanddecorator.controller;

import com.example.commanddecorator.model.Command;
import com.example.commanddecorator.model.Fan;
import com.example.commanddecorator.model.Light;

public class RemoteController {
    private final Command onCommand;
    private final Command offCommand;

    public RemoteController(Command onCommand, Command offCommand) {
        this.onCommand  = onCommand;
        this.offCommand = offCommand;
    }

    public void on() {
        onCommand.execute();
    }
    public void off() {
        offCommand.execute();
    }

    public static void demo(){

        Fan fan   = new Fan();
        Light light = new Light();

        // use method references for commands
        RemoteController fanRemote   = new RemoteController(fan::startRotate, fan::stopRotate);
        RemoteController lightRemote = new RemoteController(light::turnOn,   light::turnOff);

        System.out.println("FOR FAN:------");
        fanRemote.on();
        fanRemote.off();

        System.out.println("\nFOR LIGHT:------");
        lightRemote.on();
        lightRemote.off();
    }
}
