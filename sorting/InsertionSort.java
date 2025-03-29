package sorting;

import ui.VisualizerPanel;

public class InsertionSort implements SortAlgorithm {

    @Override
    public void sort(int[] array, VisualizerPanel panel) throws InterruptedException {
        for (int i = 1; i < array.length; i++) {

            // Check pause before starting the outer loop iteration
            while (panel.isPaused()) {
                Thread.sleep(10);
            }

            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                // Pause check inside inner loop
                while (panel.isPaused()) {
                    Thread.sleep(10);
                }

                array[j + 1] = array[j];

                panel.highlight(j, j + 1);
                panel.repaint();
                Thread.sleep(panel.getSpeed());

                j--;
            }

            array[j + 1] = key;

            panel.highlight(j + 1, i); // Highlight where insertion happens
            panel.repaint();
            Thread.sleep(panel.getSpeed());
        }

        panel.clearHighlights(); // Clear at the end
        panel.repaint();
    }
}
