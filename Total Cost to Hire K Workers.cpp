#include "queue"

using namespace std;

class Solution {
public:
    long long totalCost(vector<int>& costs, int k, int can) {
        if(can + can >= costs.size()){
            sort(costs.begin(),costs.end());
            long long ans = 0;
            for(int i = 0;i<k;i++) ans += costs[i];
            return ans;
        }
        int i = 0, j = costs.size() - 1;
        long long ans = 0;
        priority_queue<pair<int,int>,
        vector<pair<int,int>>,greater<pair<int,int>>> pq;

        for(int k = 0;i<costs.size() && k<can;k++){
            pq.push({costs[k],k});
            i++;
        }

        for(int l = 0;j<costs.size() && l<can;l++){
            pq.push({costs[costs.size() - l - 1],costs.size() - l - 1});
            j--;
        }
        int val;
        while(k--){
            ans += pq.top().first;
            val = pq.top().second;
            pq.pop();
            if(val < i){
                if(i <= j){
                    pq.push({costs[i],i});
                    i++;
                }
            }else{
                if(j >= i){
                    pq.push({costs[j],j});
                    j--;
                }
            }
        }

        return ans;
    }
};