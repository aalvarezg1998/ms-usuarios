package com.aalvarezg.ms_usuarios.domain.spi;

public interface IPasswordEncoderPort {
    String encode(String rawPassword);
    boolean matches(String rawPassword, String encodedPassword);
}
