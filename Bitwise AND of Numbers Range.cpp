
class Solution
{
public:
    int rangeBitwiseAnd(int left, int right)
    {
        int i;
        for (i = 0; left != right; i++)
        {
            left >>= 1;
            right >>= 1;
        }

        return left << i;
    }
};