#include "string"
#include "vector"
#include "unordered_map"

using namespace std;

class Solution {
    bool is(string &a,const string &b){
        int i = 0;
        for(char c:b){
            while(i < a.size() && a[i] != c) i++;
            if(i == a.size()) return false;
            i++;
        }
        return true;
    }
public:
    int numMatchingSubseq(string s, vector<string>& words) {
        int count = 0;
        unordered_map<string,int> mp;
        for(string &word: words){
            mp[word]++;
        }
        for(auto &p: mp){
            if(is(s,p.first))
            count += p.second;
        }
        return count;
    }
};