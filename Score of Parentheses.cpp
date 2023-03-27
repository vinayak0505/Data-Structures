#include "stack"
#include "string"

using namespace std;

class Solution {
public:
    int scoreOfParentheses(string s) {
        stack<int> st;
        int T = 10000;
        int val = 0;
        for(char c: s){
            if(st.size() == 0){
                st.push(c + T);
                continue;
            }
            if(c == '('){
                if(val){ 
                    st.push(val);
                    val = 0;
                }
                st.push(c + T);
            }else{
                int t = 0;
                while(st.size() && st.top() != ('(' + T)){
                    t += st.top();
                    st.pop();
                }
                st.pop();
                if(t) st.push(2 * t);
                else st.push(1);
            }
        }
        int ans = 0;
        while(st.size()){
            ans += st.top();
            st.pop();
        }
        return ans;
    }
};