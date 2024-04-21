package com.catalog.admin.domain.pagination;

import java.util.List;

public record Pagination<T>(
        int page,
        int limit,
        long total,
        List<T> items
) {}

