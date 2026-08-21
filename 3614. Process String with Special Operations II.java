class Solution {
    public char processStr(String s, long k) {
        long count = 0;
        int n = s.length();
        boolean isInvalid[] = new boolean[n];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            switch (c) {
                case '*':
                    if (count > 0)
                        count--;
                    else {
                        isInvalid[i] = true;
                    }
                    break;
                case '#':
                    count *= 2;
                    break;
                case '%':
                    break;

                default:
                    count++;
                    break;
            }
        }

        if (count == 0 || k >= count)
            return '.';

        char ans = '.';
        for (int i = s.length() - 1; i >= 0; i--) {
            if(isInvalid[i]) continue;
            char c = s.charAt(i);
            switch (c) {
                case '*':
                    count++;
                    break;
                case '#':
                    count /= 2;
                    if (count == 0)
                        continue;
                    k = k % count;
                    break;
                case '%':
                    k = count - 1 - k;
                    break;
                default:
                    count--;
                    if (count == k) {
                        return c;
                    }
                    break;
            }
        }

        return ans;
    }
}