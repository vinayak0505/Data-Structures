#include "vector"
#include "stack"

using namespace std;

class Solution {
public:
    int findRadius(vector<int>& houses, vector<int>& heaters) {
        sort(houses.begin(),houses.end());
        sort(heaters.begin(),heaters.end());
        vector<int> left = vector<int>(houses.size(),-1), 
        right = vector<int>(houses.size(),-1);
        stack<int> h;
        for(int heat: heaters){
            h.push(heat);
        }
        int pre = -1;
        for(int i = houses.size() - 1;i >=0 ;i--){
            while(h.size() && h.top() >= houses[i]){
                pre = h.top();
                h.pop();
            }
            right[i] = pre;
        }
        h = stack<int>();
        for(int i = heaters.size() - 1;i >= 0; i--){
            h.push(heaters[i]);
        }
        pre = -1;
        for(int i = 0; i < houses.size(); i++){
            while(h.size() && h.top() <= houses[i]){
                pre = h.top();
                h.pop();
            }
            left[i] = pre;
        }
        int ans = 0;
        for(int i = 0;i<left.size(); i++){
            if(left[i] != -1 && right[i] != -1){
                ans = max(ans,min(houses[i] - left[i],right[i] - houses[i]));
            }else if(left[i] != -1){
                ans = max(ans,houses[i] - left[i]);
            }else if(right[i] != -1){
                ans = max(ans,right[i] - houses[i]);
            }
        }
        return ans;
    }
};