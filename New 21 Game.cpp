#include "vector"

using namespace std;

class Solution {
public:
    double new21Game(int n, int k, int maxPts) {
        if(k == 0 || n >= k + maxPts) return 1;
        auto dp = vector<double>(k + maxPts + 1);
        auto prob = 1.0 / maxPts;
        double sum = 0;
        dp[1] += prob;
        dp[min((int)dp.size() - 1,maxPts + 1)] -= prob;
        for(int i = 1;i<dp.size() - 1;i++){
            sum += dp[i];
            dp[i] = sum;
            if(i >= k) continue;
            auto prob = dp[i] / maxPts;
            dp[i + 1] += prob;
            dp[min((int)dp.size() - 1,i + 1 + maxPts)] -= prob;
        }
        double up = 0, down = 0;
        for(int i = k;i<=n;i++) up += dp[i];
        down = up;
        for(int i = n + 1;i < dp.size() - 1;i++) down += dp[i];
        return up/down;
    }
};