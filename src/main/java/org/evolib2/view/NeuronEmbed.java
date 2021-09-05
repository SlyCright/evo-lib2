package org.evolib2.view;

import org.evolib2.model.worlds.Neuron;
import org.evolib2.model.worlds.PointParticle;

import java.util.List;

public interface NeuronEmbed {

    Neuron getNeuron();

    void addInputConnectionDecorator(ConnectionDecorator connectionDecorator);

    void addOutputConnectionDecorator(ConnectionDecorator connectionDecorator);

    List<ConnectionDecorator> getInputConnectionDecorators();

    List<ConnectionDecorator> getOutputConnectionDecorators();

    PointParticle getPointParticle();
}
