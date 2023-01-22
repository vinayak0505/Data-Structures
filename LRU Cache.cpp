#include "unordered_map"
#include "queue"

using namespace std;

class DNode
{
public:
    int val;
    int key;
    DNode *next = nullptr;
    DNode *pre = nullptr;
    DNode(int val,int key) : val(val),key(key) {}

    void joinNext(DNode *node)
    {
        node->next = next;
        node->pre = this;
        if(next)
            next->pre = node;
        next = node;
    }

    void remove(){
        next->pre = pre;
        pre->next = next;
        next = nullptr;
        pre = nullptr;
    }
};

class DLL
{
    int size = 0;
    DNode *head, *tail;

public:
    DLL()
    {
        head = new DNode(0,0);
        tail = new DNode(0,0);
        head->joinNext(tail);
    }

    ~DLL()
    {
        auto node = head;
        while (head)
        {
            node = head;
            head = head->next;
            delete node;
        }
    }

    DNode *add(int val,int key)
    {
        DNode *node = new DNode(val,key);
        head->joinNext(node);
        size++;
        return node;
    }

    int getSize()
    {
        return size;
    }

    int removeLast()
    {
        if (size == 0)
            return -1;
        int key = tail->pre->key;
        auto node = tail->pre;
        tail->pre = node->pre;
        tail->pre->next = tail;
        delete node;
        size--;
        return key;
    }

    DNode *putFirst(DNode *node)
    {
        node->remove();
        head->joinNext(node);
        return node;
    }

    DNode *update(DNode *node,int value){
        node->val = value;
        return putFirst(node);
    }
};

class LRUCache
{
    DLL dll;
    int capacity;
    unordered_map<int, DNode*> mp;

public:
    LRUCache(int capacity) : capacity(capacity)
    {
    }

    int get(int key)
    {
        if(mp.count(key) == 0 || mp[key] == nullptr) return -1;
        dll.putFirst(mp[key]);
        return mp[key]->val;
    }

    void put(int key, int value)
    {
        // setting put method also as to first
        if(mp.count(key) && mp[key]){
            dll.update(mp[key],value);
            return;
        }
        if(dll.getSize() == capacity){
            mp[dll.removeLast()] = nullptr;
        }
        mp[key] = dll.add(value,key);
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */