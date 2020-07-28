package de.cerus.logicbuilder.gate;

import de.cerus.logicbuilder.node.ConnectorHolder;
import de.cerus.logicbuilder.node.Node;
import de.cerus.logicbuilder.node.NodeRegistry;
import de.cerus.logicbuilder.node.ValueReturner;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class GateConnector extends Node {

    private State state;
    private GateConnector connectedTo;
    private boolean out;

    public GateConnector() {
        this(false);
    }

    public GateConnector(boolean out) {
        this(100, 100, out);
    }

    public GateConnector(int x, int y, boolean out) {
        this.x = x;
        this.y = y;
        this.out = out;
    }

    @Override
    public void init(JPanel panel) {
        super.init(panel);

        this.state = State.NOT_CONNECTED;
        this.connectedTo = null;

        this.width = 12;
        this.height = 12;
    }

    public void render(Graphics graphics) {
        drawConnection(graphics);

        graphics.setColor(Color.BLACK);
        graphics.fillOval(x, y, width, height);
    }

    private void drawConnection(Graphics graphics) {
        if (state != State.IS_CONNECTED) {
            return;
        }

        graphics.setColor(Color.BLACK);
        graphics.drawLine(
                x + (width / 2),
                y + (height / 2),
                connectedTo.x + (connectedTo.width / 2),
                connectedTo.y + (connectedTo.height / 2)
        );
    }

    public void connect(GateConnector other, State state) {
        this.state = state;
        this.connectedTo = other;
    }

    public void updatePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean value() {
        if (state == State.NOT_CONNECTED) {
            return false;
        }

        if (state == State.WAS_CONNECTED) {
            return connectedTo.value();
        } else {
            Optional<ConnectorHolder> optional = NodeRegistry.getConnectionHolder(this);
            if (optional.isPresent()) {
                ConnectorHolder connectorHolder = optional.get();
                if (connectorHolder instanceof ValueReturner) {
                    return ((ValueReturner) connectorHolder).value(connectorHolder.getConnectors().indexOf(this));
                }
            }
            return false;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public State getState() {
        return state;
    }

    public boolean isOut() {
        return out;
    }

    public void setOut(boolean out) {
        this.out = out;
    }

    public GateConnector getConnectedTo() {
        return connectedTo;
    }

    public enum State {
        /**
         * Indicates that the gate connector is not connected to another gate connector
         */
        NOT_CONNECTED,

        /**
         * Indicates that the gate connector is connected to an input gate connector
         */
        IS_CONNECTED,

        /**
         * Indicated that the gate connector is connected to an output gate connector
         */
        WAS_CONNECTED;
    }
}
