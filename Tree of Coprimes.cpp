#include "unordered_map"
#include "unordered_set"
#include "vector"
using namespace std;

class Solution
{
    int gcd(int x, int y)
    {
        if (x == 0)
            return y;
        return gcd(y % x, x);
    }

public:
    unordered_map<int, vector<int>> cops;
    unordered_map<int, vector<pair<int, int>>> ancestors;
    void traverse(vector<int> &n, vector<vector<int>> &al, int i, int parent, int level, vector<int> &res)
    {
        int max_level = -1;
        for (auto cop : cops[n[i]])
        {
            auto &v = ancestors[cop];
            if (!v.empty() && v.back().first > max_level)
            {
                max_level = v.back().first;
                res[i] = v.back().second;
            }
        }
        ancestors[n[i]].push_back({level, i});
        for (auto child : al[i])
            if (child != parent)
                traverse(n, al, child, i, level + 1, res);
        ancestors[n[i]].pop_back();
    }
    vector<int> getCoprimes(vector<int> &n, vector<vector<int>> &edges)
    {
        unordered_set<int> s(begin(n), end(n));
        for (auto n1 : s)
        {
            for (auto n2 : s)
                if (gcd(n1, n2) == 1)
                    cops[n1].push_back(n2);
        }
        vector<vector<int>> al(n.size());
        vector<int> res(n.size(), -1);
        for (auto &e : edges)
        {
            al[e[0]].push_back(e[1]);
            al[e[1]].push_back(e[0]);
        }
        traverse(n, al, 0, 0, 0, res);
        return res;
    }
};