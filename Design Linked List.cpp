class MyLinkedList {
    class Node{
        public:
        int val;
        Node* next = nullptr;
        Node(int val):val(val){}
        Node(int val,Node* node):val(val){
            next = node;
        }
    };

public:

    Node* head;
    MyLinkedList() {
        head = new Node(0);
    }
    
    int get(int index) {
        index++;
        auto cur = head;
        while(cur && index--) cur = cur->next;
        if(index != -1) return -1;
        return cur->val;
    }
    
    void addAtHead(int val) {
        auto node = new Node(val);
        node->next = head->next;
        head->next = node;
    }
    
    void addAtTail(int val) {
        auto cur = head;
        while(cur->next) cur = cur->next;
        cur->next = new Node(val);
    }
    
    void addAtIndex(int index, int val) {
        auto cur = head;
        while(cur && index--) cur = cur->next;
        if(index != -1) return;
        cur->next = new Node(val,cur->next);
    }
    
    void deleteAtIndex(int index) {
        auto cur = head;
        while(cur->next && index--) cur = cur->next;
        if(index != -1) return;
        if(cur->next) cur->next = cur->next->next;
    }
};

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList* obj = new MyLinkedList();
 * int param_1 = obj->get(index);
 * obj->addAtHead(val);
 * obj->addAtTail(val);
 * obj->addAtIndex(index,val);
 * obj->deleteAtIndex(index);
 */