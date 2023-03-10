#include "sstream"
#include "string"

using namespace std;

class Solution {
    pair<int,int> convertToComplet(string num){
        stringstream ss(num);
        pair<int,int> ans = {0,0};
        getline(ss,num,'+');
        if(num.size()) ans.first = stoi(num);
        getline(ss,num,'+');
        if(num.size()) {
            num.pop_back();
            ans.second = stoi(num);
        }
        return ans;
    }

public:
    string complexNumberMultiply(string num1, string num2) {
        auto p1 = convertToComplet(num1);
        auto p2 = convertToComplet(num2);
        pair<int,int> ans;
        ans.first = p1.first * p2.first - p1.second * p2.second;
        ans.second = p1.second * p2.first + p1.first * p2.second;
        string temp;
        return temp + to_string(ans.first) 
        + "+" + to_string(ans.second) + "i";
    }
};