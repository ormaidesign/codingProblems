import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        System.out.println("Implemented functions");
        String reversedString = reverseString("Hello Genpact!");
        System.out.println("to reverse a string: %s %b".formatted(reversedString, reversedString.equals("!tcapneG olleH")));
        boolean isGenpactPalindrome = isPalindrome("Genpact");
        System.out.println("is Genpact a palindrome: %b".formatted(isGenpactPalindrome));
        boolean istpacneGGenpactPalindrome = isPalindrome("tcapneGGenpact");
        System.out.println("is tcapneGGenpact a palindrome: %b".formatted(istpacneGGenpactPalindrome));
        int[] arr = {14324234, 43432, 453333, 676744, 5666533, 6002323, 7, 8};
        int max = findMax(arr);
        System.out.println("to find max value in arr: %d".formatted(max));
        int factorial = factorial(5);
        System.out.println("to find factorial of 5: %d".formatted(factorial));
        boolean isAnagram1 = isAnagram("spar", "rasp");
        System.out.println("is spar anagram of rasp: %b".formatted(isAnagram1));
        boolean isAnagram2 = isAnagram("spartacus", "rasptaucS");
        System.out.println("is spar anagram of rasp: %b".formatted(isAnagram2));
        int fibonacci = fibonacci(10);
        System.out.println("to find 10th fibonacci number: %d".formatted(fibonacci));
        boolean isPrime1 = isPrime(7);
        System.out.println("is 7 a prime number: %b".formatted(isPrime1));
        int primeNum2 = 6700417;
        boolean isPrime2 = isPrime(primeNum2);
        System.out.println("is %d a prime number: %b".formatted(primeNum2, isPrime2));
        int nonePrimeNum1 = 5764889;
        boolean isPrime3 = isPrime(nonePrimeNum1);
        System.out.println("is %d a prime number: %b".formatted(nonePrimeNum1, isPrime3));
        int a[] = { 1, 2, 5, 6, 2, 3, 5 };
        int b[] = { 2, 4, 5, 6, 8, 9, 4, 6, 5 };
        int[] intersection = intersection(a, b);
        String intersectionStr = Arrays.stream(intersection).mapToObj(String::valueOf).collect(Collectors.joining(", "));
        System.out.println("to find intersection of two arrays: %s".formatted(intersectionStr));
        int[] noDuplication = removeDuplicates(b);
        String noDuplicationStr = Arrays.stream(noDuplication).mapToObj(String::valueOf).collect(Collectors.joining(", "));
        System.out.println("to remove duplicates from an array: %s".formatted(noDuplicationStr));
        String[] strArr = {"Hello Genpact!", "Hello Genpacter!", "Hello Groovy!", "Hello Globe!", "Hello Giorno!"};
        String longestCommonPrefix = longestCommonPrefix(strArr);
        System.out.println("to find longest common prefix: %s".formatted(longestCommonPrefix));
    }

    // implement a function to reverse a string
    public static String reverseString(String str) {
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }

    // implement a function to check if a string is a palindrome
    public static boolean isPalindrome(String str) {
        return str.equals(reverseString(str));
    }

    // implement a function to find the maximum value in an array
    public static int findMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; ++i) {
            if (arr[i] > max) max = arr[i];
        }
        return max;
        // return Arrays.stream(arr).max().getAsInt(); // easier way
    }

    // implement a function to find the factorial of a number
    public static int factorial(int n) {
        if (n == 0) return 1;
        return n * factorial(n - 1);
        // return IntStream.rangeClosed(1, n).reduce(1, (x, y) -> x * y); // easier way and less stack usage
    }

    // Anagram - a word, phrase, or name formed by rearranging the letters of another, such as spar, formed from rasp.
    // Implement a function to check if two string are anagrams
    public static boolean isAnagram(String str1, String str2) {
        if (str1.length() != str2.length()) return false;
        int[] count = new int[256];
        for (int i = 0; i < str1.length(); ++i) {
            ++count[str1.charAt(i)];
            --count[str2.charAt(i)];
        }
        for (int i = 0; i < 256; ++i) {
            if (count[i] != 0) return false;
        }
        return true;
    }

    // implement a function to find nth Fibonacci number
    public static int fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
        // return IntStream.rangeClosed(1, n).reduce(1, (x, y) -> x * y); // easier way and less stack usage
    }

    // implement a function to check if a number is prime
    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false; // just to make it faster
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }

    // implement a function to find intersection of two arrays
    public static int[] intersection(int[] arr1, int[] arr2) {
        Map<Integer, Integer> valuesMap = new HashMap<>();

        // Inserting array elements in map with their counts
        addArrToMapForCount(arr1, valuesMap);
        addArrToMapForCount(arr2, valuesMap);

        // If count of element is greater than 1, then add it to result
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : valuesMap.entrySet()) {
            if (entry.getValue() > 1) {
                result.add(entry.getKey());
            }
        }
        return result.stream().mapToInt(i -> i).toArray();
    }

    private static void addArrToMapForCount(int[] arr1, Map<Integer, Integer> valuesMap) {
        boolean firstRound = valuesMap.isEmpty();
        Set<Integer> keySet = new HashSet<>();
        keySet.addAll(Arrays.stream(arr1).boxed().collect(Collectors.toList()));
        Iterator<Integer> it1 = keySet.iterator();
        while(it1.hasNext()) {
            Integer key = it1.next();
            if (firstRound) {
                valuesMap.put(key, 1);
            } else {
                valuesMap.put(key, valuesMap.containsKey(key) ? 2 : 1);
            }
        }
    }

    // implement a function to remove duplicate elements from an array
    public static int[] removeDuplicates(int[] arr) {
        Set<Integer> set = new HashSet<>();
        set.addAll(Arrays.stream(arr).boxed().collect(Collectors.toList()));
        return set.stream().mapToInt(i -> i).toArray();
    }

    // implement a function to find the longest common prefix in an array of strings
    public static String longestCommonPrefix(String[] arr) {
        if (arr.length == 0) return "";
        String prefix = arr[0];
        for (int i = 1; i < arr.length; ++i) {
            while (arr[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }

}