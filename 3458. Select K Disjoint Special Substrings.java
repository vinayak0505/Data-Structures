import java.util.ArrayList;
import java.util.Arrays;

class Solution {

    class Pair {
        int start;
        int end;

        Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }

    }

    public boolean maxSubstringLength(String s, int k) {
        int charCount = 26;
        int[] firstOcc = new int[charCount];
        int[] lastOcc = new int[charCount];
        Arrays.fill(firstOcc, -1);

        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            if (firstOcc[c] == -1) {
                firstOcc[c] = i;
            }
            lastOcc[c] = i;
        }

        ArrayList<Pair> array = new ArrayList<>();

        for (int charAt = 0; charAt < 26; charAt++) {
            if (firstOcc[charAt] == -1)
                continue;
            int start = firstOcc[charAt], end = lastOcc[charAt];
            for (; start <= end; start++) {
                if (firstOcc[s.charAt(start) - 'a'] < firstOcc[charAt]) {
                    end = -1;
                    break;
                }
                end = Math.max(end, lastOcc[s.charAt(start) - 'a']);
            }
            if (end != -1 && (firstOcc[charAt] != 0 || end != s.length() - 1)) {
                array.add(new Pair(firstOcc[charAt], end));
            }

        }

        array.sort((a, b) -> Integer.compare(a.end, b.end));
        for (int i = 0, end = -1; i < array.size(); i++) {
            if (array.get(i).start <= end)
                continue;
            k--;
            end = array.get(i).end;
        }
        return k <= 0;
    }
}