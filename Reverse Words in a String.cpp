#include "sstream"
#include "string"

using namespace std;

class Solution {
public:
    string reverseWords(string s) {
        stringstream ss(s);
        string ans;
        while(getline(ss,s,' ')){
            if(s.size())
            ans = s + " " + ans;
        }
        ans.pop_back();
        return ans;
    }
};