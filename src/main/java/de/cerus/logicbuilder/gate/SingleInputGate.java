package de.cerus.logicbuilder.gate;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class SingleInputGate extends LogicGate {

    protected GateConnector inputConnector;
    protected GateConnector outputConnector;

    @Override
    public void init(JPanel panel) {
        super.init(panel);

        this.inputConnector = createInputGate();
        this.outputConnector = createOutputGate();

        this.inputConnector.init(panel);
        this.outputConnector.init(panel);
    }

    @Override
    public void render(Graphics graphics) {
        inputConnector.render(graphics);
        outputConnector.render(graphics);

        renderGate(graphics);
    }

    protected abstract GateConnector createInputGate();

    protected abstract GateConnector createOutputGate();

    public abstract boolean calculate(boolean a);

    public boolean value() {
        return value(0);
    }

    public boolean value(int conIndex) {
        if (inputConnector.getState() != GateConnector.State.WAS_CONNECTED) {
            return false;
        }
        return calculate(inputConnector.value());
    }

    public GateConnector getInputConnector() {
        return inputConnector;
    }

    public GateConnector getOutputConnector() {
        return outputConnector;
    }

    @Override
    public List<GateConnector> getConnectors() {
        return Arrays.asList(inputConnector, outputConnector);
    }

}
