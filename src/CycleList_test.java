import cycle_list.CycleList;
import factory.UserFactory;
import org.junit.Before;
import org.junit.Test;
import types.users.UserType;

import junit.framework.Assert;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class CycleList_test {

    private UserFactory userFactory;
    private UserType userType;
    private CycleList actualList, expectedList;

    @Before
    public void setUp() {
        userFactory = new UserFactory();
        userType = userFactory.getBuilderByName("Double");
        actualList = new CycleList(userType.getTypeComparator());
        expectedList = new CycleList(userType.getTypeComparator());
    }

    /**
     * Исходный набор содержит одинаковые значения
     */
    @Test
    public void sortTestFirst() {
        for (int i = 0; i <= 10; i++) {
            actualList.add(userType.parseValue(0+""));
            expectedList.add(userType.parseValue(0+""));
        }
        actualList.sort(userType.getTypeComparator());
        assertEquals(expectedList.toString(),actualList.toString());
    }

    /**
     * Исходный набор упорядочен в прямом порядке
     */
    @Test
    public void sortTestSecond() {
        for (int i = 0; i <= 10; i++) {
            actualList.add(userType.parseValue(i + ""));
        }
        for (int i = 0; i <= 10; i++) {
            expectedList.add(userType.parseValue(i + ""));
        }
        actualList.sort(userType.getTypeComparator());
        assertEquals(expectedList.toString(), actualList.toString());
    }


    /**
     * Исходный набор упорядочен в обратном порядке
     */
    @Test
    public void sortTestThird() {
        for (int i = 10; i >= 0; i--) {
            actualList.add(userType.parseValue(i + ""));
        }
        for (int i = 0; i <= 10; i++) {
            expectedList.add(userType.parseValue(i + ""));
        }
        actualList.sort(userType.getTypeComparator());
        assertEquals(expectedList.toString(), actualList.toString());
    }

    /**
     * В наборе имеются повторяющиеся элементы
     */
    @Test
    public void sortTestFourth() {
        ArrayList<String> numbers = new ArrayList<>(Arrays.asList("0", "1", "2", "3", "4", "2"));
        for (String number: numbers) {
            actualList.add(userType.parseValue(number));
        }

        actualList.sort(userType.getTypeComparator());

        Collections.sort(numbers);
        for (String number: numbers) {
            expectedList.add(userType.parseValue(number));
        }
        assertEquals(expectedList.toString(), actualList.toString());
    }

    /**
     * В наборе имеются несколько групп повторяющихся элементов
     */
    @Test
    public void sortTestFifth() {
        ArrayList<String> numbers = new ArrayList<>(Arrays.asList("3", "1", "2", "1", "3", "2"));
        for (String number: numbers) {
            actualList.add(userType.parseValue(number));
        }

        actualList.sort(userType.getTypeComparator());

        Collections.sort(numbers);
        for (String number: numbers) {
            expectedList.add(userType.parseValue(number));
        }
        assertEquals(expectedList.toString(), actualList.toString());
    }

    /**
     * В наборе экстремальное значение находится в начале
     */
    @Test
    public void sortTestSixth() {
        ArrayList<String> numbers = new ArrayList<>(Arrays.asList("9999", "3", "1", "7", "1", "5", "2"));
        for (String number : numbers) {
            actualList.add(userType.parseValue(number));
        }

        actualList.sort(userType.getTypeComparator());

        Collections.sort(numbers);
        for (String number : numbers) {
            expectedList.add(userType.parseValue(number));
        }
        assertEquals(actualList.toString(), expectedList.toString());
    }

    /**
     * В наборе экстремальное значение находится в середине
     */
    @Test
    public void sortTestSeventh() {
        ArrayList<String> numbers = new ArrayList<>(Arrays.asList("7", "3", "1", "9999", "1", "5", "2"));
        for (String number : numbers) {
            actualList.add(userType.parseValue(number));
        }

        actualList.sort(userType.getTypeComparator());

        Collections.sort(numbers);
        for (String number : numbers) {
            expectedList.add(userType.parseValue(number));
        }
        assertEquals(actualList.toString(), expectedList.toString());
    }

    /**
     * В наборе экстремальное значение находится в конце
     */
    @Test
    public void sortTestEighth() {
        ArrayList<String> numbers = new ArrayList<>(Arrays.asList("7", "3", "1", "2", "1", "5", "9999"));
        for (String number : numbers) {
            actualList.add(userType.parseValue(number));
        }

        actualList.sort(userType.getTypeComparator());

        Collections.sort(numbers);
        for (String number : numbers) {
            expectedList.add(userType.parseValue(number));
        }
        assertEquals(actualList.toString(), expectedList.toString());
    }

    /**
     * В наборе несколько совпадающих экстремальных значений
     */
    @Test
    public void sortTestTenth() {
        ArrayList<String> numbers = new ArrayList<>(Arrays.asList("9999", "3", "1", "9999", "4", "5", "9999"));
        for (String number : numbers) {
            actualList.add(userType.parseValue(number));
        }

        actualList.sort(userType.getTypeComparator());

        Collections.sort(numbers);
        for (String number : numbers) {
            expectedList.add(userType.parseValue(number));
        }
        assertEquals(actualList.toString(), expectedList.toString());
    }

    @Test
    public void sortPerformanceTest() {
        for (int i = 1; i < 1025; i *= 2) {
            int n = i * 1000;
            System.out.print(n+"\t");
            CycleList cycleList = new CycleList(userType.getTypeComparator());
            for (int j = 0; j < n; j++) cycleList.add(userType.create());

            long start = System.nanoTime();

            try {
                cycleList.sort(userType.getTypeComparator());
            } catch (StackOverflowError ignored) {
                System.err.println("Stack error");
                return;
            }
            long end = System.nanoTime();
            System.out.println((end - start) * 1.0 / 1_000_000);
        }
    }
}
