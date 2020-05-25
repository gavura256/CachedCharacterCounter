package com.gavura.cachedcharactercounter.provider;

import java.util.Map;

public interface CounterProvider {

    Map<Character, Integer> countCharacters(String sentence);
}
