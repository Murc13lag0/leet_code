package Java;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LongestSubStringWithoutRepeatingCharacters {
  public static void main(String[] args) {
    String testString =
        "abcdefghijklmnopqrstuvwxyz".repeat(1000)
            + // Repeating unique characters
            "A".repeat(5000)
            + // Long repeating sequence
            "1234567890".repeat(500)
            + // Numeric patterns
            "!@#$%^&*()_+-=".repeat(1000)
            + // Special characters
            "TheQuickBrownFoxJumpsOverTheLazyDog".repeat(500)
            + // Mixed case
            "X".repeat(10000);
    Instant now = Instant.now();
    lengthOfLongestSubstring3(testString);
    System.out.println(Duration.between(now, Instant.now()).toMillis());
  }

  // Attempt 3 (works)
  public static int lengthOfLongestSubstring3(String s) {

    char[] chars = s.toCharArray();

    List<Character> uniqueChars = new ArrayList<>();
    List<Integer> scores = new ArrayList<>();

    if (chars.length == 0) {
      return 0;
    }

    for (int i = 0; i < chars.length; i++) {
      if (uniqueChars.contains(chars[i])) {
        scores.add(uniqueChars.size());

        for (int j = uniqueChars.indexOf(chars[i]); j >= 0; j--) {
          uniqueChars.remove(j);
        }

        uniqueChars.add(chars[i]);

      } else {
        uniqueChars.add(chars[i]);
      }
    }
    scores.add(uniqueChars.size());

    return Collections.max(scores);
  }

  // Attempt 2 - (doesn't work)
  public static int lengthOfLongestSubstring2(String s) {

    char[] chars = s.toCharArray();

    List<Character> uniqueChars = new ArrayList<>();
    int offset = 0;

    if (chars.length == 0) {
      return 0;
    }

    for (int i = 0; i < chars.length; i++) {
      if (uniqueChars.contains(chars[i]) && i != 1) {
        int charIndex = uniqueChars.indexOf(chars[i]);

        offset += i - offset;

        for (int j = charIndex; j > offset && j >= 0; j--) {
          uniqueChars.remove(chars[j]);
        }

      } else if (uniqueChars.contains(chars[i])) {

        uniqueChars.remove(Character.valueOf(chars[i]));

      } else {
        uniqueChars.add(chars[i]);
      }
    }

    return uniqueChars.size();
  }

  // Attempt 1 - (doesn't work)
  public static int lengthOfLongestSubstring(String s) {

    char[] chars = s.toCharArray();

    Set<Character> uniqueChars = new HashSet<>();
    List<Integer> uniqueOccurrences = new ArrayList<>();

    if (chars.length == 0) {
      return 0;
    }

    for (char aChar : chars) {
      if (uniqueChars.contains(aChar)) {
        uniqueOccurrences.add(uniqueChars.size());
        uniqueChars.remove(aChar);
        uniqueChars.add(aChar);
      } else {
        uniqueChars.add(aChar);
      }
    }

    uniqueOccurrences.add(uniqueChars.size());

    return Collections.max(uniqueOccurrences);
  }
}
