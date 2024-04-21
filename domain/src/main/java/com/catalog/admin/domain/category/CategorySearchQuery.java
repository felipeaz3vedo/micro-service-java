package com.catalog.admin.domain.category;

public record CategorySearchQuery(
        int page,
        int limit,
        int total,
        String terms,
        String sort,
        String direction
) {}
