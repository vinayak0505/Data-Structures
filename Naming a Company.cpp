#include "unordered_map"
#include "unordered_set"
#include "vector"
#include "string"

using namespace std;

class Solution {
public:
    long long distinctNames(vector<string>& ideas) {
        unordered_set<string> t(ideas.begin(), ideas.end());
        unordered_map<char, unordered_map<char,int>> m;
        for (auto& str : t){
            string s = str;
            char prev = s[0];
            for (char c = 'a'; c <= 'z'; ++c){
                s[0] = c;
                if (t.count(s) == 0)
                 ++m[c][prev];
            }
        }
        long long ans = 0;
        for (auto& str : t){
            string s = str;
            char prev = s[0];
            for (char c = 'a'; c <= 'z'; ++c){
                s[0] = c;
                if (t.count(s) == 0)
                 ans += m[prev][c];
            }
        }
        return ans;
    }
};