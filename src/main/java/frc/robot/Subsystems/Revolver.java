package frc.robot.Subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.Commands.RevolverCommand;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Revolver extends Subsystem {
    TalonSRX revolverMotor;
    Encoder revolverEncoder;

    public double angle;
    private NetworkTable revolverTable;
    private NetworkTableEntry angleEntry, slot1Entry, slot2Entry, slot3Entry, slot4Entry, slot5Entry;
    private boolean slot1, slot2, slot3, slot4, slot5;
    private int clickAngle = 66;

    public Revolver() {
        System.out.println("Revolver initialization...");

        revolverMotor = new TalonSRX(RobotMap.REVOLVER_MOTOR);

        revolverEncoder = new Encoder(RobotMap.ENCODER_CHANNEL_A, RobotMap.ENCODER_CHANNEL_B);

        revolverEncoder.setDistancePerPulse(((double) 360 / (double) 2048));

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
        System.out.println(revolverEncoder.getDistance());
        updateSlotSensors();
        angle += 0.01;
        slot1 = !slot1;
        slot1Entry.setBoolean(slot1);
        slot2Entry.setBoolean(slot2);
        slot3Entry.setBoolean(slot3);
        slot4Entry.setBoolean(slot4);
        slot5Entry.setBoolean(slot5);
        angleEntry.setNumber(angle);
    }

    public void trimLeft(){
        setSpeed(.2);
    }

    public void trimRight(){
        setSpeed(-.2);
    }

    private void updateSlotSensors() {
        // read slot sensors into slot variables
    }

    public void setSpeed(double percent) {
        revolverMotor.set(ControlMode.PercentOutput, percent);
    }

    private double getEncoderValue() {
        return revolverEncoder.getDistance();
    }

    public void halfClick(boolean clockwise) {
        double startValue = getEncoderValue();
        if (clockwise) {
            while (startValue + clickAngle / 2 > getEncoderValue())
                revolverMotor.set(ControlMode.PercentOutput, .25);
        } else {
            while (startValue - clickAngle / 2 < getEncoderValue())
                revolverMotor.set(ControlMode.PercentOutput, -.25);
        }
        revolverMotor.set(ControlMode.PercentOutput, 0);
    }

    public void click(boolean clockwise) {
        double startValue = getEncoderValue();
        if (clockwise) {
            while (startValue + clickAngle > getEncoderValue())
                revolverMotor.set(ControlMode.PercentOutput, .25);
        } else {
            while (startValue - clickAngle < getEncoderValue())
                revolverMotor.set(ControlMode.PercentOutput, -.25);
        }
        revolverMotor.set(ControlMode.PercentOutput, 0);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new RevolverCommand());
    }
}