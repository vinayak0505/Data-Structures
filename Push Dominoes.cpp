#include "string"

using namespace std;

class Solution {
public:
    string pushDominoes(string dominoes) {
        string temp;
        while(true){
            temp = dominoes;
            for(int i = 0;i<temp.size();i++){
                if(temp[i] == 'R' && i != temp.size() - 1 && temp[i + 1] == '.'){
                    temp[i + 1] ='r';
                }
                if(temp[i] == 'L' && i != 0){
                    if(temp[i - 1] == '.')
                        temp[i - 1] = 'L';
                    else if(temp[i - 1] == 'r')
                        temp[i - 1] = '.';
                }
            }
            for(char &c: temp) if(c == 'r') c = 'R';
            if(temp == dominoes) return temp;
            dominoes = temp;
        }
        return "";
    }
};