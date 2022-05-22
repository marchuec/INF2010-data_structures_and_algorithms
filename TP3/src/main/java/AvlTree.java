import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class AvlTree<ValueType extends Comparable<? super ValueType> > {

    // Only node which has its parent to null
    private BinaryNode<ValueType> root;

    public AvlTree() { }

    /** TODO Worst case : O ( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE (DONE & VERIFIED)
     *
     * Adds value to the tree and keeps it as a balanced AVL Tree
     * Should call balance only if insertion succeeds
     * AVL Trees do not contain duplicates
     *
     * @param value value to add to the tree
     */
    public void add(ValueType value) {
        if (root == null) {
            root = new BinaryNode<ValueType>(value, null);
            return;
        }

        BinaryNode<ValueType> currentNode = root;
        BinaryNode<ValueType> addedNode = null;
        while (true) {
            // À gauche ou à droite du noeud courant
            if (value.compareTo(currentNode.value) < 0) {
                addedNode = currentNode;
                currentNode = currentNode.left;
            } else if (value.compareTo(currentNode.value) > 0) {
                addedNode = currentNode;
                currentNode = currentNode.right;
                // On évite les duplicats
            } else {
                return;
            }

            // Quand on atteint l'emplacement d'ajout
            if (currentNode == null) {
                if (value.compareTo(addedNode.value) > 0) {
                    addedNode.right = new BinaryNode<>(value, addedNode);
                } else {
                    addedNode.left = new BinaryNode<>(value, addedNode);
                }
                balance(addedNode);
                return;
            }
        }
    }

    /** TODO Worst case : O ( log n )
     *
     * Removes value from the tree and keeps it as a balanced AVL Tree
     * Should call balance only if removal succeeds
     *
     * @param value value to remove from the tree
     */
    public void remove(ValueType value){
        BinaryNode<ValueType> currentNode = root;
        while (true) {
            // Si la valeur est absente de l'arbre
            if (currentNode == null) {
                return;
            }
            // Lorsqu'on trouve le noeud à supprimer
            if (currentNode.value.compareTo(value) == 0) {
                if (currentNode.left == null && currentNode.right == null) {
                    currentNode = null;
                }
                else if (currentNode.left == null) {

                }
                else {

                }

                while (true) {
                    if (currentNode == null) {
                        return;
                    }
                }
            }
            currentNode = value.compareTo(currentNode.value) < 0 ? currentNode.left : currentNode.right;

        }
    }

    /** TODO Worst case : O ( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE (DONE & VERIFIED)
     * Verifies if the tree contains value
     * @param value value to verify
     * @return if value already exists in the tree
     */
    public boolean contains(ValueType value) {
        BinaryNode<ValueType> node = root;

        while (true) {
            if (node != null) {
                if (node.value.equals(value))
                    return true;
                node = node.value.compareTo(value) > 0 ? node.left : node.right;
            } else {
                return false;
            }
        }
    }

    /** TODO Worst case : O( 1 ) (DONE & VERIFIED)
     * Returns the max level contained in our root tree
     * @return Max level contained in our root tree
     */
    public int getHeight() {
        if (root != null) {
            return root.height;
        }
        return -1;
    }

    /** TODO O( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE (DONE & VERIFIED)
     *
     * Returns the node which has the minimal value contained in our root tree
     * @return Node which has the minimal value contained in our root tree
     */
    public ValueType findMin() {
        if (root != null) {
            BinaryNode<ValueType> nodeMin = root;
            while (nodeMin.left != null) {
                nodeMin = nodeMin.left;
            }
            return nodeMin.value;
        }
        else {
            return null;
        }
    }

    /** TODO Worst case : O( n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     * Returns all values contained in the root tree in ascending order
     * @return Values contained in the root tree in ascending order
     */
    public List<ValueType> infixOrder() {
        BinaryNode<ValueType> currentNode = root;
        if ( root==null ) {
            return new LinkedList<>();
        }

        LinkedList<ValueType> orderedList = new LinkedList<>();
        while (true) {
            LinkedList<ValueType> orderedList1 = orderedList;
            if (currentNode.left == null || orderedList1.contains(currentNode.left.value)) {
                if (currentNode.right == null || orderedList1.contains(currentNode.right.value)) {
                    if (currentNode.equals(root)) {
                        return orderedList1;
                    }
                    if (!orderedList1.contains(currentNode.value)) {
                        orderedList1.add(currentNode.value);
                    }
                    currentNode = currentNode.parent;
                } else {
                    orderedList1.add(currentNode.value);
                    currentNode = currentNode.right;
                }
            } else {
                currentNode = currentNode.left;
            }
        }
    }

    /** TODO O( n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     * Returns all values contained in the root tree in level order from top to bottom
     * @return Values contained in the root tree in level order from top to bottom
     */
    public List<ValueType> levelOrder(){
        if (root == null) {
            LinkedList<ValueType> orderedList = new LinkedList<>();
            return orderedList;
        }

        LinkedList<BinaryNode<ValueType>> list = new LinkedList<>();
        list.add(root);
        int i = 0;
        while (i < list.size()) {
            BinaryNode<ValueType> addedNode = list.get(i);
            if(addedNode.left != null) {
                list.add(addedNode.left);
            }
            if(addedNode.right != null) {
                list.add(addedNode.right);
            }
            i++;
        }

        LinkedList<ValueType> valueList = new LinkedList<>();
        for (BinaryNode<ValueType> iterator : list) {
            ValueType value = iterator.value;
            valueList.add(value);
        }
        return valueList;
    }

    /** TODO Worst case : O( log n ) HAS TO BE ITERATIVE, NOT RECURSIVE
     *
     * Balances the whole tree
     * @param node Node to balance all the way to root
     */
    private void balance(BinaryNode<ValueType> node) {

    }

    /** TODO Worst case : O ( 1 )
     *
     * Single rotation to the left child, AVR Algorithm
     * @param node1 Node to become child of its left child
     */
    private void rotateLeft(BinaryNode<ValueType> node1){}

    /** TODO Worst case : O ( 1 )
     *
     * Single rotation to the right, AVR Algorithm
     * @param node1 Node to become child of its right child
     */
    private void rotateRight(BinaryNode<ValueType> node1){

    }

    /** TODO O( n )
     *
     * Builds items which should contain all values within the root tree in ascending order
     * @param currentNode Node currently being accessed in this recursive method
     * @param items List being modified to contain all values in the root tree in ascending order
     */
    private void infixOrder(BinaryNode<ValueType> currentNode, List<ValueType> items){}

    static private class BinaryNode<ValueType> {
        ValueType value;

        BinaryNode<ValueType> parent; // Pointer to the node containing this node
        BinaryNode<ValueType> left = null; // Pointer to the node on the left which should contain a value below this.value
        BinaryNode<ValueType> right = null; // Pointer to the node on the right which should contain a value above this.value

        int height = 0;

        // Null-safe height accessor
        // Raw use of parameterized class is justified because we do not care about the values in it, only the height
        static public int getHeight(BinaryNode node) {
            return node != null ? node.height : -1;
        }

        BinaryNode(ValueType value, BinaryNode<ValueType> parent)
        {
            this.value = value;
            this.parent = parent;
        }
    }
}