#include "string"

using namespace std;

class Solution {
public:
    
    int maximumSwap(int num) {
        string n = to_string(num);
        string ans = n;
        for(int i = 0;i<n.size(); i++){
            for(int j = i + 1;j < n.size();j++){
                string newn = n;
                swap(newn[i],newn[j]);
                ans = max(newn,ans);
            }
        }
        return stoi(ans);
    }
};