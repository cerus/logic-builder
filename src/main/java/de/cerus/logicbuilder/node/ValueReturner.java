package de.cerus.logicbuilder.node;

/**
 * Represents a node that can process and return something
 *
 * @author Cerus
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ValueReturner {

    /**
     * Calculates and returns a value
     *
     * @param conIndex The index of the gate connector that was used to retrieve the value
     * @return The calculated value
     */
    boolean value(int conIndex);

}
