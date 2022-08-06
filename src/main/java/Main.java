import java.util.*;

public class Main {
    public static void main(String[] args) {
        JoinOperation joinOperation = JoinedDataRow.getJoinOperation();

        ArrayList<DataRow<Integer, String>> leftCollection = new ArrayList<>();
        leftCollection.add(new DataRow<>(1, "Kyiv"));
        leftCollection.add(new DataRow<>(2, "Budapesht"));
        leftCollection.add(new DataRow<>(3, "London"));

        ArrayList<DataRow<Integer, String>> rightCollection = new ArrayList<>();
        rightCollection.add(new DataRow<>(1, "Ukraine"));
        rightCollection.add(new DataRow<>(2, "Hungary"));
        rightCollection.add(new DataRow<>(4,"Germany"));

        Collection<JoinedDataRow<Integer, String, String>> innerJoin = JoinedDataRow.innerJoin(leftCollection, rightCollection, joinOperation);
        for (JoinedDataRow<Integer, String, String> joinedDataRow : innerJoin) {
            System.out.println(joinedDataRow);
        }

        System.out.println("________________________-");

        Collection<JoinedDataRow<Integer, String, String>> leftJoin = JoinedDataRow.leftJoin(leftCollection, rightCollection, joinOperation);
        for (JoinedDataRow<Integer, String, String> joinedDataRow : leftJoin) {
            System.out.println(joinedDataRow);

        }
        System.out.println("________________________-");

        Collection<JoinedDataRow<Integer, String, String>> rightJoin = JoinedDataRow.rightJoin(leftCollection, rightCollection, joinOperation);
        for (JoinedDataRow<Integer, String, String> joinedDataRow : rightJoin) {
            System.out.println(joinedDataRow);

        }

    }
}
