// File: src/ui/VisualizerPanel.java
package ui;

import javax.swing.*;


import sorting.BubbleSort;
import sorting.QuickSort;
import sorting.InsertionSort;
import sorting.SortAlgorithm;

import java.awt.*;

public class VisualizerPanel extends JPanel {

    private int[] array;
    private String selectedAlgorithm = "Quick Sort";
    private int speed = 50;
    private JComboBox<String> algoDropdownRef;

    private boolean isSorting = false;
    private boolean isPaused = false;

    private JButton toggleButtonRef;
    private JButton shuffleButtonRef;

    private SortAlgorithm algorithm = new QuickSort(); // Default set here
    private int highlightA = -1;
    private int highlightB = -1;

    public VisualizerPanel() {
        array = new int[50];
        for (int k = 0; k < array.length; k++) {
            array[k] = (int) (Math.random() * 400);
        }
    }

    public void setToggleButton(JButton button) {
        this.toggleButtonRef = button;
    }

    public void setShuffleButton(JButton button) {
        this.shuffleButtonRef = button;
    }
    public void setAlgoDropdown(JComboBox<String> dropdown) {
        this.algoDropdownRef = dropdown;
    }
    public void toggleSorting() {
        if (!isSorting) {
            if (algoDropdownRef != null) algoDropdownRef.setEnabled(false);
            
            isSorting = true;
            isPaused = false;

            if (toggleButtonRef != null) toggleButtonRef.setText("Pause");
            if (shuffleButtonRef != null) shuffleButtonRef.setEnabled(false);

            new Thread(() -> {
                try {
                    algorithm.sort(array, this);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    isSorting = false;
                    isPaused = false;
                    clearHighlights();
                    if (toggleButtonRef != null) {
                        toggleButtonRef.setText("Start");
                        toggleButtonRef.setEnabled(false);
                    }
                    if (shuffleButtonRef != null) {
                        shuffleButtonRef.setEnabled(true);
                    }
                    SwingUtilities.invokeLater(() ->
                        JOptionPane.showMessageDialog(this, "Done! Please click shuffle to try again.", "Sort Complete", JOptionPane.INFORMATION_MESSAGE)
                    );
                }
            }).start();

        } else if (isPaused) {
            isPaused = false;
            if (toggleButtonRef != null) toggleButtonRef.setText("Pause");
        } else {
            isPaused = true;
            if (toggleButtonRef != null) toggleButtonRef.setText("Start");
        }
    }
    private boolean darkMode = false;

    public void setDarkMode(boolean dark) {
        this.darkMode = dark;
        repaint();
    }
    public void setSortAlgorithm(String algo) {
        this.selectedAlgorithm = algo;

        switch (algo) {
            case "Bubble Sort":
                algorithm = new BubbleSort();
                break;
            case "Quick Sort":
                algorithm = new QuickSort();
                break;
            case "Insertion Sort":
                algorithm = new InsertionSort();
                break;
            default:
                algorithm = new QuickSort(); // fallback
        }
    }

    public void setSpeed(int delay) {
        this.speed = delay;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void highlight(int a, int b) {
        this.highlightA = a;
        this.highlightB = b;
    }

    public void clearHighlights() {
        this.highlightA = -1;
        this.highlightB = -1;
    }
    public boolean isSorting() {
        return isSorting;
    }

    public void stopSorting() {
        isSorting = false;
        isPaused = false;
        clearHighlights(); 
        if (toggleButtonRef != null) {
            toggleButtonRef.setText("Start");
            toggleButtonRef.setEnabled(true);
        }
        if (shuffleButtonRef != null) {
            shuffleButtonRef.setEnabled(true);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 400);
    }

    public void setArraySize(int size) {
        array = new int[size];
        for (int k = 0; k < array.length; k++) {
            array[k] = (int) (Math.random() * getHeight());
        }
        repaint();
    }

    public void shuffleArray() {
        if (isSorting) {
            JOptionPane.showMessageDialog(this, "Please wait for the program to be complete.", "Sorting in Progress", JOptionPane.WARNING_MESSAGE);
            return;
        }

        for (int k = 0; k < array.length; k++) {
            array[k] = (int) (Math.random() * getHeight());
        }
        isSorting = false;
        isPaused = false;
        clearHighlights();
        repaint();
        if (toggleButtonRef != null) {
            toggleButtonRef.setText("Start");
            toggleButtonRef.setEnabled(true);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    
        // Background color based on theme
        setBackground(darkMode ? Color.BLACK : Color.WHITE);
    
        int width = getWidth();
        int height = getHeight();
        if (array.length == 0) return;
    
        int barWidth = Math.max(1, width / array.length);
    
        // Bar color based on theme
        g.setColor(darkMode ? Color.YELLOW : new Color(0, 128, 0)); // dark blue
    
        for (int k = 0; k < array.length; k++) {
            int barHeight = array[k];
            g.fillRect(k * barWidth, height - barHeight, Math.max(1, barWidth - 1), barHeight);
        }
    }
}