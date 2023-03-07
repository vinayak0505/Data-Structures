#include "vector"
#include "stack"

using namespace std;

class Solution {
public:
    vector<int> nextGreaterElements(vector<int>& nums) {
        vector<int> newnums(nums.begin(),nums.end());
        newnums.insert(newnums.end(),nums.begin(),nums.end());
        vector<int> ans = vector<int>(newnums.size(),-1);
        stack<int> st;
        for(int i = 0; i < newnums.size(); i++){
            while(st.size() && newnums[st.top()] < newnums[i]){
                ans[st.top()] = newnums[i];
                st.pop();
            }
            st.push(i);
        }
        return vector<int>(ans.begin(),ans.begin() + nums.size());
    }
};