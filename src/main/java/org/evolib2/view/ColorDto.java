package org.evolib2.view;

public class ColorDto {
    public int hue;
    public int saturation;
    public int brightness;
    public int alpha;

    public ColorDto(int hue, int saturation, int brightness, int alpha) {
        this.hue = hue;
        this.saturation = saturation;
        this.brightness = brightness;
        this.alpha = alpha;
    }

    public ColorDto() {
        this(360, 100, 100, 255);
    }
}
