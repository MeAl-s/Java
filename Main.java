import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import ui.VisualizerPanel;
import ui.ControlPanel;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Bubble Sort Visualizer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            VisualizerPanel visualizer = new VisualizerPanel();
            ControlPanel controls = new ControlPanel(visualizer);

            frame.add(visualizer, BorderLayout.CENTER);
            frame.add(controls, BorderLayout.SOUTH);

            // Theme Menu Bar
            JMenuBar menuBar = new JMenuBar();
            JMenu themeMenu = new JMenu("Select a Theme");

            JRadioButtonMenuItem lightTheme = new JRadioButtonMenuItem("Light");
            JRadioButtonMenuItem darkTheme = new JRadioButtonMenuItem("Dark");
            ButtonGroup group = new ButtonGroup();
            group.add(lightTheme);
            group.add(darkTheme);

            lightTheme.setSelected(true); // Default theme

            // Theme toggle logic
            lightTheme.addActionListener(e -> {
                visualizer.setDarkMode(false);
                controls.setDarkMode(false);
                frame.getContentPane().setBackground(Color.WHITE);
            });

            darkTheme.addActionListener(e -> {
                visualizer.setDarkMode(true);
                controls.setDarkMode(true);
                frame.getContentPane().setBackground(new Color(45, 45, 45));
            });

            themeMenu.add(lightTheme);
            themeMenu.add(darkTheme);
            menuBar.add(themeMenu);
            frame.setJMenuBar(menuBar);

            frame.pack();
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
        });
    }
}

