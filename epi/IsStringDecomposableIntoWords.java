package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class IsStringDecomposableIntoWords {

    public static List<String>
    decomposeIntoDictionaryWords(String domain, Set<String> dictionary) {
        // TODO - you fill in here.
        int last_length[] = new int[domain.length() + 1];
        Arrays.fill(last_length, -1);

        for (int i = 0; i < domain.length(); i++) {
            if (dictionary.contains(domain.substring(0, i + 1))) {
                last_length[i] = i + 1;
                continue;
            }
            for (int j = 0; j < i; j++) {
                if (last_length[j] != -1 && dictionary.contains(domain.substring(j + 1, i + 1))) {
                    last_length[i] = i - j;
                    break;
                }
            }
        }
        List<String> ans = new ArrayList<>();
        if (last_length[domain.length() - 1] != -1) {
            int idx = domain.length() - 1;
            int i = domain.length() - 1;
            while (idx >= 0) {
                String temp = domain.substring(idx - last_length[idx] + 1, idx + 1);
                ans.add(temp);
                idx = idx - last_length[idx];
            }
            Collections.reverse(ans);
        }
//        System.out.println(last_length[domain.length() - 1]+"shivam "+ans);
        return ans;
    }

    @EpiTest(testDataFile = "is_string_decomposable_into_words.tsv")
    public static void decomposeIntoDictionaryWordsWrapper(TimedExecutor executor,
                                                           String domain,
                                                           Set<String> dictionary,
                                                           Boolean decomposable)
            throws Exception {
        List<String> result =
                executor.run(() -> decomposeIntoDictionaryWords(domain, dictionary));

        if (!decomposable) {
            if (!result.isEmpty()) {
                throw new TestFailure("domain is not decomposable");
            }
            return;
        }

        if (result.stream().anyMatch(s -> !dictionary.contains(s))) {
            throw new TestFailure("Result uses words not in dictionary");
        }

        if (!String.join("", result).equals(domain)) {
            throw new TestFailure("Result is not composed into domain");
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsStringDecomposableIntoWords.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
