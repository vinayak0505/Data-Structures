#include <iostream>
#include <vector>

using namespace std;
typedef long long ll;

class SegmentedTree
{
    vector<int> stree;
    int size;

    void build(int node, int start, int end, vector<int> &arr)
    {
        if (start == end)
        {
            stree[node] = arr[start];
            return;
        }
        int mid = (start + end) / 2;
        build(node * 2, start, mid, arr);
        build(node * 2 + 1, mid + 1, end, arr);
        stree[node] = max(stree[node * 2], stree[node * 2 + 1]);
    }

    int query(int node, int left, int right, int start, int end)
    {
        if (start > right || left > end)
            return 0;
        if (left == right)
            return stree[node];
        if (start <= left && right <= end)
            return stree[node];
        int mid = (left + right) / 2;
        return max(query(node * 2, left, mid, start, end),
                   query(node * 2 + 1, mid + 1, right, start, end));
    }

public:
    SegmentedTree(vector<int> &arr)
    {
        size = arr.size();
        stree = vector<int>(size * 4);
        build(1, 0, size - 1, arr);
    }
    int query(int jump, int k, int n)
    {
        int start = jump % n;
        int end = start + n - k;
        return query(1, 0, size - 1, start, end);
    }
};
void solve()
{
    // take input
    int n, k, p;
    cin >> n >> k >> p;
    k = min(k,n);
    vector<int> arr(n);
    for (int &i : arr)
        cin >> i;

    // dublicate the array
    for (int i = 0; i < n; i++)
        arr.push_back(arr[i]);

    // count k num
    int num = 0;
    for (int i = 0; i < k; i++)
        num += arr[i];
    
    // store in sum, count remaining ones
    vector<int> sum;
    sum.push_back(num);
    for (int i = k; i < arr.size(); i++)
    {
        num += arr[i] - arr[i - k];
        sum.push_back(num);
    }

    // create segmented tree from sum
    SegmentedTree st(sum);

    // run query
    int start = n;
    char c;
    while(p--)
    {
        cin>>c;
        if (c == '?')
            cout << st.query(start, k, n) << endl;
        else {
            start--;
            if(start == 0) start = n;
        }
    }
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    int test = 1;
    // cin>>test;
    while (test--)
    {
        solve();
    }
    return 0;
}