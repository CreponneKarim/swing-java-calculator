// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        BlockingQueue p1 = new BlockingQueue(); // entre gui et calc
        BlockingQueue p2 = new BlockingQueue(); // entre gui et trace
        BlockingQueue p3 = new BlockingQueue(); // entre calc et trace

        Gui gui = new Gui(p2,p1);
        new Thread(gui).start();

        System.out.println("works");
        EvaluatorWrapper ew = new EvaluatorWrapper(p3,p1);
        new Thread(ew).start();

        System.out.println("je3fer");
        Trace tr = new Trace(p2,p3);
        new Thread(tr).start();
        System.out.println("works");
    }
}