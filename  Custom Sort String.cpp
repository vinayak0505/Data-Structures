#include "string"
#include "unordered_map"

using namespace std;

class Solution {
    
    class Comp{
        public:
        unordered_map<char,int> um;

        Comp(unordered_map<char,int> um){
            this->um = um;
        }

        bool operator()(char &a,char &b){
            return um[a] < um[b];
        }
    };
public:
    string customSortString(string order, string s) {
        unordered_map<char,int> um;
        for(int i = 0;i<order.size();i++){
            um[order[i]] = i;
        }
        sort(s.begin(),s.end(),Comp(um));
        return s;
    }
};