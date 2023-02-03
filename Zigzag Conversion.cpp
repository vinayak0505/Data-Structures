#include "vector"
#include "string"

using namespace std;

class Solution {
public:
    string convert(string s, int numRows) {
        if(numRows == 1) return s;
        int gap = numRows + numRows - 2;
        vector<string> ans(numRows);
        for(int i = 0;i<s.size();i++){
            ans[min(i % gap, gap - (i % gap))] += s[i];
        }
        string finalans;
        for(auto &str:ans) finalans += str;
        return finalans;
    }
};