package org.evolib2.view;

import org.evolib2.controller.Simulation;
import org.evolib2.model.worlds.SpecimenComponentsFactory;
import org.evolib2.model.worlds.World;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VisualizerTest {

    @Test
    void doTick() {
        Visualizer vis = new Visualizer(new World());

        for (int i = 0; i < 1_000; i++) {

            vis.doTick();

            List<ShapeDto> shapes = vis.getShapeDtos();

            List<CircleDto> circles = shapes.stream()
                    .filter(s -> s instanceof CircleDto)
                    .map(s -> (CircleDto) s)
                    .collect(Collectors.toList());

            List<LineDto> lines = shapes.stream()
                    .filter(s -> s instanceof LineDto)
                    .map(s -> (LineDto) s)
                    .collect(Collectors.toList());

            int circlesShouldBe = World.SPECIMENS_TOTAL * SpecimenComponentsFactory.SEGMENTS_TOTAL;
            int linesShouldBe = World.SPECIMENS_TOTAL * SpecimenComponentsFactory.SEGMENTS_TOTAL;

            assertEquals(circlesShouldBe + linesShouldBe, shapes.size());
            assertEquals(linesShouldBe, lines.size());
            assertEquals(circlesShouldBe, circles.size());
        }
    }

    @Test
    void getShapeDtos() {

        Simulation simulation = new Simulation();
        Visualizer visualizer = simulation.getVisualizer();

        simulation.doTick();
        visualizer.doTick();
        List<ShapeDto> shapeDtos = visualizer.getShapeDtos();

    }
}