package org.evolib2.view;

import lombok.Getter;
import org.evolib2.controller.Adjustable;
import org.evolib2.model.worlds.Neuron;
import org.evolib2.model.worlds.Sensor;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class SensorDecorator extends PointParticleDecorator implements NeuronEmbed {

    @Adjustable
    public final static int SENSOR_HUE = 360 * 2 / 3; // 2/3 of 360

    @Adjustable
    public final static float SENSOR_DIAMETER = 15f;

    @Getter
    private final Sensor sensor;

    @Getter
    private List<ConnectionDecorator> inputConnectionDecorators = new ArrayList<>();

    @Getter
    private List<ConnectionDecorator> outputConnectionDecorators = new ArrayList<>();

    public SensorDecorator(Sensor sensor) {
        this.sensor = sensor;
    }

    @Override
    protected CircleDto makeCircle() {

        CircleDto circleDto = new CircleDto(
                CIRCLE_NO_STROKE_MODE,
                new ColorDto(
                        SENSOR_HUE,
                        mapToSaturation(sensor.getSignal()),
                        mapToBrightness(sensor.getSignal()),
                        mapToAlpha(sensor.getSignal())),
                this.getPointParticle().getPosition().copy(),
                SENSOR_DIAMETER);
        return circleDto;
    }

    @Override
    public Neuron getNeuron() {
        return sensor.getSNeuron();
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
        return super.pointParticle.getPosition();
    }

    @Override
    protected void applyForce(PVector force) {
        super.pointParticle.applyForce(force);
    }
}
