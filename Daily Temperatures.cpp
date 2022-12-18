
#include "stack";
#include "vector";
using namespace std;

class Solution {
public:
    vector<int> dailyTemperatures(vector<int>& t) {
        stack<int> st;
        vector<int> ans(t.size());
        for(int i = 0;i<t.size();i++){
            while(st.size() && t[st.top()] < t[i]){
                ans[st.top()] = i - st.top();
                st.pop();
            }
            st.push(i);
        }
        return ans;
    }
};