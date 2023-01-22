
// Definition for singly-linked list.
struct ListNode {
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

class Solution {


    bool join(ListNode* &head,ListNode* last){
        if(last == nullptr) return false;
        if(join(head,last->next)) return true;;
        if(head == last || head->next == last){
            last->next = nullptr;
            return true;
        }
        last->next = head->next;
        head->next = last;
        head = last->next;
        return false;
    }
public:
    void reorderList(ListNode* head) {
        join(head,head);
    }
};