package de.cerus.logicbuilder.gate.impl;

import de.cerus.logicbuilder.gate.GateConnector;
import de.cerus.logicbuilder.gate.MultiGate;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.List;

public class SplitterGate extends MultiGate {

    @Override
    public void init(JPanel panel) {
        super.init(panel);

        this.width = 40;
        this.height = 60;

        updatePosition(100, 100);
    }

    @Override
    protected List<GateConnector> createConnectors() {
        return Arrays.asList(
                new GateConnector(x - 20 - (12 / 2), y + (this.height / 2) - (12 / 2), false),
                new GateConnector(x + this.width + (12 / 2), y + 10 - (12 / 2), true),
                new GateConnector(x + this.width + (12 / 2), y + this.height - 10 - (12 / 2), true)
        );
    }

    @Override
    public boolean calculate(GateConnector connector) {
        GateConnector in = connectors.get(0);
        if (in.getState() == GateConnector.State.NOT_CONNECTED) {
            return false;
        }

        return in.value();
    }

    @Override
    protected void renderGate(Graphics graphics) {
        int[] xes = {
                this.x + this.width,
                this.x + this.width,
                this.x - 20,
        };
        int[] yes = {
                this.y,
                this.y + this.height,
                this.y + (this.height / 2),
        };

        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillPolygon(xes, yes, xes.length);

        graphics.setColor(Color.BLACK);
        graphics.drawPolygon(xes, yes, xes.length);

        String str = "Split";
        Rectangle2D bounds = graphics.getFontMetrics().getStringBounds(str, graphics);
        graphics.drawString(str, this.x + ((int) (bounds.getWidth() / 2)) - 5,
                this.y + (this.height / 2) + ((int) (bounds.getHeight() / 2)));
    }

    @Override
    public void updatePosition(int x, int y) {
        connectors.get(0).updatePosition(x - 20 - (12 / 2), y + (this.height / 2) - (12 / 2));
        connectors.get(1).updatePosition(x + this.width - (12 / 3), y + 10 - (12 / 2));
        connectors.get(2).updatePosition(x + this.width - (12 / 3), y + this.height - 10 - (12 / 2));

        this.x = x;
        this.y = y;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

}
