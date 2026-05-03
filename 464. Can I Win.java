import java.util.HashMap;
import java.util.Map;

class Solution {
    private Map<Integer, Map<Integer, Boolean>> mp;
    private boolean canIWin(int maxChoosableInteger, int desiredTotal,int used){
        if(desiredTotal <= 0) return false;

        if(mp.containsKey(used) == false){
            mp.put(used, new HashMap<Integer, Boolean>());
        }

        Map<Integer, Boolean> dp = mp.get(used);

        if(dp.containsKey(desiredTotal)) return dp.get(desiredTotal);

        for(int i = 1;i<=maxChoosableInteger;i++){
            if(((1 << i) | used) == used) continue;
            if(canIWin(maxChoosableInteger, desiredTotal - i, used | (1 << i)) == false) {
                dp.put(desiredTotal, true);
                return true;
            }
        }
        dp.put(desiredTotal, false);
        return false;
    }

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        mp = new HashMap<>();
        if(desiredTotal == 0) return true;
        
        // Add this: if max possible sum < desiredTotal, impossible to win
        int maxSum = maxChoosableInteger * (maxChoosableInteger + 1) / 2;
        if(maxSum < desiredTotal) return false;
        
        return canIWin(maxChoosableInteger, desiredTotal, 0);
    }
}