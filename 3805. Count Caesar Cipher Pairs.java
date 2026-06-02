import java.util.HashMap;
import java.util.Map;

class Solution {
    
    private String shift(String word) {
        StringBuilder sb = new StringBuilder();
        for(char c: word.toCharArray()){
            char shifted = (char)((c + 'a' - word.charAt(0) + 26) % 26 + 'a');
            sb.append(shifted);
        }
        return sb.toString();
    }

    public long countPairs(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        for(String word: words){
            String shifted = shift(word);
            map.put(shifted, map.getOrDefault(shifted, 0) + 1);
        }
        long count = 0;
        for(Integer freq: map.values()){
            count += (long)freq * (freq - 1) / 2;
        }
        return count;
    }
}