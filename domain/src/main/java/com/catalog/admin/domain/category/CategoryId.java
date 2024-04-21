package com.catalog.admin.domain.category;

import com.catalog.admin.domain.seedworks.Identifier;

import java.util.Objects;
import java.util.UUID;

public class CategoryId extends Identifier {

    private final String value;

    private CategoryId(String value) {
        Objects.requireNonNull(value);

        this.value = value;
    }

    public static CategoryId create() {
        return CategoryId.from(UUID.randomUUID());
    }

    public static CategoryId from(String anId) {
        return new CategoryId(anId);
    }

    public static CategoryId from(UUID anId) {
        return new CategoryId(anId.toString().toLowerCase());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final CategoryId that = (CategoryId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
