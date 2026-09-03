
## Contents
 - [Initial Setup](#initial-setup) 
 - [Simulation Hello World](#00-sim-hello-world) 

## Initial Setup

[Back to Contents](#contents)

Below are the minimum steps for running Java code on our FRC robots and testbeds with these example projects.

### Install FRC Game Tools

To control and test a RoboRIO, install the following software:
  - FRC Game Tools
    - LabVIEW Update
    - FRC Driver Station
    - FRC RoboRIO Imaging Tool and Images

For detailed installation instructions, see FIRST's documentation [here](https://docs.wpilib.org/en/stable/docs/zero-to-robot/step-2/frc-game-tools.html).

### Installing VS Code with the WPILib Command Palette

To develop Java code for our robots, use the special version of VS Code that includes the WPILib Command Palette and other FRC development tools.

For detailed installation instructions, see FIRST's documentation [here](https://docs.wpilib.org/en/stable/docs/zero-to-robot/step-2/wpilib-setup.html).
  - NOTE: FIRST's documentation may not point to the latest WPILib version. Check the latest release on the [WPILib VS Code GitHub page](https://github.com/wpilibsuite/vscode-wpilib/releases).

### Preparing Your Robot

This section is only necessary if you are working with physical hardware. You can skip it when running the simulation example. If you are using a RoboRIO and radio that have already been configured, such as Linguini or another robot, you can also skip it. Otherwise, you will need to image and program them.

For detailed instructions, see FIRST's documentation:
  - [Imaging RoboRIO](https://docs.wpilib.org/en/stable/docs/zero-to-robot/step-3/roborio2-imaging.html)
  - [Programming Radio](https://docs.wpilib.org/en/stable/docs/zero-to-robot/step-3/radio-programming.html)

At this point, you should have the software and, if needed, the hardware required to work through these examples.

### Clone this repository

To use these examples, clone this repository from GitHub. Git is the version control software used by this project.
  - NOTE: Windows users may want to install [TortoiseGit](https://tortoisegit.org) or [Git for Windows](https://gitforwindows.org).

1. Open a terminal and navigate to the directory where you want to store the repository.
1. Run the following command:
   ```bash
   git clone https://github.com/secret-city-wildbots/wild-boarding.git
   ```

## 00-sim-hello-world

[Back to Contents](#contents)

### Description

The goal of this section is to help you:

1. Run robot code in simulation.
1. Use the FRC Driver Station with the simulation.
1. Understand and modify the provided template code.

This tutorial assumes that you have a basic understanding of Java or a similar language such as JavaScript or Rust. You do not need a RoboRIO or other physical robot hardware to complete this simulation tutorial.

### Set Up VS Code

Open the `00-sim-hello-world` Gradle project in the WPILib version of VS Code:

  1. Launch the WPILib version of VS Code.
  1. Select `File` -> `Open Folder...`.
  1. Select the repository's `00-sim-hello-world` directory. This directory contains the Gradle project for the example.
  1. VS Code should now load the project and its WPILib tools. Your window should look similar to the following image:
    ![Example VS Code view](images/00-sim-hello-world-1.png)

### Code Overview

The main code is in the `src/main/java/frc/robot` directory. It contains four files:
  - Main.java
    - The entry point for the program.
  - Robot.java
    - The robot's initialization and periodic functions:
       - robot
       - autonomous
       - teleop
       - disabled
       - test
       - simulation
  - RobotContainer.java
    - Contains the robot's subsystems and their corresponding commands.
    - Also contains the controller instance and all of its bindings.
  - Constants.java
  - Contains constants such as CAN IDs and speeds.


In `Robot.java`, the `robotInit()` method contains the following code:

```java
@Override
  public void robotInit() {
    System.out.println("ROBOT INITIALIZED!!!");
  }
```

The `teleopInit()` method contains this code:

```java
 @Override
  public void teleopInit() {
    System.out.println("[ENABLED]");
  }
```

An `Init` method is typically called once when its mode starts. For example, `teleopInit()` runs when the robot enters teleoperated mode, while `robotInit()` runs when the robot code starts.

### Running Example

1. In VS Code, select `...` in the top-right corner, then select `Build Robot Code`. You should see `BUILD SUCCESSFUL` in the VS Code terminal.
   ![Build Code](images/00-sim-hello-world-2.png)
1. Open the FRC Driver Station and connect an Xbox controller.
1. In VS Code, select `...` again, then select `Simulate Robot Code`.
1. When the simulation options prompt appears at the top of the window, select `Use Real Driver Station`, then select `OK`.
1. In the Driver Station, select `Teleoperated`, then select `Enable`.
   ![Driver Station](images/00-sim-hello-world-3.png)
1. Once the robot is enabled, press the `A` button. The message `A WAS PRESSED` should appear in the VS Code terminal, not in the Driver Station.

### A Closer Look at Robot Container

The `RobotContainer` class organizes the robot's commands and controller bindings. The `configureBindings()` method defines how controller buttons control the robot. In this example, pressing the `A` button runs a command that logs `A WAS PRESSED` to the VS Code terminal.

```java
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
```

There are three parts to this button binding:

1. The button being bound, e.g. `.a()`
1. The condition under which the command should run, e.g. `.onTrue()`
2. The command to be run when the condition is met, e.g. `Commands.runOnce(() -> { ... })`

The `runOnce` command will run the given `Runnable`. There are many other types of commands, but that's a lesson for a later day.

For example, the binding in `RobotContainer.java` is:
```java
joystick.a().onTrue(Commands.runOnce(() -> {
  System.out.println("A WAS PRESSED");
}));
```

Try adding more bindings to `configureBindings()` by calling methods on the `joystick` object, such as `a()`, `b()`, `x()`, `y()`, `leftBumper()`, `rightBumper()`, `leftTrigger()`, and `rightTrigger()`. You can also change the condition to `.whileTrue()` or `.onFalse()`. Remember to enable the robot in the Driver Station before the controller will do anything.