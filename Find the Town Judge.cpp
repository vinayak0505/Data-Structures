#include "vector"

using namespace std;
class Solution {
public:
    int findJudge(int n, vector<vector<int>>& trust) {
        vector<bool> trust_one(n,false);
        for(auto persons:trust) trust_one[persons.front()-1] = true;
        int judge = 0;
        for(int i = 0;i<n;i++){
            if(trust_one[i] == false){
                if(judge) return -1;
                judge = i + 1;
            }
        }
        if(judge == 0) return -1;
        trust_one = vector<bool>(n,false);
        for(auto persons:trust) if(persons.back() == judge)
            trust_one[persons.front()-1] = true;
        for(int i = 0;i<n;i++){
            if(trust_one[i] == false && i + 1!= judge) return -1;
        }
        return judge;
    }
};