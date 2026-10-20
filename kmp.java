import java.util.ArrayList;

public class kmp {
    public ArrayList<Integer> getAllMatched(String str, String pattern) {

        int p = pattern.length();

        int piTable[] = new int[p];

        for (int i = 1, j = 0; i < piTable.length; i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = piTable[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            piTable[i] = j;
        }

        ArrayList<Integer> ans = new ArrayList<>();

        for (int i = 0, j = 0; i < str.length(); i++) {
            while (j > 0 && str.charAt(i) != pattern.charAt(j)) {
                j = piTable[j - 1];
            }
            if (str.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            if (j == p) {
                ans.add(i - j + 1);
                j = piTable[j - 1];
            }
        }
        return ans;
    }
}
