#include "string"
#include "stack"

using namespace std;

class Solution {

    int join(int a,int b,char sign){
        switch(sign){
            case '\n':
            a = b;
            break;
            case '+': 
            a += b;
            break;
            case '-': 
            a -= b;
            break;
            case '*': 
            a *= b;
            break;
            case '/': 
            a /= b;
            break;
        }
        return a;
    }
public:
    int calculate(string s) {
        string t;
        char sign = '\n';
        int cur = 0;
        stack<string> st;
        for(char c:s){
            switch(c){
                case ' ':
                break;
                case '+': 
                case '-': 
                case '*': 
                case '/': 
                if(sign == '*' || sign == '/'){
                    cur = join(stoi(st.top()),cur,sign);
                    st.pop();
                }
                st.push(to_string(cur));
                if(c != '*' && c != '/')
                st.push(t + c);
                cur = 0;
                sign = c;
                break;
                default:
                cur *= 10;
                cur += (c - '0');
            }
        }
        if(sign == '*' || sign == '/'){
            cur = join(stoi(st.top()),cur,sign);
            st.pop();
        }
        stack<string> ss;
        ss.push(to_string(cur));
        while(st.size()){
            ss.push(st.top());
            st.pop();
        }
        if(ss.size()){
            cur = stoi(ss.top());
            ss.pop();
        }
        while(ss.size()){
            sign = ss.top()[0];
            ss.pop();
            cur = join(cur,stoi(ss.top()),sign);
            ss.pop();
        }
        return cur;
    }
};