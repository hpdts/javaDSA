import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        System.out.println("Hello, World! 2");
        for (int i = 0; i < 10; i++) {
            System.out.println("Hello, World! " + i);
        }

        for( String s : args) {
            System.out.println(s);
        }

        List<String> l = new ArrayList<String>();
        l.add("Hello");
        l.add("World");
        l.add("2");
        System.out.println(l);
    }
}
