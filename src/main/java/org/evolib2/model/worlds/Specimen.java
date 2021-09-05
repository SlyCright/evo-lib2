package org.evolib2.model.worlds;

import lombok.Getter;
import org.evolib2.controller.Ticker;
import processing.core.PVector;

import java.util.List;

public class Specimen implements Ticker {

    private static final int ITERATIONS_OF_NEURAL_NETWORK_PER_TICK = 1;

    @Getter
    private final List<SpecimenComponent> specimenComponents;

    @Getter
    private final List<Membrane> membranes;

    @Getter
    private final List<Node> nodes;

    @Getter
    private final List<Sensor> sensors;

    @Getter
    private final List<ANeuron> aNeurons;

    @Getter
    private final List<Connection> connections;

    @Getter
    PVector position = new PVector();

    public Specimen(
            List<SpecimenComponent> specimenComponents,
            List<Membrane> membranes,
            List<Node> nodes,
            List<Sensor> sensors,
            List<ANeuron> aNeurons,
            List<Connection> connections) {

        this.specimenComponents = specimenComponents;
        this.membranes = membranes;
        this.nodes = nodes;
        this.sensors = sensors;
        this.aNeurons = aNeurons;
        this.connections = connections;
    }

    @Override
    public void doTick() {

        sensors.forEach(Sensor::doTick);

        for (int i = 0; i < ITERATIONS_OF_NEURAL_NETWORK_PER_TICK; i++) {
            connections.forEach(Connection::doTick);
            aNeurons.forEach(Neuron::doTick);
        }

        InternalPressure.affect(membranes);
        membranes.forEach(Membrane::doTick);

        nodes.forEach(Node::doTick);

        updatePosition();
    }

    private void updatePosition() {

        float x = 0f;
        float y = 0f;

        for(Node node: this.nodes){
            x+=node.getPosition().x;
            y+=node.getPosition().y;
        }

        x/=(float) nodes.size();
        y/=(float) nodes.size();

        position.x=x;
        position.y=y;
    }
}
