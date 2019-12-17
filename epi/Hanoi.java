package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class Hanoi {

    private static final int NUM_PEGS = 3;

    public static List<List<Integer>> computeTowerHanoi(int numRings) {
        // TODO - you fill in here.
        List<Deque<Integer>> pegs = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            pegs.add(new ArrayDeque<>());
        }
        //Initialization pegs
        for (int i = numRings; i >= 1; i--) {
            pegs.get(0).addFirst(i);
        }
        List<List<Integer>> result = new ArrayList();
        computeTowerHanoiSteps(numRings, pegs, 0, 1, 2, result);
        return result;
    }

    public static void computeTowerHanoiSteps(int numRings, List<Deque<Integer>> pegs, int fromPeg, int auxPeg, int toPeg, List<List<Integer>> result) {
        if (numRings > 0) {
            computeTowerHanoiSteps(numRings - 1, pegs, fromPeg, toPeg, auxPeg, result);
            result.add(List.of(fromPeg, toPeg));
            pegs.get(toPeg).addFirst(pegs.get(fromPeg).removeFirst());
            computeTowerHanoiSteps(numRings - 1, pegs, auxPeg, fromPeg, toPeg, result);
        }
    }

    @EpiTest(testDataFile = "hanoi.tsv")
    public static void computeTowerHanoiWrapper(TimedExecutor executor,
                                                int numRings) throws Exception {
        List<Deque<Integer>> pegs = new ArrayList<>();
        for (int i = 0; i < NUM_PEGS; i++) {
            pegs.add(new LinkedList<>());
        }
        for (int i = numRings; i >= 1; --i) {
            pegs.get(0).addFirst(i);
        }

        List<List<Integer>> result =
                executor.run(() -> computeTowerHanoi(numRings));

        for (List<Integer> operation : result) {
            int fromPeg = operation.get(0);
            int toPeg = operation.get(1);
            if (!pegs.get(toPeg).isEmpty() &&
                    pegs.get(fromPeg).getFirst() >= pegs.get(toPeg).getFirst()) {
                throw new TestFailure("Illegal move from " +
                        String.valueOf(pegs.get(fromPeg).getFirst()) +
                        " to " +
                        String.valueOf(pegs.get(toPeg).getFirst()));
            }
            pegs.get(toPeg).addFirst(pegs.get(fromPeg).removeFirst());
        }

        List<Deque<Integer>> expectedPegs1 = new ArrayList<>();
        for (int i = 0; i < NUM_PEGS; i++) {
            expectedPegs1.add(new LinkedList<Integer>());
        }
        for (int i = numRings; i >= 1; --i) {
            expectedPegs1.get(1).addFirst(i);
        }

        List<Deque<Integer>> expectedPegs2 = new ArrayList<>();
        for (int i = 0; i < NUM_PEGS; i++) {
            expectedPegs2.add(new LinkedList<Integer>());
        }
        for (int i = numRings; i >= 1; --i) {
            expectedPegs2.get(2).addFirst(i);
        }
        if (!pegs.equals(expectedPegs1) && !pegs.equals(expectedPegs2)) {
            throw new TestFailure("Pegs doesn't place in the right configuration");
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "Hanoi.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
