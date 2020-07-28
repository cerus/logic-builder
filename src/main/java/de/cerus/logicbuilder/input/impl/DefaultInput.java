package de.cerus.logicbuilder.input.impl;

import de.cerus.logicbuilder.gate.GateConnector;
import de.cerus.logicbuilder.input.Input;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class DefaultInput extends Input {

    @Override
    public void init(JPanel panel) {
        super.init(panel);

        this.width = 40;
        this.height = 40;

        this.updatePosition(100, 100);
    }

    @Override
    public void handleMouseEvent(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() != 2) {
            return;
        }

        setOn(!isOn());
    }

    @Override
    protected GateConnector createOutputConnector() {
        return new GateConnector(x + this.width - (12 / 2), y + (this.height / 2) - (12 / 2), true);
    }

    @Override
    public void render(Graphics graphics) {
        outputConnector.render(graphics);

        graphics.setColor(isOn() ? Color.YELLOW : Color.WHITE);
        graphics.fillOval(this.x, this.y, this.width, this.height);

        graphics.setColor(Color.BLACK);
        graphics.drawOval(this.x, this.y, this.width, this.height);

        FontMetrics fontMetrics = graphics.getFontMetrics();

        String str = isOn() ? "1" : "0";
        graphics.drawString(str, this.x + (this.width / 2) - (fontMetrics.stringWidth(str) / 2), this.y
                + (this.height / 3) + ((int) (fontMetrics.getStringBounds(str, graphics).getHeight() / 3)));

        str = "In";
        graphics.drawString(str, this.x + (this.width / 2) - (fontMetrics.stringWidth(str) / 2), this.y
                + this.height - (this.height / 3) + ((int) (fontMetrics.getStringBounds(str, graphics).getHeight() / 3)));
    }

    @Override
    public void updatePosition(int x, int y) {
        outputConnector.updatePosition(x + this.width - (outputConnector.getWidth() / 2),
                y + (this.height / 2) - (outputConnector.getHeight() / 2));

        this.x = x;
        this.y = y;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
