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
                hasNextComputed = false;
                return result;
            }
        };
    }

    public static void demo(){

        System.out.println("\n-------RegexIterable---------");
        List<String> items = Arrays.asList("apple", "banana", "avocado", "cherry", "aardvark","pearl");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a regular expression to filter the list: ");
        String inputRegex = scanner.nextLine();

        RegexIterable<String> filtered = new RegexIterable<>(items, inputRegex);

        System.out.println("\n---- Items matching \"" + inputRegex + "\" ------");
        for (String s : filtered) {
            System.out.println(s);
        }
    }
}
