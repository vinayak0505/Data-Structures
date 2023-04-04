#include "unordered_set"
#include "string"

using namespace std;

class Solution {
public:
    int partitionString(string s) {
        unordered_set<char> us;
        int count = 1;
        for(char c: s){
            if(us.count(c)){
                us = unordered_set<char>();
                count++;
            }
                us.insert(c);
            
        }
        return count;
    }
};