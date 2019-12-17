package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ListCyclicRightShift {
  @EpiTest(testDataFile = "list_cyclic_right_shift.tsv")

  public static ListNode<Integer> cyclicallyRightShiftList(ListNode<Integer> L,
                                                           int k) {
      // TODO - you fill in here.
      ListNode node = L;
      while (k-- > 0) {
          if (node == null) {
              node = L;
          }
          node = node.next;

      }
      if (node == null) {
          return L;
      }
      ListNode endNode = node;
      while (endNode.next != null) {
          endNode = endNode.next;
      }
      endNode.next = L;
      ListNode newStart = node.next;
      node.next = null;
      return newStart;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ListCyclicRightShift.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
