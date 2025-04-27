package com.example.commanddecorator.decorator;

import java.util.*;
import java.util.regex.Pattern;

public class RegexIterable<T> implements Iterable<T> {
    private final Iterable<T> source;
    private final Pattern pattern;

    public RegexIterable(Iterable<T> source, String regex) {
        this.source  = source;
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = source.iterator();
        return new Iterator<>() {
            private T nextMatch;
            private boolean hasNextComputed = false;

            private void computeNext() {
                while (it.hasNext()) {
                    T candidate = it.next();
                    if (pattern.matcher(candidate.toString()).matches()) {
                        nextMatch = candidate;
                        hasNextComputed = true;
                        return;
                    }
                }
                nextMatch = null;
                hasNextComputed = true;
            }

            @Override
            public boolean hasNext() {
                if (!hasNextComputed) {
                    computeNext();
                }
                return nextMatch != null;
            }

            @Override
            public T next() {
                if (!hasNextComputed) {
                    computeNext();
                }
                if (nextMatch == null) {
                    throw new NoSuchElementException();
                }
                T result = nextMatch;
                hasNextComputed = false;  // reset for subsequent calls
                return result;
            }
        };
    }

    public static void demo(){
        // --- DECORATOR PATTERN DEMO ---
        System.out.println("\n-------RegexIterable---------");
        List<String> items = Arrays.asList("apple", "banana", "avocado", "cherry", "aardvark","pearl");
//        RegexIterable<String> startsWithA = new RegexIterable<>(items, "^a.*");
//
//        for (String s : startsWithA) {
//            System.out.println(s);
//        }

        // 1) Read a regex from the user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a regular expression to filter the list: ");
        String inputRegex = scanner.nextLine();

        // 2) Apply the decorator with that regex
        RegexIterable<String> filtered = new RegexIterable<>(items, inputRegex);

        // 3) Print out the matching elements
        System.out.println("\n---- Items matching \"" + inputRegex + "\" ------");
        for (String s : filtered) {
            System.out.println(s);
        }
    }
}
