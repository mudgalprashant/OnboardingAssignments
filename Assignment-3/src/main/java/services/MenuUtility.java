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
    String nodeId;
    String parentId;
    String childId;
    int inputCounter;

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
          parentId = "";
          childId = "";
          inputCounter = Constant.COUNTER;
          boolean childNodeExists = false;
          boolean parentNodeExists = false;

          while (true) {
            inputCounter = Constant.COUNTER;
            // Input Parent ID
            while (inputCounter++ < Constant.INVALID_INPUT_LIMIT) {
              System.out.println(Constant.INPUT_DEPENDENCY_PARENT_ID);
              parentId = Constant.SCANNER.nextLine().trim();
              if (familyTree.graph.containsKey(parentId)) {
                parentNodeExists = true;
                break;
              } else {
                System.out.println(Constant.NODE_ID_DOES_NOT_EXIST_TEXT);
              }
            }
            if (inputCounter > Constant.INVALID_INPUT_LIMIT) {
              System.out.println(Constant.INPUT_LIMIT_REACHED);
            } else {

              inputCounter = Constant.COUNTER;
              // Input Child ID
              while (inputCounter++ < Constant.INVALID_INPUT_LIMIT) {
                System.out.println(Constant.INPUT_DEPENDENCY_CHILD_ID);
                childId = Constant.SCANNER.nextLine().trim();
                if (familyTree.graph.containsKey(childId)) {
                  childNodeExists = true;
                  break;
                } else {
                  System.out.println(Constant.NODE_ID_DOES_NOT_EXIST_TEXT);
                }
              }
              if (inputCounter > Constant.INVALID_INPUT_LIMIT) {
                System.out.println(Constant.INPUT_LIMIT_REACHED);
              }

              if (childNodeExists && parentNodeExists) {
                if (validator.validateDependency(familyTree, parentId, childId)) {
                  familyTree.addDependency(parentId, childId);
                  break;
                }
              }
            }
          }
          break;

        case 3: // Get parents
          // Input nodeId and validate
          nodeId = "";
          inputCounter = Constant.COUNTER;
          while (inputCounter <= Constant.INVALID_INPUT_LIMIT) {
            System.out.println(Constant.NODE_ID_INPUT_TEXT);
            nodeId = Constant.SCANNER.nextLine().trim();
            if (familyTree.graph.containsKey(nodeId)) {
              break;
            } else {
              System.out.println(Constant.NODE_ID_DOES_NOT_EXIST_TEXT);
              inputCounter++;
            }
          }
          if (inputCounter > Constant.INVALID_INPUT_LIMIT) {
            System.out.println(Constant.INPUT_LIMIT_REACHED);
          } else {
            HashSet<String> parentNodeIds = familyTree.getParents(nodeId);
            if (!Objects.isNull(parentNodeIds)) {
              for (String parentNodeId : parentNodeIds) {
                System.out.println(familyTree.graph.get(parentNodeId));
              }
            }
          }
          break;

        case 4: // Get children
          // Input nodeId and validate
          nodeId = "";
          inputCounter = Constant.COUNTER;
          while (inputCounter <= Constant.INVALID_INPUT_LIMIT) {
            System.out.println(Constant.NODE_ID_INPUT_TEXT);
            nodeId = Constant.SCANNER.nextLine().trim();
            if (familyTree.graph.containsKey(nodeId)) {
              break;
            } else {
              System.out.println(Constant.NODE_ID_DOES_NOT_EXIST_TEXT);
              inputCounter++;
            }
          }
          if (inputCounter > Constant.INVALID_INPUT_LIMIT) {
            System.out.println(Constant.INPUT_LIMIT_REACHED);
          } else {
            HashSet<String> childNodeIds = familyTree.getChildren(nodeId);
            if (!Objects.isNull(childNodeIds)) {
              for (String childNodeId : childNodeIds) {
                System.out.println(familyTree.graph.get(childNodeId));
              }
            }
          }
          break;

        case 5: // Get ancestors
          // Input and validate nodeId
          nodeId = "";
          inputCounter = Constant.COUNTER;
          while (inputCounter <= Constant.INVALID_INPUT_LIMIT) {
            System.out.println(Constant.NODE_ID_INPUT_TEXT);
            nodeId = Constant.SCANNER.nextLine().trim();
            if (familyTree.graph.containsKey(nodeId)) {
              break;
            } else {
              System.out.println(Constant.NODE_ID_DOES_NOT_EXIST_TEXT);
              inputCounter++;
            }
          }
          if (inputCounter > Constant.INVALID_INPUT_LIMIT) {
            System.out.println(Constant.INPUT_LIMIT_REACHED);
          } else {
            HashSet<String> ancestorNodeIds = familyTree.getAncestors(nodeId);
            for (String ancestorNodeId : ancestorNodeIds) {
              System.out.println(familyTree.graph.get(ancestorNodeId));
            }
          }
          break;

        case 6: // Get Descendants
          // Input and validate nodeId
          nodeId = "";
          inputCounter = Constant.COUNTER;
          while (inputCounter <= Constant.INVALID_INPUT_LIMIT) {
            System.out.println(Constant.NODE_ID_INPUT_TEXT);
            nodeId = Constant.SCANNER.nextLine().trim();
            if (familyTree.graph.containsKey(nodeId)) {
              break;
            } else {
              System.out.println(Constant.NODE_ID_DOES_NOT_EXIST_TEXT);
              inputCounter++;
            }
          }
          if (inputCounter > Constant.INVALID_INPUT_LIMIT) {
            System.out.println(Constant.INPUT_LIMIT_REACHED);
          } else {
            HashSet<String> descendantNodeIds = familyTree.getDescendants(nodeId);
            for (String descendantNodeId : descendantNodeIds) {
              System.out.println(familyTree.graph.get(descendantNodeId));
            }
          }
          break;

        case 7: // Delete node
          // Input and validate nodeId
          nodeId = "";
          inputCounter = Constant.COUNTER;
          while (inputCounter <= Constant.INVALID_INPUT_LIMIT) {
            System.out.println(Constant.NODE_ID_INPUT_TEXT);
            nodeId = Constant.SCANNER.nextLine().trim();
            if (familyTree.graph.containsKey(nodeId)) {
              break;
            } else {
              System.out.println(Constant.NODE_ID_DOES_NOT_EXIST_TEXT);
              inputCounter++;
            }
          }
          if (inputCounter > Constant.INVALID_INPUT_LIMIT) {
            System.out.println(Constant.INPUT_LIMIT_REACHED);
          } else {
            familyTree.deleteNode(nodeId);
            System.out.println(Constant.SUCCESSFUL_DELETION_TEXT);
          }
          break;

        case 8: // Delete dependency
          // Input Parent ID
          parentId = "";
          childId = "";
          inputCounter = Constant.COUNTER;
          while (inputCounter <= Constant.INVALID_INPUT_LIMIT) {
            System.out.println(Constant.INPUT_DEPENDENCY_PARENT_ID);
            parentId = Constant.SCANNER.nextLine().trim();
            if (familyTree.graph.containsKey(parentId)) {
              break;
            } else {
              System.out.println(Constant.NODE_ID_DOES_NOT_EXIST_TEXT);
              inputCounter++;
            }
          }
          if (inputCounter > Constant.INVALID_INPUT_LIMIT) {
            System.out.println(Constant.INPUT_LIMIT_REACHED);
          } else {

            // Input Child ID
            inputCounter = Constant.COUNTER;
            while (inputCounter <= Constant.INVALID_INPUT_LIMIT) {
              System.out.println(Constant.INPUT_DEPENDENCY_CHILD_ID);
              childId = Constant.SCANNER.nextLine().trim();
              if (familyTree.graph.containsKey(childId)) {
                break;
              } else {
                System.out.println(Constant.NODE_ID_DOES_NOT_EXIST_TEXT);
                inputCounter++;
              }
            }
            if (inputCounter > Constant.INVALID_INPUT_LIMIT) {
              System.out.println(Constant.INPUT_LIMIT_REACHED);
            } else {
              familyTree.deleteDependency(parentId, childId);
            }
          }
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
