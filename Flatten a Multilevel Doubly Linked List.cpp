
// Definition for a Node.
class Node
{
public:
    int val;
    Node *prev;
    Node *next;
    Node *child;
};

class Solution
{
public:
    Node *flatten(Node *head, Node *tail)
    {
        if (head == nullptr)
            return nullptr;
        Node *cur = head, *pre;
        while (cur)
        {
            if (cur->child)
            {
                cur->next = flatten(cur->child, cur->next);
                if (cur->next)
                    cur->next->prev = cur;
                cur->child = nullptr;
            }
            pre = cur;
            cur = cur->next;
        }
        pre->next = tail;

        if (tail)
            tail->prev = pre;

        return head;
    }
};