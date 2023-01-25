#include "vector"

using namespace std;
class Solution {
public:
    vector<int> twoSum(vector<int>& numbers, int target) {
        int a = 0, b = numbers.size() - 1;
        while(a < b){
            if(numbers[a] + numbers[b] == target) return {a + 1,b + 1};
            else if(numbers[a] + numbers[b] > target) b--;
            else a++;
        }
        return {a,b};
    }
};