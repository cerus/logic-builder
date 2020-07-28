package de.cerus.logicbuilder.gate.impl;

import de.cerus.logicbuilder.gate.GateConnector;
import de.cerus.logicbuilder.gate.SingleInputGate;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class InverterGate extends SingleInputGate {

    @Override
    public void init(JPanel panel) {
        super.init(panel);

        this.width = 60;
        this.height = 60;

        updatePosition(100, 100);
    }

    @Override
    protected GateConnector createInputGate() {
        return new GateConnector(this.x, this.y + (this.height / 2), false);
    }

    @Override
    protected GateConnector createOutputGate() {
        return new GateConnector(this.x + this.width, this.y + (this.height / 2), true);
    }

    @Override
    public boolean calculate(boolean a) {
        return !a;
    }

    @Override
    public boolean value(int conIndex) {
        return value();
    }

    @Override
    public boolean value() {
        return calculate(inputConnector.getState() == GateConnector.State.WAS_CONNECTED ? inputConnector.value() : false);
    }

    @Override
    protected void renderGate(Graphics graphics) {
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillPolygon(new int[]{
                this.x,
                this.x,
                this.x + this.width,
        }, new int[]{
                this.y,
                this.y + this.height,
                this.y + (this.height / 2),
        }, 3);

        graphics.setColor(Color.BLACK);
        graphics.drawPolygon(new int[]{
                this.x,
                this.x,
                this.x + this.width,
        }, new int[]{
                this.y,
                this.y + this.height,
                this.y + (this.height / 2),
        }, 3);

        String str = "NOT";
        Rectangle2D bounds = graphics.getFontMetrics().getStringBounds(str, graphics);
        graphics.drawString(str, this.x + ((int) (bounds.getWidth() / 2)),
                this.y + (this.height / 2) + ((int) (bounds.getHeight() / 2)));
    }

    @Override
    public void updatePosition(int x, int y) {
        inputConnector.updatePosition(x - (inputConnector.getWidth() / 2),
                y + (this.height / 2) - (inputConnector.getHeight() / 2));
        outputConnector.updatePosition(x + this.width - (outputConnector.getWidth() / 2),
                y + (this.height / 2) - (outputConnector.getHeight() / 2));

        this.x = x;
        this.y = y;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }

}
