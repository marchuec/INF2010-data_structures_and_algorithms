package KthSmallestElement;

import java.util.PriorityQueue;

public class KthSmallestElement {
    /**
     * Soit "m" le nombre de lignes de la matrice :
     *
     * ->> Explication de votre complexité temporelle
     * Construire la priorityQueue initialement avec m éléments est O(m). Ensuite, à chaque itération, on
     * effectue un retrait et parfois une insertion. Dans le pire cas, la queue aura toujours une taille de m et ces
     * opérations prendront toujours O(log(m)). On a "k" itérations, donc
     * O(2*k*log(m)) -> O(k*log(m)). Les autres opérations sont O(1). Au total, on a donc
     * O(1 + m + k*log(m)) = O(m + k*log(m)). Si k est suffisamment grand (pire cas), on a O(k*log(m)).
     *
     * ->> Explication de votre complexité spatiale
     * La priorityQueue contient au plus m éléments. Elle est initialisée avec m éléments
     * et à chaque itération, on enlève un élément et ajoute ou pas un autre élément (donc la queue ne peut pas
     * dépasser une taille de m) -> O(m). Les autres espaces mémoires sont O(1). On a donc O(1 + m) = O(m).
     *
     * Le raisonnement est identique dans le cas où on construit la queue initiale avec la 1ère ligne. Dans ce
     * cas, les complexités seront selon "n", le nombre de colonnes.
     *
     * Ici, on souhaite avoir max(m,n). Donc, on construit la queue selon la dimension la plus grande de la matrice.
     * (si on prenait la dimension la plus petite, on aurait plutôt min(m,n)... ).
     *
     * Alors : Complexité temporelle pire cas : O(k*log(max(m,n)))
     *         Complexité spatiale pire cas : O(max(m,n))
     *
     */
    /** TODO Worst case
     *      Time complexity : O ( k log max(m,n) )
     *      Space complexity : O ( max(m,n) )
     *
     * Returns the `k`th smallest element in `matrix`
     * @param matrix 2D table of shape M x N respecting the following rules
     *               matrix[i][j] <= matrix[i+1][j]
     *               matrix[i][j] <= matrix[i][j + 1]
     * @param k Index of the smallest element we want
     * @return `K`th smallest element in `matrix`, null if non-existent
     */
    static public <T extends Comparable<T>> T findKthSmallestElement(final T[][] matrix, final int k) {
        if (matrix == null) { return null; }

        int ROWS = matrix.length;
        if (ROWS == 0) { return null; } // Matrice vide
        int COLS = matrix[0].length;

        if (k < 0 || k >= ROWS*COLS) { return null; }

        String criterion = ROWS > COLS ? "ROWS" : "COLS";   // Permet de choisir si on utilise la 1ère colonne ou
                                                            // la 1ère ligne pour la queue

        // On met la 1ère ligne/colonne dans la queue
        PriorityQueue<Node<T>> queue = new PriorityQueue<>();
        if (criterion == "COLS") {
            for (int i = 0; i < ROWS; i++)
                queue.add(new Node<>(matrix[i][0], i, 0));
        } else {
            for (int i = 0; i < COLS; i++)
                queue.add(new Node<>(matrix[0][i], 0, i));
        }

        // Si on utilise la 1ère colonne pour la priorityQueue (même raisonnement pour 1ère ligne) :
        // On effectue k itérations. À chaque itération, on retire le minimum de la queue
        // et on ajoute le prochain élément situé sur la même ligne que l'élément retiré, sur la colonne
        // de droite par rapport à l'élément retiré. Si l'élément retiré est déjà sur la dernière colonne,
        // on n'ajoute rien à la queue. De cette façon, la queue contient toujours l'élément le plus petit
        // suivant de la matrice.
        for (int i = 0; i < k; i++) {
            Node<T> current = queue.poll();
            int row = current.row;
            int col = current.col;
            if (criterion == "COLS") {
                if (col < COLS - 1)
                    queue.add(new Node(matrix[row][col + 1], row, col + 1));
            } else {
                if (row < ROWS - 1)
                    queue.add(new Node(matrix[row + 1][col], row + 1, col));
            }
        }

        return queue.poll().value;
    }

    private static class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
        final T value;
        final int row;
        final int col;

        /**
         * Constructeur de Node
         * @param value La valeur
         * @param row La ligne dans la matrice
         * @param col La colonne dans la matrice
         */
        Node(T value, int row, int col) {
            this.value = value;
            this.col = col;
            this.row = row;
        }

        @Override
        public int compareTo(Node<T> other) {
            if (value.compareTo(other.value) < 0) { return -1; }
            else if (value.compareTo(other.value) > 0) { return 1; }
            else { return 0; }
        }
    }
}
