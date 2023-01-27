#include "vector"
#include "string"

using namespace std;

class Node{
    public:
    vector<Node*> next = vector<Node*>(26);
    bool isEnd = false;
};

class Solution {
    Node* head;

    void add(Node* node,string& word,int i){
        if(word.size() == i){
            node->isEnd = true;
            return;
        }
        if(node->next[word[i] - 'a'] == nullptr){
            node->next[word[i] - 'a'] = new Node();
        }
        add(node->next[word[i] - 'a'],word,i + 1);
    }

    bool found(Node* node,string& word,int i,int count){
        if(node == nullptr) return false;
        if(word.size() == i){
            if(node->isEnd == false || count == 0) return false;
            return true;
        }
        if(node->isEnd){
            if(found(head,word,i,count+1)) return true;
        }
        return found(node->next[word[i] - 'a'],word,i + 1,count);
    }
public:
    vector<string> findAllConcatenatedWordsInADict(vector<string>& words) {
        head = new Node();
        for(string &word: words) add(head,word,0);

        vector<string> ans;

        for(string &word: words){
            if(found(head,word,0,0))
                ans.push_back(word);
        } 
        return ans;
    }
};