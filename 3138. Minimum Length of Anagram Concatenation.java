import java.util.ArrayList;

class Solution {

    private ArrayList<Integer> divisibleList;

    private void fillData(int l) {
        divisibleList = new ArrayList<>();
        for (int i = 1; i * i <= l; i++) {
            if (l % i != 0)
                continue;
            divisibleList.add(i);
            if (i * i != l) {
                divisibleList.add(l / i);
            }
        }

        divisibleList.sort((a, b) -> Integer.compare(a, b));

    }

    private int getUniqueCount(String s) {
        boolean contains[] = new boolean[26];
        for (char c : s.toCharArray()) {
            contains[c - 'a'] = true;
        }
        int data = 0;
        for (boolean c : contains) {
            if (c)
                data++;
        }
        return data;
    }

    private boolean valid(String s, int l) {
        int count[] = new int[26];
        int n = s.length();
        int times = n / l;
        for (int i = 0; i < l; i++) {
            count[s.charAt(i) - 'a']++;
        }

        for (int i = 1; i < times; i++) {
            int tempCount[] = new int[26];

            for (int j = 0; j < l; j++) {
                tempCount[s.charAt(l * i + j) - 'a']++;
            }

            for (int j = 0; j < 26; j++) {
                if(tempCount[j] != count[j]) return false;
            }
        }
        return true;
    }

    public int minAnagramLength(String s) {
        int n = s.length();
        fillData(n);

        int uniqueCount = getUniqueCount(s);

        for (int divi : divisibleList) {
            if (divi < uniqueCount)
                continue;
            if (valid(s, divi))
                return divi;
        }

        throw new Error("Something is wrong with the logic");

    }
}