package org.evolib2.view;

public abstract class ShapeDto {
    public boolean isNoStroke;
    public ColorDto colorDto;

    public ShapeDto(boolean isNoStroke, ColorDto colorDto) {
        this.isNoStroke = isNoStroke;
        this.colorDto = colorDto;
    }
}
