package org.evolib2.model.worlds;

import processing.core.PConstants;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SpecimenComponentsFactory {

    public static final int SEGMENTS_TOTAL = 9;

    public static final int A_NEURONS_TOTAL = 9;

    //public static final float INITIAL_CONNECTION_WEIGHT = 1f;

    public static List<Membrane> createMembranesAndBindWith(List<SpecimenComponent> components) {

        List<Membrane> membranes = new ArrayList<>();

        for (int i = 0; i < SEGMENTS_TOTAL; i++) {
            Membrane membrane = new Membrane();
            membranes.add(membrane);
            components.add(membrane);
        }

        return membranes;
    }

    public static List<Node> createNodesAndBindWith(List<SpecimenComponent> components) {

        List<Node> nodes = new ArrayList<>();

        for (int i = components.size() - 1; i >= 0; i--) {
            SpecimenComponent specimenComponent = components.get(i);
            if (specimenComponent instanceof Membrane) {
                Node node = new Node();
                nodes.add(node);
                components.add(i, node);
            }
        }

        makeLinksBetweenMembranesAndNodes(components);

        return nodes;
    }

    private static void makeLinksBetweenMembranesAndNodes(List<SpecimenComponent> components) {

        int size = components.size();

        linkMembraneNodeMembrane(
                (Membrane) components.get(size - 1),
                (Node) components.get(0),
                (Membrane) components.get(1));

        for (int i = 1; i < size - 1; i++) {

            SpecimenComponent specimenComponent = components.get(i);

            if (specimenComponent instanceof Membrane) {
                Node nodeA = (Node) components.get(i - 1);
                Membrane membrane = (Membrane) components.get(i);
                Node nodeB = (Node) components.get(i + 1);
                linkNodeMembraneNode(nodeA, membrane, nodeB);
            }

            if (specimenComponent instanceof Node) {
                Membrane membraneA = (Membrane) components.get(i - 1);
                Node node = (Node) components.get(i);
                Membrane membraneB = (Membrane) components.get(i + 1);
                linkMembraneNodeMembrane(membraneA, node, membraneB);
            }

        }

        linkNodeMembraneNode(
                (Node) components.get(size - 2),
                (Membrane) components.get(size - 1),
                (Node) components.get(0)
        );

    }

    private static void linkNodeMembraneNode(Node nodeA, Membrane membrane, Node nodeB) {
        membrane.setNodeA(nodeA);
        membrane.setNodeB(nodeB);
    }

    private static void linkMembraneNodeMembrane(Membrane membraneA, Node node, Membrane membraneB) {
        node.setMembrane(Math.random() < 0.5 ? membraneA : membraneB);
    }

    public static List<Sensor> createSensorsAndBindWith(List<SpecimenComponent> components) {

        List<PointParticle> pointParticles = new ArrayList<>();
        pointParticles = components.stream()
                .filter(c -> c instanceof Node)
                .map(c -> (Node) c)
                .map(Node::getPointParticle)
                .collect(Collectors.toList());

        PointParticle particleA = pointParticles.get(0);
        PointParticle particleB = pointParticles.get(pointParticles.size() / 2);

        List<Sensor> sensors = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            float angle = i * PConstants.PI / 2f;
            PVector orientationVector = new PVector(1f, 0f);
            orientationVector.rotate(angle);

            OrientationSensor orientationSensor = new OrientationSensor(orientationVector, particleA, particleB);

            sensors.add(orientationSensor);
            components.add(orientationSensor);
        }

        return sensors;
    }

    public static List<ANeuron> createNeuronsAndBindWith(List<SpecimenComponent> components) {
        components.addAll(createEmbeddedNeurons(components));
        List<ANeuron> aNeurons = CreateANeurons();
        components.addAll(aNeurons);
        return aNeurons;
    }

    private static List<Neuron> createEmbeddedNeurons(List<SpecimenComponent> components) {

        List<Neuron> neurons = new ArrayList<>();

        for (SpecimenComponent component : components) {

            if (component instanceof Membrane) {
                Membrane membrane = (Membrane) component;
                RNeuron rNeuron = new RNeuron();
                membrane.setRNeuron(rNeuron);
                rNeuron.setSpecimenComponent(membrane);
                neurons.add(rNeuron);
            }

            if (component instanceof Sensor) {
                Sensor sensor = (Sensor) component;
                SNeuron sNeuron = new SNeuron();
                sensor.setSNeuron(sNeuron);
                sNeuron.setSensor(sensor);
                neurons.add(sNeuron);
            }
        }

        return neurons;
    }

    public static List<ANeuron> CreateANeurons() {

        List<ANeuron> ANeurons = new ArrayList<>();

        for (int i = 0; i < A_NEURONS_TOTAL; i++) {
            ANeurons.add(new ANeuron());
        }

        return ANeurons;
    }

    public static List<Connection> createConnectionsAndBindWith(List<SpecimenComponent> components) {
        List<Connection> connections = new ArrayList<>();
        connections.addAll(createSensorsToNeuronsConnections(components));
        connections.addAll(createNeuronsToMembranesConnections(components));
        return connections;
    }

    private static List<Connection> createSensorsToNeuronsConnections(List<SpecimenComponent> components) {

        List<Connection> connections = new ArrayList<>();

        List<ANeuron> aNeurons = components.stream()
                .filter(c -> c instanceof ANeuron)
                .map(c -> (ANeuron) c)
                .collect(Collectors.toList());

        List<SNeuron> sNeurons = components.stream()
                .filter(c -> c instanceof SNeuron)
                .map(c -> (SNeuron) c)
                .collect(Collectors.toList());

        aNeurons.forEach(aNeuron ->
                sNeurons.forEach(sNeuron -> {
                    Connection connection = new Connection(sNeuron, (float) Math.random()/*INITIAL_CONNECTION_WEIGHT*/, aNeuron);
                    sNeuron.getOutputConnections().add(connection);
                    aNeuron.getInputConnections().add(connection);
                    connections.add(connection);
                }));

        return connections;
    }

    private static List<Connection> createNeuronsToMembranesConnections(List<SpecimenComponent> components) {

        List<Connection> connections = new ArrayList<>();

        List<ANeuron> aNeurons = components.stream()
                .filter(c -> c instanceof ANeuron)
                .map(c -> (ANeuron) c)
                .collect(Collectors.toList());

        int aNeuronsTotal = aNeurons.size();
        int aNeuronIndex = 0;

        for (SpecimenComponent component : components) {

            if (component instanceof RNeuron) {

                RNeuron rNeuron = (RNeuron) component;
                ANeuron aNeuron = aNeurons.get(aNeuronIndex);
                Connection connection = new Connection(aNeuron, (float) Math.random()/*INITIAL_CONNECTION_WEIGHT*/, rNeuron);
                connections.add(connection);
                aNeuron.getOutputConnections().add(connection);
                rNeuron.getInputConnections().add(connection);

                aNeuronIndex++;
                if (aNeuronIndex > aNeuronsTotal - 1) {
                    aNeuronIndex = 0;
                }
            }
        }

        return connections;
    }
}
