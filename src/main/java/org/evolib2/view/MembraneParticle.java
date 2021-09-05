package org.evolib2.view;

import org.evolib2.model.worlds.PointParticle;
import processing.core.PVector;

public class MembraneParticle extends PointParticle {

    MembraneDecorator membraneDecorator;

    MembraneParticle(MembraneDecorator membraneDecorator) {
        this.membraneDecorator = membraneDecorator;
    }

    @Override
    public PVector getPosition() {
        return membraneDecorator.getPosition();
    }

    @Override
    public void applyForce(PVector force) {
    }

}
