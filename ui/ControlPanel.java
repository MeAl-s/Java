package ui;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {

    public void setDarkMode(boolean darkMode) {
        setBackground(darkMode ? new Color(60, 63, 65) : Color.LIGHT_GRAY);
        
        for (Component comp : getComponents()) {
            comp.setForeground(darkMode ? Color.WHITE : Color.BLACK);
    
            // Set background on buttons, dropdown, sliders, etc.
            if (comp instanceof JButton || comp instanceof JComboBox || comp instanceof JSlider) {
                comp.setBackground(darkMode ? new Color(77, 77, 77) : UIManager.getColor("Panel.background"));
            }
        }
    }

    public ControlPanel(VisualizerPanel visualizer) {
        setLayout(new FlowLayout()); // Set layout first

        // Algorithm dropdown (Quick Sort comes first)
        String[] algorithms = { "Quick Sort", "Bubble Sort", "Insertion Sort" };
        JComboBox<String> algoDropdown = new JComboBox<>(algorithms);
        algoDropdown.setSelectedItem("Quick Sort");               //  Show Quick Sort on launch
        visualizer.setSortAlgorithm("Quick Sort");                //  Set logic to match

        algoDropdown.addActionListener(e -> {
            String selected = (String) algoDropdown.getSelectedItem();
            visualizer.setSortAlgorithm(selected);
            System.out.println("Selected algorithm: " + selected); // Debug print
        });

        add(new JLabel("Algorithm:"));
        add(algoDropdown);

        // Toggle Start/Pause Button
        JButton toggleButton = new JButton("Start");
        visualizer.setToggleButton(toggleButton); // connect button reference
        toggleButton.addActionListener(e -> visualizer.toggleSorting());

        // Shuffle Button
        JButton shuffleButton = new JButton("Shuffle");
        shuffleButton.addActionListener(e -> visualizer.shuffleArray());
        visualizer.setShuffleButton(shuffleButton); // 👈 Make sure it's set

        // Speed slider
        JLabel speedLabel = new JLabel("Speed:");
        JSlider speedSlider = new JSlider(1, 200, 150);
        speedSlider.setPreferredSize(new Dimension(100, 40));
        speedSlider.addChangeListener(e -> {
            int sliderValue = speedSlider.getValue();
            int delay = 201 - sliderValue; // higher = slower
            visualizer.setSpeed(delay);
        });

//  Size slider
        JLabel sizeLabel = new JLabel("Size:");
        JSlider sizeSlider = new JSlider(10, 100, 50);
        sizeSlider.setPreferredSize(new Dimension(100, 40));
        sizeSlider.addChangeListener(e -> {
            int size = sizeSlider.getValue();

            if (visualizer.isSorting()) {
                // Pause and reset if it's sorting
                visualizer.stopSorting(); // stop + reset Start button
                JOptionPane.showMessageDialog(this,
                    "Sorting was interrupted because you changed the size.",
                    "Sorting Paused", JOptionPane.INFORMATION_MESSAGE);
            }

            visualizer.setArraySize(size);
        });


        //  Add everything to control panel
        add(toggleButton);
        add(shuffleButton);
        add(speedLabel);
        add(speedSlider);
        add(sizeLabel);
        add(sizeSlider);
    }
}
