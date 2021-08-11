 package cs1302.genlist;

 import cs1302.genlistadt.GenList;
 import java.util.Comparator;
 import java.util.function.*;

 /**
  * Test Dtriver that demos the map, reduce, filter, min, and allMatch methods.
  */

 public class LinkedGenListTest {

     public static void main(String [] args) {
         GenList<String> stringList = new LinkedGenList<String>(); //String List
         stringList.add("COVID-19");
         stringList.add("UGA");
         stringList.add("cs1302");
         stringList.add("Piazza");
         stringList.add("Java");
         stringList.add("Project");
         GenList<Integer> intList = new LinkedGenList<Integer>(); //Integer List
         intList.add(10);
         intList.add(20);
         intList.add(30);
         intList.add(40);
         intList.add(50);
         intList.add(60);
         //Below tests each method
         demoMap(stringList,intList);
         demoReduce(stringList,intList);
         demoFilter(stringList,intList);
         demoMin(stringList,intList);
         demoAllMatch(stringList,intList);


     } //main

     /**
      * Tests the {@code map} method on a {@code GenList} of type {@code String}
      * and {@code Integer}. For the {@code String} list each element is made lowercase is made into
      * a Object. For the {@code Integer} list each element is made into a double by adding .1.
      *
      * @param sList This is the String GenList.
      * @param iList This is the Integer GenList.
      */

     public static void demoMap(GenList<String> sList, GenList<Integer> iList) {
         Function<String, Object> mapOne = a -> a.toLowerCase();
         System.out.println();
         System.out.println("MAP METHOD:");
         System.out.println("Demo 1 with String List: all strings are lowercase with type Object");
         System.out.println("Before: " + sList.makeString(","));
         System.out.println("After: " + sList.map(mapOne).makeString(","));
         System.out.println();
         Function<Integer, Double> mapTwo = b -> b + .1;
         System.out.println("Demo 2 with Integer List: all integers are made into doubles");
         System.out.println("Before: " + iList.makeString(","));
         System.out.println("After: " + iList.map(mapTwo).makeString(","));
         System.out.println();
     } //demoMap

     /**
      * Tests the {@code reduce} method on a {@code GenList} of type {@code String}
      * and {@code Integer}. For the {@code String} list the first letter of each element is
      * combined into one word. For the {@code Integer} list the sum is presented.
      *
      * @param sList This is the String GenList.
      * @param iList This is the Integer GenList.
      */

     public static void demoReduce(GenList<String> sList, GenList<Integer> iList) {
         BinaryOperator<String> reduceOne = (a,b) -> a.concat(b.substring(0, 1));
         System.out.println();
         System.out.println("REDUCE METHOD:");
         System.out.println("Demo 1 with String List: First Letter of each word combined. ");
         System.out.println("Before: " + sList.makeString(","));
         System.out.println("After: " + sList.reduce("First Letter of each word: ", reduceOne));
         System.out.println();
         BinaryOperator<Integer> reduceTwo = (a,b) -> a + b;
         System.out.println("Demo 2 with Integer List: Sum of all Numbers. ");
         System.out.println("Before: " + iList.makeString(","));
         System.out.println("After: " + iList.reduce(0, reduceTwo));
         System.out.println();
     } //demoReduce

     /**
      * Tests the {@code filter} method on a {@code GenList} of type {@code String}
      * and {@code Integer}. For the {@code String} list it looks for words that contain a
      * lowercase a or e. For the {@code Integer} list it looks for numbers >20 but <50.
      *
      * @param sList This is the String GenList.
      * @param iList This is the Integer GenList.
      */

     public static void demoFilter(GenList<String> sList, GenList<Integer> iList) {
         Predicate<String> filterOne = a -> (a.indexOf("a") != -1 || a.indexOf("e") != -1);
         System.out.println();
         System.out.println("FILTER METHOD:");
         System.out.println("Demo 1 with String List: Words with lowercase a or e. ");
         System.out.println("Before: " + sList.makeString(","));
         System.out.println("After: " + sList.filter(filterOne).makeString(","));
         System.out.println();
         Predicate<Integer> filterTwo = a -> (a < 50 && a > 20);
         System.out.println("Demo 2 with Integer List: Numbers greater than 20 less than 50. ");
         System.out.println("Before: " + iList.makeString(","));
         System.out.println("After: " + iList.filter(filterTwo).makeString(","));
         System.out.println();
     } //demoFilter

     /**
      * Tests the {@code min} method on a {@code GenList} of type {@code String}
      * and {@code Integer}. For the {@code String} list it find the smallest string.
      * For the {@code Integer} list it looks for the smallest number.
      *
      * @param sList This is the String GenList.
      * @param iList This is the Integer GenList.
      */

     public static void demoMin(GenList<String> sList, GenList<Integer> iList) {
         Comparator<String> minOne = (a,b) -> {
             if (a.length() < b.length()) {
                 return -1;
             } else if (a.length() == b.length()) {
                 return 0;
             } else {
                 return 1;
             }
         };
         System.out.println();
         System.out.println("MIN METHOD:");
         System.out.println("Demo 1 with String List: Smallest String ");
         System.out.println("Before: " + sList.makeString(","));
         System.out.println("After: " + sList.min(minOne));
         System.out.println();
         Comparator<Integer> minTwo = (a,b) -> {
             if (a < b) {
                 return -1;
             } else if (a == b) {
                 return 0;
             } else {
                 return 1;
             }
         };
         System.out.println("Demo 2 with Integer List: Smallest Number ");
         System.out.println("Before: " + iList.makeString(","));
         System.out.println("After: " + iList.min(minTwo));
         System.out.println();
     } //demoMin

     /**
      * Tests the {@code allMatch} method on a {@code GenList} of type {@code String}
      * and {@code Integer}. For the {@code String} list it checks if every element's first letter is
      * either a upercase U or P. For the {@code Integer} list it checks if all numbers are positive
      * and not 0.
      *
      * @param sList This is the String GenList.
      * @param iList This is the Integer GenList.
      */

     public static void demoAllMatch(GenList<String> sList, GenList<Integer> iList) {
         Predicate<String> allMatchOne = a -> (a.charAt(0) == 'U' || a.charAt(0) == 'P');
         System.out.println();
         System.out.println("ALL MATCH METHOD:");
         System.out.println("Demo 1 with String List: First letter is either U or P for all ");
         System.out.println("Before: " + sList.makeString(","));
         System.out.println("After: " + sList.allMatch(allMatchOne));
         System.out.println();
         Predicate<Integer> allMatchTwo = a -> (a >= 0) && (a != 0);
         System.out.println("Demo 2 with Integer List: Number is positive and not 0 ");
         System.out.println("Before: " + iList.makeString(","));
         System.out.println("After: " + iList.allMatch(allMatchTwo));
         System.out.println();
     } //demoAllMatch




 } //LinkedGenListTest

