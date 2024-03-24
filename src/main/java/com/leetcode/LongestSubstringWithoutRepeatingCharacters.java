package com.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class LongestSubstringWithoutRepeatingCharacters {
    private static Pattern ALLOWED_SYMBOLS = Pattern.compile("[\\w\\W\\s]*");

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabc") == 3);
        System.out.println(lengthOfLongestSubstring("bbbbbb") == 1);
        System.out.println(lengthOfLongestSubstring("pwwkew") == 3);
        System.out.println(lengthOfLongestSubstring("au") == 2);
        System.out.println(lengthOfLongestSubstring("aab") == 2);
        System.out.println(lengthOfLongestSubstring("abba") == 2);
        System.out.println(lengthOfLongestSubstring("dvdf") == 3);
    }

    public static int lengthOfLongestSubstring(String s) {

        if (s == null || s.length() > 50_000 || !s.matches(ALLOWED_SYMBOLS.pattern()))
            throw new IllegalArgumentException();

        if (s.equals("")) {
            return 0;
        }

        int firstIndex = 0;
        int longestLength = 0;

        HashMap<Byte, Integer> characters = new HashMap<>();

        byte[] bytes = s.getBytes();
        int stringLength = bytes.length;
        for (int i = 0; i < stringLength; i++) {
            byte currentByte = bytes[i];
            if (characters.containsKey(currentByte)) {
                firstIndex = Integer.max(firstIndex, characters.get(currentByte) + 1);
            }
            longestLength = Integer.max(longestLength, i - firstIndex + 1);
            characters.put(currentByte, i);
        }
        return longestLength;
    }

    /**worth*/
    public static int lengthOfLongestSubstring2(String s) {

        if (s == null || s.length() > 50_000 || !s.matches(ALLOWED_SYMBOLS.pattern()))
            throw new IllegalArgumentException();

        if (s.equals("")) {
            return 0;
        }

        int longestSequence = 1;
//        String[] symbols = s.split("");
        outer:
        for (int i = 0; i < s.length() - longestSequence; i++) {
            Set<Character> alreadyUsed = new HashSet<>();
            alreadyUsed.add(s.charAt(i));
            for (int j = i + 1; j < s.length(); j++) {
                if (!alreadyUsed.contains(s.charAt(j))) {
                    alreadyUsed.add(s.charAt(j));
                    if (alreadyUsed.size() > longestSequence) {
                        longestSequence = alreadyUsed.size();
                    }
                } else {
                    continue outer;
                }
            }
        }
        return longestSequence;
    }
}
