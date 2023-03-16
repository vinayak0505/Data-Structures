#include "vector"
#include "stack"

using namespace std;

class Solution {
public:
    vector<int> asteroidCollision(vector<int>& asteroids) {
        stack<int> st;
        for(int a: asteroids){
            if(a < 0){
                while(st.size() && st.top() < -a && st.top() > 0) st.pop();
                if(st.size() && st.top() == -a){
                    st.pop();
                    continue;
                }
                if(st.size() && st.top() > -a) continue;
            }
            st.push(a);
        }
        vector<int> ans;
        while(st.size()){
            ans.push_back(st.top());
            st.pop();
        }
        reverse(ans.begin(),ans.end());
        return ans;
    }
};