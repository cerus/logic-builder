package de.cerus.logicbuilder.node;

import de.cerus.logicbuilder.gate.GateConnector;
import de.cerus.logicbuilder.gate.LogicGate;
import de.cerus.logicbuilder.input.Input;
import de.cerus.logicbuilder.output.Output;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Represents the central registry of every node
 *
 * @author Cerus
 * @version 1.0.0
 * @since 1.0.0
 */
public class NodeRegistry {

    private static final Set<Node> nodes = new HashSet<>();
    private static final Set<LogicGate> gates = new HashSet<>();
    private static final Set<Input> inputs = new HashSet<>();
    private static final Set<Output> outputs = new HashSet<>();

    private static int[] connectorDrag = null;

    private NodeRegistry() {
    }

    /**
     * Registers a gate
     *
     * @param gate The gate to register
     */
    public static void addGate(LogicGate gate) {
        nodes.add(gate);
        gates.add(gate);
    }

    /**
     * Registers an input
     *
     * @param input The input to register
     */
    public static void addInput(Input input) {
        nodes.add(input);
        inputs.add(input);
    }

    /**
     * Registers an output
     *
     * @param output The output to register
     */
    public static void addOutput(Output output) {
        nodes.add(output);
        outputs.add(output);
    }

    /**
     * Cuts all connections of the provided node and then removes it
     *
     * @param node The node to remove
     */
    public static void safeRemove(Node node) {
        if (node instanceof ConnectorHolder) {
            for (GateConnector connector : ((ConnectorHolder) node).getConnectors()) {
                if (connector.getState() != GateConnector.State.NOT_CONNECTED) {
                    connector.getConnectedTo().connect(null, GateConnector.State.NOT_CONNECTED);
                    connector.connect(null, GateConnector.State.NOT_CONNECTED);
                }
            }
        }
        remove(node);
    }

    /**
     * Remove the provided node
     *
     * @param node The node to remove
     */
    public static void remove(Node node) {
        nodes.remove(node);

        if (node instanceof LogicGate) {
            gates.remove(node);
        }
        if (node instanceof Input) {
            inputs.remove(node);
        }
        if (node instanceof Output) {
            outputs.remove(node);
        }
    }

    /**
     * Tries to find a gate at the provided coordinates
     *
     * @param x The x coordinate
     * @param y The y coordinate
     * @return The found logic gate or nothing
     */
    public static Optional<LogicGate> getGate(int x, int y) {
        return gates.stream()
                .filter(logicGate -> logicGate.getBounds().contains(x, y))
                .findAny();
    }

    /**
     * Tries to find an input at the provided coordinates
     *
     * @param x The x coordinate
     * @param y The y coordinate
     * @return The found input or nothing
     */
    public static Optional<Input> getInput(int x, int y) {
        return inputs.stream()
                .filter(input -> input.getBounds().contains(x, y))
                .findAny();
    }

    /**
     * Tries to find an output at the provided coordinates
     *
     * @param x The x coordinate
     * @param y The y coordinate
     * @return The found output or nothing
     */
    public static Optional<Output> getOutput(int x, int y) {
        return outputs.stream()
                .filter(output -> output.getBounds().contains(x, y))
                .findAny();
    }

    /**
     * Tries to find a node at the provided coordinates
     *
     * @param x The x coordinate
     * @param y The y coordinate
     * @return The found node or nothing
     */
    public static Optional<Node> getNode(int x, int y) {
        return nodes.stream()
                .filter(node -> node.getBounds().contains(x, y))
                .findAny();
    }

    /**
     * Tries to find a gate connector at the provided coordinates
     *
     * @param x The x coordinate
     * @param y The y coordinate
     * @return The found connector or nothing
     */
    public static Optional<GateConnector> getConnector(int x, int y) {
        return Optional.ofNullable(gates.stream()
                .flatMap(gate -> gate.getConnectors().stream())
                .filter(connector -> connector.getBounds().contains(x, y))
                .findAny()
                .orElse(inputs.stream()
                        .flatMap(input -> input.getConnectors().stream())
                        .filter(connector -> connector.getBounds().contains(x, y))
                        .findAny()
                        .orElse(outputs.stream()
                                .flatMap(output -> output.getConnectors().stream())
                                .filter(connector -> connector.getBounds().contains(x, y))
                                .findAny()
                                .orElse(null))));
    }

    /**
     * Tries to find the owner of the gate connector
     *
     * @param connector The gate connector
     * @return The owner or nothing
     */
    public static Optional<ConnectorHolder> getConnectionHolder(GateConnector connector) {
        return nodes.stream()
                .filter(node -> node instanceof ConnectorHolder)
                .map(node -> (ConnectorHolder) node)
                .filter(gateHolder -> gateHolder.getConnectors().contains(connector))
                .findAny();
    }

    /**
     * @return All gates
     */
    public static Set<LogicGate> getGates() {
        return gates;
    }

    /**
     * @return All inputs
     */
    public static Set<Input> getInputs() {
        return inputs;
    }

    /**
     * @return All outputs
     */
    public static Set<Output> getOutputs() {
        return outputs;
    }

    /**
     * @return All nodes
     */
    public static Set<Node> getNodes() {
        return nodes;
    }

    /**
     * @return The coordinates of the dragged connection
     */
    public static int[] getConnectorDrag() {
        return connectorDrag;
    }

    /**
     * Sets the connection drag coordinates
     *
     * @param connectorDrag The coordinates
     */
    public static void setConnectorDrag(int[] connectorDrag) {
        NodeRegistry.connectorDrag = connectorDrag;
    }

}
