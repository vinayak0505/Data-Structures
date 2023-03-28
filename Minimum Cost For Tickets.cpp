#include "vector"
#include "unordered_set"

using namespace std;

class Solution
{
public:
    int mincostTickets(vector<int> &days, vector<int> &costs)
    {
        vector<int> dp(days.back() + 1);
        unordered_set<int> st;
        for (int day : days)
            st.insert(day);
        for (int i = 1; i <= days.back(); i++)
        {
            if (st.count(i))
            {
                int month_ticket = dp[max(i - 30, 0)] + costs[2];
                int week_ticket = dp[max(i - 7, 0)] + costs[1];
                int day_ticket = dp[max(i - 1, 0)] + costs[0];
                dp[i] = min({month_ticket, week_ticket, day_ticket});
            }
            else
            {
                dp[i] = dp[i - 1];
            }
        }
        return dp.back();
    }
};