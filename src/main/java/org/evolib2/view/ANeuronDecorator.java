package org.evolib2.view;

import lombok.Getter;
import org.evolib2.controller.Adjustable;
import org.evolib2.model.worlds.ANeuron;
import org.evolib2.model.worlds.Neuron;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class ANeuronDecorator extends PointParticleDecorator implements NeuronEmbed {

    @Adjustable
    public final static int NEURON_HUE = 360 / 3; // 1/3 of 360

    @Adjustable
    public final static float NEURON_DIAMETER = 12f;

    @Getter
    private final ANeuron aNeuron;

    @Getter
    private List<ConnectionDecorator> inputConnectionDecorators = new ArrayList<>();

    @Getter
    private List<ConnectionDecorator> outputConnectionDecorators = new ArrayList<>();

    public ANeuronDecorator(ANeuron aNeuron) {
        this.aNeuron = aNeuron;
        aNeuron.setANeuronDecorator(this);
    }

    @Override
    protected CircleDto makeCircle() {
        return new CircleDto(
                CIRCLE_NO_STROKE_MODE,
                detectColorOf(aNeuron),
                this.getPointParticle().getPosition().copy(),
                NEURON_DIAMETER);
    }

    private ColorDto detectColorOf(ANeuron aNeuron) {

        ColorDto colorDto = aNeuron.getSignal() > Neuron.THRESHOLD ?
                new ColorDto(NEURON_HUE, ACTIVE_BRIGHTNESS, ACTIVE_SATURATION, ACTIVE_ALPHA) :
                new ColorDto(NEURON_HUE, INACTIVE_BRIGHTNESS, INACTIVE_SATURATION, INACTIVE_ALPHA);

        return colorDto;
    }

    @Override
    public Neuron getNeuron() {
        return aNeuron;
    }

    @Override
    public void addInputConnectionDecorator(ConnectionDecorator connectionDecorator) {
        inputConnectionDecorators.add(connectionDecorator);
    }

    @Override
    public void addOutputConnectionDecorator(ConnectionDecorator connectionDecorator) {
        outputConnectionDecorators.add(connectionDecorator);
    }

    @Override
    protected PVector getPosition() {
        return this.pointParticle.getPosition();
    }

    @Override
    protected void applyForce(PVector force) {
        this.pointParticle.applyForce(force);
    }
}
