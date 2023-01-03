#include "vector"
#include "map"
#include "unordered_map"

using namespace std;

class Union {

    // -2 tells that parent is not active
    // -1 tells self parent
    // @value tells the parent
    vector<int> parent;
public:
    Union(int value){
        parent = vector<int>(value,-1);
    }

    int getParent(int a){
        if(parent[a] == -1) return a;
        return parent[a] = getParent(parent[a]);
    }
    void join(int a,int b){
        a = getParent(a);
        b = getParent(b);
        parent[a] = b;
    }

};
class Solution {

    int sum(int a){
        return (a * (a+1))/2;
    }
public:
    int numberOfGoodPaths(vector<int>& vals, vector<vector<int>>& edges) {
        int ans = 0;
        // storing notes in front of value
        map<int,vector<int>> mp;
        for(int i = 0; i<vals.size(); i++){
            mp[vals[i]].push_back(i);
        }

        // elements that have lower values then them
        vector<vector<int>> graph(vals.size());
        for(auto &edge: edges){
            if(vals[edge.front()] >= vals[edge.back()]){
                graph[edge.front()].push_back(edge.back());
            }else {
                graph[edge.back()].push_back(edge.front());
            }
        }

        auto u = Union(vals.size());
        for(auto &m:mp){
            unordered_map<int,int> freq;
            for(int from:m.second){
                for(int to:graph[from]){
                    u.join(from,to);
                }
            }
            for(int from:m.second){
                freq[u.getParent(from)]++;
            }
            for(auto &count:freq){
                ans += sum(count.second);
            }
        }
        return ans;
    }
};