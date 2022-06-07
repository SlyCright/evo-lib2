package org.evolib2.model.worlds;

import org.jetbrains.annotations.NotNull;
import processing.core.PVector;

import java.util.List;

public class Friction {// TODO: 11.08.2021 refactor: friction should be applied to particle points only

    public static void affect(List<Specimen> specimens) {
        for (Specimen specimen : specimens) {
            List<Node> nodes = specimen.getNodes();
            for (Node node : nodes) {
                PointParticle pointParticle = node.getPointParticle();
                affect(pointParticle, node.getFrictionFactor());
            }
        }
    }

    public static void affect(@NotNull PointParticle pointParticle, float frictionFactor) {
        PVector velocity = pointParticle.getVelocity();
        float velocityMagnitude = velocity.mag();
        float frictionForceMagnitude = frictionFactor * velocityMagnitude;
        PVector frictionForce = velocity.copy().normalize().mult(-1 * frictionForceMagnitude);
        pointParticle.applyForce(frictionForce);
    }
}
