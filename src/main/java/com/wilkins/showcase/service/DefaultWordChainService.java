package com.wilkins.showcase.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

@Service
public class DefaultWordChainService implements WordChainService {

    private static final Logger log = LoggerFactory.getLogger(DefaultWordChainService.class);
    private static final List<Character> validCharacters = List.of('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'x', 'y', 'z');
    private static final int checkLimit = 1000;
    private final WordChecker wordChecker;

    public DefaultWordChainService(WordChecker wordChecker) {
        this.wordChecker = wordChecker;
    }

    @Override
    public List<String> chainWords(String sourceWord, String targetWord) {

        var target = asCharacters(targetWord.toLowerCase());
        var current = asCharacters(sourceWord.toLowerCase());
        var chain = new ArrayList<String>();
        chain.add(sourceWord);

        var checkCount = new AtomicInteger();

        while (!asString(current).equals(asString(target))) {
            for (int characterPosition = 0; characterPosition < target.length; characterPosition++) {
                var nextWord = current.clone();
                nextWord[characterPosition] = target[characterPosition];
                if (!chain.contains(asString(nextWord)) && isValid(nextWord, checkCount)) {
                    chain.add(asString(nextWord));
                    current = nextWord;
                } else {
                    var idx = characterPosition;
                    current = maybeValidCharacter(chain, nextWord, characterPosition, checkCount)
                            .map(validChar -> {
                                nextWord[idx] = validChar;
                                addTo(chain, nextWord);
                                return nextWord;
                            })
                            .orElse(current);
                }
            }
        }
        log.debug("Checked {} words. Returning chain: {}.", checkCount.get(), chain);
        return chain;
    }

    private boolean isValid(Character[] nextWord, AtomicInteger checkCount) {
        if (checkCount.getAndIncrement() >= checkLimit) {
            log.error("Cannot find a valid chain for given words.");
            throw new RuntimeException("Cannot find a valid chain for given words");
        }
        return wordChecker.isValid(asString(nextWord));
    }

    private Optional<Character> maybeValidCharacter(List<String> chain, Character[] nextWord, int characterPosition, AtomicInteger checkCount) {
        return validCharacters.stream()
                .filter(c -> {
                    nextWord[characterPosition] = c;
                    return !chain.contains(asString(nextWord)) && isValid(nextWord, checkCount);
                })
                .findFirst();
    }

    private static void addTo(List<String> chain, Character[] nextWord) {
        log.debug("Adding word to chain: {}", asString(nextWord));
        chain.add(asString(nextWord));
    }

    private static String asString(Character[] characters) {
        return Stream.of(characters).map(String::valueOf).collect(joining());
    }

   private static Character[] asCharacters(String word) {
        var characters = new Character[word.length()];
        for (int i = 0; i < word.length(); i++) {
            characters[i] = word.charAt(i);
        }
        return characters;
    }
}
