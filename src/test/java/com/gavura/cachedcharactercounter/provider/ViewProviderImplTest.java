package com.gavura.cachedcharactercounter.provider;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class ViewProviderImplTest {
    private final ViewProvider viewProvider = new ViewProviderImpl();

    @Test
    void provideMapViewShouldReturnStringWhenSentenceContainsManySymbols() {
        HashMap<Character, Integer> actualMap = new HashMap<>();
        actualMap.put('q', 8);
        actualMap.put('2', 4);
        actualMap.put('c', 7);
        actualMap.put('e', 1);
        actualMap.put('7', 2);
        actualMap.put('h', 6);
        actualMap.put('i', 2);
        actualMap.put('k', 1);
        String actualString = "\"q\" : 8\n" +
                "\"2\" : 4\n" +
                "\"c\" : 7\n" +
                "\"e\" : 1\n" +
                "\"7\" : 2\n" +
                "\"h\" : 6\n" +
                "\"i\" : 2\n" +
                "\"k\" : 1\n";

        assertThat(viewProvider.provideMapView(actualMap), is(actualString));
    }

    @Test
    void provideMapViewShouldReturnStringWhenSentenceContainOneSymbol() {
        HashMap<Character, Integer> actualMap = new HashMap<>();
        actualMap.put('1', 1);
        String actualString = "\"1\" : 1\n";

        assertThat(viewProvider.provideMapView(actualMap), is(actualString));
    }
}
