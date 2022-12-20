import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
        // Java program to implement the
// above approach

//        static Character[][] numberToCharMap;
//
//        private static List<String> printWords(int[] numbers,
//                                               int len,
//                                               int numIndex,
//                                               String s)
//        {
//            if (len == numIndex) {
//                return new ArrayList<>(
//                        Collections.singleton(s));
//            }
//
//            List<String> stringList = new ArrayList<>();
//
//            for (int i = 0;
//                 i < numberToCharMap[numbers[numIndex]].length;
//                 i++) {
//                String sCopy
//                        = String.copyValueOf(s.toCharArray());
//                sCopy = sCopy.concat(
//                        numberToCharMap[numbers[numIndex]][i]
//                                .toString());
//                stringList.addAll(printWords(
//                        numbers, len, numIndex + 1, sCopy));
//            }
//            return stringList;
//        }
//
//        private static void printWords(int[] numbers)
//        {
//            generateNumberToCharMap();
//            List<String> stringList
//                    = printWords(numbers, numbers.length, 0, "");
//            stringList.stream().forEach(System.out::println);
//        }
//
//        private static void generateNumberToCharMap()
//        {
//            numberToCharMap = new Character[10][5];
//            numberToCharMap[0] = new Character[] { '$' };
//            numberToCharMap[1] = new Character[] { '@' };
//            numberToCharMap[2]
//                    = new Character[] { 'a', 'b', 'c' };
//            numberToCharMap[3]
//                    = new Character[] { 'd', 'e', 'f' };
//            numberToCharMap[4]
//                    = new Character[] { 'g', 'h', 'i' };
//            numberToCharMap[5]
//                    = new Character[] { 'j', 'k', 'l' };
//            numberToCharMap[6]
//                    = new Character[] { 'm', 'n', 'o' };
//            numberToCharMap[7]
//                    = new Character[] { 'p', 'q', 'r', 's' };
//            numberToCharMap[8]
//                    = new Character[] { 't', 'u', 'v' };
//            numberToCharMap[9]
//                    = new Character[] { 'w', 'x', 'y', 'z' };
//        }
//
//        // Driver code
        public static void main(String[] args)
        {
            Scanner s = new Scanner(System.in);
            System.out.print("nhập số có 2 chữ số:");
            int num = s.nextInt();
            int[] number = {num/10, num%10};

            // Function call
            tohopsdt(num);
        }
//

    static void tohopsdt(int num) {

        String[] list1 = null;
        String[] list2= null;
        String[] list3= null;
        Integer so1 = null;
        Integer so2 = null;
        Integer so3 = null;
        String sNum = String.valueOf(num);
        if(sNum != null && sNum.length() > 0) {
            for(int i = 0; i < sNum.length(); i++) {
                if(i == 0) {
                    char s = sNum.charAt(i);
//                    so1 = Integer.parseInt(s);
                    so1 = Integer.parseInt(String.valueOf(s));
                    list1 = getData(so1);
                }else if(i ==1) {
                    char s = sNum.charAt(i);
                    so2 = Integer.parseInt(String.valueOf(s));
                    list2= getData(so2);
                }else if(i == 2) {
                    char s = sNum.charAt(i);
                    so3 = Integer.parseInt(String.valueOf(s));
                    list2= getData(so3);
                }
            }
        }//end for


        if(list1 != null && list1.length > 0) {
            for(String s1: list1) {
                if(list2 != null && list2.length > 0) {
                    for(String s2: list2) {
                        if(list3 != null && list3.length > 0) {
                            for(String s3: list3) {
                                if(sNum.length() == 3) {
                                    System.out.println(s1+s2+s3);
                                }
                            }
                        }
                        if(sNum.length() == 2) {
                            System.out.println(s1+s2);
                        }
                    }
                }
                if(sNum.length() == 1) {
                    System.out.println(s1);
                }
            }
        }

    }

    public static String[] getData(Integer num) {
        if(num != null) {

            switch (num) {
                case 1: {
                    String[] s = {"a", "b", "c"};
                    return s;
                }
                case 2: {
                    String[] s = {"d", "e", "f"};
                    return s;
                }
                case 3: {
                    String[] s = {"g", "h", "i"};
                    return s;
                }
                default:
                    throw new IllegalArgumentException("Unexpected value: " + num);
            }
        }
        return null;
    }
    }

