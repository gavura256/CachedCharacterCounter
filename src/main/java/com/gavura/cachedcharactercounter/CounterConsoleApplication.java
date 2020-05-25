package com.gavura.cachedcharactercounter;

import com.gavura.cachedcharactercounter.provider.CacheProvider;
import com.gavura.cachedcharactercounter.provider.CacheProviderImpl;
import com.gavura.cachedcharactercounter.provider.CharacterCalculator;
import com.gavura.cachedcharactercounter.provider.CounterProvider;
import com.gavura.cachedcharactercounter.provider.CounterProviderImpl;
import com.gavura.cachedcharactercounter.provider.ViewProvider;
import com.gavura.cachedcharactercounter.provider.ViewProviderImpl;
import com.gavura.cachedcharactercounter.reader.Reader;
import com.gavura.cachedcharactercounter.reader.ReaderImpl;
import com.gavura.cachedcharactercounter.validator.Validator;
import com.gavura.cachedcharactercounter.validator.ValidatorImpl;

import java.util.Scanner;

public class CounterConsoleApplication {
    public static void main(String[] args) {
        final Validator validator = new ValidatorImpl();
        final CounterProvider counterProvider = new CounterProviderImpl();
        final CacheProvider cacheProvider = new CacheProviderImpl();
        final ViewProvider viewProvider = new ViewProviderImpl();
        final Reader reader = new ReaderImpl(new Scanner(System.in));
        final CharacterCalculator calculator =
                new CharacterCalculator(validator, counterProvider, cacheProvider, viewProvider);
        System.out.println("Please input your sentence");
        String sentence = reader.readSentence();
        while (!sentence.equalsIgnoreCase("no")) {
            System.out.println(calculator.calculateCharacters(sentence));
            System.out.println("Would you like to continue? Sentence/No");
            sentence = reader.readSentence();
        }
        reader.close();
    }
}
