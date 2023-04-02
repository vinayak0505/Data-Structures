#include "vector"

using namespace std;

class Solution {
    vector<int> arr;

    int _find(int a){
        int ans = 0;
        int start = 0, end = arr.size() - 1;
        while(start <= end){
            int mid = (start + end) / 2;
            if(arr[mid] > a){
                ans = mid;
                end = mid - 1;
            }else {
                start = mid + 1;
            }
        }
        return ans;
    }

    int getRand(){
        return rand() % (arr.back());
    }
public:
    Solution(vector<int>& w) {
        srand(time(0));
        int sum = 0;
        for(int w: w){
            sum += w;
            arr.push_back(sum);
        }
    }
    
    int pickIndex() {
        return _find(getRand());
    }
};