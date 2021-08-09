public class AVL {
	Node root;
	int size;

	public class Node {
		int data;
		Node left;
		Node right;
		int height;
		int balanceFactor;

		public Node(int data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

		public Node(int data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}

		public Node() {}
	}

	public AVL() {
		this.root = null;
		this.size = 0;
	}

	public void add(int data) {
		root = addHelper(root, data);
	}

	public Node addHelper(Node root, int data) {
		if (root == null) {
			root = new Node(data);
			size++;
		} else {
			if (data < root.data) {
				root.left = addHelper(root.left, data);
				if (countBF(root) == 2) {
					if (data < root.left.data) {
						root = rotateRight(root);
					} else {
						root = rotateLeftRight(root);
					}
				}
			} else if (data > root.data) {
				root.right = addHelper(root.right, data);
				if (countBF(root) == -2) {
					if (data > root.right.data) {
						root = rotateLeft(root);
					} else {
						root = rotateRightLeft(root);
					}
				}
			}
			root.height = countHeight(root);
			root.balanceFactor = countBF(root);
		}
		return root;
	}

	private int countBF(Node root) {
		int bfLeft;
		int bfRight;

		if (root.left == null) {
			bfLeft = -1;
		} else {
			bfLeft = root.left.height;
		}

		if (root.right == null) {
			bfRight = 1;
		} else {
			bfRight = root.right.height;
		}
		return bfLeft - bfRight;
	}

	private int countHeight(Node root) {
		int heightLeft;
		int heightRight;

		if (root == null) {
			return -1;
		} 
		if (root.left == null) {
			heightLeft = -1;
		} else {
			heightLeft = root.left.height;
		}
		if (root.right == null) {
			heightRight = -1;
		} else {
			heightRight = root.right.height;
		}
		int height = Math.max(heightLeft, heightRight) + 1;
		return height;
	}

	private Node rotateRight(Node a) {
		Node b = a.left;
		a.left = b.right;
		b.right = a;
		a.height = countHeight(a);
		b.height = countHeight(b);
		a.balanceFactor = countBF(a);
		b.balanceFactor = countBF(b);
		return b;
	}

	private Node rotateLeft(Node a) {
		Node b = a.right;
		a.right = b.left;
		b.left = a;
		a.height = countHeight(a);
		b.height = countHeight(b);
		a.balanceFactor = countBF(a);
		b.balanceFactor = countBF(b);
		return b;
	}

	private Node rotateLeftRight(Node a) {
		a.left = rotateLeft(a.left);
		return rotateRight(a);
	}

	private Node rotateRightLeft(Node a) {
		a.right = rotateRight(a.right);
		return rotateRight(a);
	}

	 public List<Integer> preorder() {
        List<Integer> result = new ArrayList<>();
        preorderHelper(root, result);
        return result;
    }

    private void preorderHelper(Node node, List<Integer> result) {
        if (node != null) {
            result.add(node.data);
            preorderHelper(node.left, result);
            preorderHelper(node.right, result);
        }
    }
}