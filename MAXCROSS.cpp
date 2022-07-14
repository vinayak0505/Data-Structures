#include <iostream>
#include <vector>

#define vs(n) n, vector<int>(n)
using namespace std;
typedef long long ll;

int fill_horizontal(vector<vector<int>> &hor, vector<string> const &arr, int i, int j)
{

    if (j == 0 || arr[i][j - 1] != 'X')
    {
        int k = j;
        while (k + 1 < hor.size() && arr[i][k + 1] == 'X')
            k++;
        return k - j + 1;
    }
    else
    {
        return hor[i][j - 1];
    }
}

int fill_vertical(vector<vector<int>> &ver, vector<string> const &arr, int i, int j)
{

    if (i == 0 || arr[i - 1][j] != 'X')
    {
        int k = i;
        while (k + 1 < ver.size() && arr[k + 1][j] == 'X')
            k++;
        return k - i + 1;
    }
    else
    {
        return ver[i - 1][j];
    }
}

int fill_diagnolright(vector<vector<int>> &digr, vector<string> const &arr, int i, int j)
{

    if (i == 0 || j == 0 || arr[i - 1][j - 1] != 'X')
    {
        int k = i, l = j;
        while (k + 1 < digr.size() && l + 1 < digr.size() && arr[k + 1][l + 1] == 'X')
            k++, l++;
        return k - i + 1;
    }
    else
    {
        return digr[i - 1][j - 1];
    }
}

int fill_diagnolleft(vector<vector<int>> &digl, vector<string> const &arr, int i, int j)
{

    if (i == 0 || j == arr.size() - 1 || arr[i - 1][j + 1] != 'X')
    {
        int k = i, l = j;
        while (k + 1 < digl.size() && l - 1 < digl.size() && arr[k + 1][l - 1] == 'X')
            k++, l--;
        return k - i + 1;
    }
    else
    {
        return digl[i - 1][j + 1];
    }
}

void solve()
{
    int n;
    cin >> n;
    vector<string> arr(n);
    for (auto &str : arr)
        cin >> str;
    vector<vector<int>> ver(vs(n)), hor(vs(n)), digl(vs(n)), digr(vs(n));
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            if (arr[i][j] != 'X'){
                cout<<"0 ";
                continue;
            }
            ver[i][j] = fill_vertical(ver, arr,i,j);
            hor[i][j] = fill_horizontal(hor, arr,i,j);
            digr[i][j] = fill_diagnolright(digr, arr,i,j);
            digl[i][j] = fill_diagnolleft(digl, arr,i,j);
            cout<< max(max(ver[i][j],hor[i][j]),max(digr[i][j],digl[i][j]))<<" ";
        }
        cout<<endl;
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