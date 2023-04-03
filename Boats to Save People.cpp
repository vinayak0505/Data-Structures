#include "vector"

using namespace std;

class Solution {
public:
    int numRescueBoats(vector<int>& people, int limit) {
        sort(people.begin(),people.end());
        int start = 0, end = people.size() - 1;
        int count = 0;
        while(start <= end){
            if(start == end) return count + 1;
            if(people[start] + people[end] <= limit){
                start++;
                end--;
            }else{
                end--;
            }
            count++;
        }
        return count;
    }
};