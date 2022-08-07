import com.sun.istack.internal.NotNull;
import com.sun.org.apache.xpath.internal.operations.Number;

import java.util.Comparator;
import java.util.Objects;

public class DataRow<K extends Integer, V > implements Comparator<DataRow<K,V>> {
    private K key;
    private V value;

    public DataRow(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public int compare(DataRow o1, DataRow o2) {
        return o1.getKey()-o2.getKey();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataRow<?, ?> dataRow = (DataRow<?, ?>) o;
        return Objects.equals(key, dataRow.key) && Objects.equals(value, dataRow.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}
