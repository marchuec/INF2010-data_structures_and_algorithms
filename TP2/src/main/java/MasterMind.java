import java.util.ArrayList;
import java.util.Arrays;

public final class MasterMind {

    /** Expliquez votre complexité temporelle et spatiale à l'aide de commentaire dans le code
     *  Indiquez les équivalences telles que O(N + 1) => O(N) et O(2N) => O(N)
     *
     ** TODO Time Complexity  : Worst Case O(N) (DONE)
     ** TODO Space Complexity : Worst Case O(N) (DONE)
     * @param guess String containing the first digit sequence
     * @param secret String containing the second digit sequence
     * @return Array containing two elements, first being the number of digits in the valid position,
     * second being the number of missplaced digits which are still part of the sequence
     */
    public static Integer[] findMatches(String guess, String secret) {
        /* Complexité temporelle globale : O(N + N) = O(N)
         * Complexité spatiale globale : O(N + 1) = O(N)
         */

        /* Partie 1 : chiffres communs à la même position
         * On compare les caractères 1 à 1. On stocke les index qui ne satisfont pas le critère de la partie 1
         * dans idxPart2, qui serviront à la partie 2.
         *
         * Complexité temporelle : la boucle for itère sur chaque caractère -> O(N). Le reste est O(1). Donc,
         * O(N+1) = O(N).
         * Complexité spatiale : un arrayList d'une taille maximale de guess.length -> O(N). Le reste est O(1). Donc,
         * O(N+1) = O(N);
         */
        int compteur1 = 0;
        ArrayList<Integer> idxPart2 = new ArrayList<>(guess.length());
        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == secret.charAt(i)) {
                compteur1++;
            }
            else {idxPart2.add(i); }
        }

        /* Parti 2 : chiffres communs pas à la même position
         * 2 arrays ("arrayGuess" et "arraySecret") contiennent le nombre d'occurences de chaque chiffre dans chaque
         * string. L'index de l'array correspond au chiffre associé. Ex. [0 2 1] -> 0 x chiffre(0), 2 x chiffre(1),
         * 1 x chiffre(2). Ensuite, on trouve le nombre minimal d'occurences de chaque chiffre à partir de ces 2 arrays.
         *
         * Complexité temporelle : la 1ère boucle for -> O(N). La 2e boucle for -> O(1). Le reste est O(1). Donc,
         * O(N+1+1) = O(N).
         * Complexité spatiale: 2 arrays -> O(1). Le reste est O(1). Donc, O(1+1) = O(1).
        */
        int[] arrayGuess = new int[10];
        int[] arraySecret = new int[10];

        for (int idx : idxPart2) {
            arrayGuess[Character.getNumericValue(guess.charAt(idx))]++;
            arraySecret[Character.getNumericValue(secret.charAt(idx))]++;
        }

        int compteur2 = 0;
        for (int i = 0; i < arrayGuess.length; i++) {
            compteur2 += Math.min(arrayGuess[i], arraySecret[i]);
        }

        // Concaténation des résultats des 2 parties
        Integer[] result = {compteur1, compteur2};

        return result;
    }
}
