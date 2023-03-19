#include "vector"

using namespace std;

class Solution
{
public:
    double largestSumOfAverages(vector<int> &nums, int k)
    {
        vector<vector<double>> dp(nums.size() + 1, vector<double>(k + 1, 0));

        for (int m = nums.size() - 1; m >= 0; m--)
        {
            for (int n = 1; n <= k; n++)
            {
                if (n == 1)
                {
                    double sum = 0;
                    for (int i = m; i < nums.size(); i++)
                        sum += nums[i];

                    dp[m][n] = sum / (nums.size() - m);
                    continue;
                }

                double maxi = 0;
                double curr_avg = 0;
                for (int i = m; i <= nums.size() - n; i++)
                {
                    curr_avg += nums[i];
                    maxi = max(maxi, (curr_avg / (i - m + 1)) + dp[i + 1][n - 1]);
                }

                dp[m][n] = maxi;
            }
        }

        return dp[0][k];
    }
};