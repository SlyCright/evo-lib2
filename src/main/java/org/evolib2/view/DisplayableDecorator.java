package org.evolib2.view;

import processing.core.PApplet;
import processing.core.PVector;

public abstract class DisplayableDecorator implements Displayable {

    protected static final int ACTIVE_SATURATION = 75; //of 100

    protected static final int ACTIVE_BRIGHTNESS = 75; //of 100

    protected static final int ACTIVE_ALPHA = 255; //of 255

    protected static final int INACTIVE_SATURATION = 50; //of 100

    protected static final int INACTIVE_BRIGHTNESS = 50; //of 100

    protected static final int INACTIVE_ALPHA = Math.round(255f / 4f * 3f); //of 255

    abstract protected PVector getPosition();

    abstract protected void applyForce(PVector force);

    public int mapToSaturation(float signal) {
        return Math.round(PApplet.map(signal, 0f, 1f, INACTIVE_SATURATION, ACTIVE_SATURATION));
    }

    public int mapToBrightness(float signal) {
        return Math.round(PApplet.map(signal, 0f, 1f, INACTIVE_BRIGHTNESS, ACTIVE_BRIGHTNESS));
    }

    public int mapToAlpha(float signal) {
        return Math.round(PApplet.map(signal, 0f, 1f, INACTIVE_ALPHA, ACTIVE_ALPHA));
    }

}
