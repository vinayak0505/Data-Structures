import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Solution {
	private class Node {
		int i, x, y, z;

		public Node() {
			this.i = 0;
			this.x = 0;
			this.y = 0;
			this.z = 0;
		}

		public Node(Node node, int i) {
			this.i = i;
			this.x = node.x;
			this.y = node.y;
			this.z = node.z;
		}

		public Node mul(Node node) {
			Node newnode = new Node();
			newnode.i = this.i;
			newnode.x = node.x + this.x;
			newnode.y = node.y + this.y;
			newnode.z = node.z + this.z;
			return newnode;
		}

		public Node div(Node node) {
			Node newnode = new Node();
			newnode.i = this.i;
			newnode.x = this.x - node.x;
			newnode.y = this.y - node.y;
			newnode.z = this.z - node.z;
			return newnode;
		}

		public boolean isEqual(Node node) {
			return this.x == node.x && this.y == node.y && this.z == node.z;
		}

		private static boolean fillNode(Node node, long value) {
			int x = 0, y = 0, z = 0;
			while (value % 2 == 0) {
				value /= 2;
				x++;
			}
			while (value % 3 == 0) {
				value /= 3;
				y++;
			}
			while (value % 5 == 0) {
				value /= 5;
				z++;
			}
			node.x += x;
			node.y += y;
			node.z += z;
			return value == 1;
		}

		private static boolean fillNode(Node node, int value) {
			return Node.fillNode(node, (long) value);
		}

		@Override
		public String toString() {
			return "value of i " + i + " and of x " + x + " and of y " + y + " and of z " + z;
		}

		@Override
		public boolean equals(Object node) {
			if (this == node) {
				return true;
			}
			if (node == null || getClass() != node.getClass()) {
				return false;
			}
			Node other = (Node) node;
			return this.i == other.i && this.x == other.x && this.y == other.y && this.z == other.z;
		}

		@Override
		public int hashCode() {
			return Objects.hash(i, x, y, z);
			
		}

	}

	Map<Node, Integer> map;

	private int countSequences(Node[] nums, Node k, Node node, int i) {
		// System.out.print("\t".repeat(i));
		// System.out.println(node);
		if (i == nums.length) {
			if (node.isEqual(k)) {
				return 1;
			}
			return 0;
		}
		Node newnode = new Node(node, i);
		if (map.containsKey(newnode)) {
			return map.get(newnode);
		}

		int count = 0;
		count += countSequences(nums, k, newnode, i + 1);
		count += countSequences(nums, k, newnode.mul(nums[i]), i + 1);
		count += countSequences(nums, k, newnode.div(nums[i]), i + 1);
		map.put(newnode, count);
		return count;
	}

	public int countSequences(int[] nums, long k) {
		Node node = new Node();
		if (Node.fillNode(node, k) == false) {
			return 0;
		}

		Node[] nodes = new Node[nums.length];
		for (int i = 0; i < nums.length; i++) {
			Node newNode = new Node();
			Node.fillNode(newNode, nums[i]);
			// System.out.println(newNode);
			nodes[i] = newNode;
		}

		map = new HashMap<>();

		return countSequences(nodes, node, new Node(), 0);
	}
}