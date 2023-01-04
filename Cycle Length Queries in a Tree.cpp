#include "vector"

using namespace std;
class Solution {

    int solve(int a,int b){
        int ans = 1;
        while(a != b){
            if(a > b) a /= 2;
            else b /= 2;
            ans++;
        }
        return ans;
    }
public:
    vector<int> cycleLengthQueries(
        int n, 
        vector<vector<int>>& queries
        ) {
        vector<int> ans;
        for(auto &q:queries)
            ans.push_back(solve(q.front(),q.back()));
        return ans;
    }
};