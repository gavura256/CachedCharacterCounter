package com.gavura.cachedcharactercounter.validator;

public class ValidatorImpl implements Validator {

    @Override
    public void validate(String sentence) {
        if (sentence == null) {
            throw new IllegalArgumentException("Null is not supported");
        }
        if (sentence.isEmpty()) {
            throw new IllegalArgumentException("Sentence is empty");
        }
    }
}
