#include "math.h"

using namespace std;

class Solution {
public:
    int kthGrammar(int n, int k) {
        n = pow(2,n);
        int start = 1;
        while(k != 1){
            n /= 2;
            if(k > n){
                start = !start;
                k -= n;
            }
        }
        return start == 0;
    }
};
