package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
public class FindSalaryThreshold {
  @EpiTest(testDataFile = "find_salary_threshold.tsv")


  public static double findSalaryCap(int targetPayroll, List<Integer> currentSalaries) {

    double unAdjustedSalary = 0;
    Collections.sort(currentSalaries);
    for (int i = 0; i < currentSalaries.size(); i++) {
      final int adjustedPeople = currentSalaries.size() - i;
      final double adjustedSalary = currentSalaries.get(i) * adjustedPeople;
      if (unAdjustedSalary + adjustedSalary >= targetPayroll) {
        return (targetPayroll - unAdjustedSalary) / adjustedPeople;
      }
      unAdjustedSalary += currentSalaries.get(i);
    }
    return -1.0;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "FindSalaryThreshold.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
