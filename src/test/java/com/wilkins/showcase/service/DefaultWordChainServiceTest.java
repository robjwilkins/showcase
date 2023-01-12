package com.wilkins.showcase.service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.wilkins.showcase.service.DefaultWordChainServiceTest.FixedWordChecker.withValidWords;
import static java.util.List.copyOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DefaultWordChainServiceTest {

    WordChainService underTest;

    @Test
    void chainWhenCatAndDog() {
        underTest = new DefaultWordChainService(withValidWords(List.of("cot", "cog", "dog")));
        assertThat(underTest.chainWords("cat", "dog"))
                .containsExactly("cat", "cot", "cog", "dog");

        underTest = new DefaultWordChainService(withValidWords(List.of("bat", "bet", "bed", "dad", "dab", "deb", "cot", "cog", "dog", "dat", "dot")));
        assertThat(underTest.chainWords("cat", "dog"))
                .containsExactly("cat", "dat", "dot", "dog");
    }

    @Test
    void chainWhenHatAndDog() {
        underTest = new DefaultWordChainService(withValidWords(List.of("cat", "cot", "cog", "dat", "dot", "dog")));
        assertThat(underTest.chainWords("hat", "dog"))
                .containsExactly("hat", "dat", "dot", "dog");

        underTest = new DefaultWordChainService(withValidWords(List.of("hat", "cat", "cot", "cog", "dog")));
        assertThat(underTest.chainWords("hat", "dog"))
                .containsExactly("hat", "cat", "cot", "cog", "dog");
    }

    @Test
    void chainWhenBirdAndFish() {
        underTest = new DefaultWordChainService(withValidWords(List.of("bird", "bard", "bare", "base", "bash", "dash", "dish", "fish")));
        assertThat(underTest.chainWords("bird", "fish"))
                .containsExactly("bird", "bard", "bare", "base", "bash", "dash", "dish", "fish");
    }

    @Test
    void chainWhenNoValidPath() {
        underTest = new DefaultWordChainService(withValidWords(List.of("hat", "cat", "cog", "dog")));
        var exception = assertThrows(RuntimeException.class, () -> underTest.chainWords("hat", "dog"));
        assertThat(exception.getMessage()).isEqualTo("Cannot find a valid chain for given words");
    }

    static class FixedWordChecker implements WordChecker {

        Logger log = LoggerFactory.getLogger(FixedWordChecker.class);
        final List<String> validWords;

        FixedWordChecker(List<String> validWords) {
            this.validWords = validWords;
        }

        static FixedWordChecker withValidWords(List<String> words) {
            return new FixedWordChecker(copyOf(words));
        }

        @Override
        public boolean isValid(String word) {
            log.debug("checking word: {}", word);
            return validWords.contains(word);
        }
    }
}
