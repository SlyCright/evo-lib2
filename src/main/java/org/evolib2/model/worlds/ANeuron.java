package org.evolib2.model.worlds;

import lombok.Getter;
import lombok.Setter;
import org.evolib2.view.ANeuronDecorator;

import java.util.ArrayList;
import java.util.List;

public class ANeuron extends Neuron{

    @Getter
    private final List<Connection> inputConnections = new ArrayList<>();

    @Getter
    private final List<Connection> outputConnections = new ArrayList<>();

    @Setter
    @Getter
    private ANeuronDecorator aNeuronDecorator;

    @Override
    public void doTick() {
        signal = calculateSignal(inputConnections);
    }
}
