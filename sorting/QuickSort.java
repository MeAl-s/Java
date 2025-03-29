// File: src/sorting/QuickSort.java
package sorting;

import ui.VisualizerPanel;

public class QuickSort implements SortAlgorithm {

    private int[] array;
    private VisualizerPanel visualizer;

    @Override
    public void sort(int[] array, VisualizerPanel visualizer) throws InterruptedException {
        this.array = array;
        this.visualizer = visualizer;
        quickSort(0, array.length - 1);
        visualizer.clearHighlights(); // Optional: remove highlights after sorting
    }

    private void quickSort(int low, int high) throws InterruptedException {
        if (low < high) {
            int pi = partition(low, high);
            quickSort(low, pi - 1);
            quickSort(pi + 1, high);
        }
    }

    private int partition(int low, int high) throws InterruptedException {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            while (visualizer.isPaused()) Thread.sleep(10); // Respect pause

            visualizer.highlight(j, high); // highlight current and pivot
            if (array[j] < pivot) {
                i++;
                swap(i, j);
            }

            visualizer.repaint();
            Thread.sleep(visualizer.getSpeed());
        }

        swap(i + 1, high);
        return i + 1;
    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}