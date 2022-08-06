package references_to_methods;


class Main {
    public static void main(String[] args) {
        SimpleGen simpleGen = new SimpleGen(5);
        Generator generator = simpleGen::getNumber;
        int temp = generator.getNextElement();
        System.out.println(temp);

    }
}


public class SimpleGen {
    private int number;

    public SimpleGen(int number) {
        super();
        this.number = number;
    }

    public SimpleGen() {
        super();
    }

    public int getNumber() {
        int temp = number;
        number = number + 1;
        return temp;
    }
}

@FunctionalInterface
interface Generator {
    public int getNextElement();
   // String
}


