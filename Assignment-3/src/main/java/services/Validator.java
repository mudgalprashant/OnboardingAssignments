package services;

import constants.Constant;
import exceptions.CyclicDependencyException;
import exceptions.InvalidEntryException;
import models.FamilyTree;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashSet;

/**
 * The type Validator.
 */
public class Validator {
  private final Log log = LogFactory.getLog(Validator.class);

  /**
   * Validate node id boolean.
   *
   * @param nodeId the node id
   * @return the boolean
   */
  public boolean validateNodeId(String nodeId) {
    boolean isValid = true;
    try {
      if (nodeId.isBlank()) {
        throw new InvalidEntryException(Constant.BLANK_ID_EXCEPTION_TEXT);
      }
      if (!nodeId.matches(Constant.REGEX_FOR_NODE_ID)) {
        throw new InvalidEntryException(Constant.NON_ALPHANUMERIC_ID_EXCEPTION_TEXT);
      }
    } catch (InvalidEntryException exception) {
      log.error(exception);
      isValid = false;
    }
    return isValid;
  }

  /**
   * Validate task boolean.
   *
   * @param task the task
   * @return the boolean
   */
  public boolean validateTask(String task) {
    boolean isValid = true;
    try {
      final int taskNumber = Integer.parseInt(task);
      if (taskNumber >= Constant.NUMBER_OF_TASKS || taskNumber < 0) {
        throw new InvalidEntryException(Constant.INVALID_TASK_TEXT);
      }
    } catch (InvalidEntryException | NumberFormatException exception) {
      log.error(exception);
      isValid = false;
    }
    return isValid;
  }

  /**
   * Validate dependency boolean.
   *
   * @param familyTree the family tree
   * @param parentId   the parent id
   * @param childId    the child id
   * @return the boolean
   */
  public boolean validateDependency(FamilyTree familyTree, String parentId, String childId) {
    boolean isValid = true;
    try {
      HashSet<String> ancestors = familyTree.getAncestors(parentId);
      if (ancestors.contains(childId)) {
        throw new CyclicDependencyException(Constant.CYCLIC_DEPENDENCY_TEXT);
      }
    } catch (CyclicDependencyException exception) {
      log.error(exception);
      isValid = false;
    }
    return isValid;
  }
}
