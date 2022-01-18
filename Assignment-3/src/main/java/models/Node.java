package models;

import constants.Constant;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import services.Validator;

import java.util.HashMap;
import java.util.HashSet;

@Builder
@Data
public class Node {
  private String nodeId;
  private String name;
  private HashMap<String, String> metadata;
  private HashSet<String> parentIds;
  private HashSet<String> childIds;

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

  public void addName() {
    // Input and set node name
    System.out.println(Constant.NODE_NAME_INPUT_TEXT);
    final String name = Constant.SCANNER.nextLine().trim();
    this.setName(name);
  }

  public void addMetadata() {
    // Ask for metadata confirmation
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
