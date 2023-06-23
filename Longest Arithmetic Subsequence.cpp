#include "vector"

using namespace std;

class Solution
{
    int getNum(int cur, int from)
    {
        return cur - from + 500;
    }

public:
    int longestArithSeqLength(vector<int> &nums)
    {
        if (nums.size() < 3) return nums.size();
        vector<vector<int>> dp(nums.size(), vector<int>(1001, 1));
        int ans = 0;
        for (int i = 0; i < nums.size(); i++)
        {
            for (int j = 0; j < i; j++)
            {
                int val = getNum(nums[i], nums[j]);
                if (val)
                {
                    dp[i][val] = max(dp[i][val], dp[j][val] + 1);
                }
                ans = max(ans, dp[i][val]);
            }
        }
        return ans;
    }
};