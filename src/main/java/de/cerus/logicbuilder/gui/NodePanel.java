package de.cerus.logicbuilder.gui;

import de.cerus.logicbuilder.node.NodeRegistry;

import javax.swing.*;
import java.awt.*;

public class NodePanel extends JPanel {

    public NodePanel() {
        NodePanelMouseListener mouseListener = new NodePanelMouseListener(this);
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);

        NodeRegistry.getNodes().forEach(node -> {
            if (node.isInitialized()) {
                return;
            }
            node.init(this);
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        NodeRegistry.getNodes().forEach(node -> {
            if (node.isInitialized()) {
                return;
            }
            node.init(this);
        });

        int[] connectorDrag = NodeRegistry.getConnectorDrag();
        if (connectorDrag != null) {
            g.setColor(Color.DARK_GRAY);
            g.drawLine(connectorDrag[0], connectorDrag[1], connectorDrag[2], connectorDrag[3]);
        }

        NodeRegistry.getNodes().forEach(node -> node.render(g));
    }

}
