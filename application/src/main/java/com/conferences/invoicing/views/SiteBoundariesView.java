package com.conferences.invoicing.views;

import java.util.Set;

public record SiteBoundariesView(
        String id,
        Set<String> boundaries
) {}
