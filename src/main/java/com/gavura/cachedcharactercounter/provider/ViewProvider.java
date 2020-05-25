package com.gavura.cachedcharactercounter.provider;

import java.util.Map;

public interface ViewProvider {

    String provideMapView(Map<Character, Integer> countedCharacters);
}
