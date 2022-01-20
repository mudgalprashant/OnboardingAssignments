package models;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Family tree test.
 */
class FamilyTreeTest {

  /**
   * Gets parents.
   */
  @Test
  void getParents() {

    // Create test node and set ID
    Node node = Node.builder().build();
    node.setNodeId("1");

    // Crete parent IDs for test node
    HashSet<String> parentIds = new HashSet<>();
    parentIds.add("2");
    parentIds.add("3");
    parentIds.add("4");
    parentIds.add("5");

    // Add node IDs to node
    node.setParentIds(parentIds);

    // Add node to FamilyTree data structure
    FamilyTree familyTree = new FamilyTree();
    familyTree.addNode(node);

    assertEquals(parentIds, familyTree.getParents(node.getNodeId()));
  }

  /**
   * Gets children.
   */
  @Test
  void getChildren() {
    // Create test node
    Node node = Node.builder().build();
    node.setNodeId("1");

    // Create child IDs for test node
    HashSet<String> childIds = new HashSet<>();
    childIds.add("2");
    childIds.add("3");
    childIds.add("4");
    childIds.add("5");

    // Add child IDs to node
    node.setChildIds(childIds);

    // Add node to FamilyTree data structure
    FamilyTree familyTree = new FamilyTree();
    familyTree.addNode(node);

    assertEquals(childIds, familyTree.getChildren(node.getNodeId()));
  }

  /**
   * Gets ancestors.
   */
  @Test
  void getAncestors() {

    // Create two nodes
    Node node1 = Node.builder().build();
    node1.setNodeId("1");

    Node node2 = Node.builder().build();
    node2.setNodeId("2");

    // Add parents to both nodes
    HashSet<String> parentIdsNode1 = new HashSet<>();
    parentIdsNode1.add("2");
    parentIdsNode1.add("3");
    parentIdsNode1.add("4");
    node1.setParentIds(parentIdsNode1);

    HashSet<String> parentIdsNode2 = new HashSet<>();
    parentIdsNode2.add("5");
    parentIdsNode2.add("6");
    parentIdsNode2.add("7");
    node2.setParentIds(parentIdsNode2);


    // Add nodes to FamilyTree data structure
    FamilyTree familyTree = new FamilyTree();
    familyTree.addNode(node1);
    familyTree.addNode(node2);
    parentIdsNode1.addAll(parentIdsNode2);

    // Test for ancestors of child node
    assertEquals(parentIdsNode1, familyTree.getParents(node1.getNodeId()));
  }

  /**
   * Gets descendants.
   */
  @Test
  void getDescendants() {
    // Create two nodes
    Node node1 = Node.builder().build();
    node1.setNodeId("1");

    Node node2 = Node.builder().build();
    node2.setNodeId("2");

    // Add children to both nodes
    HashSet<String> childIdsNode1 = new HashSet<>();
    childIdsNode1.add("2");
    childIdsNode1.add("3");
    childIdsNode1.add("4");
    node1.setChildIds(childIdsNode1);

    HashSet<String> childIdsNode2 = new HashSet<>();
    childIdsNode2.add("5");
    childIdsNode2.add("6");
    childIdsNode2.add("7");
    node2.setChildIds(childIdsNode2);


    // Add nodes to FamilyTree data structure
    FamilyTree familyTree = new FamilyTree();
    familyTree.addNode(node1);
    familyTree.addNode(node2);
    childIdsNode1.addAll(childIdsNode2);

    // Test for parent node
    assertEquals(childIdsNode1, familyTree.getChildren(node1.getNodeId()));
  }
}