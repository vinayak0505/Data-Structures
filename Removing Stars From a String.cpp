#include "string"
#include "stack"

using namespace std;

class Solution {
public:
    string removeStars(string s) {
        stack<char> st;
        for(char c: s){
            if(c == '*'){
                if(st.size())st.pop();
            }else st.push(c);
        }
        string ans;
        while(st.size()){
            ans += st.top();
            st.pop();
        }
        reverse(ans.begin(), ans.end());
        return ans;
    }
};