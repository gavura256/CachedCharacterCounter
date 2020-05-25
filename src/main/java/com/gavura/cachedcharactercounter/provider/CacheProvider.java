package com.gavura.cachedcharactercounter.provider;

import java.util.Map;

public interface CacheProvider {

    Map<Character, Integer> getCashedResult(String sentence);

    boolean contains(String sentence);

    void putToCache(String sentence, java.util.Map<Character, Integer> mapWithCalculatedResult);

}
