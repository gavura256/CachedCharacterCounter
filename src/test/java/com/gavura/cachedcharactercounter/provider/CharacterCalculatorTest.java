package com.gavura.cachedcharactercounter.provider;

import com.gavura.cachedcharactercounter.validator.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CharacterCalculatorTest {

    @Mock
    private Validator validator;

    @Mock
    private CounterProvider counterProvider;

    @Mock
    private CacheProvider cacheProvider;

    @Mock
    private ViewProvider viewProvider;

    @InjectMocks
    private CharacterCalculator calculator;

    @Test
    void calculateCharactersShould1ReturnResultFromCacheWhenSentenceIsCountedBefore() {
        String sentence = "dsadas";
        Map<Character, Integer> mapWithResults = new HashMap<>();
        mapWithResults.put('d', 2);
        mapWithResults.put('s', 2);
        mapWithResults.put('a', 2);

        doNothing().when(validator).validate(sentence);
        when(cacheProvider.contains(sentence)).thenReturn(true);
        when(cacheProvider.getCashedResult(sentence)).thenReturn(mapWithResults);
        when(viewProvider.provideMapView(mapWithResults)).thenReturn("Ok");

        calculator.calculateCharacters(sentence);

        verify(validator).validate(anyString());
        verify(cacheProvider).contains(anyString());
        verify(cacheProvider).getCashedResult(anyString());
        verify(viewProvider).provideMapView(anyMap());

        assertThat(calculator.calculateCharacters(sentence), is("Ok"));
    }

    @Test
    void calculateCharactersShouldCountSentenceBecauseCashDoesntContainResult() {
        String sentence = "dsadas";
        Map<Character, Integer> mapWithResults = new HashMap<>();
        mapWithResults.put('d', 2);
        mapWithResults.put('s', 2);
        mapWithResults.put('a', 2);

        doNothing().when(validator).validate(sentence);
        when(cacheProvider.contains(sentence)).thenReturn(false);
        when(counterProvider.countCharacters(sentence)).thenReturn(mapWithResults);
        when(viewProvider.provideMapView(mapWithResults)).thenReturn("Ok");

        calculator.calculateCharacters(sentence);

        verify(validator).validate(anyString());
        verify(cacheProvider).contains(anyString());
        verify(counterProvider).countCharacters(anyString());
        verify(cacheProvider).putToCache(anyString(), anyMap());
        verify(viewProvider).provideMapView(anyMap());
        verifyNoMoreInteractions(validator, counterProvider, cacheProvider, viewProvider);

        assertThat(calculator.calculateCharacters(sentence), is("Ok"));
    }

    @Test
    void validateShouldThrowIllegalArgumentExceptionWhenTheArgumentIsEmpty() {
        doThrow(IllegalArgumentException.class).when(validator).validate("");

        assertThrows(IllegalArgumentException.class,
                () -> calculator.calculateCharacters(""));

        verifyNoInteractions(counterProvider, cacheProvider, viewProvider);
    }

    @Test
    void validateShouldThrowIllegalArgumentExceptionWhenTheArgumentIsNull() {
        doThrow(IllegalArgumentException.class).when(validator).validate(null);

        assertThrows(IllegalArgumentException.class,
                () -> calculator.calculateCharacters(null));

        verifyNoInteractions(counterProvider, cacheProvider, viewProvider);
    }
}
