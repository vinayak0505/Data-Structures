#include "vector"

using namespace std;
class Solution {
public:
    int canCompleteCircuit(vector<int>& gas, vector<int>& cost) {
        int size = gas.size();
        for(int i = 0;i<size;i++){
            gas.push_back(gas[i]);
            cost.push_back(cost[i]);
        }
        for(int i = 0;i<size;){
            int count = 0;
            int tgas = 0;
            while(tgas + gas[i+count] >= cost[i + count]){
                tgas += gas[i+count] - cost[i+count];
                count++;
                if(count == size) return i;
            }
            i += count?count:1;
        }
        return -1;
    }
};