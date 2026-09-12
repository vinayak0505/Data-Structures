class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        StringBuilder sb = new StringBuilder(n + m - 1);
        for (int i = 0; i < n + m - 1; i++) {
            sb.append('a');
        }

        boolean[] fixed = new boolean[n + m - 1];

        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F')
                continue;
            for (int j = 0; j < str2.length(); j++) {
                if (fixed[i + j] == false) {
                    fixed[i + j] = true;
                    sb.setCharAt(i + j, str2.charAt(j));
                }
                if (sb.charAt(i + j) != str2.charAt(j)) {
                    return "";
                }

            }
        }

        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F') {
                if (isEqual(sb, str2, i)) {
                    int changeIdx = -1;
                    for (int k = i + m - 1; k >= i; k--) {
                        if (!fixed[k]) {
                            changeIdx = k;
                            break;
                        }
                    }

                    if (changeIdx == -1)
                        return "";

                    sb.setCharAt(changeIdx, 'b');
                }
            }
        }

        return sb.toString();
    }

    private boolean isEqual(StringBuilder sb, String str2, int i) {
        for (int j = 0; j < str2.length(); j++) {
            if(sb.charAt(i + j) != str2.charAt(j)) return false;
        }
        return true;
    }
}