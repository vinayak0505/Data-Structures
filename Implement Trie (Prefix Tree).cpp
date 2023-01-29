#include "string"
#include "vector"

using namespace std;
class Node
{
public:
    vector<Node *> next;
    bool isEnd = false;
    Node()
    {
        next = vector<Node *>(26, nullptr);
    }
};

class Trie
{
    Node *head;

    void _insert(string &word, Node *node, int i)
    {
        if (i == word.size())
        {
            node->isEnd = true;
            return;
        }
        if (node->next[word[i] - 'a'] == nullptr)
        {
            node->next[word[i] - 'a'] = new Node();
        }
        _insert(word, node->next[word[i] - 'a'], i + 1);
    }

    bool _search(string &word, Node *node, int i)
    {
        if (node == nullptr)
            return false;
        if (i == word.size())
        {
            return node->isEnd;
        }
        return _search(word, node->next[word[i] - 'a'], i + 1);
    }

    bool _startsWith(string &word, Node *node, int i)
    {
        if (node == nullptr)
            return false;
        if (i == word.size())
        {
            return true;
        }
        return _startsWith(word, node->next[word[i] - 'a'], i + 1);
    }

public:
    Trie()
    {
        head = new Node();
    }

    void insert(string &word)
    {
        _insert(word, head, 0);
    }

    bool search(string &word)
    {
        return _search(word, head, 0);
    }

    bool startsWith(string &word)
    {
        return _startsWith(word, head, 0);
    }
};

/**
 * Your Trie object will be instantiated and called as such:
 * Trie* obj = new Trie();
 * obj->insert(word);
 * bool param_2 = obj->search(word);
 * bool param_3 = obj->startsWith(prefix);
 */