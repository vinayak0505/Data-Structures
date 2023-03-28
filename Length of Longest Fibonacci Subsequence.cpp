#include "vector"
#include "unordered_set"
using namespace std;

class Solution {
    int size(unordered_set<int>& st,int a,int b){
        int ans = 0;
        while(st.count(a + b)){
            int c = a + b;
            a = b;
            b = c;
            ans++;
        }
        return ans;
    }
public:
    int lenLongestFibSubseq(vector<int>& arr) {
        int ans = 0;
        unordered_set<int> st(arr.begin(),arr.end());
        for(int i = 0;i<arr.size();i++){
            for(int j = i + 1;j < arr.size();j++){
                ans = max(ans,size(st,arr[i],arr[j]));
            }
        }
        if(ans)
            return ans + 2;
        return 0;
    }
};