#include "vector"

using namespace std;

class Solution {
public:
    vector<int> advantageCount(vector<int>& nums1, vector<int>& nums2) {
        vector<pair<int,int>> num;
        for(int i = 0;i<nums1.size();i++){
            num.push_back({nums2[i],i});
        }
        sort(num.begin(),num.end());
        sort(nums1.begin(),nums1.end());
        vector<int> ans = vector<int>(nums1.size() , -1);
        for(int i = 0,j = 0;i<nums1.size();i++,j++){
            while(j < num.size() && nums1[j] <= num[i].first) j++;
            if(j == num.size()) break;
            ans[num[i].second] = nums1[j];
            nums1[j] = -1;
        }

        for(int i = 0,j = 0;i<num.size();i++){
            if(ans[i] != -1) continue;
            while(nums1[j] == -1) j++;
            ans[i] = nums1[j++];
        }

        return ans;
    }
};