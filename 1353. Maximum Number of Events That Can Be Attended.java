import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    int max = (int)1e5 + 1;

    public int maxEvents(int[][] events) {
        Arrays.sort(events, (a, b) ->  a[0] - b[0]);
        int ans = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 0, j = 0;i<max;i++){
            while(j < events.length && events[j][0] <= i){
                pq.add(events[j][1]);
                j++;
            }
            while(pq.size() > 0 && pq.peek() < i){
                pq.poll();
            }
            if(pq.size() > 0){
                ans++;
                pq.poll();
            }
        }
        return ans;
    }
}