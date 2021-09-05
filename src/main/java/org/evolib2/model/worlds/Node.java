package org.evolib2.model.worlds;

import lombok.Getter;
import lombok.Setter;
import processing.core.PVector;

public class Node extends SpecimenComponent {

    public static final float INACTIVE_FRICTION_FACTOR = 0.25f;

    public static final float CONTRACTING_FRICTION_FACTOR = 1.0f;

    @Getter
    private PointParticle pointParticle = new PointParticle();

    @Setter
    private Membrane membrane;

    public void doTick() {
        pointParticle.doTick();
    }

    public void applyForce(PVector force) {
        pointParticle.applyForce(force);
    }

    public float getFrictionFactor() {
        return isRubbing() ? CONTRACTING_FRICTION_FACTOR : INACTIVE_FRICTION_FACTOR;
    }

    public boolean isRubbing() {
        return membrane.isContracting();
    }

    public PVector getVelocity(){
      return   pointParticle.getVelocity();
    }

    public PVector getPosition() {
       return pointParticle.getPosition();
    }
}
