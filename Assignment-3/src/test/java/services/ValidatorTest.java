package services;

import models.FamilyTree;
import models.Node;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {
  final Validator validator = new Validator();
  @Test
  void validateNodeId() {

    // Test for valid node Id
    assertTrue(validator.validateNodeId("11"));

    // Test for blank node Id
    assertFalse(validator.validateNodeId("  "));

  }

  @Test
  void validateTask() {
    // Tests for valid tasks
    String task = "0";
    assertTrue(validator.validateTask(task));

    task = "1";
    assertTrue(validator.validateTask(task));

    task = "5";
    assertTrue(validator.validateTask(task));

    task = "3";
    assertTrue(validator.validateTask(task));

    task = "8";
    assertTrue(validator.validateTask(task));

    // Tests for invalid tasks
    task = "-1";
    assertFalse(validator.validateTask(task));

    task = "10";
    assertFalse(validator.validateTask(task));

    task = "random";
    assertFalse(validator.validateTask(task));

    task = "";
    assertFalse(validator.validateTask(task));
  }

  @Test
  void validateDependency() {

    // Create Tree
    // 1->3, 1->4, 2->3, 2->4, 3->5, 4->5

    FamilyTree familyTree = new FamilyTree();
    Node node1 = Node.builder().build();
    node1.setNodeId("1");
    Node node2 = Node.builder().build();
    node2.setNodeId("2");
    Node node3 = Node.builder().build();
    node3.setNodeId("3");
    Node node4 = Node.builder().build();
    node4.setNodeId("4");
    Node node5 = Node.builder().build();
    node5.setNodeId("5");

    HashSet<String> idSet1 = new HashSet<>();
    idSet1.add("3");
    idSet1.add("4");

    HashSet<String> idSet2 = new HashSet<>();
    idSet2.add("1");
    idSet2.add("2");

    node1.setChildIds(idSet1);
    node2.setChildIds(idSet1);
    node3.setParentIds(idSet2);
    node4.setParentIds(idSet2);
    node5.setParentIds(idSet1);

    familyTree.graph.put("1", node1);
    familyTree.graph.put("2", node2);
    familyTree.graph.put("3", node3);
    familyTree.graph.put("4", node4);
    familyTree.graph.put("5", node5);

    // Check for valid dependency
    String parentId = "1";
    String childId = "6";
    assertTrue(validator.validateDependency(familyTree, parentId, childId));

    // Check for invalid/cyclic dependency
    parentId = "5";
    childId = "1";
    assertFalse(validator.validateDependency(familyTree, parentId, childId));

  }
}