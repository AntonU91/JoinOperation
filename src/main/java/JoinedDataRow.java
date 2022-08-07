import com.sun.istack.internal.NotNull;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JoinedDataRow<K, V1, V2> {
    private K key;
    private V1 firstValue;
    private V2 secondValue;
    private static JoinOperation joinOperation;

    public JoinedDataRow(K key, V1 firstValue, V2 secondValue) {
        this.key = key;
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public static Collection<JoinedDataRow<Integer, String, String>> innerJoin(Collection<DataRow<Integer, String>> leftCollection, Collection<DataRow<Integer, String>> rightCollection, JoinOperation joinOperation) {
        checkCollectionsForNullValueKey(leftCollection, rightCollection);
        Collection<JoinedDataRow<Integer, String, String>> joinedDataRow = new ArrayList<>();
        joinOperation = (left, right) -> {
            for (DataRow<Integer, String> dataRowLeft : leftCollection) {
                for (DataRow<Integer, String> dataRowRight : rightCollection) {
                    if (dataRowLeft.getKey().equals(dataRowRight.getKey())) {
                        joinedDataRow.add(new JoinedDataRow<>(dataRowLeft.getKey(), dataRowLeft.getValue(), dataRowRight.getValue()));
                    }
                }
            }
            return joinedDataRow;
        };
        return joinOperation.join(leftCollection, rightCollection);
    }

    public static Collection<JoinedDataRow<Integer, String, String>> leftJoin(Collection<DataRow<Integer, String>> leftCollection, Collection<DataRow<Integer, String>> rightCollection,
                                                                              JoinOperation joinOperation) {
        checkCollectionsForNullValueKey(leftCollection, rightCollection);
        Collection<JoinedDataRow<Integer, String, String>> joinedDataRow = new ArrayList<>();
        joinOperation = (left, right) -> {
            for (DataRow<Integer, String> dataRowLeft : leftCollection) {
                boolean keyMatching = false;
                for (DataRow<Integer, String> dataRowRight : rightCollection) {
                    if (dataRowLeft.getKey().equals(dataRowRight.getKey())) {
                        keyMatching = true;
                        joinedDataRow.add(new JoinedDataRow<>(dataRowLeft.getKey(), dataRowLeft.getValue(), dataRowRight.getValue()));
                    }
                }
                if (!keyMatching) {
                    joinedDataRow.add(new JoinedDataRow<>(dataRowLeft.getKey(), dataRowLeft.getValue(), null));
                }

            }
            return joinedDataRow;
        };
        return joinOperation.join(leftCollection, rightCollection);
    }

    public static Collection<JoinedDataRow<Integer, String, String>> rightJoin(Collection<DataRow<Integer, String>> leftCollection, Collection<DataRow<Integer, String>> rightCollection,
                                                                               JoinOperation joinOperation) {
        checkCollectionsForNullValueKey(leftCollection, rightCollection);
        Collection<JoinedDataRow<Integer, String, String>> joinedDataRow = new ArrayList<>();
        joinOperation = (left, right) -> {
            for (DataRow<Integer, String> dataRowRight : rightCollection) {
                boolean keyMatching = false;
                for (DataRow<Integer, String> dataRowLeft : leftCollection) {
                    if (dataRowRight.getKey().equals(dataRowLeft.getKey())) {
                        keyMatching = true;
                        joinedDataRow.add(new JoinedDataRow<>(dataRowRight.getKey(), dataRowLeft.getValue(), dataRowRight.getValue()));
                    }
                }
                if (!keyMatching) {
                    joinedDataRow.add(new JoinedDataRow<>(dataRowRight.getKey(), null, dataRowRight.getValue()));
                }

            }
            return joinedDataRow;
        };
        return joinOperation.join(leftCollection, rightCollection);
    }

    private static void checkCollectionsForNullValueKey(Collection<DataRow<Integer, String>> leftList, Collection<DataRow<Integer, String>> rightList) {
        Collection<DataRow<Integer, String>> unitedCollection = Stream.concat(leftList.stream(), rightList.stream()).collect(Collectors.toList());
        for (DataRow<Integer, String> dataRow : unitedCollection) {
            if (dataRow.getKey() == null) throw new NullPointerException();
        }
    }

    public K getKey() {
        return key;
    }

    public V1 getFirstValue() {
        return firstValue;
    }

    public V2 getSecondValue() {
        return secondValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JoinedDataRow<?, ?, ?> that = (JoinedDataRow<?, ?, ?>) o;
        return Objects.equals(key, that.key) && Objects.equals(firstValue, that.firstValue) && Objects.equals(secondValue, that.secondValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, firstValue, secondValue);
    }

    public static JoinOperation getJoinOperation() {
        return joinOperation;
    }

    @Override
    public String toString() {
        return "JoinedDataRow{" +
                "key=" + key +
                ", firstValue=" + firstValue +
                ", secondValue=" + secondValue +
                '}';
    }

}
