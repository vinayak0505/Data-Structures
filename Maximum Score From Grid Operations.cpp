#include "vector";
#include "math.h";

using namespace std;
class Solution
{
    vector<vector<bool>> taken;
    // first is token and second and not token
    vector<vector<pair<long long, long long>>> dp;

    // long long maximumScore(vector<vector<int>> &grid, int j)
    // {
    //     if (j == grid.size())
    //         return 0LL;
    //     long long ans = maximumScore(grid, j + 1);
    //     long long tempans = 0;
    //     for (int i = 0; i < grid.size(); i++)
    //     {
    //         taken[i][j] = true;

    //         if (j + 1 != grid.size())
    //         {
    //             tempans += grid[i][j + 1];
    //         }

    //         auto var = 0;
    //         if (dp[i][j])
    //         {
    //             var = dp[i][j];
    //         }
    //         else
    //         {
    //             var = dp[i][j] = maximumScore(grid, j + 1);
    //         }

    //         if (j != 0)
    //         {
    //             if (taken[i][j - 1])
    //             {
    //                 tempans -= grid[i][j];
    //             }
    //             else if (j == 1 || !taken[i][j - 2])
    //                 tempans += grid[i][j - 1];
    //         }
    //         ans = max(tempans + var, ans);
    //     }
    //     for (int i = 0; i < grid.size(); i++)
    //     {
    //         taken[i][j] = false;
    //     }
    //     return ans;
    // }

    void fillDp(vector<vector<int>> &grid, int j)
    {
        if (grid.size() == 0)
            return;
        if (j == grid.size() - 1)
            return;
        fillDp(grid, j + 1);
        for (int i = 0; i < grid.size(); i++)
        {
            auto parent = i == 0 ? pair<long long, long long>({0, 0}) : dp[i - 1][j];

            // if taken current value
            for (int k = 0; k < grid.size(); k++)
            {
                if (k < i)
                {
                    dp[i][j].first = max(parent.first + grid[i][j + 1]);
                }
            }

            dp[i][j].second = max(parent.first - grid[i][j + 1], );
        }
    }

public:
    long long maximumScore(vector<vector<int>> &grid)
    {
        taken = vector<vector<bool>>(grid.size(), vector<bool>(grid.front().size(), false));
        dp = vector<vector<pair<long long>>>(grid.size(), vector<pair<long long>>(grid.front().size(), {0, 0}));
        return maximumScore(grid, 0);
    }
};