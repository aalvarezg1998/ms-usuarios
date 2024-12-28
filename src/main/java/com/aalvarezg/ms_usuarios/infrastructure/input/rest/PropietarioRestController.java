package com.aalvarezg.ms_usuarios.infrastructure.input.rest;

import com.aalvarezg.ms_usuarios.application.dto.PropietarioRequestDTO;
import com.aalvarezg.ms_usuarios.application.dto.PropietarioResponseDTO;
import com.aalvarezg.ms_usuarios.application.handler.IPropietarioHandler;
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

    @PostMapping
    public ResponseEntity<PropietarioResponseDTO> crearPropietario(@RequestBody PropietarioRequestDTO propietarioRequestDTO){
        PropietarioResponseDTO propietarioResponseDTO = propietarioHandler.crearPropietario(propietarioRequestDTO);
        return ResponseEntity.ok(propietarioResponseDTO);
    }
}
