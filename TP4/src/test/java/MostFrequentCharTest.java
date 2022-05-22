import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MostFrequentCharTest {
    @Test
    void simple() {
        String text = "cbpappbd";

        assertEquals('p', MostFrequentChar.findKth(text, 0));
        assertEquals('b', MostFrequentChar.findKth(text, 1));
    }

    @Test
    void prioritizeSmallerLetters() {
        String text = "qwertayuiop";

        assertEquals('a', MostFrequentChar.findKth(text, 0));
        assertEquals('e', MostFrequentChar.findKth(text, 1));
    }

    @Test
    void sample0() throws IOException {
        String text = getSample(0);

        assertEquals('u', MostFrequentChar.findKth(text, 0));
        assertEquals('K', MostFrequentChar.findKth(text, 10));
        assertEquals('q', MostFrequentChar.findKth(text, 30));
    }

    @Test
    void sample1() throws IOException {
        String text = getSample(1);

        assertEquals('R', MostFrequentChar.findKth(text, 5));
        assertEquals('r', MostFrequentChar.findKth(text, 10));
        assertEquals('Y', MostFrequentChar.findKth(text, 20));
    }

    @Test
    void sample2() throws IOException {
        String text = getSample(2);

        assertEquals('G', MostFrequentChar.findKth(text, 3));
        assertEquals('v', MostFrequentChar.findKth(text, 9));
        assertEquals('j', MostFrequentChar.findKth(text, 27));
    }

    @Test
    void sample3() throws IOException {
        String text = getSample(3);

        assertEquals('d', MostFrequentChar.findKth(text, 50));
        assertEquals('o', MostFrequentChar.findKth(text, 51));
        assertEquals('*', MostFrequentChar.findKth(text, 52));
    }

    @Test
    void sample4() throws IOException {
        String text = getSample(4);

        assertEquals('R', MostFrequentChar.findKth(text, 18));
        assertEquals('k', MostFrequentChar.findKth(text, 36));
        assertEquals('\'', MostFrequentChar.findKth(text, 72));
    }

    @Test
    void sample5() throws IOException {
        String text = getSample(5);

        assertEquals('%', MostFrequentChar.findKth(text, 40));
        assertEquals('*', MostFrequentChar.findKth(text, 60));
        assertEquals('7', MostFrequentChar.findKth(text, 80));
    }

    @Test
    void sample6() throws IOException {
        String text = getSample(6);

        assertEquals('n', MostFrequentChar.findKth(text, 10));
        assertEquals('E', MostFrequentChar.findKth(text, 30));
        assertEquals('X', MostFrequentChar.findKth(text, 50));
    }

    @Test
    void sample7() throws IOException {
        String text = getSample(7);

        assertEquals('A', MostFrequentChar.findKth(text, 50));
        assertEquals('?', MostFrequentChar.findKth(text, 93));
    }

    String getSample(Integer i) throws IOException {
        return Files.readString(Paths.get("src/test/resources/sample_" + i.toString() + ".txt"));
    }
}