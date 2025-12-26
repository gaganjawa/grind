package org.dsa.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringSubstituition {

    public static void main(String[] args) {
        List<List<String>> replacements = List.of(List.of("A","bce"), List.of("B","ace"), List.of("C","abc%B%"));
        String text = "%A%_%B%_%C%";

        System.out.println(applySubstitutions(replacements, text));
    }

    public static String applySubstitutions(List<List<String>> replacements, String text) {

        Map<String, String> adj = new HashMap<>();

        for(List<String> replacement : replacements) {
            adj.put("%" + replacement.get(0) + "%", replacement.get(1));
        }

        String[] values = text.split("_");
        int n = values.length;
        StringBuilder sb = new StringBuilder(text);

        for(int i=0; i<n; i++) {
            String placeholder = values[i];

            if(adj.containsKey(placeholder)) {
                String word = adj.get(placeholder);
                if(word.contains(placeholder)) {
                    word.replace(placeholder, adj.get(placeholder));
                }
                text = text.replace(placeholder, word);
            }

        }

        return text;

    }
}
