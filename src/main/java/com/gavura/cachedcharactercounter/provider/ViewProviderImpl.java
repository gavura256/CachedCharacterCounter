package com.gavura.cachedcharactercounter.provider;

import java.util.Map;

public class ViewProviderImpl implements ViewProvider {

    public static final String STRING_FORMAT = "\"%c\" : %d%n";

    @Override
    public String provideMapView(Map<Character, Integer> countedCharacters) {
        StringBuilder resultString = new StringBuilder();
        countedCharacters.forEach(
                (key, value) -> resultString.append(String.format(STRING_FORMAT, key, value)));

        return resultString.toString();
    }
}
