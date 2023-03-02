#include "vector"
#include "string"

using namespace std;

class Solution {

    void fill(vector<char>& ans,int num){
        if(num < 2) return;
        string str = to_string(num);
        for(char c: str){
            ans.push_back(c);
        }
    }

public:
    int compress(vector<char>& chars) {
        int count = 0;
        vector<char> ans;
        char cur = '\n';
        for(char c: chars){
            if(cur != c){
                if(count != 0){
                    ans.push_back(cur);
                    fill(ans,count);
                }
                cur = c;
                count = 1;
            }else{
                count++;
            }
        }
        if(count){
           ans.push_back(cur);
           fill(ans,count);
        }
        chars = ans;
        return ans.size();
    }
};