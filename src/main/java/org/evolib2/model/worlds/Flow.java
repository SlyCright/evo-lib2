package org.evolib2.model.worlds;

import processing.core.PVector;

import java.util.List;

public class Flow {

    public final static float FLOW_FORCE_MAGNITUDE = .05f;

    private static PVector flowForceVector = new PVector(FLOW_FORCE_MAGNITUDE, 0f);

    public static void affect(List<Specimen> specimens) {
        specimens.forEach(s -> s.getNodes().forEach(n -> {
            n.applyForce(flowForceVector);
        }));
    }
}
