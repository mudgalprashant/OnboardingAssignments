package services;

import constants.Constant;
import exceptions.CyclicDependencyException;
import exceptions.InvalidEntryException;
import exceptions.InvalidTaskException;
import models.FamilyTree;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashSet;

public class Validator {
  private final Log log = LogFactory.getLog(Validator.class);

  public boolean validateNodeId(String nodeId) {
    try {
      if (nodeId.isBlank()) {
        throw new InvalidEntryException(Constant.BLANK_ID_EXCEPTION_TEXT);
      }
      if (!nodeId.matches(Constant.REGEX_FOR_NODE_ID)) {
        throw new InvalidEntryException(Constant.NON_ALPHANUMERIC_ID_EXCEPTION_TEXT);
      }
    } catch (InvalidEntryException exception) {
      log.error(exception);
      return false;
    }
    return true;
  }

  public boolean validateTask(String task) {
    try {
      final int taskNumber = Integer.parseInt(task);
      if (taskNumber >= Constant.NUMBER_OF_TASKS || taskNumber < 0) {
        throw new InvalidTaskException(Constant.INVALID_TASK_TEXT);
      }
    } catch (InvalidTaskException | NumberFormatException exception) {
      log.error(exception);
      return false;
    }
    return true;
  }

  public boolean validateDependency(FamilyTree familyTree, String parentId, String childId) {

    try {
      HashSet<String> ancestors = familyTree.getAncestors(parentId);
      if (ancestors.contains(childId)) {
        throw new CyclicDependencyException(Constant.CYCLIC_DEPENDENCY_TEXT);
      }
    } catch (CyclicDependencyException exception) {
      log.error(exception);
      return false;
    }
    return true;
  }
}
