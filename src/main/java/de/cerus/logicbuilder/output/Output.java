package de.cerus.logicbuilder.output;

import de.cerus.logicbuilder.gate.GateConnector;
import de.cerus.logicbuilder.node.ConnectorHolder;
import de.cerus.logicbuilder.node.Node;
import de.cerus.logicbuilder.node.ValueReturner;

import javax.swing.*;
import java.util.Collections;
import java.util.List;

public abstract class Output extends Node implements ValueReturner, ConnectorHolder {

    protected GateConnector inConnector;

    protected abstract GateConnector createInputConnector();

    @Override
    public void init(JPanel panel) {
        super.init(panel);

        this.inConnector = createInputConnector();
        this.inConnector.init(panel);
    }

    @Override
    public boolean value(int conIndex) {
        return isOn();
    }

    public boolean isOn() {
        if (inConnector.getState() == GateConnector.State.NOT_CONNECTED) {
            return false;
        }

        return inConnector.value();
    }

    public GateConnector getInConnector() {
        return inConnector;
    }

    @Override
    public List<GateConnector> getConnectors() {
        return Collections.singletonList(inConnector);
    }

}
