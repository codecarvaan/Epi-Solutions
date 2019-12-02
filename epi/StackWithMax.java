package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;

public class StackWithMax {

    public static class ElementWithCachedMax {
        int val;
        int max;

        public ElementWithCachedMax(int val, int max) {
            this.val = val;
            this.max = max;
        }
    }

    public static class Stack {
        int currentMax;
        private Deque<ElementWithCachedMax> elementWithCachedMax = new ArrayDeque<>();

        public boolean empty() {
            return elementWithCachedMax.isEmpty();
        }

        public Integer max() {
            return elementWithCachedMax.peekFirst().max;
        }

        public Integer pop() {
            if (empty()) {
                throw new IllegalStateException("pop(): empty stack");
            }
            return elementWithCachedMax.pollFirst().val;
        }

        public void push(Integer x) {
          elementWithCachedMax.push(new ElementWithCachedMax(x, Math.max(x,empty()?x:max())));

            return;
        }
    }

    @EpiUserType(ctorParams = {String.class, int.class})
    public static class StackOp {
        public String op;
        public int arg;

        public StackOp(String op, int arg) {
            this.op = op;
            this.arg = arg;
        }
    }

    @EpiTest(testDataFile = "stack_with_max.tsv")
    public static void stackTest(List<StackOp> ops) throws TestFailure {
        try {
            Stack s = new Stack();
            int result;
            for (StackOp op : ops) {
                switch (op.op) {
                    case "Stack":
                        s = new Stack();
                        break;
                    case "push":
                        s.push(op.arg);
                        break;
                    case "pop":
                        result = s.pop();
                        if (result != op.arg) {
                            throw new TestFailure("Pop: expected " + String.valueOf(op.arg) +
                                    ", got " + String.valueOf(result));
                        }
                        break;
                    case "max":
                        result = s.max();
                        if (result != op.arg) {
                            throw new TestFailure("Max: expected " + String.valueOf(op.arg) +
                                    ", got " + String.valueOf(result));
                        }
                        break;
                    case "empty":
                        result = s.empty() ? 1 : 0;
                        if (result != op.arg) {
                            throw new TestFailure("Empty: expected " + String.valueOf(op.arg) +
                                    ", got " + String.valueOf(s));
                        }
                        break;
                    default:
                        throw new RuntimeException("Unsupported stack operation: " + op.op);
                }
            }
        } catch (NoSuchElementException e) {
            throw new TestFailure("Unexpected NoSuchElement exception");
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "StackWithMax.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
