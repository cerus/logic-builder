package de.cerus.logicbuilder.input.impl;

import de.cerus.logicbuilder.gate.GateConnector;
import de.cerus.logicbuilder.input.Input;
import de.cerus.logicbuilder.misc.BCDConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class BCDInput extends Input {

    private final List<GateConnector> connectors = new ArrayList<>();

    private int num = 0;

    @Override
    public void init(JPanel panel) {
        super.init(panel);

        this.width = 60;
        this.height = 100;

        connectors.add(new GateConnector(x + width - (12 / 2), y + 10, true));
        connectors.add(new GateConnector(x + width - (12 / 2), y + 30, true));
        connectors.add(new GateConnector(x + width - (12 / 2), y + 50, true));
        connectors.add(new GateConnector(x + width - (12 / 2), y + 70, true));
        connectors.forEach(connector -> connector.init(panel));

        updatePosition(100, 100);
    }

    @Override
    public void handleMouseEvent(MouseEvent mouseEvent) {
        String input = JOptionPane.showInputDialog(null, "Please input a number between 0 and 9:",
                "Input", JOptionPane.QUESTION_MESSAGE);
        if (input != null && input.matches("\\d")) {
            num = Integer.parseInt(input);
        }
    }

    @Override
    protected GateConnector createOutputConnector() {
        return new GateConnector(x, y, true);
    }

    @Override
    public void render(Graphics graphics) {
        connectors.forEach(connector -> connector.render(graphics));

        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(x, y, width, height);

        graphics.setColor(Color.BLACK);
        graphics.drawRect(x, y, width, height);

        String str = "Num: " + num;
        graphics.drawString(str, x + (width / 2) - (graphics.getFontMetrics().stringWidth(str) / 2), y + 20);
    }

    @Override
    public void updatePosition(int x, int y) {
        connectors.get(0).updatePosition(x + width - (12 / 2), y + 10);
        connectors.get(1).updatePosition(x + width - (12 / 2), y + 30);
        connectors.get(2).updatePosition(x + width - (12 / 2), y + 50);
        connectors.get(3).updatePosition(x + width - (12 / 2), y + 70);

        this.x = x;
        this.y = y;
    }

    @Override
    public boolean value(int conIndex) {
        boolean[] arr = BCDConverter.convertToBdc(num);

        return arr[conIndex];
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
