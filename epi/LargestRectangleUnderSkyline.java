package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
import java.util.Stack;

public class LargestRectangleUnderSkyline {
    @EpiTest(testDataFile = "largest_rectangle_under_skyline.tsv")

    public static int calculateLargestRectangle(List<Integer> heights) {

        Stack<Integer> height_stack = new Stack<>();
        Stack<Integer> position_stack = new Stack<>();
        int maxArea = 0;


        for (int i = 0; i < heights.size(); i++) {
            if (height_stack.size() == 0 || heights.get(i) > height_stack.peek()) {
                height_stack.push(heights.get(i));
                position_stack.push(i);

            } else if (heights.get(i) < height_stack.peek()) {
                int pos = i;
                while (height_stack.size() != 0 && heights.get(i) < height_stack.peek()) {
                    pos = position_stack.pop();
                    int h = height_stack.pop();
                    int area = (i - pos) * h;
                    maxArea = Math.max(area, maxArea);
                }
                height_stack.push(heights.get(i));
                position_stack.push(pos);
            }

        }
        while (!height_stack.empty()) {
            int h = height_stack.pop();
            int pos = position_stack.pop();
            maxArea = Math.max(maxArea, h * (heights.size() - pos));
        }
        return maxArea;
    }

  public static void main(String[] args) {
    System.exit(
            GenericTest
                    .runFromAnnotations(args, "LargestRectangleUnderSkyline.java", new Object() {
                    }.getClass().getEnclosingClass())
            .ordinal());
  }
}
