#include "queue"

using namespace std;

class Solution {
public:
    int findMaximizedCapital(int k, int w, vector<int>& profits, vector<int>& capital) {
        vector<pair<int,int>> arr = vector<pair<int,int>>(profits.size());
        for(int i = 0;i<arr.size();i++){
            arr[i] = {capital[i],profits[i]};
        }
        sort(arr.begin(),arr.end());
        int used = 0;
        // int ans = 0;
        priority_queue<int> pq;
        while(k--){
            while(used < arr.size() && w >= arr[used].first){
                pq.push(arr[used++].second);
            }
            if(pq.size() == 0) return w;
            // ans += pq.top();
            w += pq.top();
            pq.pop();
        }
        return w;
    }
};