package de.cerus.logicbuilder.gui;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import de.cerus.logicbuilder.gate.impl.*;
import de.cerus.logicbuilder.input.impl.DefaultInput;
import de.cerus.logicbuilder.node.NodeRegistry;
import de.cerus.logicbuilder.output.impl.DefaultOutput;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashSet;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Logic Builder");
        setSize(810, 725);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        try {
            UIManager.setLookAndFeel(new WindowsLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
            System.err.println("Failed to set look and feel");
        }

        setupComponents();
    }

    private void setupComponents() {
        NodePanel nodePanel = new NodePanel();
        nodePanel.setBounds(5, 5, 785, 650);
        nodePanel.setBorder(new LineBorder(Color.BLACK));

        JComboBox<String> nodeChooser = new JComboBox<>();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("AND Gate");
        model.addElement("OR Gate");
        model.addElement("XOR Gate");
        model.addElement("NOT Gate");
        model.addElement("Splitter Gate");
        model.addElement("Input");
        model.addElement("Output");
        nodeChooser.setModel(model);
        nodeChooser.setBounds(5, 660, 100, 20);

        JButton addButton = new JButton("Add");
        addButton.setBounds(110, 660, 60, 20);
        addButton.addActionListener(getAddButtonListener(nodeChooser, nodePanel));

        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(720, 660, 70, 20);
        clearButton.addActionListener(getClearButtonListener(nodePanel));

        add(nodePanel);
        add(nodeChooser);
        add(addButton);
        add(clearButton);
    }

    private ActionListener getAddButtonListener(JComboBox<String> nodeChooser, NodePanel panel) {
        return e -> {
            if (nodeChooser.getSelectedItem() == null) {
                return;
            }

            switch (nodeChooser.getSelectedItem().toString()) {
                case "AND Gate":
                    NodeRegistry.addGate(new AndGate());
                    break;
                case "OR Gate":
                    NodeRegistry.addGate(new OrGate());
                    break;
                case "XOR Gate":
                    NodeRegistry.addGate(new XorGate());
                    break;
                case "NOT Gate":
                    NodeRegistry.addGate(new InverterGate());
                    break;
                case "Splitter Gate":
                    NodeRegistry.addGate(new SplitterGate());
                    break;
                case "Input":
                    NodeRegistry.addInput(new DefaultInput());
                    break;
                case "Output":
                    NodeRegistry.addOutput(new DefaultOutput());
                    break;
            }

            panel.repaint();
        };
    }

    private ActionListener getClearButtonListener(NodePanel panel) {
        return e -> {
            new HashSet<>(NodeRegistry.getNodes()).forEach(NodeRegistry::safeRemove);
            panel.repaint();
        };
    }

}
