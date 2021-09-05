package org.evolib2.model.worlds;

import lombok.Getter;
import org.evolib2.controller.Ticker;
import processing.core.PVector;

public class PointParticle implements Ticker {

    private static final float VELOCITY_LIMIT = 20f;

    private final PVector acceleration = new PVector(0f, 0f);

    @Getter
    private final PVector velocity = new PVector(0f, 0f);

    @Getter
    private final PVector position = new PVector(
            (float) (Math.random() +World.WORLD_WIDTH/2),
            (float) (Math.random() + World.WORLD_HEIGHT)/2);

    public void doTick() {
        velocity.add(acceleration);
        velocity.limit(VELOCITY_LIMIT);
        position.add(velocity);
        acceleration.set(0f, 0f);
    }

    public void applyForce(PVector force) {
        acceleration.add(force);
    }
}
