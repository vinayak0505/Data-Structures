#include "unordered_map"

using namespace std;
class Solution {
    unordered_map<int,int> mp;
public:
    int tribonacci(int n) {
        if(n == 0) return n;
        if(n == 1 || n == 2) return 1;
        if(mp.count(n)) return mp[n];
        return mp[n] = tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
    }
};