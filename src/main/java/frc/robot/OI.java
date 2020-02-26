package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  private NetworkTable joystickTable;
  private NetworkTableEntry xEntry;
  private NetworkTableEntry yEntry;
  Joystick extreme;


  // Joystick joystick
  public OI() {
    System.out.println("OI initialized..."); 
    extreme = new Joystick(RobotMap.EXTREME);
    joystickTable = Robot.table.getSubTable("joystick");
    xEntry = joystickTable.getEntry("X");
    yEntry = joystickTable.getEntry("Y");
    xEntry.setDefaultDouble(0.0);
    yEntry.setDefaultDouble(0.0);
  }


  public Boolean getButton(int button) {
    boolean isPressed = false;
    isPressed = extreme.getRawButton(button);
    return isPressed;
  }

  public void updateNetworkTable(){
    xEntry.setDouble(getAxis("X"));
    yEntry.setDouble(getAxis("Y"));
  }

  public Double getAxis(String axis) {
    double axisValue;
    axisValue = 0;
    switch (axis) {
    case "X":
      axisValue = extreme.getRawAxis(0);
      break;
    case "Y":
      axisValue = extreme.getRawAxis(1);
      break;
    case "Z":
      axisValue = extreme.getRawAxis(2);
      break;
    case "SLIDER":
      axisValue = extreme.getRawAxis(3);
      break;
    }
    return axisValue;
  }
}