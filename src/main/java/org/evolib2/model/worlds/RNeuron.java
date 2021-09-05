package org.evolib2.model.worlds;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class RNeuron extends Neuron {

    @Getter
    private final List<Connection> inputConnections = new ArrayList<>();

    @Setter
    @Getter
    private SpecimenComponent specimenComponent;

    @Override
    public void doTick() {
        signal = calculateSignal(inputConnections);
    }
}
