package Letter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import net.sourceforge.tess4j.Tesseract;

import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LetterTest {
    @Test
    void all() throws Exception {
        Tesseract tesseract = new Tesseract();
        tesseract.setLanguage("fra");

        LetterPlacer letterPlacer = new LetterPlacer();
        letterPlacer.placeNext('C');
        letterPlacer.placeNext('A');
        letterPlacer.placeNextln('V');
        letterPlacer.placeNext('A');
        letterPlacer.placeNextln('B');
        letterPlacer.placeNext('I');
        letterPlacer.placeNext('E');
        letterPlacer.placeNext('N');
        letterPlacer.placeNextln('A');
        letterPlacer.placeNext('L');
        letterPlacer.placeNext('L');
        letterPlacer.placeNext('E');
        letterPlacer.placeNext('R');
        letterPlacer.saveImage("all", true);

        File tmpFile = new File("all.jpg");
        String output = tesseract.doOCR(tmpFile)
                .replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String input = "cavabienaller";
        boolean foundEnoughChars = findMatchingChars(output, input).doubleValue() > input.length() / 2;

        try {
            Desktop dt = Desktop.getDesktop();
            File fileToOpen = foundEnoughChars ? letterPlacer.saveImage("image", false) : tmpFile;
            dt.open(fileToOpen);
        } catch (Exception e) {
            System.out.println("Unable to display the graphical message : " + e.getMessage());
        }

        assertTrue(foundEnoughChars, "Nous ne pouvons pas reconnaitre assez de caracteres");
    }

    private static Integer findMatchingChars(String str1, String str2) {
        Set<String> chars1 = new HashSet<String>(Arrays.asList(str1.split("(?!^)")));
        Set<String> chars2 = new HashSet<String>(Arrays.asList(str2.split("(?!^)")));
        chars1.retainAll(chars2);
        return chars1.size();
    }
}
