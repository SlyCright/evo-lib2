package org.evolib2.view;

import org.evolib2.model.worlds.Specimen;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpecimenDecorator implements Displayable {

    public static final boolean IS_NO_STROKE_POSITION_MARK = true;

    public final static int POSITION_MARK_HUE = 60; //  of 360

    public final static float POSITION_MARK_DIAMETER_START = 2.5f;

    public final static float POSITION_MARK_DIAMETER_END = 1f;

    public final static int POSITION_MARKS_TOTAL = 500;

    private final Specimen specimen;

    private List<PVector> positions = new ArrayList<>();

    private Random random = new Random();

    private int counter = 0;

    SpecimenDecorator(Specimen specimen) {
        this.specimen = specimen;
    }

    @Override
    public List<ShapeDto> getShapeDtos() {

        List<ShapeDto> shapeDtos = new ArrayList<>();

        positions.add(0, this.specimen.getPosition().copy());

        if (positions.size() > POSITION_MARKS_TOTAL) {
            positions.remove(positions.size() - 1);
        }

        for (int i = 0; i < positions.size(); i++) {

            PVector vector = positions.get(i);

//            vector.x += (random.nextFloat() - 0.5f) * .50f;

//            vector.y += (random.nextFloat() - 0.5f) * .50f;

            shapeDtos.add(new CircleDto(
                    IS_NO_STROKE_POSITION_MARK,
                    new ColorDto(POSITION_MARK_HUE,
                            SpecimenComponentDecorator.ACTIVE_SATURATION, //todo refactor
                            SpecimenComponentDecorator.ACTIVE_BRIGHTNESS,
                            Math.round(PApplet.map(
                                    (float) i,
                                    0f,
                                    (float) positions.size(),
                                    255f,
                                    0f))),
                    vector,
                    PApplet.map(
                            (float) i,
                            0f,
                            (float) positions.size(),
                            (float) POSITION_MARK_DIAMETER_START,
                            (float) POSITION_MARK_DIAMETER_END)));
        }

        return shapeDtos;
    }
}
