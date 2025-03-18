package org.example.backend.exceptions;

import java.time.Instant;

public record ErrorMessage(Instant timestamp, String message) {}
