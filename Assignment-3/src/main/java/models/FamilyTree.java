package models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;


public class FamilyTree {
  public HashMap<String, Node> graph = new HashMap<>();
  public HashSet<String> visited = new HashSet<>();

  public void addNode(Node node) {
    graph.put(node.getNodeId(), node);
  }

  public void addDependency(String parentId, String childId) {
    // Set dependency in parent
    Node parentNode = graph.get(parentId);
    HashSet<String> childIds = parentNode.getChildIds();
    if (childIds == null){
      childIds = new HashSet<>();
    }
    childIds.add(childId);
    parentNode.setChildIds(childIds);
    graph.put(parentId, parentNode);

    // Set dependencies in child
    Node childNode = graph.get(childId);
    HashSet<String> parentIds = childNode.getParentIds();
    if (parentIds == null){
      parentIds = new HashSet<>();
    }
    parentIds.add(parentId);
    childNode.setParentIds(parentIds);
    graph.put(childId, childNode);

  }

  public HashSet<String> getParents(String nodeId) {
    // Print parent nodes
    return graph.get(nodeId).getParentIds();

  }

  public HashSet<String> getChildren(String nodeId) {
    // Print child nodes
    return graph.get(nodeId).getChildIds();

  }

  public void deleteNode(String nodeId) {
    Node node = graph.get(nodeId);

    // Delete dependencies with parents
    HashSet<String> parentIds = node.getParentIds();
    if (parentIds != null) {
      for (String parentId : parentIds) {
        graph.get(parentId).getChildIds().remove(nodeId);
      }
    }

    // Delete dependencies with children
    HashSet<String> childIds = node.getChildIds();
    if (childIds != null) {
      for (String childId : childIds) {
        graph.get(childId).getParentIds().remove(nodeId);
      }
    }

    // Delete node from graph
    graph.remove(nodeId);
  }

  public void deleteDependency(String parentId, String childId) {
    // Delete dependency from parent node
    Node parentNode = graph.get(parentId);
    HashSet<String> childIds = parentNode.getChildIds();
    if (childIds != null) {
      childIds.remove(childId);
    }
    parentNode.setChildIds(childIds);
    graph.put(parentId, parentNode);

    // Delete dependency from child node
    Node childNode = graph.get(childId);
    HashSet<String> parentIds = childNode.getParentIds();
    if (parentIds != null) {
      parentIds.remove(parentId);
    }
    childNode.setParentIds(parentIds);
    graph.put(childId, childNode);
  }

  public HashSet<String> getAncestors(String currentNodeId) {

    HashSet<String> ancestorNodeIds = new HashSet<>();
    LinkedList<String> bfsQueue = new LinkedList<>();
    HashSet<String> currentParentIds = graph.get(currentNodeId).getParentIds();
    if (currentParentIds != null) {
      bfsQueue.addAll(currentParentIds);
    }



    while (bfsQueue.size() > 0) {

      // Add current node ID to ancestor IDs and mark it visited
      currentNodeId = bfsQueue.poll();
      visited.add(currentNodeId);
      ancestorNodeIds.add(currentNodeId);


      // Add unvisited parents to bfsQueue
      currentParentIds = graph.get(currentNodeId).getParentIds();
      if (currentParentIds != null){
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

  public HashSet<String> getDescendants(String currentNodeId) {

    HashSet<String> descendantNodeIds = new HashSet<>();
    HashSet<String> currentChildIds = graph.get(currentNodeId).getChildIds();
    LinkedList<String> bfsQueue = new LinkedList<>();
    if (currentChildIds != null) {
      bfsQueue.addAll(currentChildIds);
    }

    while (bfsQueue.size() > 0) {

      // Add current node ID to descendant IDs and mark it visited
      currentNodeId = bfsQueue.poll();
      visited.add(currentNodeId);
      descendantNodeIds.add(currentNodeId);


      // Add unvisited children to bfsQueue
      currentChildIds = graph.get(currentNodeId).getChildIds();
      if (currentChildIds != null) {
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

