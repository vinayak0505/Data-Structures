#include "vector"
#include "string"

using namespace std;

class Solution
{
public:
    bool validTicTacToe(vector<string> &grid)
    {
        int count_x = 0;
        int count_o = 0;

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (grid[i][j] == 'X')
                    count_x++;
                else if (grid[i][j] == 'O')
                    count_o++;
            }
        }

        if (count_o > count_x || count_x - count_o >= 2)
            return false;

        if (count_x >= 3 && count_x == count_o && check_x_win(grid))
            return false;

        if (count_o >= 3 && count_x > count_o && check_o_win(grid))
            return false;

        return true;
    }

private:
    bool check_x_win(const std::vector<std::string> &grid)
    {
        bool res = false;
        res |= grid[0][0] == 'X' && grid[0][1] == 'X' && grid[0][2] == 'X';
        res |= grid[1][0] == 'X' && grid[1][1] == 'X' && grid[1][2] == 'X';
        res |= grid[2][0] == 'X' && grid[2][1] == 'X' && grid[2][2] == 'X';

        res |= grid[0][0] == 'X' && grid[1][0] == 'X' && grid[2][0] == 'X';
        res |= grid[0][1] == 'X' && grid[1][1] == 'X' && grid[2][1] == 'X';
        res |= grid[0][2] == 'X' && grid[1][2] == 'X' && grid[2][2] == 'X';

        res |= grid[0][0] == 'X' && grid[1][1] == 'X' && grid[2][2] == 'X';
        res |= grid[0][2] == 'X' && grid[1][1] == 'X' && grid[2][0] == 'X';

        return res;
    }

    bool check_o_win(const std::vector<std::string> &grid)
    {
        bool res = false;
        res |= grid[0][0] == 'O' && grid[0][1] == 'O' && grid[0][2] == 'O';
        res |= grid[1][0] == 'O' && grid[1][1] == 'O' && grid[1][2] == 'O';
        res |= grid[2][0] == 'O' && grid[2][1] == 'O' && grid[2][2] == 'O';

        res |= grid[0][0] == 'O' && grid[1][0] == 'O' && grid[2][0] == 'O';
        res |= grid[0][1] == 'O' && grid[1][1] == 'O' && grid[2][1] == 'O';
        res |= grid[0][2] == 'O' && grid[1][2] == 'O' && grid[2][2] == 'O';

        res |= grid[0][0] == 'O' && grid[1][1] == 'O' && grid[2][2] == 'O';
        res |= grid[0][2] == 'O' && grid[1][1] == 'O' && grid[2][0] == 'O';

        return res;
    }
};