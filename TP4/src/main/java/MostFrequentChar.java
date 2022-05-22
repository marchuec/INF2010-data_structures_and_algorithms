import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.PriorityQueue;

public final class MostFrequentChar {
    /**
     * Expliquez vos complexités temporelle et spatiale à l'aide de commentaires dans le code
     * Indiquez les équivalences telles que O(n + 1) => O(n) et O(2n) => O(n)
     * <p>
     * n étant la taille de `text`
     * m étant le nombre de lettres différentes dans `text`
     * k étant le paramètre `k` en entrée
     * <p>
     * * TODO Time Complexity  : Worst case O( max(k log m, n) )
     * * TODO Space Complexity : Worst Case O(m)
     *
     * @param text String in which to find the `k`th most frequent letter
     * @param k    Value inclusively between 0 and m - 1
     * @return `k`th most frequent character within `text`
     */
    static public Character findKth(String text, int k) {
        // Complexité globale :
        // Complexité temporelle : O(n + m + m + k*log(m)) = O(n + k*log(m)) = O(max(k*log(m), n)
        // Complexité spatiale : freqsMap -> O(m), freqsArray -> O(m), freqQueue -> O(m).
        // Les autres variables sont O(1). Donc, O(m + m + m + 1) = O(m)

        // On crée une map contenant la lettre (clé) et sa fréquence d'apparition (valeur)
        // Complexité temporelle : charAt() -> O(1), map.put() -> O(1), dans le for() -> O(1 + 1) = O(1)
        // for() -> O(n x 1) = O(n)
        Map<Character, Integer> freqsMap = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            // Si la clé n'existe pas, la valeur par défaut est de 0
            // On ajoute +1 à chaque insertion avec la clé
            freqsMap.put(ch, freqsMap.getOrDefault(ch, 0) + 1);
        }

        // On convertit la map en un array de Data
        // Complexité temporelle : ArrayList.add() -> O(1), new Data() -> O(1), dans le for() -> O(1 + 1) = O(1)
        // for() -> O(m x 1) = O(m)
        ArrayList<Data> freqsArray = new ArrayList<>(freqsMap.size());
        for (Map.Entry<Character,Integer> entry : freqsMap.entrySet()) {
            Data data = new Data(entry.getKey(), entry.getValue());
            freqsArray.add(data);
        }

        // On crée une priorityQueue à partir de freqsArray
        // Complexité temporelle : O(m)
        PriorityQueue<Data> freqsQueue = new PriorityQueue<>(freqsArray);

        // On trouve le k-th élément du priorityQueue
        // Complexité temporelle : PriorityQueue.poll() -> O(log(m)), for() -> O(k*log(m))
        Data mostFreqData = freqsQueue.poll();
        for (int i = 1; i <= k; i++) {
            mostFreqData = freqsQueue.poll();
        }

        return mostFreqData.letter;
    }

    static private class Data implements Comparable<Data> {
        final char letter;
        final int freq;

        /**
         * Constructeur
         * @param letter Une lettre
         * @param freq   La fréquence d'apparition de la lettre dans "text"
         */
        Data(char letter, int freq) {
            this.letter = letter;
            this.freq = freq;
        }

        // Complexité temporelle : O(1)
        @Override
        public int compareTo(Data data) {
            // Puisque c'est un minHeap par défaut, on inverse les <, >
            if (freq < data.freq) { return 1; }
            else if (freq > data.freq) { return -1; }
            else {
                // Ordre alphabétique si égalité
                if (letter < data.letter) { return -1; }
                else { return 1; }
            }
        }
    }
}
