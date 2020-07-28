package de.cerus.logicbuilder.node;

import de.cerus.logicbuilder.gate.GateConnector;

import java.util.List;

/**
 * Represents a node with gate connectors
 *
 * @author Cerus
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ConnectorHolder {

    /**
     * Returns all gate connectors of this node
     *
     * @return The gate connectors
     */
    List<GateConnector> getConnectors();

}
