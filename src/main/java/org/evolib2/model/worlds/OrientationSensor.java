package org.evolib2.model.worlds;

import processing.core.PConstants;
import processing.core.PVector;

public class OrientationSensor extends Sensor {

    private final PointParticle particleA;
    private final PointParticle particleB;
    private PVector orientationVector;

    public OrientationSensor(PVector orientationVector, PointParticle particleA, PointParticle particleB) {
        this.orientationVector = orientationVector.copy().normalize();
        this.particleA = particleA;
        this.particleB = particleB;
    }

    @Override
    protected float measureSignal() {
        PVector currentDirection = PVector.sub(particleB.getPosition(), particleA.getPosition());
        float angle = PVector.angleBetween(currentDirection, orientationVector);
        return angle / PConstants.PI;
    }
}
