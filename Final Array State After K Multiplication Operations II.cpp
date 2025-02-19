#include "vector";
#include "queue";
#include "iostream";

using namespace std;
class Solution {
    int mod = 1e9 + 7;

    int getNumber(int k, int multiplier){
        long long num = 1;
        while(k--){
            cout<<"getNumber";
            num = (num * multiplier) % mod;
        }
        return num;
    }
public:
    vector<int> getFinalState(vector<int>& nums, int k, int multiplier) {
        priority_queue<pair<long long,int>, vector<pair<long long,int>>,
        greater<pair<long long,int>>> pq;
        int max_num = 0;
        for(int i = 0;i<nums.size();i++){
            pq.push({nums[i], i});
            max_num = max(max_num, nums[i]);
        }
        // cout<<"max_num"<<max_num<<endl;
        while(k-- > 0){
            cout<<"while1";
            auto a = pq.top();
            long long pre = (long long)a.first * multiplier;
            if(pre > max_num) {
                k++;
                break;
            }
            a.first = pre;
            pq.pop();
            pq.push(a);
        }
        // cout<<"k"<<k<<endl;
        if(k){
            int rem = k % nums.size();
            
            while(rem--){
            cout<<"while2";
                auto a = pq.top();
                pq.pop();
                long long pre = (long long)a.first * multiplier;
                a.first = pre % mod;
                pq.push(a);
            }
        }
        // cout<<"k"<<k<<endl;

        k = getNumber(k / nums.size(), multiplier);
        // cout<<"k"<<k<<endl;
        while(pq.size()){
            cout<<"while3";
            auto a = pq.top();
            pq.pop();
            nums[a.second] = ((long long)a.first * k) % mod;
        }
        return nums;
    }
};

int main(){
    Solution s;
    vector<int> nums = {1};
    int k = 1;
    int multiplier = 1;
    s.getFinalState(nums,k,multiplier);
}