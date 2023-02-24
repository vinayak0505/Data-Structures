#include "queue"

using namespace std;

class Solution {
public:
    int minimumDeviation(vector<int>& nums) {
        int max_odd = INT_MAX;
        for(int &num : nums){
            if(num & 1) num *= 2;
            max_odd = min(max_odd,num);
        }

        priority_queue<int> pq(nums.begin(),nums.end());
        int ans = INT_MAX;
        while(pq.top()%2 == 0){
            ans = min(ans,pq.top() - max_odd);
            max_odd = min(max_odd,pq.top()/2);
            pq.push(pq.top()/2);
            pq.pop();
        }
        return min(ans,pq.top() - max_odd);
    }
};