package org.evolib2.view;

import processing.core.PVector;

public class LineDto extends ShapeDto {

    public float strokeWeight;
    public final PVector pointA;
    public final PVector pointB;

    public LineDto(float strokeWeight, ColorDto color, PVector PointA, PVector PointB) {
        super(false, color);
        this.strokeWeight = strokeWeight;
        pointA = PointA;
        pointB = PointB;
    }

}
