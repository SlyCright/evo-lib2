package org.evolib2.controller;

import lombok.Getter;
import org.evolib2.model.worlds.World;
import org.evolib2.view.Visualizer;

public class Simulation implements Ticker {

    World world = new World();

    @Getter
    Visualizer visualizer = new Visualizer(world);

    public void start() {/*todo*/}

    @Override
    public void doTick() {
        world.doTick();
    }
}
