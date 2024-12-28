package com.aalvarezg.ms_usuarios.application.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PropietarioRequestDTO {
    private String nombre;
    private String apellido;
    private String numeroDocumento;
    private String celular;
    private LocalDate fechaNacimiento;
    private String correo;
    private String clave;
}
