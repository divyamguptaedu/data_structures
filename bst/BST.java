public class BST {
	Node root;

	public class Node {
		int data;
		Node left;
		Node right;

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

	public BST() {
		this.root = null;
	}

	public void add(int data) {
		if (root == null) {
			root = new Node(data);
		} else {
			addHelper(root, data);
		}
	}

	private void addHelper(Node root, int data) {
		boolean leftSide = data < root.data;
		if (leftSide) {
			if (root.left == null) {
				root.left = new Node(data);
			} else {
				addHelper(root.left, data);
			}
		} else {
			if (root.right == null) {
				root.right = new Node(data);
			} else {
				addHelper(root.left, data);
			}
		}
	}

	public int remove(int data) {
		Node removedNode = new Node();
		root = removeHelper(root, data, removedNode);
		return removedNode.data;
	}

	private Node removeHelper(Node current, int data, Node removedNode) {
		if (data == current.data) {
			removedNode.data = current.data;
			if (current.left == null && current.right == null) {
				return null;
			}
			if (current.left == null) {
				return current.right;
			}
			if (current.right == null) {
				return current.left;
			}
			current.right = findSuccessor(current.right, removedNode);
			int newData = removedNode.data;
			removedNode.data = current.data;
			current.data = newData;
			return current;
		}
		if (data < current.data) {
			current.left = removeHelper(current.left, data, removedNode);
			return current;
		}
		if (data > current.data) {
			current.right = removeHelper(current.right, data, removedNode);
			return current;
		}
		return current;

	}

	private Node findSuccessor(Node current, Node removedNode) {
		if (current.left == null) {
			removedNode.data = current.data;
			return current.right;
		} else {
			current.left = findSuccessor(current.left, removedNode);
		}
		return current;
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