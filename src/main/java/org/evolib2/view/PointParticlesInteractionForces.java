package org.evolib2.view;

import org.evolib2.controller.Adjustable;
import org.evolib2.model.worlds.PointParticle;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.List;

public class PointParticlesInteractionForces {

    @Adjustable
    public final static float ZERO_FORCE_DISTANCE = 12f;

    @Adjustable
    public final static float REPULSION_FORCE_FACTOR =2f;

    @Adjustable
    public final static float ATTRACTION_FORCE_FACTOR = 2f;

    @Adjustable
    public static final float MIN_STRENGTH_FACTOR = 0.1f;

    @Adjustable
    public static final float MAX_STRENGTH_FACTOR = 1.0f;

    public static void affectRepulsion(List<PointParticle> pointParticles) {
        pointParticles.forEach(particleA -> pointParticles.forEach(particleB -> {

            if (particleA != particleB) {

                PVector positionOfA = particleA.getPosition();
                PVector positionOfB = particleB.getPosition();
                PVector vectorAB = PVector.sub(positionOfB, positionOfA);
                float distanceAB = vectorAB.mag();

                if (distanceAB < ZERO_FORCE_DISTANCE) {

                    float forceMagnitude = REPULSION_FORCE_FACTOR * distanceAB / ZERO_FORCE_DISTANCE - REPULSION_FORCE_FACTOR;
                    PVector force = vectorAB.copy().normalize().mult(forceMagnitude);

                    particleA.applyForce(force);
                }
            }
        }));
    }

    public static void affectAttraction(PointParticle particleA, PointParticle particleB, float connectionStrength) {
        PVector vectorAB = PVector.sub(particleB.getPosition(), particleA.getPosition());
        float distanceAB = vectorAB.mag();
        if (distanceAB > ZERO_FORCE_DISTANCE) {
            float forceMagnitude = ATTRACTION_FORCE_FACTOR * distanceAB / ZERO_FORCE_DISTANCE - ATTRACTION_FORCE_FACTOR;
            float strengthFactor = PApplet.map(connectionStrength, 0f, 1f, MIN_STRENGTH_FACTOR, MAX_STRENGTH_FACTOR); // TODO: 11.08.2021 There is hardcoded values 0f and 1f. Should be defined as max and min constants of connections weight values somewhere else
            PVector force = vectorAB.copy().normalize().mult(forceMagnitude * strengthFactor);
            particleA.applyForce(force);
            particleB.applyForce(force.mult(-1f));
        }
    }
}
