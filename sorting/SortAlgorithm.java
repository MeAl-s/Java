// File: src/sorting/SortAlgorithm.java
package sorting;

import ui.VisualizerPanel;

public interface SortAlgorithm {
    void sort(int[] array, VisualizerPanel visualizer) throws InterruptedException;
}
