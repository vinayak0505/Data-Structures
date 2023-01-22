#include "vector"
#include "string"

using namespace std;

class Solution
{
public:
    vector<vector<string>> partition(string s)
    {
        vector<vector<string>> ans;
        vector<string> d;
        pal(0, s, ans, d);
        return ans;
    }

    void pal(int index, string s, vector<vector<string>> &ans, vector<string> &d)
    {
        if (index == s.length())
        {
            ans.push_back(d);
            return;
        }
        for (int i = index; i < s.length(); i++)
        {
            if (ispal(s, index, i))
            {
                d.push_back(s.substr(index, i - index + 1));
                pal(i + 1, s, ans, d);
                d.pop_back();
            }
        }
    }

    bool ispal(string s, int start, int end)
    {
        while (start <= end)
            if (s[start++] != s[end--])
                return false;
        return true;
    }
};