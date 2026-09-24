class Solution {

    private boolean notMoreThen(String s, int maxLength, int numOps) {
        int maxSize = 1;
        char prev = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == prev) {
                maxSize++;
                if (maxSize > maxLength) {
                    prev = '2'; 
                    maxSize = 0;
                    numOps--;
                    if(numOps < 0) return false;
                }
            } else {
                prev = s.charAt(i);
                maxSize = 1;
            }
        }
        return true;
    }

    private boolean canBe1(String s, int numOps) {
        char start = '1';
        int newnumops = numOps;
        for (int i = 0; i < s.length(); i++, start = (start == '1') ? '0' : '1') {
            if (s.charAt(i) != start)
                newnumops--;
        }
        start = '0';
        for (int i = 0; i < s.length(); i++, start = (start == '1') ? '0' : '1') {
            if (s.charAt(i) != start)
                numOps--;
        }
        return (newnumops >= 0) || (numOps >= 0);
    }

    public int minLength(String s, int numOps) {
        // ill hadle if i can make 1 or not

        if (canBe1(s, numOps)) {
            return 1;
        }
        int left = 2;
        int right = s.length();
        int ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (notMoreThen(s, mid, numOps)) {
                right = mid - 1;
                ans = mid;
            }else{
                left = mid + 1;
            }
        }
        return ans;
    }
}