package services;

import constants.Constant;
import models.FamilyTree;
import models.Node;
import services.Validator;

import java.util.HashSet;
import java.util.Objects;

/**
 * The type Menu utility.
 */
public class MenuUtility {

  private final FamilyTree familyTree = new FamilyTree();

  /**
   * Perform tasks.
   */
  public void performTasks() {

    // Print menu
    System.out.println(Constant.DIVIDER);
    System.out.println(Constant.PRINT_MENU_TEXT);

    final String inputTask = Constant.SCANNER.nextLine();
    Validator validator = new Validator();

    if (validator.validateTask(inputTask)) {
      final int taskNumber = Integer.parseInt(inputTask);

      switch (taskNumber) {

        case 1: // Add node
          Node node = Node.builder().build();
          node.addNodeId();
          while (familyTree.graph.containsKey(node.getNodeId())) {
            System.out.println(Constant.NODE_ID_ALREADY_EXISTS_TEXT);
            node.addNodeId();
          }

          node.addName();
          node.addMetadata();
          familyTree.addNode(node);
          break;

        case 2: // Add dependency
          String parentId;
          String childId;
          do {
            // Input Parent ID
            while (true) {
              System.out.println(Constant.INPUT_DEPENDENCY_PARENT_ID);
              parentId = Constant.SCANNER.nextLine().trim();
              if (familyTree.graph.containsKey(parentId)) {
                break;
              } else {
                System.out.println(Constant.NODE_ID_DOES_NOT_EXIST_TEXT);
              }
            }

            // Input Child ID
            while (true) {
              System.out.println(Constant.INPUT_DEPENDENCY_CHILD_ID);
              childId = Constant.SCANNER.nextLine().trim();
              if (familyTree.graph.containsKey(childId)) {
                break;
              } else {
                System.out.println(Constant.NODE_ID_DOES_NOT_EXIST_TEXT);
              }
            }


          } while (!validator.validateDependency(familyTree, parentId, childId));

          familyTree.addDependency(parentId, childId);
          break;

        case 3: // Get parents
          // Input nodeId and validate
          String nodeId;
          while (true) {
            System.out.println(Constant.NODE_ID_INPUT_TEXT);
            nodeId = Constant.SCANNER.nextLine().trim();
            if (familyTree.graph.containsKey(nodeId)) {
              break;
            } else {
              System.out.println(Constant.NODE_ID_DOES_NOT_EXIST_TEXT);
            }
          }

          HashSet<String> parentNodeIds = familyTree.getParents(nodeId);
          if (!Objects.isNull(parentNodeIds)) {
            for (String parentNodeId : parentNodeIds) {
              System.out.println(familyTree.graph.get(parentNodeId));
            }
          }
          break;

        case 4: // Get children
          // Input nodeId and validate
          while (true) {
            System.out.println(Constant.NODE_ID_INPUT_TEXT);
            nodeId = Constant.SCANNER.nextLine().trim();
            if (familyTree.graph.containsKey(nodeId)) {
              break;
            } else {
              System.out.println(Constant.NODE_ID_DOES_NOT_EXIST_TEXT);
            }
          }
          HashSet<String> childNodeIds = familyTree.getChildren(nodeId);
          if (!Objects.isNull(childNodeIds)) {
            for (String childNodeId : childNodeIds) {
              System.out.println(familyTree.graph.get(childNodeId));
            }
          }
          break;

        case 5: // Get ancestors
          // Input and validate nodeId
          while (true) {
            System.out.println(Constant.NODE_ID_INPUT_TEXT);
            nodeId = Constant.SCANNER.nextLine().trim();
            if (familyTree.graph.containsKey(nodeId)) {
              break;
            } else {
              System.out.println(Constant.NODE_ID_DOES_NOT_EXIST_TEXT);
            }
          }

          HashSet<String> ancestorNodeIds = familyTree.getAncestors(nodeId);
          for (String ancestorNodeId : ancestorNodeIds) {
            System.out.println(familyTree.graph.get(ancestorNodeId));
          }
          break;

        case 6: // Get Descendants
          // Input and validate nodeId
          while (true) {
            System.out.println(Constant.NODE_ID_INPUT_TEXT);
            nodeId = Constant.SCANNER.nextLine().trim();
            if (familyTree.graph.containsKey(nodeId)) {
              break;
            } else {
              System.out.println(Constant.NODE_ID_DOES_NOT_EXIST_TEXT);
            }
          }

          HashSet<String> descendantNodeIds = familyTree.getDescendants(nodeId);
          for (String descendantNodeId : descendantNodeIds) {
            System.out.println(familyTree.graph.get(descendantNodeId));
          }
          break;

        case 7: // Delete node
          // Input and validate nodeId
          while (true) {
            System.out.println(Constant.NODE_ID_INPUT_TEXT);
            nodeId = Constant.SCANNER.nextLine().trim();
            if (familyTree.graph.containsKey(nodeId)) {
              break;
            } else {
              System.out.println(Constant.NODE_ID_DOES_NOT_EXIST_TEXT);
            }
          }

          familyTree.deleteNode(nodeId);
          System.out.println(Constant.SUCCESSFUL_DELETION_TEXT);
          break;

        case 8:
          // Input Parent ID
          while (true) {
            System.out.println(Constant.INPUT_DEPENDENCY_PARENT_ID);
            parentId = Constant.SCANNER.nextLine().trim();
            if (familyTree.graph.containsKey(parentId)) {
              break;
            } else {
              System.out.println(Constant.NODE_ID_DOES_NOT_EXIST_TEXT);
            }
          }

          // Input Child ID
          while (true) {
            System.out.println(Constant.INPUT_DEPENDENCY_CHILD_ID);
            childId = Constant.SCANNER.nextLine().trim();
            if (familyTree.graph.containsKey(childId)) {
              break;
            } else {
              System.out.println(Constant.NODE_ID_DOES_NOT_EXIST_TEXT);
            }
          }

          familyTree.deleteDependency(parentId, childId);
          break;

        case 0:
          System.out.println(Constant.SUCCESSFUL_EXIT_TEXT);
          return;
        default:
          break;
      }
    } else {
      System.out.println(Constant.INVALID_TASK_TEXT);
    }
    performTasks();
  }
}
