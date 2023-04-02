#include "vector"

using namespace std;

class Solution {

    int find(vector<int> &arr, long long a){
        int ans = arr.size();
        int start = 0, end = ans - 1;
        while(start <= end){
            int mid = (start + end) / 2;
            if(a <= arr[mid]){
                ans = mid;
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        return ans;
    }
public:
    vector<int> successfulPairs(vector<int>& spells, vector<int>& potions, long long success) {
        vector<int> ans;
        sort(potions.begin(),potions.end());
        for(int i : spells){
            long long val = success / i;
            val += ((success % i) > 0);
            ans.push_back(potions.size() - find(potions,val));
        }
        return ans;
    }
};