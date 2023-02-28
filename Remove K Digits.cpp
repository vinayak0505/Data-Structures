#include "stack"
#include "string"

using namespace std;

class Solution {
    int check(string& num,int& i){
        while(i<num.size() - 1){
            if(num[i] > num[i + 1]) return i;
            i++;
        }
        return -1;
    }
public:
    string removeKdigits(string num, int k) {
        int i = 0;
        stack<char> st;
        for(char n: num){
            while(st.size() && st.top() > n && k){
                st.pop();
                k--;
            }
            st.push(n);
        }
        num = "";
        while(st.size()){
            num += st.top();
            st.pop();
        }
        
        reverse(num.begin(),num.end());

        if(k != -1)num = num.substr(0,num.size() - k);
        while(num.size() && num.front() == '0') num = num.substr(1);
        if(num == "") return "0";
        return num;
    }
};