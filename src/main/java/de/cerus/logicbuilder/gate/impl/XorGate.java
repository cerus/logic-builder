package de.cerus.logicbuilder.gate.impl;

import de.cerus.logicbuilder.gate.DoubleInputGate;
import de.cerus.logicbuilder.gate.GateConnector;

import javax.swing.*;
import java.awt.*;

public class XorGate extends DoubleInputGate {

    @Override
    public void init(JPanel panel) {
        super.init(panel);

        this.width = 50;
        this.height = 50;

        updatePosition(100, 100);
    }

    @Override
    protected GateConnector createFirstInputGate() {
        return new GateConnector(x - (12 / 2), y + 10 - (12 / 2), false);
    }

    @Override
    protected GateConnector createSecondInputGate() {
        return new GateConnector(x - (12 / 2), y + this.height - 10 - (12 / 2), false);
    }

    @Override
    protected GateConnector createOutputGate() {
        return new GateConnector(x + this.width - (12 / 2), y + (this.height / 2) - (12 / 2), true);
    }

    @Override
    public boolean calculate(boolean a, boolean b) {
        return a ^ b;
    }

    @Override
    protected void renderGate(Graphics graphics) {
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(x, y, width, height);

        graphics.setColor(Color.BLACK);
        graphics.drawRect(x, y, width, height);

        String str = "XOR";
        graphics.drawString(str, x + (width / 2) - (graphics.getFontMetrics().stringWidth(str) / 2), y + 20);
    }

    @Override
    public void updatePosition(int x, int y) {
        firstInputConnector.updatePosition(x - (12 / 2), y + 10 - (12 / 2));
        secondInputConnector.updatePosition(x - (12 / 2), y + this.height - 10 - (12 / 2));
        outputConnector.updatePosition(x + this.width - (12 / 2), y + (this.height / 2) - (12 / 2));

        this.x = x;
        this.y = y;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

}
