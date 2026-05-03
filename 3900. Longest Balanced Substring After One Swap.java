import java.util.HashMap;
import java.util.Map;

class Solution {
    private boolean check(boolean start, int i,boolean zero, int[] array){
        int n = array.length;
        if(i == n || i < 0) return false;

        if(start){
            if(zero){
                return (array[n - 1] > array[i]);
            }else{
                return (n - 1 - i > array[n - 1] - array[i]);
            }
        }else{
            if(zero){
                return (array[i] > 0);
            }else{
                return ((i + 1 - array[i]) > 0);
            }
        }
    }

    public int longestBalanced(String s) {
        int n = s.length();
        int startzero[] = new int[n];
        int presum = 0;
        for(int i = 0;i<n;i++){
            if(s.charAt(i) == '0')
                presum++;
            startzero[i] = presum;
        }
        // presum = 0;
        // for(int i = n - 1;i>=0;i--){
        //     if(s.charAt(i) == '0')
        //         presum++;
        //     endzero[i] = presum;
        // }

        int ans = 0;
        Map<Integer, Integer> mp = new HashMap<>();
        mp.put(0, -1);
        int sum = 0;
        for(int i = 0;i<n;i++){
            if(s.charAt(i) == '0'){
                sum--;
            }else{
                sum++;
            }
            if(mp.containsKey(sum)){
                ans = Math.max(ans, i - mp.get(sum));
                System.out.println("normal " + ans);
            }else{
                mp.put(sum, i);
            }

            if(mp.containsKey(sum - 2) && check(true, i + 1, true, startzero)){
                ans = Math.max(ans, i - mp.get(sum - 2));
                // System.out.println("start with 0 " + ans + " " + i);

            }
            if(mp.containsKey(sum + 2) && check(true, i + 1, false, startzero)){
                ans = Math.max(ans, i - mp.get(sum + 2));
                // System.out.println("start with 1 " + ans + " " + i);
            }
        }

        mp = new HashMap<>();
        mp.put(0, n);
        sum = 0;

        for(int i = n - 1;i>=0;i--){
            if(s.charAt(i) == '0'){
                sum--;
            }else{
                sum++;
            }
            if(mp.containsKey(sum)){
                ans = Math.max(ans, i - mp.get(sum));
            }else{
                mp.put(sum, i);
            }

            if(mp.containsKey(sum - 2) && check(false, i - 1, true, startzero)){
                ans = Math.max(ans, mp.get(sum - 2) - i);
            }
            if(mp.containsKey(sum + 2) && check(false, i - 1, false, startzero)){
                ans = Math.max(ans, mp.get(sum + 2) - i);
            }
        }

        
        return ans;
    }
}