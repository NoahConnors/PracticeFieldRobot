package org.usfirst.frc.team2175.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();
	CANTalon rightDriveRespectfulLeader;
	CANTalon rightDriveRespectedServant1;
	CANTalon rightDriveRespectedServant2;
	CANTalon leftDriveRespectfulLeader;
	CANTalon leftDriveRespectedServant1;
	CANTalon leftDriveRespectedServant2;
	Solenoid shifter;
	RobotDrive drive;
	Joystick rightJoystick;
	Joystick leftJoystick;
	Joystick gamepad;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
		rightDriveRespectfulLeader = new CANTalon(0);
		rightDriveRespectedServant1 = new CANTalon(1);
		rightDriveRespectedServant2 = new CANTalon(2);
		leftDriveRespectfulLeader = new CANTalon(3);
		leftDriveRespectedServant1 = new CANTalon(4);
		leftDriveRespectedServant2 = new CANTalon(5);
		setRespectedServant(leftDriveRespectedServant1, leftDriveRespectfulLeader);
		setRespectedServant(leftDriveRespectedServant2, leftDriveRespectfulLeader);
		setRespectedServant(rightDriveRespectedServant1, rightDriveRespectfulLeader);
		setRespectedServant(rightDriveRespectedServant1, rightDriveRespectfulLeader);
		shifter = new Solenoid(0);
		leftJoystick = new Joystick(0);
		rightJoystick = new Joystick(1);
		gamepad = new Joystick (2);
		drive = new RobotDrive(leftDriveRespectfulLeader, rightDriveRespectfulLeader);
	}
	
	private void setRespectedServant(CANTalon servant, CANTalon leader) {
		servant.changeControlMode(TalonControlMode.Follower);
		servant.set(leader.getDeviceID());
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		autoSelected = chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			break;
		case defaultAuto:
		default:
			// Put default auto code here
			break;
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		drive.arcadeDrive(leftJoystick.getY(), leftJoystick.getX());
		if(leftJoystick.getRawButton(1)) {
			shifter.set(true);
		} else {
			shifter.set(false);
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}

