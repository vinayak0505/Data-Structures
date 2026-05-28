class Solution {

    private int[][][] greater;
    

    private int _getGreaterCount(int i, int j, int[][] matrix, int value){
        if(i < 0 || j < 0) return 0;
        int n = matrix.length;
        int m = matrix[0].length;
        if(i >= n || j >= m) return greater[value][Math.min(n - 1, i)][Math.min(m - 1, j)];
        return greater[value][i][j];
    }

    private int _getGreaterCount2(int i, int j, int[][] matrix, int value){
        if(i < 0 || j < 0) return 0;
        int n = matrix.length;
        int m = matrix[0].length;
        if(i >= n || j >= m) return 0;
        return matrix[i][j] > value ? 1 : 0;
    }

    private int getGreaterCount(int i, int j, int[][] matrix){
        int value = matrix[i][j];
        int right = i + value;
        int left = i - value;
        int top = j - value;
        int bottom = j + value;

        int count = 0;
        count -= _getGreaterCount2(left, top, matrix, value);
        count -= _getGreaterCount2(left, bottom, matrix, value);
        count -= _getGreaterCount2(right, top, matrix, value);
        count -= _getGreaterCount2(right, bottom, matrix, value);

        count += _getGreaterCount(right, bottom, matrix, value);
        count += _getGreaterCount(left - 1, top - 1, matrix, value);

        count -= _getGreaterCount(left - 1, bottom, matrix, value);
        count -= _getGreaterCount(right, top - 1, matrix, value);

        return count;


    }

    private void createGreaterCount(int[][] matrix){
        int n = matrix.length;
        int m = matrix[0].length;

        greater = new int[201][n][m];
        for(int i = 0;i<=200;i++){
            for (int j = 0; j < n; j++) {
                for (int j2 = 0; j2 < m; j2++) {
                    int left = (j == 0 ? 0 : greater[i][j - 1][j2]);
                    int up = (j2 == 0 ? 0 : greater[i][j][j2 - 1]);

                    int cur = (matrix[j][j2] > i ? 1 : 0);
                    int rem = ((j > 0 && j2 > 0) ? greater[i][j - 1][j2 - 1] : 0);

                    greater[i][j][j2] = left + up + cur - rem;
                }
            }
        }

    }
    public int countLocalMaximums(int[][] matrix) {
        createGreaterCount(matrix);

        int ans = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == 0) continue;
                if(getGreaterCount(i, j, matrix) == 0) ans++;
            }
        }
        return ans;
    }
}