package com.gavura.cachedcharactercounter.provider;

import java.util.HashMap;
import java.util.Map;

public class CacheProviderImpl implements CacheProvider {
    private Map<String, Map<Character, Integer>> cache = new HashMap<>();

    @Override
    public Map<Character, Integer> getCashedResult(String sentence) {
        return cache.get(sentence);
    }

    @Override
    public boolean contains(String sentence) {
        return cache.containsKey(sentence);
    }

    @Override
    public void putToCache(String sentence, Map<Character, Integer> mapWithCalculatedResult) {
        cache.put(sentence, mapWithCalculatedResult);
    }
}
