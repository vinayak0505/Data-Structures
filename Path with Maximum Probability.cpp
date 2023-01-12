#include "queue"

using namespace std;

class Solution {

    bool isGreater(double a,double b){
        int ia = a * 1e5;
        int ib = b * 1e5;
        return a >= b;
    }
    vector<double> ans;

public:
    double maxProbability(int n, vector<vector<int>>& edges, vector<double>& succProb, int start, int end) {
        ans = vector<double>(n,0);
        vector<vector<pair<int,double>>> graph(n);
        for(int i = 0;i<edges.size();i++){
            auto edge = edges[i];
            graph[edge.front()].push_back({edge.back(),succProb[i]});
            graph[edge.back()].push_back({edge.front(),succProb[i]});
        }
        priority_queue<pair<double,int>> q;
        q.push({1,start});
        while(q.size()){
            auto top = q.top();
            if(top.second == end) return top.first;
            q.pop();
            if(isGreater(ans[top.second],top.first)) continue;
            ans[top.second] = top.first;
            for(auto child:graph[top.second]){
                if(ans[child.first]) continue;
                q.push({child.second * top.first,child.first});
            }
        }
        return ans[end];
    }
};