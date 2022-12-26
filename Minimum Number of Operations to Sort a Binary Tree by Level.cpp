
// Definition for a binary tree node.

#include "vector"
#include "queue"

using namespace std;

struct TreeNode
{
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

class Solution
{
    int solve(vector<int> &arr, int n)
    {
        pair<int, int> arrPos[n];
        for (int i = 0; i < n; i++)
        {
            arrPos[i].first = arr[i];
            arrPos[i].second = i;
        }

        sort(arrPos, arrPos + n);

        vector<bool> vis(n, false);

        int ans = 0;

        for (int i = 0; i < n; i++)
        {
            if (vis[i] || arrPos[i].second == i)
                continue;

            int cycle_size = 0;
            int j = i;
            while (!vis[j])
            {
                vis[j] = 1;

                j = arrPos[j].second;
                cycle_size++;
            }

            if (cycle_size > 0)
            {
                ans += (cycle_size - 1);
            }
        }

        return ans;
    }

public:
    int minimumOperations(TreeNode *root)
    {
        int ans = 0;
        queue<TreeNode *> q;
        q.push(root);
        while (q.size())
        {
            int size = q.size();
            vector<int> v;
            while (size--)
            {
                v.push_back(q.front()->val);

                if (q.front()->left)
                    q.push(q.front()->left);
                if (q.front()->right)
                    q.push(q.front()->right);
                q.pop();
            }
            ans += solve(v, v.size());
        }
        return ans;
    }
};