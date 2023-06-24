#include "unordered_map"
#include "vector"

using namespace std;

class Solution {

    unordered_map<int,unordered_map<int,int>> dp;
    int select(int value1, vector<int>& rods,int i){
        if(dp[value1].count(i)) return dp[value1][i];
        if(i == rods.size()){
            if(value1 == 0) return 0;
            return INT_MIN;
        }
        int val3 = select(value1,rods,i+1);
        int val1 = select(value1 + rods[i],rods,i+1);
        int val2 = select(value1 - rods[i],rods,i+1);
        return dp[value1][i] = max({val1 + rods[i],val2 + rods[i],val3});
    }
public:
    int tallestBillboard(vector<int>& rods) {
        return select(0,rods,0)/2;
    }
};