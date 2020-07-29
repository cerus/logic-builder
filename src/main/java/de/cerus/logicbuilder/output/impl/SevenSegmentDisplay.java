package de.cerus.logicbuilder.output.impl;

import de.cerus.logicbuilder.gate.GateConnector;
import de.cerus.logicbuilder.output.Output;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SevenSegmentDisplay extends Output {

    private final List<GateConnector> connectors = new ArrayList<>();

    @Override
    public void init(JPanel panel) {
        super.init(panel);

        this.width = 120;
        this.height = 200;

        for (int i = 0; i < 7; i++) {
            connectors.add(new GateConnector(x - (12 / 2), y + 10 + (i * 20), false));
        }
        connectors.forEach(connector -> connector.init(panel));

        updatePosition(100, 100);
    }

    @Override
    protected GateConnector createInputConnector() {
        return new GateConnector(x, y, false);
    }

    @Override
    public void render(Graphics graphics) {
        connectors.forEach(connector -> connector.render(graphics));

        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(x, y, width, height);

        graphics.setColor(Color.BLACK);
        graphics.drawRect(x, y, width, height);

        for (int i = 0; i < 7; i++) {
            drawSegment(i, graphics);
        }
    }

    private void drawSegment(int i, Graphics graphics) {
        GateConnector gateConnector = connectors.get(i);
        boolean val = gateConnector.value();

        graphics.setColor(val ? Color.RED : new Color(172, 172, 172));

        switch (i) {
            case 0: // a
                graphics.fillRect(x + 30, y + 10, 60, 10);
                break;
            case 1: // b
                graphics.fillRect(x + 30 + 60, y + 20, 10, 60);
                break;
            case 2: // c
                graphics.fillRect(x + 30 + 60, y + 20 + 60 + 10, 10, 60);
                break;
            case 3: // d
                graphics.fillRect(x + 30, y + 20 + 60 + 10 + 60, 60, 10);
                break;
            case 4: // e
                graphics.fillRect(x + 20, y + 20 + 60 + 10, 10, 60);
                break;
            case 5: // f
                graphics.fillRect(x + 20, y + 20, 10, 60);
                break;
            case 6: // g
                graphics.fillRect(x + 30, y + 20 + 60, 60, 10);
                break;
        }
    }

    @Override
    public void updatePosition(int x, int y) {
        for (int i = 0; i < 7; i++) {
            connectors.get(i).updatePosition(x - (12 / 2), y + 10 + (i * 20));
        }

        this.x = x;
        this.y = y;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    @Override
    public List<GateConnector> getConnectors() {
        return connectors;
    }

}
