import java.util.ArrayList;
import java.util.HashMap;

class Solution {
    Integer[] digits;
    int size;
    HashMap<Integer, Long> dp;
    HashMap<Integer, Long> dp2;
    private long helper(int pos, int carry, int n1, int n2){
        if(carry == 1){
            if(dp.containsKey(pos)) return dp.get(pos);
        }else{
            if(dp2.containsKey(pos)) return dp2.get(pos);
        }
        if(pos == size){
            if(carry == 1) return 0;
            return 1;
        }

        long ans = 0;
        int target = digits[pos];
        int astart = pos < n1 ? 1 : 0;
        int aend = pos < n1 ? 9 : 0;
        int bstart = pos < n2 ? 1 : 0;
        int bend = pos < n2 ? 9 : 0;


        for (int a = astart; a <= aend; a++) {
            for (int b = bstart; b <= bend; b++) {
                int sum = a + b + carry;
                if(sum % 10 == target){
                    ans += helper(pos + 1, sum / 10, n1, n2);
                }
            }
        }
        
        
        if(carry == 1) dp.put(pos, ans);
        else dp2.put(pos, ans);
        return ans;
    }

    public long countNoZeroPairs(long n) {
        ArrayList<Integer> array = new ArrayList<>();
        while(n > 0){
            array.add((int)(n % 10));
            n /= 10;
        }
        long ans = 0;
        digits = array.toArray(new Integer[0]);
        size = digits.length;
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                dp = new HashMap<>();
                dp2 = new HashMap<>();
                ans += helper(0, 0, i, j);
            }
        }
        return ans;
    }
}