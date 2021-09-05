package org.evolib2.model.worlds;

import lombok.Getter;
import lombok.Setter;

public abstract class Sensor extends SpecimenComponent {

    @Getter
    private float signal;

    @Setter
    @Getter
    private SNeuron sNeuron;

    @Override
    public void doTick() {
        signal = measureSignal();
        sNeuron.setSignal(signal);
    }

    abstract protected float measureSignal();
}
