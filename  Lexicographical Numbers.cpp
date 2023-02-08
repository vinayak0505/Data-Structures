#include "vector"

using namespace std;
class Solution {
    vector<int> ans;

    void solve(int a,int n){
        a *= 10;
        int end = a + 10;
        for(;a < end && a <= n;a++){
            ans.push_back(a);
            solve(a,n);
        }
    }
public:
    vector<int> lexicalOrder(int n) {
        for(int i = 1;i < 10 && i <= n;i++){
            ans.push_back(i);
            solve(i,n);
        }
        return ans;
    }
};