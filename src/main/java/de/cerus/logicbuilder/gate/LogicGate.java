package de.cerus.logicbuilder.gate;

import de.cerus.logicbuilder.node.ConnectorHolder;
import de.cerus.logicbuilder.node.Node;
import de.cerus.logicbuilder.node.ValueReturner;

import java.awt.*;

public abstract class LogicGate extends Node implements ValueReturner, ConnectorHolder {

    protected abstract void renderGate(Graphics graphics);

}
