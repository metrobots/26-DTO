package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.dashboard.Dashboard;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.utils.Constants;
import frc.robot.utils.Constants.OIConstants;

/*
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // Auto SendableChooser
  private final SendableChooser<Command> autoChooser;
  // Subsystem declarations
  final Drivetrain m_drivetrain;
  final Dashboard m_dashboard;

  
  // The driver's controller
  private final CommandXboxController primary = Constants.primary;

   public RobotContainer() {

    m_drivetrain = new Drivetrain();
    m_dashboard = new Dashboard();
    
    
    registerNamedCommands();

    autoChooser = AutoBuilder.buildAutoChooser();

    // Configure the button bindings
    configureButtonBindings();

    m_drivetrain.setDefaultCommand( // IF THE DRIVETRAIN ISN'T DOING ANYTHING ELSE, DO THIS. MULTIPLY THE SPEED BY THE ROBOT SPEED VALUE FROM DASHBOARD
        new RunCommand(() -> {
            m_drivetrain.drive(
                (-MathUtil.applyDeadband(primary.getLeftY(), OIConstants.kDriveDeadband))*(m_dashboard.getRobotSpeed()),
                (-MathUtil.applyDeadband(primary.getLeftX(), OIConstants.kDriveDeadband))*(m_dashboard.getRobotSpeed()),
                (MathUtil.applyDeadband(primary.getRightX(), OIConstants.kDriveDeadband))*(m_dashboard.getRobotSpeed()),
                true);
        }, m_drivetrain)
    );

    SmartDashboard.putData("Auto Chooser", autoChooser); // Put the auto chooser on the dashboard
  } 


  private void registerNamedCommands() {
    // Register commands for auto
  }

  private void configureButtonBindings() {
    // Configure bindings for controller
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }
}
