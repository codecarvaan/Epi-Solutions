package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalendarRendering {
    @EpiTest(testDataFile = "calendar_rendering.tsv")

    public static int findMaxSimultaneousEvents(List<Event> A) {
        List<Endpoint> endpoints = new ArrayList();
        for (Event event : A) {
            endpoints.add(new Endpoint(event.start, true));
            endpoints.add(new Endpoint(event.finish, false));
        }
        Collections.sort(endpoints);
        int count = 0, maxcount = 0;
        for (Endpoint e : endpoints) {
            if (e.isStart) {
                count++;
            } else {
                count--;
            }
            maxcount = Math.max(maxcount, count);

        }
        return maxcount;
    }

    @EpiUserType(ctorParams = {int.class, int.class})

    public static class Event {
        public int start, finish;

        public Event(int start, int finish) {
            this.start = start;
            this.finish = finish;
        }
    }

    private static class Endpoint implements Comparable {
        public int time;
        public boolean isStart;

        Endpoint(int time, boolean isStart) {
            this.time = time;
            this.isStart = isStart;
        }


        @Override
        public int compareTo(Object o) {
            Endpoint obj = (Endpoint) o;
            if (this.time > obj.time) {
                return 1;
            } else if (this.time < obj.time) {
                return -1;
            } else {
                return this.isStart ? -1 : 1;
            }

        }
    }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "CalendarRendering.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
