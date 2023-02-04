#include "string"
#include "stack"
#include "vector"

using namespace std;

class Solution {
    
public:
    string removeDuplicateLetters(string s) {
        stack<char> st;
        vector<int> count(26);
        for(char c:s){
            count[c - 'a']++;
        }

        vector<int> vis(26);
        for(char c: s){
            count[c - 'a']--;
            if(vis[c - 'a']) continue;

            while(st.size() && st.top() > c && count[st.top() - 'a']){
                vis[st.top() - 'a'] = false;
                st.pop();
            }

            vis[c - 'a'] = true;
            st.push(c);
        }

        string ans;
        while(st.size()){
            ans = st.top() + ans;
            st.pop();
        }
        return ans;
    }
};