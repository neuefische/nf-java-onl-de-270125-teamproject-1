package org.example.backend.bookLibrary;

import lombok.Builder;

@Builder
public record Book(
        String id,
        String title,
        String author
) {
}
