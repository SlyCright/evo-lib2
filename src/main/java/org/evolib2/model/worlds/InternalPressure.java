package org.evolib2.model.worlds;

import processing.core.PVector;

import java.util.List;


public class InternalPressure {
    public static final float INTERNAL_PRESSURE_FORCE_MAGNITUDE = 0.0075f;

    public static void affect(List<Membrane> membranes) {
        for (Membrane membrane : membranes) {
            PVector membraneVector = membrane.getMembraneVector();
            PVector pressureForce = membraneVector.
                    copy().
                    normalize().
                    rotate((float) (Math.PI / 2.0)).
                    mult(INTERNAL_PRESSURE_FORCE_MAGNITUDE * membraneVector.mag());
            membrane.getNodeA().applyForce(pressureForce);
            membrane.getNodeB().applyForce(pressureForce);
        }
    }
}
