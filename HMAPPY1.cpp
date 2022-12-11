#include <iostream>
#include <vector>
#include <set>
#include <list>

using namespace std;
typedef long long ll;

void print(vector<int> &arr)
{
    cout << endl;
    for (int i : arr)
        cout << i << " ";
    cout << endl;
}

class DenseRing
{
    // first is value 0 or 1, second is the quantity
    list<pair<int, int>> l;
    multiset<int> s;

    void pop_or_decrement_back()
    {
        if (l.back().second > 1)
            l.back().second--;
        else
            l.pop_back();
    }

    void push_or_increment_front(int i)
    {
        if (l.front().first == i)
            l.front().second++;
        else
            l.push_front({i, 1});
    }
    void decrease_sequence(int val)
    {
        s.erase(s.find(val));
        if (val > 1)
            s.insert(val - 1);
    }

    void increase_sequence(int val)
    {
        if (val)
            s.erase(s.find(val));
        s.insert(val + 1);
    }

public:
    DenseRing(vector<int> &arr)
    {

        l.push_back({arr.front(), 1});
        for (int i = 1; i < arr.size(); i++)
        {
            if (l.back().first == arr[i])
                l.back().second++;
            else
                l.push_back({arr[i], 1});
        }

        for (auto i : l)
        {
            if (i.first)
                s.insert(i.second);
        }
    }

    void rotate()
    {
        if (l.back().first == 1)
        {
            decrease_sequence(l.back().second);
            int sz = l.front().first ? l.front().second : 0;
            increase_sequence(sz);
        }
        push_or_increment_front(l.back().first);
        pop_or_decrement_back();
    }
    int max_seq_of_ones() const
    {
        return *--s.end();
    }
};
void solve()
{
    int a, b, c;
    cin >> a >> b >> c;
    vector<int> arr(a);
    for (int &i : arr)
        cin >> i;
    string s;
    cin >> s;

    if (count(begin(arr), end(arr), 0) == a)
    {
        for (auto i : s)
            if (i == '?')
                cout << 0 << endl;
        return;
    }
    if (count(begin(arr), end(arr), 1) == a)
    {
        for (auto i : s)
            if (i == '?')
                cout << min(c, a) << endl;
        return;
    }
    DenseRing dr(arr);
    for (char i : s)
    {
        if (i == '?')
            cout << min(c, dr.max_seq_of_ones()) << endl;
        else
            dr.rotate();
    }
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int test = 1;
    // cin >> test;
    while (test--)
    {
        solve();
    }
    return 0;
}