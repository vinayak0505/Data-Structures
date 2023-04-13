#include "vector"
#include "stack"

using namespace std;

class Solution {
public:
    bool validateStackSequences(vector<int>& pushed, vector<int>& popped) {
        stack<int> st;
        int j = 0;
        for(int i: pushed){
            st.push(i);
            while(st.size() && j < popped.size() && popped[j] == st.top()){
                st.pop();
                j++;
            }
        }
        return popped.size() == j && st.size() == 0;
    }
};