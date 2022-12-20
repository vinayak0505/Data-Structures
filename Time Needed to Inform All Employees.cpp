
#include "vector";
using namespace std;
class Solution {

    int solve(int employee,const vector<int>& manager,
    vector<int>& informTime, vector<int>& informedTime){
        if(informedTime[employee] != INT32_MAX)
            return informedTime[employee];
        
        return informedTime[employee] = solve(manager[employee],manager,
        informTime,informedTime) + informTime[manager[employee]];
    }
public:
    int numOfMinutes(int n, int headID, vector<int>& manager, vector<int>& informTime) {
        vector<int> in(manager.size(),INT32_MAX);
        in[headID] = 0;
        for(int i = 0;i<n;i++) solve(i,manager,informTime,in);
        int ans = 0;
        for(int i:in) ans = max(ans,i);
        return ans;
    }
};