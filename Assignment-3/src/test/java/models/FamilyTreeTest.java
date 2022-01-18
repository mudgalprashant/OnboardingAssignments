package models;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class FamilyTreeTest {

  @Test
  void getParents() {
    Node node = Node.builder().build();
    node.setNodeId("1");

    HashSet<String> parentIds = new HashSet<>();
    parentIds.add("2");
    parentIds.add("3");
    parentIds.add("4");
    parentIds.add("5");

    node.setParentIds(parentIds);

    FamilyTree familyTree = new FamilyTree();
    familyTree.addNode(node);

    assertEquals(parentIds, familyTree.getParents(node.getNodeId()));
  }

  @Test
  void getChildren() {
    Node node = Node.builder().build();
    node.setNodeId("1");

    HashSet<String> childIds = new HashSet<>();
    childIds.add("2");
    childIds.add("3");
    childIds.add("4");
    childIds.add("5");

    node.setChildIds(childIds);

    FamilyTree familyTree = new FamilyTree();
    familyTree.addNode(node);

    assertEquals(childIds, familyTree.getChildren(node.getNodeId()));
  }

  @Test
  void getAncestors() {
    Node node1 = Node.builder().build();
    node1.setNodeId("1");

    Node node2 = Node.builder().build();
    node2.setNodeId("2");

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


    FamilyTree familyTree = new FamilyTree();
    familyTree.addNode(node1);
    familyTree.addNode(node2);
    parentIdsNode1.addAll(parentIdsNode2);
    assertEquals(parentIdsNode1, familyTree.getParents(node1.getNodeId()));
  }

  @Test
  void getDescendants() {
    Node node1 = Node.builder().build();
    node1.setNodeId("1");

    Node node2 = Node.builder().build();
    node2.setNodeId("2");

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


    FamilyTree familyTree = new FamilyTree();
    familyTree.addNode(node1);
    familyTree.addNode(node2);
    childIdsNode1.addAll(childIdsNode2);
    assertEquals(childIdsNode1, familyTree.getChildren(node1.getNodeId()));
  }
}