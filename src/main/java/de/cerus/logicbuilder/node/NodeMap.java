package de.cerus.logicbuilder.node;

import de.cerus.logicbuilder.gate.impl.*;
import de.cerus.logicbuilder.input.impl.BCDInput;
import de.cerus.logicbuilder.input.impl.DefaultInput;
import de.cerus.logicbuilder.output.impl.DefaultOutput;
import de.cerus.logicbuilder.output.impl.SevenSegmentDisplay;

/**
 * This enum maps nodes by their visible name to their add action.
 *
 * @author Paul2708
 * @version 1.0.0
 * @since 1.0.1
 */
public enum NodeMap {

    AND_GATE("AND Gate", () -> NodeRegistry.addGate(new AndGate())),
    OR_GATE("OR Gate", () -> NodeRegistry.addGate(new OrGate())),
    XOR_GATE("XOR Gate", () -> NodeRegistry.addGate(new XorGate())),
    NOT_GATE("NOT Gate", () -> NodeRegistry.addGate(new InverterGate())),
    SPLITTER_GATE("Splitter Gate", () -> NodeRegistry.addGate(new SplitterGate())),
    BCD_TO_7SEGMENT_GATE("BCD to 7-Segment", () -> NodeRegistry.addGate(new BCDToSevenSegmentGate())),
    INPUT("Input", () -> NodeRegistry.addInput(new DefaultInput())),
    BCD_INPUT("BCD Input", () -> NodeRegistry.addInput(new BCDInput())),
    OUTPUT("Output", () -> NodeRegistry.addOutput(new DefaultOutput())),
    SEVEN_SEGMENT_DISPLAY("7-Segment Display", () -> NodeRegistry.addOutput(new SevenSegmentDisplay()));

    private final String itemName;
    private final Runnable addAction;

    NodeMap(String itemName, Runnable addAction) {
        this.itemName = itemName;
        this.addAction = addAction;
    }

    /**
     * Get the node mapping by its item name.
     *
     * @param name combo box item name
     * @return node mapping
     * @throws IllegalArgumentException if the name doesn't match a mapping (should never happen)
     */
    public static NodeMap getByName(String name) {
        for (NodeMap value : values()) {
            if (value.getItemName().equals(name)) {
                return value;
            }
        }

        throw new IllegalArgumentException(String.format("%s cannot be matched as Node", name));
    }

    /**
     * Get the item name.
     * The name is used to list the nodes in the combobox.
     *
     * @return item name
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Run the add action, so the {@link NodeRegistry} adds the node.
     */
    public void run() {
        addAction.run();
    }
}