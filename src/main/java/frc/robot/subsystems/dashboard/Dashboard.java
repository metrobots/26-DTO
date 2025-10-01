package frc.robot.subsystems.dashboard;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
public class Dashboard extends SubsystemBase{

    private final NetworkTable table;
    private final NetworkTableEntry robotSpeedEntry;

    public Dashboard() {
        table = NetworkTableInstance.getDefault().getTable("Dashboard");

        // Example value of 0.5
        robotSpeedEntry = table.getEntry("robotSpeed");
        robotSpeedEntry.setDouble(0.5);

    }

    public double getRobotSpeed() {
        return robotSpeedEntry.getDouble(0.5);
    }

    public void setRobotSpeed(double value) {
        robotSpeedEntry.setDouble(value);
    }
    
}
