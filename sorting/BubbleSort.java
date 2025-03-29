package sorting;

import ui.VisualizerPanel;

public class BubbleSort implements SortAlgorithm {

    @Override
    public void sort(int[] array, VisualizerPanel visualizer) throws InterruptedException {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                while (visualizer.isPaused()) Thread.sleep(10);

                visualizer.highlight(j, j + 1);
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }

                visualizer.repaint();
                Thread.sleep(visualizer.getSpeed());
            }
        }

        visualizer.clearHighlights();
    }
}