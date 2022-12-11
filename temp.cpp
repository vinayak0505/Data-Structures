#include <iostream>
#include <vector>

using namespace std;
typedef long long ll;

int main()
{
    int a = 1;
    a = ++a + a++;
    cout<<a<<endl;
}