import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PatternMatchingPractice {
    //for rk
    private static final int BASE = 113;

    // brute force implementation
    // best case - no match - O(n)
    // best case - one occurrence only - O(m)
    public static void bruteForce(String text, String pattern) {
        int N = text.length();
        int M = pattern.length();
        /* A loop to slide pattern one by one */
        for (int i = 0; i <= N - M; i++) {
            int j;
            // For current index i, check for pattern match.
            for (j = 0; j < M; j++) {
                if (text.charAt(i + j) != text.charAt(j)) {
                    break;
                }
            }
            if (j == M) {
                System.out.println("Pattern found at index " + i);
            }
        }
    }

    // buildLastOccurrenceTable for Boyer Moore;
    // best/avg/worst - O(m);
    public static Map<Character, Integer> buildLastOccurrenceTable(CharSequence pattern) {
        Map<Character, Integer> lastTable = new HashMap<Character, Integer>();
        for (int i = 0; i < pattern.length(); i++) {
            lastTable.put(pattern.charAt(i), i);
        }
        return lastTable;
    }

    public static List<Integer> boyerMoore(CharSequence text, CharSequence pattern) {
        Map<Character, Integer> lastTable = buildLastOccurrenceTable(pattern);
        List matches = new LinkedList();
        int i = 0;
        if (pattern.length() > text.length()) {
            return matches;
        }
        while (i <= text.length() - pattern.length()) {
            int j = pattern.length() - 1;
            while (j >= 0 && text.charAt(i + j) == pattern.charAt(j)) {
                j = j - 1;
            }
            if (j == -1) {
                ((LinkedList) matches).addLast(i);
                i++;
            } else {
                Integer shiftedIndex = lastTable.get(text.charAt(i + j));
                if (shiftedIndex == null) {
                    i = i + j + 1;
                } else if (shiftedIndex < j) {
                    i = i + (j - shiftedIndex);
                } else {
                    i++;
                }
            }
        }
        return matches;
    }

    public static int[] buildFailureTable(CharSequence pattern) {
        int[] failureTable = new int[pattern.length()];
        int i = 0;
        int j = 1;
        failureTable[0] = 0;
        while (j < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                i++;
                failureTable[j] = i;
                j++;
            } else {
                if (i == 0) {
                    failureTable[j] = 0;
                    j++;
                } else {
                    i = failureTable[i - 1];
                }
            }
        }
        return failureTable;
    }

    public static List<Integer> kmp(CharSequence text, CharSequence pattern) {
        List matches = new LinkedList();
        if (pattern.length() > text.length()) {
            return matches;
        }
        int[] failureTable = buildFailureTable(pattern);
        int i = 0;
        int j = 0;
        while (i <= text.length() - pattern.length()) {
            while (j < pattern.length() && text.charAt(i + j) == pattern.charAt(j)) {
                j++;
            }
            if (j == 0) {
                i++;
            } else {
                if (j == pattern.length()) {
                    ((LinkedList) matches).addLast(i);
                }
                int nextAlignment = failureTable[j - 1];
                i = i + j - nextAlignment;
                j = nextAlignment;
            }
        }
        return matches;
    }

    public static List<Integer> rabinKarp(CharSequence text, CharSequence pattern) {
        List matches = new LinkedList();
        long patternHash = 0;
        long textHash = 0;
        long highest = 1;
        if (pattern.length() > text.length()) {
            return matches;
        }
        for (int i = 0; i < pattern.length(); i++) {
            patternHash = patternHash * BASE + pattern.charAt(i);
            highest = BASE * highest;
            textHash = textHash * BASE + text.charAt(i);
        }
        int i = 0;
        while (i <= text.length() - pattern.length()) {
            if (patternHash == textHash) {
                int j = 0;
                while (j < pattern.length() && text.charAt(i + j) == pattern.charAt(j)) {
                    j++;
                }
                if (j == pattern.length()) {
                    ((LinkedList) matches).addLast(i);
                }
            }
            i++;
            if (i <= text.length() - pattern.length()) {
                textHash = (textHash - text.charAt(i - 1) * highest / BASE) * BASE + text.charAt(i + pattern.length() - 1);
            }
        }
        return matches;
    }
}
