package org.evolib2.view;

import org.evolib2.controller.Ticker;
import org.evolib2.model.worlds.Connection;
import org.evolib2.model.worlds.Neuron;
import org.evolib2.model.worlds.PointParticle;
import org.evolib2.model.worlds.World;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DisplayablesFactory {

    public static List<Displayable> createDisplayablesOf(World world) {

        List<Displayable> displayables = generateDisplayables(world);
        bindDecoratorsBetweenConnectionAndNeurons(displayables);

        return displayables;
    }

    @NotNull
    private static List<Displayable> generateDisplayables(World world) {
        List<Displayable> displayables = new ArrayList<>();

        List<ConnectionDecorator> connectionDecorators = new ArrayList<>();

        world.getSpecimens().forEach(specimen -> {
            specimen.getMembranes().forEach(m -> displayables.add(new MembraneDecorator(m)));
            specimen.getNodes().forEach(n -> displayables.add(new NodeDecorator(n)));
            specimen.getSensors().forEach(s -> displayables.add(new SensorDecorator(s)));
            specimen.getANeurons().forEach(an -> displayables.add(new ANeuronDecorator(an)));
            specimen.getConnections().forEach(c -> displayables.add(new ConnectionDecorator(c)));
        });

        return displayables;
    }

    private static void bindDecoratorsBetweenConnectionAndNeurons(List<Displayable> displayables) {
        displayables.stream()
                .filter(d -> d instanceof ConnectionDecorator)
                .map(d -> (ConnectionDecorator) d)
                .forEach(connectionDecorator -> {
                    Connection connection = connectionDecorator.getConnection();
                    NeuronEmbed decoratorWithNeuronForBind;

                    Neuron inputNeuron = connection.getInputNeuron();
                    decoratorWithNeuronForBind = searchDecoratorWithNeuron(inputNeuron, displayables);
                    connectionDecorator.setParticleOfInput(decoratorWithNeuronForBind.getPointParticle());
                    decoratorWithNeuronForBind.addOutputConnectionDecorator(connectionDecorator);

                    Neuron outputNeuron = connection.getOutputNeuron();
                    decoratorWithNeuronForBind = searchDecoratorWithNeuron(outputNeuron, displayables);
                    connectionDecorator.setParticleOfOutput(decoratorWithNeuronForBind.getPointParticle());
                    decoratorWithNeuronForBind.addInputConnectionDecorator(connectionDecorator);
                });
    }

    private static NeuronEmbed searchDecoratorWithNeuron(Neuron neuron, List<Displayable> displayables) {
        return displayables.stream()
                .filter(d -> d instanceof NeuronEmbed)
                .map(d -> (NeuronEmbed) d)
                .filter(ne -> neuron == ne.getNeuron())
                .findFirst()
                .get();
    }

    public static List<Ticker> extractDispalayableTickers(List<Displayable> displayables) {

        List<Ticker> dispalayableTickers = new ArrayList<>();

        displayables.stream()
                .filter(d -> d instanceof ConnectionDecorator)
                .map(d -> (ConnectionDecorator) d)
                .forEach(cd -> dispalayableTickers.add(cd));

        displayables.stream()
                .filter(d -> d instanceof MembraneDecorator)
                .map(d -> (MembraneDecorator) d)
                .forEach(md -> dispalayableTickers.add(md));

        return dispalayableTickers;
    }

    public static List<PointParticle> extractPointParticles(List<Ticker> displayableTickers) {

        HashSet<PointParticle> pointParticlesSet = new HashSet();

        displayableTickers.stream()
                .filter(dt -> dt instanceof ConnectionDecorator)
                .map(dt -> (ConnectionDecorator) dt)
                .forEach(connectionDecorator -> {
                    pointParticlesSet.add(connectionDecorator.getParticleOfInput());
                    pointParticlesSet.add(connectionDecorator.getParticleOfOutput());
                });

        List<PointParticle> pointParticlesList = new ArrayList<>(pointParticlesSet);

        displayableTickers.stream()
                .filter(dt -> dt instanceof MembraneDecorator)
                .map(dt -> (MembraneDecorator) dt)
                .map(md -> md.getMembraneParticle())
                .forEach(mp -> pointParticlesList.add(mp));

        return pointParticlesList;
    }
}
