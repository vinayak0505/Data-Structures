
#include "vector"
#include "string"

using namespace std;

class Solution {
public:
    int minDeletionSize(vector<string>& strs) {
        if(strs.size() == 1) return true;
        int count = 0;
        for(int j = 0;j<strs.front().size();j++){
            for(int i = 0;i<strs.size() - 1;i++){
                if(strs[i][j] > strs[i + 1][j]) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }
};