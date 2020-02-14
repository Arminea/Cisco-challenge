import org.junit.runner.RunWith
import org.scalatest.{FlatSpec, Matchers}
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GNodeTest extends FlatSpec with Matchers {

  val G = Node("G", List())
  val F = Node("F", List(G))
  val E = Node("E", List(F))
  val D = Node("D", List(G))
  val B = Node("B", List(D))
  val C = Node("C", List(B, E, F))
  val A = Node("A", List(B, C))

  it should "walk through all nodes" in {

    val nodesExpected = List(A, B, D, G, C, E, F)
    val nodesActual: List[GNode] = GNode.walkGraph(A)

    nodesActual shouldBe nodesExpected
  }

  "walking" should "return Nil if empty graph" in {

    val nodes = GNode.walkGraph(null)

    nodes shouldBe Nil
  }

  it should "find all paths" in {

    val pathsExpected = List(
      List(A, B, D, G),
      List(A, C, B, D, G),
      List(A, C, E, F, G),
      List(A, C, F, G)
    )
    val pathsActual: List[List[GNode]] = GNode.paths(A)

    pathsActual shouldBe pathsExpected
  }

  "path finding" should "return Nil if empty graph" in {

    val nodes = GNode.paths(null)

    nodes shouldBe Nil
  }

}
