
trait GNode {
  def getName: String
  def getChildren: List[GNode]
}

case class Node(name: String, children: List[GNode]) extends GNode {
  override def getName: String = name

  override def getChildren: List[GNode] = children

  override def toString: String = name

  def toStringWithChildren: String = children match {
    case Nil => s"$name: -"
    case list => s"$name: ${list.mkString(",")}"
  }

}

object GNode {

  /**
   * Function walks through all nodes starting at 'node'.
   *
   * @param node
   * @return unique nodes
   */
  def walkGraph(node: GNode): List[GNode] = {

    if (node == null) return Nil

    @annotation.tailrec
    def DFS(stack: Set[GNode], visited: List[GNode]): List[GNode] = {
      if (stack.isEmpty) {
        visited
      }
      else {
        val nonVisitedChildren = stack.head.getChildren.filter(!visited.contains(_)).toSet

        DFS(nonVisitedChildren ++ stack.tail, stack.head :: visited)
      }
    }

    DFS(Set(node), List()).reverse
  }

  /**
   * Function return all possible paths from through the graph starting at 'node'.
   *
   * @param node
   * @return all paths
   */
  def paths(node: GNode): List[List[GNode]] = {

    if (node == null) return Nil

    val stack = scala.collection.mutable.ListBuffer[GNode]()
    val paths = scala.collection.mutable.ListBuffer[List[GNode]]()

    // recursive function
    def paths0(stack: scala.collection.mutable.ListBuffer[GNode], node: GNode): Unit = {
      // push
      stack += node

      // last node in the path, save values in the stack
      if (node.getChildren.isEmpty)
        paths += stack.toList

      for (child <- node.getChildren)
        paths0(stack, child)

      //pop
      stack.trimEnd(1)
    }

    paths0(stack, node)

    paths.toList
  }
}