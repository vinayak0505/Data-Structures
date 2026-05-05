import java.util.Stack;

class Solution {
    public String lexSmallestAfterDeletion(String s) {
        int charCount[] = new int[26];
        int n = s.length();

        for(int i = 0;i<n;i++){
            charCount[s.charAt(i) - 'a']++;
        }

        Stack<Character> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            while(st.isEmpty() == false && st.peek() > s.charAt(i) && charCount[st.peek() - 'a'] > 1){
                charCount[st.pop() - 'a']--;
            }
            st.push(s.charAt(i));
        }

        int size = st.size();
        StringBuilder sb = new StringBuilder(size);
        while(st.size()> 0){
            sb.append(st.pop());
        }
        sb = sb.reverse();
        int prev = -1;
        for(int i = sb.length() - 1; i >= 0;i--){
            if(sb.charAt(i) - 'a' >= prev && charCount[sb.charAt(i) - 'a'] > 1){
                charCount[sb.charAt(i) - 'a']--;
                sb.deleteCharAt(i);
            }else{
                prev = Math.max(prev, sb.charAt(i) - 'a');
            }
        }
        return sb.toString();
    }
}