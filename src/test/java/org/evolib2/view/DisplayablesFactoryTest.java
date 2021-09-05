package org.evolib2.view;

import org.evolib2.controller.Ticker;
import org.evolib2.model.worlds.PointParticle;
import org.evolib2.model.worlds.World;
import org.junit.jupiter.api.Test;

import java.util.List;

class DisplayablesFactoryTest {

    @Test
    void extractPointParticles() {

        List<Displayable> displayable;

        List<PointParticle> pointParticles;

        List<Ticker> displayableTickers;

        displayable = DisplayablesFactory.createDisplayablesOf(new World());
        displayableTickers = DisplayablesFactory.extractDispalayableTickers(displayable);
        pointParticles = DisplayablesFactory.extractPointParticles(displayableTickers);
    }
}