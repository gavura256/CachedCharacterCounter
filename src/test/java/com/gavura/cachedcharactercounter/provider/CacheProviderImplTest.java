package com.gavura.cachedcharactercounter.provider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class CacheProviderImplTest {
    private final CacheProvider cacheProvider = new CacheProviderImpl();
    private final Map<Character, Integer> expectedResult = new HashMap<>();
    private final String sentence = "ceqwecwqce";

    @BeforeEach
    void init() {
        expectedResult.put('q', 2);
        expectedResult.put('c', 3);
        expectedResult.put('e', 3);
        expectedResult.put('w', 2);
        cacheProvider.putToCache(sentence, expectedResult);
    }

    @Test
    void getCashedResultShouldReturnMapIfResultIsCountedBefore() {
        assertThat(expectedResult, is(cacheProvider.getCashedResult(sentence)));
    }

    @Test
    void containsShouldReturnTrueWhenResultIsCached() {
        assertThat(cacheProvider.contains(sentence), is(true));
    }

    @Test
    void putToCacheShouldNotThrowsAnyExceptions() {
        assertDoesNotThrow(
                () -> cacheProvider.putToCache(sentence, expectedResult));
    }
}
