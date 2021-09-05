package org.evolib2.model.worlds;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class SNeuron extends Neuron {

    @Getter
    private final List<Connection> outputConnections = new ArrayList<>();

    @Setter
    @Getter
    private Sensor sensor;

    @Override
    public void doTick() {
        signal = sensor.getSignal();
    }
}
