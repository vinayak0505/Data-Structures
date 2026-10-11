class Solution {

    public int maxOperations(String s) {
        int ans = 0;
        int ones = 0;
        boolean got0 = false;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                if (got0) {
                    got0 = false;
                    ans += ones;
                }
                ones++;
            } else {
                got0 = true;
            }
        }
        if (got0) {
            return ans + ones;
        }
        return ans;
    }
}