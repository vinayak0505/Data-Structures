import java.util.Stack;

class Solution {

    class Pair{
        char c;
        int count;
        public Pair(char c, int count){
            this.c = c;
            this.count = count;
        }
    }
    public String removeSubstring(String s, int k) {
        int n = s.length();
        Stack<Pair> st = new Stack<>();
        for(int i = 0; i< n;i++){
            char c = s.charAt(i);
            if(st.size() == 0){
                st.add(new Pair(c, 1));
                continue;
            }

            if(st.peek().c == c){
                st.peek().count++;
            }else{
                st.add(new Pair(c, 1));
            }

            if(st.peek().c == ')' && st.peek().count == k){
                Pair p = st.pop();
                if(st.size() > 0 && st.peek().count >= k){
                    st.peek().count -= p.count;
                    if(st.peek().count == 0){
                        st.pop();
                    }
                }else{
                    st.add(p);
                }
            }
        }

        StringBuilder ans = new StringBuilder();
        while(!st.isEmpty()){
            Pair p = st.pop();
            for(int i=0; i< p.count; i++) ans.append(p.c);
        }
        return ans.reverse().toString();
    }
}