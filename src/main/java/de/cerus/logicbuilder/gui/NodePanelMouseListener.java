package de.cerus.logicbuilder.gui;

import de.cerus.logicbuilder.gate.GateConnector;
import de.cerus.logicbuilder.node.Node;
import de.cerus.logicbuilder.node.NodeRegistry;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Optional;

public class NodePanelMouseListener implements MouseMotionListener, MouseListener {

    private final NodePanel nodePanel;

    private boolean panelDrag = false;
    private int lastDragX = -1;
    private int lastDragY = -1;

    private Node lastDragged = null;
    private GateConnector lastDraggedConnector = null;

    public NodePanelMouseListener(NodePanel nodePanel) {
        this.nodePanel = nodePanel;
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        if (panelDrag) {
            if (lastDragX == -1 && lastDragY == -1) {
                lastDragX = event.getX();
                lastDragY = event.getY();
            }

            int diffX = event.getX() - lastDragX;
            int diffY = event.getY() - lastDragY;

            NodeRegistry.getNodes().forEach(node ->
                    node.updatePosition(node.getX() + diffX, node.getY() + diffY));

            lastDragX = event.getX();
            lastDragY = event.getY();

            nodePanel.repaint();
            return;
        }

        if (lastDraggedConnector != null) {
            NodeRegistry.setConnectorDrag(new int[]{
                    lastDraggedConnector.getX() + (lastDraggedConnector.getWidth() / 2),
                    lastDraggedConnector.getY() + (lastDraggedConnector.getHeight() / 2),
                    event.getX(), event.getY()
            });
            nodePanel.repaint();
            return;
        }

        if (lastDragged != null) {
            lastDragged.updatePosition(event.getX() - (lastDragged.getWidth() / 2),
                    event.getY() - (lastDragged.getHeight() / 2));
            nodePanel.repaint();
            return;
        }

        Optional<GateConnector> connectorOptional = NodeRegistry.getConnector(event.getX(), event.getY());
        if (connectorOptional.isPresent()) {
            GateConnector gateConnector = connectorOptional.get();
            if (gateConnector.isOut()) {
                if (gateConnector.getState() != GateConnector.State.NOT_CONNECTED) {
                    gateConnector.getConnectedTo().connect(null, GateConnector.State.NOT_CONNECTED);
                    gateConnector.connect(null, GateConnector.State.NOT_CONNECTED);
                }

                lastDraggedConnector = gateConnector;
                return;
            }
        }

        Optional<Node> nodeOptional = NodeRegistry.getNode(event.getX(), event.getY());
        if (nodeOptional.isPresent()) {
            lastDragged = nodeOptional.get();
            return;
        }
    }

    @Override
    public void mouseMoved(MouseEvent event) {

    }

    @Override
    public void mouseClicked(MouseEvent event) {
        if (event.getButton() == 3) {
            NodeRegistry.getNode(event.getX(), event.getY()).ifPresent(node -> {
                NodeRegistry.safeRemove(node);
                nodePanel.repaint();
            });
            return;
        }

        NodeRegistry.getInputs().stream()
                .filter(input -> input.getBounds().contains(event.getX(), event.getY()))
                .findFirst()
                .ifPresent(input -> {
                    input.handleMouseEvent(event);
                    nodePanel.repaint();
                });
    }

    @Override
    public void mousePressed(MouseEvent event) {
        if (event.getButton() == 2) {
            nodePanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            panelDrag = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        if (event.getButton() == 2) {
            nodePanel.setCursor(Cursor.getDefaultCursor());
            panelDrag = false;

            lastDragX = -1;
            lastDragY = -1;
        }

        lastDragged = null;

        if (lastDraggedConnector != null) {
            Optional<GateConnector> connectorOptional = NodeRegistry.getConnector(event.getX(), event.getY());
            if (connectorOptional.isPresent()) {
                GateConnector gateConnector = connectorOptional.get();
                if (!gateConnector.isOut() && !NodeRegistry.getConnectionHolder(lastDraggedConnector)
                        .get().getConnectors().contains(gateConnector)
                        && gateConnector.getState() == GateConnector.State.NOT_CONNECTED) {
                    lastDraggedConnector.connect(gateConnector, GateConnector.State.IS_CONNECTED);
                    gateConnector.connect(lastDraggedConnector, GateConnector.State.WAS_CONNECTED);
                }
            }

            lastDraggedConnector = null;
            NodeRegistry.setConnectorDrag(null);

            nodePanel.repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent event) {

    }

    @Override
    public void mouseExited(MouseEvent event) {

    }

}
