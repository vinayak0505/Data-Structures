
#include "queue"

using namespace std;

class Solution {
    int floor(int p){
        if(p & 1) return p / 2 + 1;
        return p / 2;
    }
public:
    int minStoneSum(vector<int>& piles, int k) {
        priority_queue<int> pq;
        for(int i:piles) pq.push(i);
        for(int i = 0;i<k;i++){
            pq.push(floor(pq.top()));
            pq.pop();
        }
        int ans = 0;
        while(pq.size()){
            ans += pq.top();
            pq.pop();
        }
        return ans;
    }
};