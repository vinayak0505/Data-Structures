class Solution {
    private String ans = "";

    private void helper(String target, StringBuilder sb, int[] charCount, int index, boolean attached){
        int n = target.length();
        if(index == n){
            if(sb.toString().compareTo(target) == 0) return;
            ans = sb.toString();
            return;
        }

        int start = attached ? target.charAt(index) - 'a' : 0;

        for(int ch = start; ch < 26; ch++){
            if(ans.compareTo("") != 0) return;
            if(charCount[ch] == 0) continue;

            charCount[ch]--;
            sb.setCharAt(index, (char)('a' + ch));
            helper(target, sb, charCount, index + 1, attached && ch == start);
            charCount[ch]++;
        }
    }
    public String lexGreaterPermutation(String s, String target) {
        int[] charCount = new int[26];
        for(char ch : s.toCharArray()) charCount[ch - 'a']++;
        StringBuilder sb = new StringBuilder(target);
        ans = "";
        helper(target, sb, charCount, 0, true);
        return ans;
    }
}