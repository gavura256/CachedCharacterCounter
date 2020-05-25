package com.gavura.cachedcharactercounter.provider;

import com.gavura.cachedcharactercounter.validator.Validator;

import java.util.Map;

public class CharacterCalculator {
    private final Validator validator;
    private final CounterProvider counterProvider;
    private final CacheProvider cacheProvider;
    private final ViewProvider viewProvider;

    public CharacterCalculator(Validator validator,
                               CounterProvider counterProvider,
                               CacheProvider cacheProvider,
                               ViewProvider viewProvider) {
        this.validator = validator;
        this.counterProvider = counterProvider;
        this.cacheProvider = cacheProvider;
        this.viewProvider = viewProvider;
    }

    public String calculateCharacters(String sentence) {
        Map<Character, Integer> countedResult;
        validator.validate(sentence);
        if (cacheProvider.contains(sentence)) {
            countedResult = cacheProvider.getCashedResult(sentence);
        } else {
            countedResult = counterProvider.countCharacters(sentence);
            cacheProvider.putToCache(sentence, countedResult);
        }

        return viewProvider.provideMapView(countedResult);
    }
}
