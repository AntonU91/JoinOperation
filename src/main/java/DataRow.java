public class DataRow<K extends Number, V > {
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
}

//    @Override
//    public int compareTo(DataRow<K, V> o) {
//        return (Number) this.getKey() - o.getKey());
//    }
//}
