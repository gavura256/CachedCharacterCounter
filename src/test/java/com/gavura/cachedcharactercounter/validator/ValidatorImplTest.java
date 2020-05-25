package com.gavura.cachedcharactercounter.validator;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidatorImplTest {
    private final Validator validator = new ValidatorImpl();

    @Test
    void validatorShouldNotThrowsAnyExceptions() {
        assertDoesNotThrow(
                () -> validator.validate("All right"));
    }

    @Test
    void validateShouldThrowsIllegalArgumentExceptionWhenSentenceIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> validator.validate(null));

        assertThat(exception.getMessage(), is("Null is not supported"));
    }

    @Test
    void validateShouldThrowsIllegalArgumentExceptionWhenSentenceIsEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> validator.validate(""));

        assertThat(exception.getMessage(), is("Sentence is empty"));
    }
}
