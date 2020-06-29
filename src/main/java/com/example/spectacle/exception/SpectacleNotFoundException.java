package com.example.spectacle.exception;

public class SpectacleNotFoundException extends RuntimeException {
    public SpectacleNotFoundException(Long id) {
        super(("Could not find Spectacle : " + id));
    }
}
