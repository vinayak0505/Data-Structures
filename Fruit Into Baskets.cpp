#include "vector"

using namespace std;
class Solution {
public:
    int totalFruit(vector<int>& fruits) {
        int a = -1,b = -1,c = 0,d = 0;
        int start = 0;
        int ans = 0;
        for(int i = 0;i<fruits.size();i++){
            if(fruits[i] == a){
                c++;
            }else if(fruits[i] == b){
                d++;
            }else if(c == 0){
                a = fruits[i],c = 1;
            }else if(d == 0){
                b = fruits[i],d = 1;
            }else {
                ans = max(ans,c+d);
                while(c && d){
                    if(fruits[start] == a){
                        c--;
                    }else if(fruits[start] == b){
                        d--;
                    }
                    start++;
                }
                i--;
            }
        }
        return max(ans,c+d);
    }
};