package org.evolib2.view;

import lombok.Getter;
import org.evolib2.controller.Adjustable;
import org.evolib2.model.worlds.PointParticle;

import java.util.ArrayList;
import java.util.List;

public abstract class PointParticleDecorator extends DisplayableDecorator {

    @Adjustable
    public final static boolean CIRCLE_NO_STROKE_MODE = true;

    @Getter
    protected final PointParticle pointParticle = new PointParticle();

    @Override
    public List<ShapeDto> getShapeDtos() {

        this.pointParticle.doTick();

        List<ShapeDto> shapeDtos = new ArrayList<>();
        shapeDtos.add(makeCircle());
        return shapeDtos;
    }

    protected abstract CircleDto makeCircle();
}
