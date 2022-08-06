package lambda;

public class SimpleClass {
    int array [];

    private Summator sm = (array) -> {
        int sum=0;
        for (int i =0; i<array.length; i++) {
            sum+=array[i];
        }
        return sum;
    };

    public SimpleClass(int[] array) {
        this.array = array;
    }


   public Summator getSummatorInstance () {
       return this.sm;
   }
}
