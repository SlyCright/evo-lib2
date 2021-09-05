package org.evolib2.model.worlds;

import lombok.Getter;
import org.evolib2.controller.Ticker;

public class Connection extends SpecimenComponent implements Ticker {

    @Getter
    private final Neuron inputNeuron;

    @Getter
    private final float weight;

    @Getter
    private final Neuron outputNeuron;

    @Getter
    private float signal = 0f;

    public Connection(Neuron inputNeuron, float weight, Neuron outputNeuron) {
        this.inputNeuron = inputNeuron;
        this.weight = weight;
        this.outputNeuron = outputNeuron;
    }

    @Override
    public void doTick() {
        signal = inputNeuron.getSignal() * weight;
    }
}
