#include "vector"
#include "string"

using namespace std;

class Node {
    public:
    vector<Node*> next;
    bool isEnd = false;
    Node(){
        next = vector<Node*>(26,nullptr);
    }
};

class WordDictionary {
    Node* head;
public:
    WordDictionary() {
        head = new Node();
    }

    void _addWord(string& word,int i,Node* node){
        if(i == word.size()){
            node->isEnd = true;
            return;
        }
        if(node->next[word[i] - 'a'] == nullptr)
            node->next[word[i] - 'a'] = new Node();
        _addWord(word,i + 1,node->next[word[i] - 'a']);
    }
    void addWord(string word) {
        _addWord(word,0,head);
    }

    bool _search(string& word,int i,Node* node) {
        if(node == nullptr) return false;
        if(i == word.size()) return node->isEnd;
        if(word[i] == '.'){
            for(int j = 0;j<26;j++){
                if(_search(word,i + 1,node->next[j])) return true;
            }
            return false;
        }
        return _search(word,i + 1,node->next[word[i] - 'a']);
    }

    bool search(string word) {
        return _search(word,0,head);
    }
};

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary* obj = new WordDictionary();
 * obj->addWord(word);
 * bool param_2 = obj->search(word);
 */