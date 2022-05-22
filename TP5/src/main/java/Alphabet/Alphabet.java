package Alphabet;
import java.util.ArrayList;

public class Alphabet {

    /**
     * From the words contained in the dictionary of a fictitious language, find the lexical order of
     * the symbols composing the language.
     *
     * @param dictionary Contains all the word of a language
     * @return The lexicalOrder of the symbols composing this language
     */
    public static ArrayList<Character> lexicalOrder(String[] dictionary) {
        // Crée un graph où chaque caractère pointe vers le caractère suivant
        // selon l'ordre lexical
        Graph<Character> graph = new Graph<Character>();

        // On prend chaque paire de mots en ordre
        for (int i = 0; i < dictionary.length - 1; i++) {
            String mot1 = dictionary[i];
            String mot2 = dictionary[i+1];

            // On compare les caractère 1 à 1 jusqu'à atteindre 2 caractères différents
            // ou jusqu'à atteindre la fin du mot le plus court.
            int taillePlusPetitMot = Math.min(mot1.length(), mot2.length());
            for (int j = 0; j < taillePlusPetitMot; j++) {
                char char1 = mot1.charAt(j);
                char char2 = mot2.charAt(j);
                // Si les caractères sont différents, on lie le caractère du 1er mot au
                // caractère du 2e mot.
                if (char1 != char2) {
                    graph.connect(char1, char2);
                    break;
                }
            }
        }

        // La méthode .topSort() a été ajoutée au fichier Graph.java.
        return graph.topSort();
    }
}


