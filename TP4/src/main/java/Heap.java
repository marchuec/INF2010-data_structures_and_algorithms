import java.util.*;

public class Heap<ValueType extends Comparable<? super ValueType>> implements Iterable<ValueType> {
    private static final boolean DEFAULT_IS_MIN_HEAP = true;
    private ArrayList<ValueType> data;
    private final boolean isMinHeap;

    /**
     * Constructor
     * Decides if the heap is a MinHeap (True) or a MaxHeap (False)
     */
    public Heap() {
        this(DEFAULT_IS_MIN_HEAP);
    }

    /**
     * Constructor
     * @param data Unordered list of elements
     */
    public Heap(Collection<ValueType> data) { this(DEFAULT_IS_MIN_HEAP, data); }

    /**
     * Constructor
     * @param isMinHeap
     */
    public Heap(boolean isMinHeap) {
        this.isMinHeap = isMinHeap;
        data = new ArrayList<>();
    }

    /**
     * Constructor
     * @param isMinHeap
     * @param data Unordered list of elements
     */
    public Heap(boolean isMinHeap, Collection<ValueType> data) {
        this.isMinHeap = isMinHeap;
        this.data = new ArrayList<>(data);
        heapify();
    }

    /**
     * @return Number of elements within `heap`
     */
    public int size() {
        return data.size();
    }

    /**
     * @return Min/Max depending on `isMinHeap`
     */
    public ValueType peek() {
        return data.get(0);
    }

    /** TODO Worst Case O(1) (DONE)
     * @param childIndex Index associated to child of the parent that will be returned
     * @return Index of the parent of `childIndex`
     */
    private int parent(int childIndex) { return childIndex / 2; }

    /** TODO Worst Case O(1) (DONE)
     * @param parentIndex Index associated to the parent of the left child that will be returned
     * @return Index of the left child of `parentIndex`
     */
    private int left(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    /** TODO Worst Case O(1) (DONE)
     * @param parentIndex Index associated to the parent of the right child that will be returned
     * @return Index of the right child of `parentIndex`
     */
    private int right(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    /** TODO Worst Case O(1) (DONE)
     * Swap value at `firstIndex` and `secondIndex`
     * Value initially at `firstIndex` will now be at `secondIndex`
     * Value initially at `secondIndex` will now be at `firstIndex`
     *
     * @param firstIndex Index of the first element to be swapped
     * @param secondIndex Index of the second element to be swapped
     */
    private void swap(int firstIndex, int secondIndex) {
        ValueType tmp = data.get(firstIndex);
        data.set(firstIndex, data.get(secondIndex));
        data.set(secondIndex, tmp);
    }

    /** TODO Worst Case O(n) (DONE)
     *   See here for complexity explanation :
     *   https://stackoverflow.com/questions/9755721/how-can-building-a-heap-be-on-time-complexity/18295327#18295327
     *
     * Rearrange elements within `data` to respect either MinHeap
     * or MaxHeap format depending on `isMinHeap`
     */
    private void heapify() {
        for (int i = data.size() / 2; i >= 0; i--) {
            percolateDown(i);
        }
    }

    /** TODO Worst Case O(log n) (DONE)
     *   HAS TO BE RECURSIVE
     * Move the value at `childIndex` towards the root (index 0) until
     * it respects either MinHeap or MaxHeap format depending on `isMinHeap`
     *
     * @param childIndex
     */
    private void percolateUp(int childIndex) {
        int parentIndex = parent(childIndex);
        if (parentIndex == 0) { return; } // Si on a atteint la racine
        if (isMinHeap) {
            // MinHeap
            if (data.get(parentIndex).compareTo(data.get(childIndex)) > 0)
                swap(childIndex, parentIndex);
        } else {
            // MaxHeap
            if (data.get(parentIndex).compareTo(data.get(childIndex)) < 0)
                swap(childIndex, parentIndex);
        }

        percolateUp(parentIndex);
    }

    /** TODO Worst Case O(log n) (DONE)
     *   HAS TO BE RECURSIVE
     * Move the value at `parentIndex` towards the leaves prioritizing the left until
     * it respects either MinHeap or MaxHeap format depending on `isMinHeap`
     *
     * @param parentIndex
     */
    private void percolateDown(int parentIndex) {
        // Choisir enfant
        int childIndex = left(parentIndex); // Enfant de gauche
        if (childIndex >= data.size()) { return; } // On atteint une feuille, arrêt
        if (isMinHeap) {
            if (childIndex != data.size() - 1 && // 2 fils et comparaison fil gauche et droit
                    data.get(childIndex + 1).compareTo(data.get(childIndex)) < 0)
                childIndex++;

            // Si son enfant est plus petit, on swap avec le parent
            if (data.get(childIndex).compareTo(data.get(parentIndex)) < 0)
                swap(parentIndex, childIndex);
        } else {
            if (childIndex != data.size() - 1 && // 2 fils et comparaison fil gauche et droit
                    data.get(childIndex + 1).compareTo(data.get(childIndex)) > 0)
                childIndex++;

            // Si son enfant est plus grand, on swap avec le parent
            if (data.get(childIndex).compareTo(data.get(parentIndex)) > 0)
                swap(parentIndex, childIndex);
        }

        // On appele percolateDown sur le parent à sa nouvelle position
        percolateDown(childIndex);
    }

    /** TODO Worst Case O(log n) (DONE)
     * Add `element` to `heap` while making sure `heap` still respects
     * either MinHeap or MaxHeap format depending on `isMinHeap`
     *
     * @param element Value to add within `heap`
     */
    public void push(ValueType element) {
        data.add(element);
        percolateUp(data.size() - 1);
    }

    /** TODO Worst Case O(log n) (DONE)
     * Remove the Min/Max from `heap` while making sure `heap` still respects
     * either MinHeap or MaxHeap format depending on `isMinHeap`
     *
     * @return Min/Max depending on `isMinHeap`
     */
    public ValueType pop() {
        ValueType firstEntry = data.get(0);
        ValueType lastEntry = data.get(data.size() - 1);
        data.remove(data.size() - 1);
        // Si l'arbre contenait > 1 élément
        if (data.size() > 0) {
            data.set(0, lastEntry);
            percolateDown(0);
        }
        return firstEntry;
    }

    /** TODO Worst Case O(n log n) (DONE)
     * Sort elements with a heap sort
     * Elements will be in ascending order if it is a MinHeap
     * Elements will be in descending order if it is a MaxHeap
     *
     * @return Sorted elements
     */
    public ArrayList<ValueType> sort() {
        ArrayList<ValueType> dataCopy = new ArrayList<>(data);
        ArrayList<ValueType> sortedList = new ArrayList<>(data.size());
        while (data.size() != 0) {
            ValueType firstEntry = pop();
            sortedList.add(firstEntry);
        }
        data = dataCopy;
        return sortedList;
    }

    @Override
    public Iterator<ValueType> iterator() {
        return data.iterator();
    }
}
