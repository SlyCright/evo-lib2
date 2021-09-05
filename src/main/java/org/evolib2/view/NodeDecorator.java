package org.evolib2.view;

import org.evolib2.controller.Adjustable;
import org.evolib2.model.worlds.Node;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class NodeDecorator extends SpecimenComponentDecorator {

    @Adjustable
    public final static int NODE_HUE = 0; // 0/3 of 360

    @Adjustable
    public final static boolean NODE_NO_STROKE_MODE = true;

    @Adjustable
    public final static float NODE_DIAMETER = 12f;

    private final Node node;

    public NodeDecorator(Node node) {
        this.node = node;
    }

    @Override
    public List<ShapeDto> getShapeDtos() {
        List<ShapeDto> shapeDtos = new ArrayList<>();
        shapeDtos.add(makeCircleOf(node));
        return shapeDtos;
    }

    private CircleDto makeCircleOf(Node node) {
        return new CircleDto(
                NODE_NO_STROKE_MODE,
                detectColorOf(node),
                node.getPosition().copy(),
                NODE_DIAMETER);
    }

    private ColorDto detectColorOf(Node node) {

        ColorDto colorDto = node.isRubbing() ?
                new ColorDto(NODE_HUE, ACTIVE_SATURATION, ACTIVE_BRIGHTNESS, ACTIVE_ALPHA) :
                new ColorDto(NODE_HUE, INACTIVE_SATURATION, INACTIVE_BRIGHTNESS, INACTIVE_ALPHA);

        return colorDto;
    }

    @Override
    protected PVector getPosition() {
        return node.getPosition();
    }

    @Override
    protected void applyForce(PVector force) {
    }
}
