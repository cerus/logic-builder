package de.cerus.logicbuilder.gate;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public abstract class DoubleInputGate extends LogicGate {

    protected GateConnector firstInputConnector;
    protected GateConnector secondInputConnector;
    protected GateConnector outputConnector;

    @Override
    public void init(JPanel panel) {
        super.init(panel);

        this.firstInputConnector = createFirstInputGate();
        this.secondInputConnector = createSecondInputGate();
        this.outputConnector = createOutputGate();

        this.firstInputConnector.init(panel);
        this.secondInputConnector.init(panel);
        this.outputConnector.init(panel);
    }

    @Override
    public void render(Graphics graphics) {
        firstInputConnector.render(graphics);
        secondInputConnector.render(graphics);
        outputConnector.render(graphics);

        renderGate(graphics);
    }

    protected abstract GateConnector createFirstInputGate();

    protected abstract GateConnector createSecondInputGate();

    protected abstract GateConnector createOutputGate();

    public abstract boolean calculate(boolean a, boolean b);

    public boolean value() {
        return value(0);
    }

    public boolean value(int conIndex) {
        if (firstInputConnector.getState() != GateConnector.State.WAS_CONNECTED) {
            return false;
        }
        if (secondInputConnector.getState() != GateConnector.State.WAS_CONNECTED) {
            return false;
        }
        return calculate(firstInputConnector.value(), secondInputConnector.value());
    }

    public GateConnector getFirstInputConnector() {
        return firstInputConnector;
    }

    public GateConnector getSecondInputConnector() {
        return secondInputConnector;
    }

    public GateConnector getOutputConnector() {
        return outputConnector;
    }

    @Override
    public List<GateConnector> getConnectors() {
        return Arrays.asList(firstInputConnector, secondInputConnector, outputConnector);
    }

}
