package de.cerus.logicbuilder.gui;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import de.cerus.logicbuilder.node.NodeMap;
import de.cerus.logicbuilder.node.NodeRegistry;

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
        for (NodeMap node : NodeMap.values()) {
            model.addElement(node.getItemName());
        }
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

            NodeMap.getByName(nodeChooser.getSelectedItem().toString()).run();
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
