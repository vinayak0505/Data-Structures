import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    int N = (int) 1e5 + 1;
    public int[] maximumMEX(int[] nums) {
        Queue<Integer> list[] = new LinkedList[N];

        for(int i = 0; i < N; i++) {
            list[i] = new LinkedList<>();
        }

        for(int i = 0; i < nums.length; i++) {
            list[nums[i]].add(i);
        }


        ArrayList<Integer> result = new ArrayList<>();

        int idx = 0;
        while(idx < nums.length){
            int mex = 0;
            int idx_value = idx;
            while(mex < N) {
                while(list[mex].peek() != null && list[mex].peek() < idx) {
                    list[mex].poll();
                }
                if(list[mex].isEmpty() || list[mex].peek() < idx) {
                    break;
                }
                mex++;
                idx_value = Math.max(idx_value, list[mex - 1].peek() + 1);
            }

            if(mex == N) {
                break;
            }
            if(idx_value == idx){
                idx++;
            }else{
                idx = idx_value;
            }
            
            result.add(mex);
        }
        return result.stream().mapToInt(i -> i).toArray();

    }
}