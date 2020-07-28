package de.cerus.logicbuilder.node;

import javax.swing.*;
import java.awt.*;

/**
 * Represents a renderable node
 *
 * @author Cerus
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class Node {

    protected JPanel panel;

    protected int x;
    protected int y;
    protected int width;
    protected int height;

    private boolean initialized;

    /**
     * Initialize the node
     *
     * @param panel The parent panel
     */
    public void init(JPanel panel) {
        this.panel = panel;
        this.initialized = true;
    }

    /**
     * Renders the node to the parent panel
     *
     * @param graphics The graphics provided by the parent panel
     */
    public abstract void render(Graphics graphics);

    /**
     * Updates the position of this node
     *
     * @param x The new x coordinate
     * @param y The new y coordinate
     */
    public abstract void updatePosition(int x, int y);

    /**
     * @return The bounds of this node
     */
    public abstract Rectangle getBounds();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isInitialized() {
        return initialized;
    }

}
