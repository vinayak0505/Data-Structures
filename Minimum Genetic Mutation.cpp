#include "vector"
#include "unordered_map"
#include "unordered_set"
#include "string"

using namespace std;

class Solution {
    const int LEN = 8;
    bool valid(string& a,string &b){
        bool found = false;
        for(int i = 0; i < LEN; i++){
            if(a[i] != b[i]){
                if(found) return false;
                found = true;
            }
        }
        return found;
    }

    int find(
        unordered_map<string,unordered_set<string>>& graph,
        string start,
        string &end,
        unordered_map<string,int>& contains,
        unordered_set<string>& s
    ){
        if(s.count(start)) return -1;
        s.insert(start);
        if(contains.count(start)) return contains[start];
        if(start == end) return 0;
        int ans = -1;
        for(string cur:graph[start]){
            int val = find(graph,cur,end,contains,s);
            if(val == -1) continue;

            if(ans == -1) ans = val + 1;
            else ans = min(ans,val + 1);
        }
        s.erase(start);
        return contains[start] = ans;
    }

public:
    int minMutation(string startGene, string endGene, vector<string>& bank) {
        unordered_map<string,unordered_set<string>> graph;
        unordered_map<string,int> con;
        unordered_set<string> s;
        bank.push_back(startGene);
        for(string& a:bank){
            for(string& b:bank){
                if(valid(a,b)){
                    graph[a].insert(b);
                }
            }
        }

        return find(graph,startGene, endGene,con,s);
    }
};