#include "unordered_map"
#include "vector"
#include "string"

using namespace std;

class Solution {
public:
    string frequencySort(string s) {
        unordered_map<char,int> mp;
        for(char c: s){
            mp[c]++;
        }
        vector<pair<int,char>> v;
        for(auto m: mp){
            v.push_back({m.second,m.first});
        }
        sort(v.begin(),v.end(),greater());
        s = "";
        for(auto m: v){
            for(int i = 0;i<m.first;i++){
                s += m.second;
            }
        }
        return s;
    }
};