#include "vector"
#include "string"
#include "unordered_map"

using namespace std;

class Solution {
    bool check(string& a, string& b){
        unordered_map<char,char> um;
        unordered_map<char,char> um1;
        for(int i = 0;i<a.size();i++){
            if(um.count(b[i])){
                if(
                    um1.count(a[i]) == 0 || 
                    um[b[i]] != a[i] ||
                    um1[a[i]] != b[i]
                ) return false;
            }else if(um1.count(a[i])){
                return false;
            }
            else{
                um[b[i]] = a[i];
                um1[a[i]] = b[i];
            }
        }
        return true;
    }
public:
    vector<string> findAndReplacePattern(vector<string>& words, string pattern) {
        vector<string> ans;
        for(string word: words){
            if(check(word,pattern)) ans.push_back(word);
        }
        return ans;
    }
};