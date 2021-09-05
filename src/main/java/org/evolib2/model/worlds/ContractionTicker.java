package org.evolib2.model.worlds;

import org.evolib2.controller.Ticker;

public class ContractionTicker  implements Ticker { //todo refactor: Too complicated. Can be made with a sin function
    final private int contractionTicks = (int) (Math.random() * 500);

    private int ticksCounter = contractionTicks;
    private Membrane membrane;
    private boolean isTickerStarted = false;

    public ContractionTicker(Membrane membrane) {
        this.membrane = membrane;
    }

    public void doTick() {

        if (!isTickerStarted) {
            if (membrane.isActive()) {
                isTickerStarted = true;
            }
        }

        if (isTickerStarted) {
            ticksCounter--;
            if (ticksCounter < -contractionTicks) {
                ticksCounter = contractionTicks;
                isTickerStarted = false;
            }
        }

        if (membrane.isActive()) {

            if (ticksCounter >= 0) {
                if (!membrane.isContracting()) {
                    membrane.setContracting(true);
                }
            }

            if (ticksCounter < 0) {
                if (membrane.isContracting()) {
                    membrane.setContracting(false);
                }
            }
        }

    }
}

