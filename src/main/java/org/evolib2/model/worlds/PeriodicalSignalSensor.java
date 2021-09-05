package org.evolib2.model.worlds;

import lombok.Getter;
import lombok.Setter;
import processing.core.PApplet;

public class PeriodicalSignalSensor extends Sensor {

    public final int SIGNAL_PERIOD_TICKS = (int) Math.round(Math.random() * 400);

    private long tickCounter = -1;

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

    protected float measureSignal() {
        tickCounter++;
        return PApplet.cos((float) tickCounter / (float) SIGNAL_PERIOD_TICKS * (float) Math.PI);
    }
}
