package models;

import constants.Constant;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import services.Validator;

import java.util.HashMap;
import java.util.HashSet;

/**
 * The type Node.
 */
@Builder
@Data
public class Node {
  /**
   * The node ID.
   */
  private String nodeId;
  /**
   * The name.
   */
  private String name;
  /**
   * The Metadata.
   */
  private HashMap<String, String> metadata;
  /**
   * The Parent IDs.
   */
  private HashSet<String> parentIds;
  /**
   * The Child IDs.
   */
  private HashSet<String> childIds;

  /**
   * Add node ID.
   */
  public void addNodeId() {
    // Input node Id
    System.out.println(Constant.NODE_ID_INPUT_TEXT);
    final String nodeId = Constant.SCANNER.nextLine().trim();

    // Validate and set node Id
    Validator validator = new Validator();
    if (validator.validateNodeId(nodeId)) {
      this.setNodeId(nodeId);
    } else {
      addNodeId();
    }
  }

  /**
   * Add name.
   */
  public void addName() {
    // Input and set node name
    System.out.println(Constant.NODE_NAME_INPUT_TEXT);
    final String name = Constant.SCANNER.nextLine().trim();
    this.setName(name);
  }

  /**
   * Add metadata.
   */
  public void addMetadata() {
    // Confirm if user wants to add metadata
    System.out.println(Constant.METADATA_QUERY_TEXT);
    final String confirmation = Constant.SCANNER.nextLine().trim();

    if (confirmation.equalsIgnoreCase(Constant.YES)) {
      // Add key and value
      System.out.println(Constant.INPUT_METADATA_KEY_TEXT);
      final String key = Constant.SCANNER.nextLine().trim();
      System.out.println(Constant.INPUT_METADATA_VALUE_TEXT);
      final String value = Constant.SCANNER.nextLine().trim();
      this.metadata.put(key, value);
      this.addMetadata();
    }
  }
}
