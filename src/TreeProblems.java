import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TreeProblems {

  /*
   postOrder (Node Version)
   -----------
   Given the root of a tree print out the values of the nodes in post-order.
   Print each value on a separate line.

   Example:
   If the tree is:
          5
       /  |  \
      3   9   8
        / | \  
       4  1  2
   The output should be:
   3
   4
   1
   2
   9
   8
   5

   If the root is null, do nothing.
   */
  public static <T> void postOrder(Node<T> root) {
    if (root == null) return;

    // traverse all
    for (Node<T> node: root.children) {
      postOrder(node);
    }

    System.out.println(root.value);
  }

  /*
   postOrder (Node Version)
   -----------
   Given the root of a tree print out the values of the nodes in post-order.
   Print each value on a separate line.
   If the tree is null or does not contain the root, do nothing.

   Example:
   For a tree represented as:
     5 -> [3, 9, 8]
     3 -> []
     9 -> [4, 1, 2]
     4 -> []
     1 -> []
     2 -> []
   The output should be:
   3
   4
   1
   2
   9
   8
   5
   */
  public static <T> void postOrder(Map<T, List<T>> tree, T root) {
    if (tree == null || !tree.containsKey(root)) return;

    for (T item : tree.get(root)) {
      postOrder(tree, item);
    }
    System.out.println(root);
  }

  /*
   sumTree (Node Version)
   -----------
   Given a tree built with the Node class (with integer values), compute and return the sum of all the node values.
   Example:
   If the tree is:
          5
       /  |  \
      3   9   8
        / | \  
       4  1  2
   then the method should return 32.
   A null tree should return 0
  */
  public static int sumTree(Node<Integer> root) {
    if (root == null) return 0;
    int count = root.value;
    
    for (Node<Integer> num: root.children) {
      count += sumTree(num);
    }
    return count;
  }

  /*
   sumTree (Map Version)
   -----------
   Given a tree represented as a map (where every node appears as a key and leaf nodes map to an empty list),
   compute the sum of all nodes.
   Example:
   For a tree represented as:
     5 -> [3, 9, 8]
     3 -> []
     9 -> [4, 1, 2]
     4 -> []
     1 -> []
     2 -> []
   the method should return 32.

   A null tree should return 0

   Hint: There's a simple way to do this!
  */
  public static int sumTree(Map<Integer, List<Integer>> tree) {
    if (tree == null) return 0;
    int count = 0;

    for (Map.Entry<Integer, List<Integer>> item : tree.entrySet()) {
      count += item.getKey();
    }
    return count;
  }

  /*
   findRoot
   -----------
   Given a tree represented as a map where each key is a parent node and the value is a list of its children,
   find the root of the tree. The root is the node with no parents.
   Example:
   If the tree is represented as:
     20 -> [40]
     8  -> []
     30 -> []
     10 -> [20, 30, 99]
     40 -> []
     99 -> [8]
   then the method should return 10.

  You can assume the tree is non-null and well-formed.

   Hint: No recursion needed! Think about how you would do this by hand.
  */
  public static <T> T findRoot(Map<T, List<T>> tree) {
    Set<T> values = new HashSet<>();

    for (Map.Entry<T, List<T>> item : tree.entrySet()) {
      values.addAll(item.getValue());
    }

    for (T key: tree.keySet()) {
      if (!values.contains(key)) {
        return key;
      }
    }
    return null;
  }

  /*
   maxDepth (Node Version)
   -----------
   Compute the maximum depth of a tree using the Node class. The depth is the number of nodes along
   the longest path from the root down to the farthest leaf. The root is at depth 1. If the tree is null, return 0.
   Example:
   For a tree structured as:
          A
       /  |  \
      B   E   C
      |      / \
      E     D   Q
             \ 
              Z
   the maximum depth is 4.

   
  */
  public static <T> int maxDepth(Node<T> root) {
    if (root == null) return 0;

    int depth = 1;

    for (Node<T> item : root.children) {
      int childDepth = maxDepth(item);
      depth = Math.max(depth, childDepth + 1);
    }
    return depth;
  }

  /*
   maxDepth (Map Version)
   -----------
   Compute the maximum depth of a tree using the Node class. The depth is the number of nodes along
   the longest path from the root down to the farthest leaf. The root is at depth 1. If the tree is null, return 0.
   Example:
   For a tree structured as:
    A -> [B, E, C]
    B -> [E]
    E -> []
    C -> [D, Q]
    D -> [Z]
    Q -> []
    Z -> []
   the maximum depth is 4 (A->C->D->Z).

   Hint: Use findRoot to start. Then, make a recursive helper method.
  */
  public static int maxDepth(Map<String, List<String>> tree) {
    if (tree == null || tree.isEmpty()) return 0;

    String root = findRoot(tree);

    return maxDepthHelper(root, tree);
  }

  // helper method
  public static int maxDepthHelper(String node, Map<String, List<String>> tree) {
    List<String> children = tree.get(node);
    if (children == null || children.isEmpty()) return 1;

    int childDepth = 0;
    for (String child : children) {
        childDepth = Math.max(childDepth, maxDepthHelper(child, tree));
    }
    return childDepth + 1;
  }
}
