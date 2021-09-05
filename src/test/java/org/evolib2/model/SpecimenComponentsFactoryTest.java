package org.evolib2.model;

import org.evolib2.model.worlds.Membrane;
import org.evolib2.model.worlds.Node;
import org.evolib2.model.worlds.SpecimenComponent;
import org.evolib2.model.worlds.SpecimenComponentsFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class SpecimenComponentsFactoryTest {

    @Test
    void createNodesAndBindWith() {

        List<SpecimenComponent> specimenComponents = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            specimenComponents.add(new Membrane());
        }

        List<Node> nodes = SpecimenComponentsFactory.createNodesAndBindWith(specimenComponents);

        for (int i = 0; i < specimenComponents.size(); i += 2) {
            Assertions.assertTrue(specimenComponents.get(i) instanceof Node);
            Assertions.assertTrue(specimenComponents.get(i+1) instanceof Membrane);
        }
    }
}