import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class JoinedDataRowTest {
    static Collection<DataRow<Integer, String>> leftCollection;
    static Collection<DataRow<Integer, String>> rightCollection;
    static JoinOperation joinOperation;

    @BeforeAll
    static void createLeftAndRightCollectionAndInterfaceJoinOperation() {
        leftCollection = new ArrayList<>();
        rightCollection = new ArrayList<>();
        joinOperation = JoinedDataRow.getJoinOperation();

    }

    @BeforeAll
    static void addElementsToRightAndLeftCollection() {
        leftCollection.add(new DataRow<>(1, "Kyiv"));
        leftCollection.add(new DataRow<>(2, "London"));
        leftCollection.add(new DataRow<>(3, "Paris"));
        rightCollection.add(new DataRow<>(1, "Ukraine"));
        rightCollection.add(new DataRow<>(2, "England"));
        rightCollection.add(new DataRow<>(4, "Germany"));
    }

    @Test
    void innerJoinShouldReturnCorrectValues() {
        Collection<JoinedDataRow<Integer, String, String>> expectedResult = new ArrayList<>();
        expectedResult.add(new JoinedDataRow<>(1, "Kyiv", "Ukraine"));
        expectedResult.add(new JoinedDataRow<>(2, "London", "England"));
        Collection<JoinedDataRow<Integer, String, String>> result = JoinedDataRow.innerJoin(leftCollection, rightCollection, joinOperation);
        assertIterableEquals(expectedResult, result);
    }

    @Test
    void leftJoinShouldReturnCorrectValues() {
        Collection<JoinedDataRow<Integer, String, String>> expectedResult = new ArrayList<>();
        expectedResult.add(new JoinedDataRow<>(1, "Kyiv", "Ukraine"));
        expectedResult.add(new JoinedDataRow<>(2, "London", "England"));
        expectedResult.add(new JoinedDataRow<>(3, "Paris", null));
        Collection<JoinedDataRow<Integer, String, String>> result = JoinedDataRow.leftJoin(leftCollection, rightCollection, joinOperation);
        assertIterableEquals(expectedResult, result);

    }

    @Test
    void rightJoinShouldReturnCorrectValues() {
        Collection<JoinedDataRow<Integer, String, String>> expectedResult = new ArrayList<>();
        expectedResult.add(new JoinedDataRow<>(1, "Kyiv", "Ukraine"));
        expectedResult.add(new JoinedDataRow<>(2, "London", "England"));
        expectedResult.add(new JoinedDataRow<>(4, null, "Germany"));
        Collection<JoinedDataRow<Integer, String, String>> result = JoinedDataRow.rightJoin(leftCollection, rightCollection, joinOperation);
        assertIterableEquals(expectedResult, result);

    }

    @Test
    void whenLeftCollectionHaveElementWithNullValueKeyShouldThrowNullPointerExceptionInInnerJoinAndLeftJoinMethods() {
        leftCollection.add(new DataRow<>(null, "SomeCity"));

        assertThrows(NullPointerException.class, () -> JoinedDataRow.innerJoin(leftCollection, rightCollection, joinOperation));
        assertThrows(NullPointerException.class, () -> JoinedDataRow.leftJoin(leftCollection, rightCollection, joinOperation));

        //Collection<JoinedDataRow<Integer, String, String>> result = JoinedDataRow.innerJoin(leftCollection, rightCollection, joinOperation);
    }

    @Test
    void whenRightCollectionHaveElementWithNullValueKeyShouldThrowNullPointerExceptionInInnerJoinAndRightJoinMethods() {
        rightCollection.add(new DataRow<>(null, "SomeCountry"));
        assertThrows(NullPointerException.class, () -> JoinedDataRow.innerJoin(leftCollection, rightCollection, joinOperation));
        assertThrows(NullPointerException.class, () -> JoinedDataRow.rightJoin(leftCollection, rightCollection, joinOperation));


    }
}