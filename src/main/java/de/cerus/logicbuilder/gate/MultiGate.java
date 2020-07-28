package de.cerus.logicbuilder.gate;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class MultiGate extends LogicGate {

    protected List<GateConnector> connectors;

    @Override
    public void init(JPanel panel) {
        super.init(panel);

        this.connectors = createConnectors();
        this.connectors.forEach(connector -> connector.init(panel));
    }

    protected abstract List<GateConnector> createConnectors();

    @Override
    public void render(Graphics graphics) {
        connectors.forEach(gateConnector -> gateConnector.render(graphics));

        renderGate(graphics);
    }

    public abstract boolean calculate(GateConnector connector);

    public boolean value(int conIndex) {
        return calculate(connectors.get(conIndex));
    }

    @Override
    public List<GateConnector> getConnectors() {
        return connectors;
    }

}
