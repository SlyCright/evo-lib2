package org.evolib2.view;

import lombok.Getter;
import org.evolib2.controller.Ticker;
import org.evolib2.model.worlds.Membrane;
import org.evolib2.model.worlds.Neuron;
import org.evolib2.model.worlds.PointParticle;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class MembraneDecorator extends DisplayableDecorator implements NeuronEmbed, Ticker {

    public final static int MEMBRANE_HUE = 0; // 0/3 of 360

    public final static float MEMBRANE_THICKNESS = 4.0f;

    private final Membrane membrane;

    @Getter
    private MembraneParticle membraneParticle = new MembraneParticle(this);

    @Getter
    private List<ConnectionDecorator> inputConnectionDecorators = new ArrayList<>();

    @Getter
    private List<ConnectionDecorator> outputConnectionDecorators = new ArrayList<>();

    public MembraneDecorator(Membrane membrane) {
        this.membrane = membrane;
    }

    @Override
    public List<ShapeDto> getShapeDtos() {

        List<ShapeDto> shapeDtos = new ArrayList<>();

        shapeDtos.add(makeLineOf(membrane));

        return shapeDtos;
    }

    private LineDto makeLineOf(Membrane membrane) {
        return new LineDto(
                MEMBRANE_THICKNESS,
                detectColorOf(membrane),
                membrane.getNodeA().getPosition().copy(),
                membrane.getNodeB().getPosition().copy());
    }

    private ColorDto detectColorOf(Membrane membrane) {

        ColorDto colorDto = membrane.isContracting() ?
                new ColorDto(MEMBRANE_HUE, ACTIVE_BRIGHTNESS, ACTIVE_SATURATION, ACTIVE_ALPHA) :
                new ColorDto(MEMBRANE_HUE, INACTIVE_BRIGHTNESS, INACTIVE_SATURATION, INACTIVE_ALPHA);

        return colorDto;
    }

    @Override
    public Neuron getNeuron() {
        return membrane.getRNeuron();
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
    public PointParticle getPointParticle() {
        return this.membraneParticle;
    }

    @Override
    protected PVector getPosition() {
        PVector position = PVector.sub(membrane.getNodeB().getPosition(), membrane.getNodeA().getPosition());
        position.mult(0.5f);
        position.add(membrane.getNodeA().getPosition());
        return position;
    }

    @Override
    protected void applyForce(PVector force) {
    }

    @Override
    public void doTick() {

    }
}
