import java.util.ArrayList;
import java.util.List;

public class KMPAlgorithm {

    /**
     * Finds all starting indices where the pattern occurs in the text.
     * 
     * @param text    The main string to search within.
     * @param pattern The substring to search for.
     * @return A list of starting indices of all matches.
     */
    public List<Integer> findAllOccurrences(String text, String pattern) {
        if (text == null || pattern == null || pattern.isEmpty()) {
            return new ArrayList<>();
        }

        int patternLength = pattern.length();
        int[] lpsTable = new int[patternLength];

        // Step 1: Precompute the LPS (Longest Proper Prefix which is also Suffix) table
        for (int currentIndex = 1, prefixLength = 0; currentIndex < patternLength; currentIndex++) {
            while (prefixLength > 0 && pattern.charAt(currentIndex) != pattern.charAt(prefixLength)) {
                prefixLength = lpsTable[prefixLength - 1];
            }
            if (pattern.charAt(currentIndex) == pattern.charAt(prefixLength)) {
                prefixLength++;
            }
            lpsTable[currentIndex] = prefixLength;
        }

        // Step 2: Search for the pattern in the text using the LPS table
        List<Integer> matchingIndices = new ArrayList<>();
        for (int textIndex = 0, patternIndex = 0; textIndex < text.length(); textIndex++) {
            while (patternIndex > 0 && text.charAt(textIndex) != pattern.charAt(patternIndex)) {
                patternIndex = lpsTable[patternIndex - 1];
            }
            if (text.charAt(textIndex) == pattern.charAt(patternIndex)) {
                patternIndex++;
            }
            if (patternIndex == patternLength) {
                matchingIndices.add(textIndex - patternIndex + 1);
                patternIndex = lpsTable[patternIndex - 1];
            }
        }

        return matchingIndices;
    }
}