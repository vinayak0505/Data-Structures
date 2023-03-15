#include "unordered_map"
#include "string"
#include "vector"

using namespace std;

class OneCharAtTime{

    class Node{
        public:
        unordered_map<char,Node*> next;
    };

    Node* head;

    void add(string& word,int i,Node* node){
        if(i + 1 == word.size()){
            if(node->next.count(word[i]) == 0)
                node->next[word[i]] = new Node();
            return;
        }
        if(node->next.count(word[i]) == 0) return;
        add(word,i + 1,node->next[word[i]]);
    }

    string _get_longest(Node* node){
        string ans = "";
        string temp = "";
        for(auto a: node->next){
            temp = temp + a.first + _get_longest(a.second);
            if(temp.size() == ans.size()){
                ans = min(temp,ans);
            }else if(temp.size() > ans.size()){
                ans = temp;
            }
            temp = "";
        }
        return ans;
    }

    public:
    OneCharAtTime(vector<string>& words){
        sort(words.begin(),words.end());
        head = new Node();
        for(string& word: words) add(word,0,head);
    }

    string get_longest(){
        return _get_longest(head);
    }

};

class Solution {
public:
    string longestWord(vector<string>& words) {
        OneCharAtTime t(words);
        return t.get_longest();
    }
};