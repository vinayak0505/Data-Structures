class Solution {

    private boolean check(String s){
        int[] freqCount = getFreqCount(s);

        boolean odd = false;

        for(int count: freqCount){
            if(count % 2 == 1){
                if(odd) return false;
                odd = true;
            }
        }
        return true;
    }

    private int[] getFreqCount(String s){
        int count[] = new int[26];

        for(char a: s.toCharArray()){
            count[a - 'a']++;
        }

        return count;
    }

    private String ans;

    
    private void helper(int[] freqCount, String target, StringBuilder sb, int i, boolean sticked){
        if(ans.equals("") == false) return;
        // abc -> 4/2 -> 2
        // abcd -> 5 /2 -> 2
        int n = target.length();
        if(i == (n + 1) / 2){
            if(sb.toString().compareTo(target) > 0){
                ans = sb.toString();
            }
            return;
        }
        for(int c = sticked ? target.charAt(i) - 'a' : 0; c < 26;c++){
            boolean same = target.charAt(i) == ((char)(c + 'a'));
            if((n % 2 == 1) && (i == n / 2)){
                if(freqCount[c] != 1) continue;
                sb.setCharAt(i, (char)('a' + c));
                helper(freqCount, target, sb, i + 1, same && sticked);
                return;
            }else{
                if(freqCount[c] < 2) continue;
                freqCount[c] -= 2;
                sb.setCharAt(i, (char)('a' + c));
                sb.setCharAt(n - 1 - i, (char)('a' + c));
                helper(freqCount, target, sb, i + 1, same && sticked);
                freqCount[c] += 2;
            }
        }
    }

    public String lexPalindromicPermutation(String s, String target) {
        if(check(s) == false) return "";
        StringBuilder sb = new StringBuilder(target);
        ans = "";
        helper(getFreqCount(s), target, sb, 0, true);
        return ans;
    }
}