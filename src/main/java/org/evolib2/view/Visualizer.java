package org.evolib2.view;

import lombok.Getter;
import org.evolib2.controller.Ticker;
import org.evolib2.model.worlds.PointParticle;
import org.evolib2.model.worlds.World;

import java.util.ArrayList;
import java.util.List;

public class Visualizer implements Displayable, Ticker {

    public final int BACKGROUND_RED = (int) (Math.random() * 256.0 / 4.0);

    public final int BACKGROUND_GREEN = (int) (Math.random() * 256.0 / 4.0);

    public final int BACKGROUND_BLUE = (int) (Math.random() * 256.0 / 4.0);

    @Getter
    int worldWidth;

    @Getter
    int worldHeight;

    List<Displayable> displayable;

    List<PointParticle> pointParticles;

    List<Ticker> displayableTickers;

    public Visualizer(World world) {
        this.displayable = DisplayablesFactory.createDisplayablesOf(world);
        this.displayableTickers = DisplayablesFactory.extractDispalayableTickers(displayable);
        this.pointParticles = DisplayablesFactory.extractPointParticles(displayableTickers);
        this.worldWidth = World.WORLD_WIDTH;
        this.worldHeight = World.WORLD_HEIGHT;
    }

    @Override
    public void doTick() {
        PointParticlesInteractionForces.affectRepulsion(pointParticles);
        displayableTickers.forEach(Ticker::doTick);
    }

    @Override
    public List<ShapeDto> getShapeDtos() {

        List<ShapeDto> shapeDtos = new ArrayList<>();

        displayable.stream()
                .map(Displayable::getShapeDtos)
                .forEach(shapeDtos::addAll);

        return shapeDtos;
    }
}
