class Solution {
    private int check(int i, int j, boolean canSkip, String s){
        if(i < 0 || j >= s.length()){
            return 0;
        }
        int sum = 0;
        if(i == j){
            sum = 1 + check(i - 1, j + 1, canSkip, s);
        }else if(s.charAt(i) == s.charAt(j)){
            sum = check(i - 1, j + 1, canSkip, s) + 2;
        }else if(canSkip){
            sum = Math.max(check(i - 1,j, false, s), check(i, j +1, false, s)) + 1;
        }
        return sum;
    }

    public int almostPalindromic(String s) {
        int ans = 0;
        for(int i = 0;i<s.length();i++){
            int one = check(i, i, true, s);
            int two = check(i, i + 1, true, s);
            ans = Math.max(ans, Math.max(one, two));
        }
        return ans;
    }
}