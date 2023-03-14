#include "vector"
#include "string"
#include "unordered_map"

using namespace std;

class Solution {
    static bool comp(pair<int,string>& a,pair<int,string>& b){
            if(a.first == b.first){
                return a.second < b.second;
            }
            return a.first > b.first;
        }
public:
    vector<string> topKFrequent(vector<string>& words, int k) {
        unordered_map<string,int> mp;
        for(string& word: words) mp[word]++;
        vector<pair<int,string>> p;
        for(auto m: mp){
            p.push_back({m.second,m.first});
        }
        
        sort(p.begin(),p.end(),comp);
        vector<string> ans;
        for(int i = 0;i<p.size() && i< k;i++)
         ans.push_back(p[i].second);
        return ans;
    }
};