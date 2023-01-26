#include "iostream"

using namespace std;

class Solution {
    int two = 0, five = 0, ten = 0;
public:
    int trailingZeroes(int n) {
        int t;
        for(int i = 1;i<=n;i++){
            t = i;
            while(t && t % 10 == 0){
                ten++;
                t /= 10;
            }
            while(t && t % 5 == 0){
                five++;
                t /= 5;
            }
            while(t && t % 2 == 0){
                two++;
                t /= 2;
            }
        }
        return ten + min(five,two);
    }
};