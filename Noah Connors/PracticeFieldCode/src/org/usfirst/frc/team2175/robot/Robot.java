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
	CANTalon leftDriveMaster;
	CANTalon leftDriveSlave1;
	CANTalon leftDriveSlave2;
	CANTalon rightDriveMaster;
	CANTalon rightDriveSlave1;
	CANTalon rightDriveSlave2;
	Solenoid shifter;
	RobotDrive drive;
	Joystick leftJoystick;
	Joystick rightJoystick;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
		leftDriveMaster = new CANTalon(0);
		leftDriveSlave1 = new CANTalon(1);
		leftDriveSlave2 = new CANTalon(2);
		rightDriveMaster = new CANTalon(3);
		rightDriveSlave1 = new CANTalon(4);
		rightDriveSlave1 = new CANTalon(5);
		setSlave(leftDriveSlave1, leftDriveMaster);
		setSlave(leftDriveSlave2, leftDriveMaster);
		setSlave(rightDriveSlave1, rightDriveMaster);
		setSlave(rightDriveSlave2, rightDriveMaster);
		shifter = new Solenoid(0);
		drive = new RobotDrive(leftDriveMaster, rightDriveMaster);
		leftJoystick = new Joystick(0);
		rightJoystick = new Joystick(1);
	}
	
	private void setSlave(CANTalon slave, CANTalon master) {
		slave.changeControlMode(TalonControlMode.Follower);
		slave.set(master.getDeviceID());
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
		drive.arcadeDrive(leftJoystick.getY(), rightJoystick.getX());
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

