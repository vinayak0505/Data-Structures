#include "vector"
#include "string"

using namespace std;

class Solution {
    bool check(vector<int>&a, vector<int>&b,int k){
        for(int i = 0;i<a.size();i++){
            if(b[i] == a[i]) continue;
            if(b[i] - a[i] < k) return false;
        }
        return true;
    }

public:
    int longestSubstring(string s, int k) {
        if(s.size() < k) return 0;
        vector<int> count(26);
        for(char c: s){
            count[c - 'a']++;
        }
        for(int i = 0;i<s.size();i++){
            if(count[s[i] - 'a'] < k){
                return max(
                    longestSubstring(s.substr(0,i),k),
                    i + 1 == s.size() ? 0 :longestSubstring(s.substr(i + 1),k)
                );
            }
        }
        return s.size();
    }
};