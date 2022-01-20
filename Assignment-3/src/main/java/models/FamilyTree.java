package models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;


/**
 * The type Family tree.
 */
public class FamilyTree {
  /**
   * The Graph.
   */
  public HashMap<String, Node> graph = new HashMap<>();
  /**
   * The Visited.
   */
  public HashSet<String> visited = new HashSet<>();

  /**
   * Add node.
   *
   * @param node the node
   */
  public void addNode(Node node) {
    graph.put(node.getNodeId(), node);
  }

  /**
   * Add dependency.
   *
   * @param parentId the parent id
   * @param childId  the child id
   */
  public void addDependency(String parentId, String childId) {
    // Set dependency in parent
    Node parentNode = graph.get(parentId);
    HashSet<String> childIds = parentNode.getChildIds();
    if (Objects.isNull(childIds)){
      childIds = new HashSet<>();
    }
    childIds.add(childId);
    parentNode.setChildIds(childIds);
    graph.put(parentId, parentNode);

    // Set dependencies in child
    Node childNode = graph.get(childId);
    HashSet<String> parentIds = childNode.getParentIds();
    if (Objects.isNull(parentIds)){
      parentIds = new HashSet<>();
    }
    parentIds.add(parentId);
    childNode.setParentIds(parentIds);
    graph.put(childId, childNode);

  }

  /**
   * Gets parents.
   *
   * @param nodeId the node id
   * @return the parents
   */
  public HashSet<String> getParents(String nodeId) {
    // Print parent nodes
    return graph.get(nodeId).getParentIds();

  }

  /**
   * Gets children.
   *
   * @param nodeId the node id
   * @return the children
   */
  public HashSet<String> getChildren(String nodeId) {
    // Print child nodes
    return graph.get(nodeId).getChildIds();

  }

  /**
   * Delete node.
   *
   * @param nodeId the node id
   */
  public void deleteNode(String nodeId) {
    Node node = graph.get(nodeId);

    // Delete dependencies with parents
    HashSet<String> parentIds = node.getParentIds();
    if (!Objects.isNull(parentIds)) {
      for (String parentId : parentIds) {
        graph.get(parentId).getChildIds().remove(nodeId);
      }
    }

    // Delete dependencies with children
    HashSet<String> childIds = node.getChildIds();
    if (!Objects.isNull(childIds)) {
      for (String childId : childIds) {
        graph.get(childId).getParentIds().remove(nodeId);
      }
    }

    // Delete node from graph
    graph.remove(nodeId);
  }

  /**
   * Delete dependency.
   *
   * @param parentId the parent id
   * @param childId  the child id
   */
  public void deleteDependency(String parentId, String childId) {
    // Delete dependency from parent node
    Node parentNode = graph.get(parentId);
    HashSet<String> childIds = parentNode.getChildIds();
    if (!Objects.isNull(childIds)) {
      childIds.remove(childId);
    }
    parentNode.setChildIds(childIds);
    graph.put(parentId, parentNode);

    // Delete dependency from child node
    Node childNode = graph.get(childId);
    HashSet<String> parentIds = childNode.getParentIds();
    if (!Objects.isNull(parentIds)) {
      parentIds.remove(parentId);
    }
    childNode.setParentIds(parentIds);
    graph.put(childId, childNode);
  }

  /**
   * Gets ancestors.
   *
   * @param currentNodeId the current node id
   * @return the ancestors
   */
  public HashSet<String> getAncestors(String currentNodeId) {

    HashSet<String> ancestorNodeIds = new HashSet<>();
    LinkedList<String> bfsQueue = new LinkedList<>();
    HashSet<String> currentParentIds = graph.get(currentNodeId).getParentIds();
    if (!Objects.isNull(currentParentIds)) {
      bfsQueue.addAll(currentParentIds);
    }



    while (bfsQueue.size() > 0) {

      // Add current node ID to ancestor IDs and mark it visited
      currentNodeId = bfsQueue.poll();
      visited.add(currentNodeId);
      ancestorNodeIds.add(currentNodeId);


      // Add unvisited parents to bfsQueue
      currentParentIds = graph.get(currentNodeId).getParentIds();
      if (!Objects.isNull(currentParentIds)){
        for (String parentNodeId : currentParentIds) {
          if (!visited.contains(parentNodeId)) {
            bfsQueue.add(parentNodeId);
          }
        }
      }
    }

    // Mark all nodes 'not visited'
    visited.clear();

    // Return ancestor Ids
    return ancestorNodeIds;
  }

  /**
   * Gets descendants.
   *
   * @param currentNodeId the current node id
   * @return the descendants
   */
  public HashSet<String> getDescendants(String currentNodeId) {

    HashSet<String> descendantNodeIds = new HashSet<>();
    HashSet<String> currentChildIds = graph.get(currentNodeId).getChildIds();
    LinkedList<String> bfsQueue = new LinkedList<>();
    if (!Objects.isNull(currentChildIds)) {
      bfsQueue.addAll(currentChildIds);
    }

    while (bfsQueue.size() > 0) {

      // Add current node ID to descendant IDs and mark it visited
      currentNodeId = bfsQueue.poll();
      visited.add(currentNodeId);
      descendantNodeIds.add(currentNodeId);


      // Add unvisited children to bfsQueue
      currentChildIds = graph.get(currentNodeId).getChildIds();
      if (!Objects.isNull(currentChildIds)) {
        for (String childNodeId : currentChildIds) {
          if (!visited.contains(childNodeId)) {
            bfsQueue.add(childNodeId);
          }
        }
      }
    }

    // Mark all nodes 'not visited'
    visited.clear();

    // Return descendants Ids
    return descendantNodeIds;
  }

}

