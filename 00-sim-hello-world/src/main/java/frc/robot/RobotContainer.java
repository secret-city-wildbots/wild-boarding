// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {

  private final CommandXboxController joystick = new CommandXboxController(0);

  private int counter = 0;

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    joystick.a().onTrue(Commands.runOnce(() -> {
      System.out.println("A WAS PRESSED");
    }));
  }
}
