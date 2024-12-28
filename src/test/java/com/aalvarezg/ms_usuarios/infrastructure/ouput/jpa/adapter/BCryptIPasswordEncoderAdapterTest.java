package com.aalvarezg.ms_usuarios.infrastructure.ouput.jpa.adapter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BCryptIPasswordEncoderAdapterTest {
    private final BCryptIPasswordEncoderAdapter passwordEncoderAdapter = new BCryptIPasswordEncoderAdapter();

    @Test
    void encode_shouldReturnEncodedPassword() {
        // Arrange
        String rawPassword = "mySecretPassword";

        // Act
        String encodedPassword = passwordEncoderAdapter.encode(rawPassword);

        // Assert
        assertNotNull(encodedPassword);
        assertNotEquals(rawPassword, encodedPassword);
    }

    @Test
    void matches_shouldReturnTrueForMatchingPasswords() {
        // Arrange
        String rawPassword = "mySecretPassword";
        String encodedPassword = passwordEncoderAdapter.encode(rawPassword);

        // Act
        boolean matches = passwordEncoderAdapter.matches(rawPassword, encodedPassword);

        // Assert
        assertTrue(matches);
    }

    @Test
    void matches_shouldReturnFalseForNonMatchingPasswords() {
        // Arrange
        String rawPassword = "mySecretPassword";
        String anotherPassword = "anotherPassword";
        String encodedPassword = passwordEncoderAdapter.encode(rawPassword);

        // Act
        boolean matches = passwordEncoderAdapter.matches(anotherPassword, encodedPassword);

        // Assert
        assertFalse(matches);
    }

}