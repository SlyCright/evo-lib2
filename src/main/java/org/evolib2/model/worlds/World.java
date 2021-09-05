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

    private final List<Ticker> tickers = new ArrayList<>();

    @Getter
    private List<Specimen> specimens;

    public World() {
        createSpecimens();
    }

    private void createSpecimens() {
        specimens = SpecimensFactory.create(SPECIMENS_TOTAL);
        tickers.addAll(specimens);
    }

    public void doTick() {

        evaluate(specimens);

        Friction.affect(specimens);
        Flow.affect(specimens);

        for (Ticker ticker : tickers) {
            ticker.doTick();
        }

    }

    private void evaluate(List<Specimen> specimens) {

        int specimensTotal = specimens.size();
        List<Specimen> ancestors = new ArrayList<>();

        specimens.forEach(specimen -> {
            if (specimen.getPosition().x > WORLD_WIDTH) {
                ancestors.add(specimen);
                specimens.remove(specimen);
            }
        });

        if (specimens.size() != 0) {
            while (specimens.size() < specimensTotal) {
                Specimen ancestor = getRandomSpecimenOf(specimens);
                Specimen offspring = Evolutioner.makeOffspringOf(ancestor);
                specimens.add(offspring);
            }
        } else {
            while (specimens.size() < specimensTotal) {
                Specimen ancestor = getRandomSpecimenOf(ancestors);
                Specimen offspring = Evolutioner.makeOffspringOf(ancestor);
                specimens.add(offspring);
            }
        }
    }

    private Specimen getRandomSpecimenOf(List<Specimen> specimens) {
        return specimens.get(new Random().nextInt(specimens.size()));
    }
}
