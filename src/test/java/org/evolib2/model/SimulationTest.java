package org.evolib2.model;

import org.evolib2.controller.Simulation;
import org.evolib2.view.Visualizer;
import org.junit.jupiter.api.Test;

class SimulationTest {

    @Test
    void testSim() {

        Simulation simulation = new Simulation();
        Visualizer visualizer = simulation.getVisualizer();

        simulation.start();

        for (int i = 0; i < 3; i++) {
            simulation.doTick();
            visualizer.doTick();
        }
    }
}
