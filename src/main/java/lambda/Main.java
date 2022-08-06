package lambda;

public class Main {
    public static void main(String[] args) {
        SimpleClass  simpleClass = new SimpleClass(new int[] {1,5,9});
        Summator summator = simpleClass.getSummatorInstance();
        System.out.println(summator.getSum(simpleClass.array));
    }
}
