import edu.princeton.cs.algs4.LinearRegression;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InterviewTest {
    @Test
    public void getBoundsTest() {
        Integer[] array = {0, 0, 0, 0, 1, 2, 2, 3, 4, 4, 4, 6, 7, 8, 8, 8};

        Integer[] actualAnswer = Interview.getBounds(array, 0);
        Integer[] expectedAnswer = {0, 3};
        assertArrayEquals(expectedAnswer, actualAnswer);

        actualAnswer = Interview.getBounds(array, 2);
        expectedAnswer = new Integer[] {5, 6};
        assertArrayEquals(expectedAnswer, actualAnswer);

        actualAnswer = Interview.getBounds(array, 3);
        expectedAnswer = new Integer[] {7, 7};
        assertArrayEquals(expectedAnswer, actualAnswer);

        actualAnswer = Interview.getBounds(array, 4);
        expectedAnswer = new Integer[] {8, 10};
        assertArrayEquals(expectedAnswer, actualAnswer);

        actualAnswer = Interview.getBounds(array, 5);
        expectedAnswer = new Integer[] {-1, -1};
        assertArrayEquals(expectedAnswer, actualAnswer);

        actualAnswer = Interview.getBounds(array, 8);
        expectedAnswer = new Integer[] {13, 15};
        assertArrayEquals(expectedAnswer, actualAnswer);
    }

    @Test
    public void getBoundsRecursiveTest() {
        Integer[] array = {0, 0, 0, 0, 1, 2, 2, 3, 4, 4, 4, 6, 7, 8, 8, 8};

        Integer[] actualAnswer = Interview.getBoundsRecursive(array, 0);
        Integer[] expectedAnswer = {0, 3};
        assertArrayEquals(expectedAnswer, actualAnswer);

        actualAnswer = Interview.getBoundsRecursive(array, 2);
        expectedAnswer = new Integer[] {5, 6};
        assertArrayEquals(expectedAnswer, actualAnswer);

        actualAnswer = Interview.getBoundsRecursive(array, 3);
        expectedAnswer = new Integer[] {7, 7};
        assertArrayEquals(expectedAnswer, actualAnswer);

        actualAnswer = Interview.getBoundsRecursive(array, 4);
        expectedAnswer = new Integer[] {8, 10};
        assertArrayEquals(expectedAnswer, actualAnswer);

        actualAnswer = Interview.getBoundsRecursive(array, 5);
        expectedAnswer = new Integer[] {-1, -1};
        assertArrayEquals(expectedAnswer, actualAnswer);

        actualAnswer = Interview.getBoundsRecursive(array, 8);
        expectedAnswer = new Integer[] {13, 15};
        assertArrayEquals(expectedAnswer, actualAnswer);
    }
}
