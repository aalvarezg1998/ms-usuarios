package com.aalvarezg.ms_usuarios.infrastructure.ouput.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "usuarios", schema = "public", catalog = "db_usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuariosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "apellido")
    private String apellido;
    @Basic
    @Column(name = "numero_documento")
    private String numeroDocumento;
    @Basic
    @Column(name = "celular")
    private String celular;
    @Basic
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    @Basic
    @Column(name = "correo")
    private String correo;
    @Basic
    @Column(name = "clave")
    private String clave;
    @Basic
    @Column(name = "id_rol")
    private Long idRol;

}
