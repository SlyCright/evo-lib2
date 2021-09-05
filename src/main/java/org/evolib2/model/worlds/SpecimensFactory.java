package org.evolib2.model.worlds;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SpecimensFactory {

    public static List<Specimen> create(int specimensTotal) {

        List<Specimen> specimens = IntStream
                .range(0, specimensTotal)
                .mapToObj(i -> buildSpecimen())
                .collect(Collectors.toList());

        return specimens;
    }

    private static Specimen buildSpecimen() {

        List<SpecimenComponent> specimenComponents = new ArrayList<>();
        List<Membrane> membranes = SpecimenComponentsFactory.createMembranesAndBindWith(specimenComponents);
        List<Node> nodes = SpecimenComponentsFactory.createNodesAndBindWith(specimenComponents);
        List<Sensor> sensors = SpecimenComponentsFactory.createSensorsAndBindWith(specimenComponents);
        List<ANeuron> aNeurons = SpecimenComponentsFactory.createNeuronsAndBindWith(specimenComponents);
        List<Connection> connections = SpecimenComponentsFactory.createConnectionsAndBindWith(specimenComponents);

        return new Specimen(specimenComponents, membranes, nodes, sensors, aNeurons, connections);
    }
}
