
#include "iostream"
#include "string"
#include "stack"
#include "vector"

using namespace std;

class Solution
{
    int isNum(string &value)
    {
        try
        {
            return std::stoi(value);
        }
        catch (...)
        {
        }
        return 201;
    }

public:
    int evalRPN(vector<string> &tokens)
    {
        stack<long long> st;
        for (auto &token : tokens)
        {
            int value = isNum(token);
            if (value == 201)
            {
                int a = st.top();
                st.pop();
                if (token == "*")
                {
                    a *= st.top();
                    st.pop();
                }
                else if (token == "/")
                {
                    a = st.top() / a;
                    st.pop();
                }
                else if (token == "+")
                {
                    a += st.top();
                    st.pop();
                }
                else if (token == "-")
                {
                    a = st.top() - a;
                    st.pop();
                }
                st.push(a);
            }
            else
                st.push(value);
        }

        return st.top();
    }
};