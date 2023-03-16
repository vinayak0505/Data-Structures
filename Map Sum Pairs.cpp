#include "unordered_map"
#include "string"

using namespace std;

class MapSum {

    class Node{
        public:
        int val = 0;
        unordered_map<char,Node*> next;
    };

    unordered_map<string,int> value;

    Node* head;
    void insert(string& key,int i,int val,Node* node){
        node->val += val;
        if(i == key.size()) return;
        if(node->next[key[i]] == nullptr) node->next[key[i]] = new Node();
        insert(key,i+1,val,node->next[key[i]]);
    }

    int sum(string prefix,Node *node){
        for(int i = 0;i<prefix.size() && node;i++){
            node = node->next[prefix[i]];
        }
        if(node) return node->val;
        return 0;
    }

public:
    MapSum() {
        head = new Node();
    }
    
    void insert(string key, int val) {
        insert(key,0,val - value[key],head);
        value[key] = val;
    }
    
    int sum(string prefix) {
        return sum(prefix,head);
    }
};

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum* obj = new MapSum();
 * obj->insert(key,val);
 * int param_2 = obj->sum(prefix);
 */