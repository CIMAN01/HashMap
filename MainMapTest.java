import java.util.*;

public class HashMapTest {

    public static void main (String[]args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("this", 1);
        map.put("coder", 2);
        map.put("this", 4);
        map.put("hi", 5);
        System.out.println(map.size());
        System.out.println(map.get("this"));
        map.remove("this");
        System.out.println(map.get("this"));
        System.out.println(map.size());
        System.out.println(map.isEmpty());


        /** output:
         *      3
         *      4
         *      null
         *      2
         *      false
         */

    }
}
