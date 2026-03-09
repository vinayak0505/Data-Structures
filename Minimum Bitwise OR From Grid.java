class Solution {
	public int minimumOR(int[][] grid) {
		int ans = (1 << 31) - 1;
		int m = grid.length;
		if (m == 0)
			return 0;

		int n = grid[0].length;

		for (int bit = 30; bit >= 0; bit--) {
			int mask = (ans - (1 << bit));
			// System.out.println("mask " + Integer.toBinaryString(mask) + " ans " +
			// Integer.toBinaryString(ans));
			boolean isValid = true;
			for (int i = 0; i < m; i++) {
				boolean found = false;
				for (int j = 0; j < n; j++) {
					if ((mask | grid[i][j]) == mask) {
						// System.out.println(mask + " mask, grid " + grid[i][j] + " i " + i + " j " +
						// j);
						found = true;
						break;
					}
				}
				if (found == false) {
					isValid = false;
					break;
				}
			}
			// System.out.println("for line number " + bit + " is " + isValid + " mask " +
			// mask);
			if (isValid) {
				ans = mask;
			}
		}
		return ans;
	}
}