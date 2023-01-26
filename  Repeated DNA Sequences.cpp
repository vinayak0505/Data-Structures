#include "vector"
#include "string"
#include "unordered_map"

using namespace std;

class Solution {
public:
    vector<string> findRepeatedDnaSequences(string s) {
        if(s.size() < 10) return {};
        unordered_map<string,int> mp;
        string str = s.substr(s.size() - 10);
        mp[str]++;
        for(int i = s.size() - 11;i >= 0; i--){
            str.pop_back();
            str = s[i] + str;
            mp[str]++;
        }
        vector<string> ans;
        for(auto a:mp){
            if(a.second > 1){
                ans.push_back(a.first);
            }
        }
        return ans;
    }
};