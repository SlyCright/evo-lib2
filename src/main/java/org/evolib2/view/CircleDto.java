package org.evolib2.view;

import processing.core.PVector;

public class CircleDto extends ShapeDto {
    public final PVector center;
    public final float diameter;

    public CircleDto(boolean isNoStroke, ColorDto color, PVector center, float diameter) {
        super(isNoStroke, color);
        this.center = center;
        this.diameter = diameter;
    }
}
