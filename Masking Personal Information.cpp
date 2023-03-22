#include "string"

using namespace std;

class Solution {
public:
    string maskPII(string s) {
        string ans="";
        int flag=0;
        if(s.find('@')!=string::npos)
        {
            flag=1;
        }
        if(flag==1)
        {
            transform(s.begin(), s.end(), s.begin(), ::tolower);
            ans+=s[0];
            ans+="*****";
            int t=0;
            for(int i=0;i<s.length();i++)
            {
                if(s[i]=='@')
                {
                    ans+=s[i-1];
                    t=1;
                    

                }
                if(t==1)
                {
                    ans+=s[i];
                }

            }
         
        }
        else
        {
            int count=0;
            for(int i=0;i<s.length();i++)
            {
                if(s[i]>='0' && s[i]<='9')
                {
                    count++;
                }
            }
            
            if(count-10==0)
            {
                ans+="***-***-";
                string temp="";
                for(int i=s.length()-1;i>=0 && temp.length()<4;i--)
                {
                    if(s[i]>='0' && s[i]<='9')
                    {
                        temp+=s[i];
                        
                    }
                }
                reverse(temp.begin(),temp.end());
                ans+=temp;
            }
            else if(count-10==1)
            {
                ans+="+*-***-***-";
                string temp="";
                for(int i=s.length()-1;i>=0 && temp.length()<4;i--)
                {
                    if(s[i]>='0' && s[i]<='9')
                    {
                        temp+=s[i];
                      
                    }
                }
                reverse(temp.begin(),temp.end());
                ans+=temp;
            }
            else if(count-10==2)
            {
                ans+="+**-***-***-";
                string temp="";
                for(int i=s.length()-1;i>=0 &&temp.length()<4;i--)
                {
                    if(s[i]>='0' && s[i]<='9')
                    {
                        temp+=s[i];
                        
                    }
                }
                reverse(temp.begin(),temp.end());
                ans+=temp;
            }
            else if(count-10==3)
            {
                ans+="+***-***-***-";
                string temp="";
                for(int i=s.length()-1;i>=0 &&temp.length()<4;i--)
                {
                    if(s[i]>='0' && s[i]<='9')
                    {
                        temp+=s[i];
                        
                    }
                }
                reverse(temp.begin(),temp.end());
                ans+=temp;
            }
        }
        return ans;
        
    }
};