import java.util.HashSet;

class Solution {
    public int minAllOneMultiple(int k) {
        HashSet<Long> seen = new HashSet<>();
        long num = 1;
        int count = 1;
        while(true){
            if(num % k == 0) return count;
            if(seen.contains(num % k)) return -1;
            seen.add(num % k);
            num = (num * 10 + 1) % k;
            count++;
        }
    }
}