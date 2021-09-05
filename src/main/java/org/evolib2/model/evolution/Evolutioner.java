package org.evolib2.model.evolution;

import org.evolib2.model.worlds.Specimen;
import org.evolib2.model.worlds.SpecimensFactory;

public class Evolutioner {

    public static Specimen makeOffspringOf(Specimen ancestor) {
        Specimen offspring = clone(ancestor);
        return mutate(offspring);
    }

    private static Specimen clone(Specimen ancestor) {
        return SpecimensFactory.create(1).get(0);
    }

    private static Specimen mutate(Specimen offspring) {
        return offspring;
    }
}
