package com.gavura.cachedcharactercounter.provider;

import java.util.Map;
import java.util.stream.Collectors;

public class CounterProviderImpl implements CounterProvider {

    @Override
    public Map<Character, Integer> countCharacters(String sentence) {
        return sentence.chars().boxed().collect(Collectors.toMap(
                k -> (char) k.intValue(),
                v -> 1,
                Integer::sum));
    }
}
