class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();

        int zerosCount[] = new int[n];
        int prev = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0')
                prev++;
            zerosCount[i] = prev;
        }

        int ans = 0;
        for (int start = 0; start < n; start++) {
            for (int end = start; end < n; end++) {

                int zeroCount = zerosCount[end] - (start == 0 ? 0 : zerosCount[start - 1]);
                int oneCount = end - (start - 1) - zeroCount;

                if ((zeroCount * zeroCount) > oneCount) {
                    end = zeroCount * zeroCount + start + zeroCount;
                } else if ((zeroCount * zeroCount) == oneCount) {
                    ans++;
                } else {
                    ans++;
                    int k = (int) Math.sqrt(oneCount) - zeroCount;
                    int next = end + k;
                    if (next >= n) {
                        ans += n - end - 1;
                        break;
                    } else {
                        ans += k;
                    }
                    end = next;

                }
            }
        }
        return ans;

    }
}