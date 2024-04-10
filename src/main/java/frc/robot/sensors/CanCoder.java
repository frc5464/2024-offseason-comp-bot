package frc.robot.sensors;

import com.ctre.phoenix6.hardware.CANcoder;

import edu.wpi.first.math.util.Units;

/**
 * The {@code CanCoder} class contains fields and methods pertaining to the
 * function of the absolute encoder.
 */
public class CanCoder {
	private CANcoder canCoder;
	private boolean inverted;
	private double positionOffset;

	public CanCoder(int port) {
		this.canCoder = new CANcoder(port);
		this.inverted = false;
		this.positionOffset = 0.0;
	}

	public double getPosition() {
		return (inverted ? -1.0 : 1.0) * Units.degreesToRadians(canCoder.getAbsolutePosition().getValueAsDouble()*360);
	}

	public void setInverted(boolean inverted) {
		this.inverted = inverted;
	}

	public void setPositionOffset(double offset) {
		positionOffset = offset;
	}

	public double getPositionOffset() {
		return positionOffset;
	}

	public double getVirtualPosition() {
		return getPosition() - positionOffset;
	}

}