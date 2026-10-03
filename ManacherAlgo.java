public class ManacherAlgo {
    StringBuilder builder;
    int palindromeLength[];

    public ManacherAlgo(String s) {
        builder = new StringBuilder();
        builder.append('#');

        for (char c : s.toCharArray()) {
            builder.append(c);
            builder.append('#');
        }

        build();
    }

    private void build() {
        int n = builder.length();
        palindromeLength = new int[n];

        int indexWithFartherestPalindrome = -1;
        int size = 0;

        for (int i = 0; i < n; i++) {
            int curSize = 0;
            if (indexWithFartherestPalindrome + size > i) {
                curSize = Math.max(0, Math.min(indexWithFartherestPalindrome + size - i,
                        palindromeLength[2 * indexWithFartherestPalindrome - i]));
            }
            while (i - curSize >= 0 && i + curSize < n && builder.charAt(i - curSize) == builder.charAt(i + curSize)) {
                curSize++;
            }
            palindromeLength[i] = curSize;
            if (i + curSize > indexWithFartherestPalindrome + size) {
                indexWithFartherestPalindrome = i;
                size = curSize;
            }
        }
    }

    public boolean isPalindrome(int start, int end) {
        start = start * 2 + 1;
        end = end * 2 + 1;
        int mid = (end + start) / 2;
        int size = end - mid;
        return size < palindromeLength[mid];
    }
}
