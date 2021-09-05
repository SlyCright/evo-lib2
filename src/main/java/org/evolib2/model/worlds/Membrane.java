package org.evolib2.model.worlds;

import lombok.Getter;
import lombok.Setter;
import org.evolib2.controller.Adjustable;
import processing.core.PVector;

public class Membrane extends SpecimenComponent {

    @Adjustable
    public final static float NORMAL_LENGTH = 75f;

    @Adjustable
    public final static float STIFFNESS_FACTOR = 0.125f;

    @Adjustable
    public final static float CONTRACTION_FACTOR = 2f;

    private final ContractionTicker contractionTicker = new ContractionTicker(this);

    @Setter
    @Getter
    RNeuron rNeuron;

    @Getter
    private boolean isActive = false;

    @Setter
    @Getter
    private boolean isContracting = false;

    @Setter
    @Getter
    private Node nodeA, nodeB;

    public void doTick() {
        rNeuron.doTick();
        isActive = rNeuron.getSignal() > Neuron.THRESHOLD;
        contractionTicker.doTick();
        PVector membraneElasticForce = calculateElasticForce();
        nodeA.applyForce(membraneElasticForce);
        nodeB.applyForce(membraneElasticForce.mult(-1));
    }

    private PVector calculateElasticForce() {
        PVector membraneVector = getMembraneVector();
        float currentLength = membraneVector.mag();
        float shouldBeLength = isContracting ? NORMAL_LENGTH / CONTRACTION_FACTOR : NORMAL_LENGTH;
        float deformedLengthDelta = currentLength - shouldBeLength;
        float forceMagnitude = STIFFNESS_FACTOR * deformedLengthDelta;
        return membraneVector.copy().normalize().mult(forceMagnitude);
    }

    public PVector getMembraneVector() {
        return PVector.sub(nodeB.getPosition(), nodeA.getPosition());
    }
}
