package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.Commands.DriveCommand;
import frc.robot.Commands.RevolverCommand;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.ExternalFollower;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Revolver extends Subsystem {
    public double angle;
    private NetworkTable revolverTable;
    private NetworkTableEntry angleEntry, slot1Entry, slot2Entry, slot3Entry, slot4Entry, slot5Entry;
    private boolean slot1, slot2, slot3, slot4, slot5;

    public Revolver() {
        System.out.println("Revolver initialization...");
        revolverTable = Robot.table.getSubTable("revolver");
        
        slot1 = true;
        slot2 = true;
        slot3 = true;
        slot4 = true;
        slot5 = true;
        angle = 1.0;

        slot1Entry = revolverTable.getEntry("slot1"); 
        slot2Entry = revolverTable.getEntry("slot2"); 
        slot3Entry = revolverTable.getEntry("slot3"); 
        slot4Entry = revolverTable.getEntry("slot4"); 
        slot5Entry = revolverTable.getEntry("slot5"); 
        angleEntry = revolverTable.getEntry("angle");

        slot1Entry.setDefaultBoolean(false);
        slot2Entry.setDefaultBoolean(false);
        slot3Entry.setDefaultBoolean(false);
        slot4Entry.setDefaultBoolean(false);
        slot5Entry.setDefaultBoolean(false);
        angleEntry.setDefaultNumber((double) 0.0);
    }

    public void updateNetworkTable() {
        updateSlotSensors();
        angle += 0.01;
        slot1 = !slot1;
        System.out.println(angle);
        slot1Entry.setBoolean(slot1);
        slot2Entry.setBoolean(slot2);
        slot3Entry.setBoolean(slot3);
        slot4Entry.setBoolean(slot4);
        slot5Entry.setBoolean(slot5);
        angleEntry.setNumber(angle);
    }

    private void updateSlotSensors(){
        //read slot sensors into slot variables
    }

    public void setSpeed(double speed) {
        // set motor to parameter
    }

    public void halfClick(boolean clockwise) {
        // set motor + or - for parameter
        try {
            wait(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // set motor 0
    }

    public void click(boolean clockwise) {

    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new RevolverCommand());
    }
}