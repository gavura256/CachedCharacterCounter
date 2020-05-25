package com.gavura.cachedcharactercounter.provider;

import org.hamcrest.collection.IsMapContaining;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class CounterProviderImplTest {
    private final CounterProvider counterProvider = new CounterProviderImpl();

    @Test
    void countCharactersShouldReturnMapWhenSentenceContainsOneSymbol() {
        Map<Character, Integer> actualMap = counterProvider.countCharacters("1");

        assertThat(actualMap, IsMapContaining.hasEntry('1', 1));
    }

    @Test
    void countCharactersShouldReturnMapWhenSentenceContainManySymbols() {
        Map<Character, Integer> actualMap = counterProvider.countCharacters("eweqeew111222");
        Map<Character, Integer> expectedMap = new HashMap<>();
        expectedMap.put('1', 3);
        expectedMap.put('q', 1);
        expectedMap.put('2', 3);
        expectedMap.put('e', 4);
        expectedMap.put('w', 2);

        assertThat(actualMap, is(expectedMap));
    }
}
