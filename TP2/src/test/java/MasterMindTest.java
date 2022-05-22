import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

class MasterMindTest {
    @Test
    void findMatchSimple() {
        //All values are in the good spot
        Integer[] answer =  MasterMind.findMatches("1111", "1111");
        Integer[] expected = new Integer[] {4, 0};
        assertArrayEquals(expected, answer);

        //No values are commmon
        answer = MasterMind.findMatches("1234","5678");
        expected = new Integer[] {0, 0};
        assertArrayEquals(expected, answer);

        //All values are common but not in the good spot
        answer = MasterMind.findMatches("1234", "4321");
        expected = new Integer[] {0, 4};
        assertArrayEquals(expected, answer);

        //Mix of common values and values in the good spot
        answer = MasterMind.findMatches("1212", "1221");
        expected = new Integer[] {2, 2};
        assertArrayEquals(expected, answer);

        //Common values in a bad spot are not counted twice
        answer =  MasterMind.findMatches("1241", "2140");
        expected = new Integer[] {1, 2};
        assertArrayEquals(expected, answer);
    }

    @Test
    void answerIndependentOfInputOrder() {
        String num1 = "1212";
        String num2 = "1221";

        Integer[] answer1 = MasterMind.findMatches(num1, num2);
        Integer[] answer2 = MasterMind.findMatches(num2, num1);

        assertArrayEquals(answer1, answer2);
    }

    @Test
    void findMatchLongNumbers() throws Exception {
        String[] numbers = new String[4];

        BufferedReader reader = new BufferedReader(new FileReader("./src/test/resources/longNumbers.txt"));
        for (int i = 0; i < 4; i++) {
            numbers[i] = reader.readLine();
        }

        reader.close();

        Integer[] answer = MasterMind.findMatches(numbers[0], numbers[1]);
        Integer[] expected = new Integer[] {100, 836};
        assertArrayEquals(expected, answer);

        answer = MasterMind.findMatches(numbers[2], numbers[3]);
        expected = new Integer[] {115, 832};
        assertArrayEquals(expected, answer);
    }
}
