package com.aalvarezg.ms_usuarios.infrastructure.configuration;

import com.aalvarezg.ms_usuarios.domain.api.IPropietarioServicePort;
import com.aalvarezg.ms_usuarios.domain.api.IRolesServicePort;
import com.aalvarezg.ms_usuarios.domain.api.IUsuarioServicePort;
import com.aalvarezg.ms_usuarios.domain.spi.IPasswordEncoderPort;
import com.aalvarezg.ms_usuarios.domain.spi.IRolesPersistencePort;
import com.aalvarezg.ms_usuarios.domain.spi.IUsuaioPersistencePort;
import com.aalvarezg.ms_usuarios.domain.usecase.PorpietarioUseCase;
import com.aalvarezg.ms_usuarios.domain.usecase.RolesUseCase;
import com.aalvarezg.ms_usuarios.domain.usecase.UsuarioUseCase;
import com.aalvarezg.ms_usuarios.infrastructure.ouput.jpa.adapter.BCryptIPasswordEncoderAdapter;
import com.aalvarezg.ms_usuarios.infrastructure.ouput.jpa.adapter.RolesJpaAdapter;
import com.aalvarezg.ms_usuarios.infrastructure.ouput.jpa.adapter.UsuarioJpaAdapter;
import com.aalvarezg.ms_usuarios.infrastructure.ouput.jpa.repository.IRolRepository;
import com.aalvarezg.ms_usuarios.infrastructure.ouput.jpa.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IUsuarioRepository usuarioRepository;
    private final IRolRepository rolRepository;

    @Bean
    public IUsuaioPersistencePort usuaioPersistencePort(){
        return new UsuarioJpaAdapter(usuarioRepository);
    }

    @Bean
    public IPasswordEncoderPort passwordEncoderPort(){
        return new BCryptIPasswordEncoderAdapter();
    }
    @Bean
    public IUsuarioServicePort usuarioServicePort(){
        return new UsuarioUseCase(usuaioPersistencePort(), passwordEncoderPort());
    }

    @Bean
    public IPropietarioServicePort propietarioServicePort(){
        return new PorpietarioUseCase(usuarioServicePort());
    }

    @Bean
    public IRolesPersistencePort rolesPersistencePort(){
        return new RolesJpaAdapter(rolRepository);
    }
    @Bean
    public IRolesServicePort rolesServicePort(){
        return new RolesUseCase(rolesPersistencePort());
    }
}
