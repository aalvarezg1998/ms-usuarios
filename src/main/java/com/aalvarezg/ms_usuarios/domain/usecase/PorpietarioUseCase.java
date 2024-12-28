package com.aalvarezg.ms_usuarios.domain.usecase;

import com.aalvarezg.ms_usuarios.domain.api.IPropietarioServicePort;
import com.aalvarezg.ms_usuarios.domain.api.IUsuarioServicePort;
import com.aalvarezg.ms_usuarios.domain.model.Usuario;

import java.time.LocalDate;

public class PorpietarioUseCase implements IPropietarioServicePort {
    public static final String REGEX_TELEFONO = "^\\+?\\d{1,13}$";
    public static final String REGEX_DOCUMENTO = "\\d+";
    private static final int ANOS_MAYOR_EDAD = 18;
    private static final String REGEX_EMAIL = "^[A-Za-z0-9+_.-]+@(.+)$";

    private final IUsuarioServicePort usuarioServicePort;

    public PorpietarioUseCase(IUsuarioServicePort usuarioServicePort) {
        this.usuarioServicePort = usuarioServicePort;
    }

    @Override
    public Usuario crearPropietario(Usuario propietario) {
        validarPropietario(propietario);
        return usuarioServicePort.saveUsuario(propietario);
    }

    private void validarPropietario(Usuario propietario) {
        if (propietario.getFechaNacimiento().isAfter(LocalDate.now().minusYears(ANOS_MAYOR_EDAD))) {
            throw new IllegalArgumentException("El usuario debe ser mayor de edad.");
        }
        if (!esDocumentoValido(propietario.getNumeroDocumento())) {
            throw new IllegalArgumentException("El documento de identidad no es válido.");
        }
        if (!esEmailValido(propietario.getCorreo())) {
            throw new IllegalArgumentException("El correo no tiene un formato válido.");
        }
        if (!esTelefonoValido(propietario.getCelular())) {
            throw new IllegalArgumentException("El teléfono no es válido.");
        }
    }

    private boolean esDocumentoValido(String documento) {
        return documento.matches(REGEX_DOCUMENTO);
    }

    private boolean esEmailValido(String email) {
        return email.matches(REGEX_EMAIL);
    }

    private boolean esTelefonoValido(String telefono) {
        return telefono.matches(REGEX_TELEFONO);
    }
}
