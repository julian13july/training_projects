package namesurfer;

import javax.swing.*;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * File: NameSurferController.java
 * ------------------------------
 * This class represents a Controller for passing data according to the MVC design model.
 */
public class NameSurferController implements NameSurferConstants, ActionListener {

    private JFrame frame;
    private JTextField nameField;
    private JButton graphButton;
    private JButton clearButton;
    private NameSurferEntry entry;
    private NameSurferView graph;

    protected NameSurferController() {
        createFrame();
        initElements();
        initData();
    }

    protected void init() {
        frame.setVisible(true);
    }

    private void createFrame() {
        frame = new JFrame("Name Surfer");
        frame.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initElements() {
        JPanel controlPanel = new JPanel();
        frame.add(controlPanel, BorderLayout.NORTH);
        controlPanel.setBackground(Color.LIGHT_GRAY);
        controlPanel.add(new JLabel("Name:"));
        nameField = new JTextField(NUM_COLUMNS);
        controlPanel.add(nameField);
        graphButton = new JButton("Graph");
        controlPanel.add(graphButton);
        clearButton = new JButton("Clear");
        controlPanel.add(clearButton);
        graphButton.addActionListener(this);
        clearButton.addActionListener(this);
        nameField.addActionListener(this);
    }

    private void initData() {
        entry = new NameSurferEntry();
        graph = new NameSurferView(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == graphButton || e.getSource() == nameField) {
            entry.findEntry(nameField.getText());
            if (entry.getEntryList() != null) {
                graph.draw(entry);
            }
        } else if (e.getSource() == clearButton) {
            graph.clear(entry);
        }
    }
}





