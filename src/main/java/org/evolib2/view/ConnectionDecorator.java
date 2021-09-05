package org.evolib2.view;

import lombok.Getter;
import lombok.Setter;
import org.evolib2.controller.Adjustable;
import org.evolib2.controller.Ticker;
import org.evolib2.model.worlds.Connection;
import org.evolib2.model.worlds.Friction;
import org.evolib2.model.worlds.Node;
import org.evolib2.model.worlds.PointParticle;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class ConnectionDecorator extends SpecimenComponentDecorator implements Displayable, Ticker {

    @Adjustable
    public final static int CONNECTION_HUE = 360 / 3; // 1/3 of 360

    private static final float neuronFrictionFactor = .1875f * (Node.CONTRACTING_FRICTION_FACTOR + Node.INACTIVE_FRICTION_FACTOR) / 2f;

    @Adjustable
    private static final float WEIGHT_SCALE = 3.5f;

    public final float connectionThickness;

    @Getter
    private final Connection connection;

    @Setter
    @Getter
    PointParticle particleOfInput;

    @Setter
    @Getter
    PointParticle particleOfOutput;

    public ConnectionDecorator(Connection connection) {
        this.connection = connection;
        connectionThickness = connection.getWeight();
    }

    @Override
    public void doTick() {

        Friction.affect(particleOfInput, neuronFrictionFactor);
        Friction.affect(particleOfOutput, neuronFrictionFactor);
        PointParticlesInteractionForces.affectAttraction(particleOfInput, particleOfOutput, connectionThickness);

    }

    @Override
    public List<ShapeDto> getShapeDtos() {

        List<ShapeDto> shapeDtos = new ArrayList<>();

        shapeDtos.add(makeLine());

        return shapeDtos;
    }

    private LineDto makeLine() {
        return new LineDto(
                connectionThickness * WEIGHT_SCALE,
                detectColorOf(connection),
                particleOfInput.getPosition().copy(),
                particleOfOutput.getPosition().copy());
    }

    private ColorDto detectColorOf(Connection connection) {

        return new ColorDto(
                CONNECTION_HUE,
                mapToSaturation(connection.getSignal()),
                mapToBrightness(connection.getSignal()),
                mapToAlpha(connection.getSignal()));
    }

    @Override
    protected PVector getPosition() {
        return PVector
                .sub(particleOfOutput.getPosition(), particleOfInput.getPosition())
                .mult(0.5f)
                .add(particleOfInput.getPosition());
    }

    @Override
    protected void applyForce(PVector force) {
    }
}
