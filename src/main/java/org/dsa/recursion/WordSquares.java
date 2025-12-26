package org.dsa.recursion;

import java.util.*;

public class WordSquares {

    public static void main(String[] args) {

        WordSquares wordSquares = new WordSquares();
        String[] words = {"area","lead","wall","lady","ball"};
        System.out.println(wordSquares.wordSquares(words));
    }

    int N = 0;
    String[] words;
    Map<String, List<String>> prefixMap = new HashMap<>();

    public List<List<String>> wordSquares(String[] words) {
        this.N = words[0].length();
        this.words = words;

        List<List<String>> res = new ArrayList<>();
        createPrefixMap(words, prefixMap);

        for(String word : words) {
            LinkedList<String> lastUsed = new LinkedList<>();
            lastUsed.addLast(word);
            backtrack(1, lastUsed, res);
        }
        return res;
    }

    private void backtrack(int step, LinkedList<String> lastUsed, List<List<String>> res) {
        if(step == N) {
            res.add(new ArrayList<>(lastUsed));
            return;
        }

        StringBuilder prefix = new StringBuilder();
        for(String word : lastUsed) {
            prefix.append(word.charAt(step));
        }

        for(String candidate : getPrefixList(prefix.toString())) {
            lastUsed.addLast(candidate);
            backtrack(step + 1, lastUsed, res);
            lastUsed.removeLast();
        }
    }

    private List<String> getPrefixList(String prefix) {
        List<String> wordList = this.prefixMap.get(prefix);
        return (wordList != null ? wordList : new ArrayList<String>());
    }

    private void createPrefixMap(String[] words, Map<String, List<String>> prefixMap) {

        for(String word : words) {
            for(int i=1; i<N; i++) {
                String prefix = word.substring(0, i);
                prefixMap.computeIfAbsent(prefix, k -> new ArrayList<>()).add(word);
            }
        }
    }
}