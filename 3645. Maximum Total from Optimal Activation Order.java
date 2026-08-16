import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

class Solution {
    public long maxTotal(int[] value, int[] limit) {
        HashMap<Integer, ArrayList<Integer>> limitToValueArrayMap = new HashMap<>();
        int n = value.length;
        for(int i = 0;i<n;i++){
            ArrayList<Integer> valueArray = limitToValueArrayMap.computeIfAbsent(limit[i], (_) -> new ArrayList<>());
            valueArray.add(value[i]);
        }

        long ans = 0;

        for(Entry<Integer,ArrayList<Integer>> entrySet: limitToValueArrayMap.entrySet()){
            entrySet.getValue().sort((a, b) -> b - a);
            for(int i = 0; i< entrySet.getKey() && i < entrySet.getValue().size();i++){
                ans += entrySet.getValue().get(i);
            }
        }
        return ans;
    }
}