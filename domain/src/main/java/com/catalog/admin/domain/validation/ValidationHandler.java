package com.catalog.admin.domain.validation;

import java.util.List;

public interface ValidationHandler {

    ValidationHandler append(Error error);

    ValidationHandler append(ValidationHandler handler);

    ValidationHandler validate(Validation validation);

    List<Error> getErrors();

    default boolean hasError() {
        var getErrorsNotNull = getErrors() != null;
        var getErrorsNotEmpty = !getErrors().isEmpty();

        return getErrorsNotNull && getErrorsNotEmpty;
    }

    public interface Validation {
        void validate();
    }
}
