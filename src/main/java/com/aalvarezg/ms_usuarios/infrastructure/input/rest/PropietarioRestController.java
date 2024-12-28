package com.aalvarezg.ms_usuarios.infrastructure.input.rest;

import com.aalvarezg.ms_usuarios.application.dto.PropietarioRequestDTO;
import com.aalvarezg.ms_usuarios.application.dto.PropietarioResponseDTO;
import com.aalvarezg.ms_usuarios.application.handler.IPropietarioHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/propietarios/")
@RequiredArgsConstructor
public class PropietarioRestController {

    private final IPropietarioHandler propietarioHandler;

    @Operation(
            summary = "Crear un propietario",
            description = "Este endpoint permite crear un nuevo propietario a partir de la información proporcionada."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Propietario creado exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PropietarioResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Solicitud inválida (error de validación)",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content
            )
    })
    @PostMapping
    public ResponseEntity<PropietarioResponseDTO> crearPropietario(
            @RequestBody PropietarioRequestDTO propietarioRequestDTO) {
        PropietarioResponseDTO propietarioResponseDTO = propietarioHandler.crearPropietario(propietarioRequestDTO);
        return ResponseEntity.ok(propietarioResponseDTO);
    }
}
