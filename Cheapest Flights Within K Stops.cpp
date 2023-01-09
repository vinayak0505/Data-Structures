#include "queue"

using namespace std;

class Solution {
    int ans = INT32_MAX;

public:
    int findCheapestPrice(
        int n, 
        vector<vector<int>>& flights, 
        int src, 
        int dst, 
        int k
    ) {
        vector<int> ans(n,INT32_MAX);
        vector<vector<pair<int,int>>> graph(n);
        for(auto flight: flights){
            graph[flight.front()].push_back({flight[1],flight.back()});
        }
        // first = stops, second = curDestination, third = value
        queue<tuple<int,int,int>> q;
        ans[src] = 0;
        q.push({0,src,0});

        while(q.size()){
            auto [stops,curDes,tprice] = q.front();
            q.pop();
            if(stops > k) continue;
            for(auto [next,price]:graph[curDes]){
                if(tprice + price < ans[next]){
                    ans[next] = tprice + price;
                    q.push({stops+1,next,tprice + price});
                }
            }
        }
        if(ans[dst] == INT_MAX) return -1;
        return ans[dst];
    }
};