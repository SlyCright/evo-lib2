package org.evolib2.model.worlds;

import lombok.Getter;
import org.evolib2.controller.Ticker;
import org.evolib2.model.evolution.Evolutioner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World implements Ticker {

    public static final int WORLD_WIDTH = 999;

    public static final int WORLD_HEIGHT = 666;

    public static final int SPECIMENS_TOTAL = 3;

    @Getter
    private List<Specimen> specimens;

    @Getter
    boolean isUpdateNeeded=false;

    public World() {
        createSpecimens();
    }

    private void createSpecimens() {
        specimens = SpecimensFactory.create(SPECIMENS_TOTAL);
    }

    public void doTick() {

        specimens = evaluate(specimens);

        Friction.affect(specimens);
        Flow.affect(specimens);

        specimens.forEach(Specimen::doTick);
    }

    private List<Specimen> evaluate(List<Specimen> specimens) {

        List<Specimen> specimensToCheck = specimens;
        specimens = new ArrayList<>();

        for (Specimen specimen : specimensToCheck) {
            if (specimen.getPosition().x > WORLD_WIDTH) {
                Random rand = new Random();
                Specimen specimenA = specimensToCheck.get(rand.nextInt(specimensToCheck.size()));
                Specimen specimenB = specimensToCheck.get(rand.nextInt(specimensToCheck.size()));
                Specimen winner = specimenA.getFitness() > specimenB.getFitness() ? specimenA : specimenB;
                specimen = Evolutioner.makeOffspringOf(winner);
                isUpdateNeeded=true;
            }
            specimens.add(specimen);
        }

        return specimens;
    }
}
