package de.cerus.logicbuilder.gate.impl;

import de.cerus.logicbuilder.gate.GateConnector;
import de.cerus.logicbuilder.gate.MultiGate;
import de.cerus.logicbuilder.misc.BCDConverter;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class BCDToSevenSegmentGate extends MultiGate {

    @Override
    public void init(JPanel panel) {
        super.init(panel);

        this.width = 60;
        this.height = 150;

        updatePosition(100, 100);
    }

    @Override
    protected List<GateConnector> createConnectors() {
        return Arrays.asList(
                new GateConnector(x - (12 / 2), y + 10, false),
                new GateConnector(x - (12 / 2), y + 30, false),
                new GateConnector(x - (12 / 2), y + 50, false),
                new GateConnector(x - (12 / 2), y + 70, false),
                new GateConnector(x + width - (12 / 2), y + 10, true),
                new GateConnector(x + width - (12 / 2), y + 30, true),
                new GateConnector(x + width - (12 / 2), y + 50, true),
                new GateConnector(x + width - (12 / 2), y + 70, true),
                new GateConnector(x + width - (12 / 2), y + 90, true),
                new GateConnector(x + width - (12 / 2), y + 110, true),
                new GateConnector(x + width - (12 / 2), y + 130, true)
        );
    }

    @Override
    public boolean calculate(GateConnector connector) {
        boolean[] arr = BCDConverter.convertToSevenSegment(
                connectors.get(0).value(),
                connectors.get(1).value(),
                connectors.get(2).value(),
                connectors.get(3).value()
        );

        int index = connectors.indexOf(connector);
        return arr[index - 4];
    }

    @Override
    protected void renderGate(Graphics graphics) {
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(x, y, width, height);

        graphics.setColor(Color.BLACK);
        graphics.drawRect(x, y, width, height);

        String str = "BCD to";
        graphics.drawString(str, x + (width / 2) - (graphics.getFontMetrics().stringWidth(str) / 2), y + 20);
        str = "7 Segment";
        graphics.drawString(str, x + (width / 2) - (graphics.getFontMetrics().stringWidth(str) / 2), y + 40);
        str = "display";
        graphics.drawString(str, x + (width / 2) - (graphics.getFontMetrics().stringWidth(str) / 2), y + 60);

        str = "In";
        graphics.drawString(str, x + 5, y + 120);
        str = "Out";
        graphics.drawString(str, x + width - (graphics.getFontMetrics().stringWidth(str)) - 5, y + 120);
    }

    @Override
    public void updatePosition(int x, int y) {
        for (int i = 0; i < connectors.size(); i++) {
            if (i <= 3) {
                connectors.get(i).updatePosition(x - (12 / 2), y + 10 + (20 * i));
            } else {
                connectors.get(i).updatePosition(x + width - (12 / 2), y + 10 + (20 * (i - 4)));
            }
        }

        this.x = x;
        this.y = y;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

}
