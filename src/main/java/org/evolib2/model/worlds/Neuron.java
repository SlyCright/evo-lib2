package org.evolib2.model.worlds;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public abstract class Neuron extends SpecimenComponent {

    public static final float THRESHOLD = 0.5f;

    @Setter
    @Getter
    protected float signal = 0f;

    protected float calculateSignal(List<Connection> inputConnections) {

        float signal = 0f;

        for (Connection connection : inputConnections) {
            signal += connection.getSignal();
        }

        signal /= inputConnections.size();
        signal = signal > THRESHOLD ? 1f : 0f;

        return signal;
    }
}
