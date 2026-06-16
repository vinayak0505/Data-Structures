import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class Solution {

    private int getPrevLargest(ArrayList<Integer> arr, int index){
        if(arr == null) return 0;
        int ans = 0;
        int l = 0;
        int r = arr.size() - 1;
        while(l <= r){
            int mid = l + (r - l) / 2;
            if(arr.get(mid) < index){
                ans = mid + 1;
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }
        return ans;
    }

    public long countStableSubarrays(int[] capacity) {
        int n = capacity.length;

        long presum = 0;

        // number -> sum, list of array
        HashMap<Integer, HashMap<Long, ArrayList<Integer>>> mp = new HashMap<>();

        for (int i = n - 1; i >= 0; i--) {
            presum += capacity[i];
            if(mp.containsKey(capacity[i]) == false){
                mp.put(capacity[i], new HashMap<>());
            }

            HashMap<Long,ArrayList<Integer>> hashMap = mp.get(capacity[i]);

            if(hashMap.containsKey(presum) == false){
                hashMap.put(presum, new ArrayList<Integer>());
            }

            ArrayList<Integer> arrayList = hashMap.get(presum);
            arrayList.add(i);
        }

        for (int key : mp.keySet()) {
            HashMap<Long,ArrayList<Integer>> hashMap = mp.get(key);
            for (long sum : hashMap.keySet()) {
                ArrayList<Integer> arrayList = hashMap.get(sum);
                Collections.reverse(arrayList);
            }
        }
        long ans = 0;
        presum = 0;
        for(int i = n - 1; i >= 0;i--){
            presum += capacity[i];
            ans += getPrevLargest(mp.get(capacity[i]).get(presum + capacity[i] + capacity[i]), i - 1);
        }
        return ans;
    }
}