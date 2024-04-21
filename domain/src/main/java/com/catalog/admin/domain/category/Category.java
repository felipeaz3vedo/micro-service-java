package com.catalog.admin.domain.category;

import com.catalog.admin.domain.seedworks.AggregateRoot;
import com.catalog.admin.domain.validation.ValidationHandler;

import java.time.Instant;

public class Category extends AggregateRoot<CategoryId> {

    private String name;
    private String description;
    private boolean isActive;
    final private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private Category (
            final CategoryId id,
            final String name,
            final String description,
            final boolean isActive,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        super(id);
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static Category create(
            final String name,
            final String description,
            final boolean isActive
    ) {
        final var id = CategoryId.create();
        final var now = Instant.now();
        final var deletedAt = isActive ? null : now;

        return new Category(
                id,
                name,
                description,
                isActive,
                now,
                now,
                deletedAt
        );
    }

    public Category update(
            final String name,
            final String description,
            final boolean isActive
    ) {
        if (isActive) activate();
        else deactivate();

        this.name = name;
        this.description = description;
        this.isActive = isActive;

        return this;
    }

    public void activate() {
        final var now = Instant.now();

        this.isActive = true;
        this.updatedAt = now;
        this.deletedAt = null;
    }

    public void deactivate() {
        final var now = Instant.now();

        this.isActive = false;
        this.updatedAt = now;

        if (this.deletedAt == null) {
            this.deletedAt = now;
        }
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new CategoryValidator(this, handler).validate();
    }

    public CategoryId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }
}
