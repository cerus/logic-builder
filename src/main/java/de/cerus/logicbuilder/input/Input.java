package de.cerus.logicbuilder.input;

import de.cerus.logicbuilder.gate.GateConnector;
import de.cerus.logicbuilder.node.ConnectorHolder;
import de.cerus.logicbuilder.node.Node;
import de.cerus.logicbuilder.node.ValueReturner;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.List;

/**
 * Represents user input
 *
 * @author Cerus
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class Input extends Node implements ValueReturner, ConnectorHolder {

    protected GateConnector outputConnector;
    protected boolean on;

    /**
     * Handles mouse clicks
     *
     * @param mouseEvent The event
     */
    public abstract void handleMouseEvent(MouseEvent mouseEvent);

    /**
     * Creates the output connector
     *
     * @return The created connector
     */
    protected abstract GateConnector createOutputConnector();

    /**
     * @param panel The parent panel
     * @see Node#init(JPanel)
     */
    @Override
    public void init(JPanel panel) {
        super.init(panel);

        this.on = false;

        this.outputConnector = createOutputConnector();
        this.outputConnector.init(panel);
    }

    /**
     * @param conIndex The index of the gate connector that was used to retrieve the value
     * @return
     * @see ValueReturner#value(int)
     */
    @Override
    public boolean value(int conIndex) {
        return isOn();
    }

    /**
     * Returns whether this input is present / on (1) or not (0)
     *
     * @return Whether input is present / on or not
     */
    public boolean isOn() {
        return on;
    }

    /**
     * Changes the state of this input
     *
     * @param on The new input value
     */
    public void setOn(boolean on) {
        this.on = on;
    }

    public GateConnector getOutputConnector() {
        return outputConnector;
    }

    @Override
    public List<GateConnector> getConnectors() {
        return Collections.singletonList(outputConnector);
    }

}
