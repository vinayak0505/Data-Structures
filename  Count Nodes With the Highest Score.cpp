
#include "vector"

using namespace std;

class Solution {
    vector<int> childCount;
    vector<vector<int>> child;
    void makeChild(int i,const vector<int>& parents){
        for(int i = 1;i<parents.size();i++){
            child[parents[i]].push_back(i);
        }
    }
    long long int ans = 0;
    int count = 0;
    int solve(int i, const vector<int>&parents){
        long long int a = 0;
        long long int t = 1;
        for(int j : child[i]){
            int r = solve(j,parents);
            a += r;
            t *= r;
        }
        t *= (parents.size() - a - 1)?(parents.size() - a - 1):1;
        if(ans == t) count++;
        else if(ans < t) ans = t,count = 1;
        return a + 1;
    }
public:
    int countHighestScoreNodes(vector<int>& parents) {
        childCount = vector<int>(parents.size());
        child = vector<vector<int>>(parents.size());
        makeChild(0,parents);
        solve(0,parents);
        return count;
    }
};