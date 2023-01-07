#include "queue"

using namespace std;

class Solution {
    
public:
    vector<int> findMinHeightTrees(int n, vector<vector<int>>& edges) {
        if(n == 1) return {0};
        vector<vector<int>> graph(n);
        vector<int> d(n);
        for(auto edge: edges){
            graph[edge.front()].push_back(edge.back());
            graph[edge.back()].push_back(edge.front());
            d[edge.front()]++;
            d[edge.back()]++;
        }
        queue<int> q;
        for(int i = 0;i<n;i++){
            if(d[i] == 1) q.push(i);
        }

        while(n > 2){
            int size = q.size();
            n -= size;
            while(size--){
                int v = q.front();
                q.pop();
                for(int i:graph[v]){
                    d[i]--;
                    if(d[i] == 1) q.push(i);
                }
            }
        }
        vector<int> ans;
        while(q.size()) {
            ans.push_back(q.front());
            q.pop();
        }
        return ans;
    }
};