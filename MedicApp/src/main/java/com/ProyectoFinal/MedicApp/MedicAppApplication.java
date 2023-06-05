package com.ProyectoFinal.MedicApp;

import com.ProyectoFinal.MedicApp.Entity.Profesional;
import com.ProyectoFinal.MedicApp.Enum.Rol;
import com.ProyectoFinal.MedicApp.Repository.ProfesionalRepositorio;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MedicAppApplication {

    @Autowired
    ProfesionalRepositorio profesionalRepositorio;

    public static void main(String[] args) {
        SpringApplication.run(MedicAppApplication.class, args);
    }

    @PostConstruct
    public void crearAdmin() {
        if (profesionalRepositorio.buscarPorEmail("admin@mi_turno.com") == null) {
            Profesional admin = new Profesional();
            admin.setEmail("admin@miturno.com");
            admin.setPassword(new BCryptPasswordEncoder().encode("Admin123"));
            admin.setRol(Rol.ADMINISTRADOR);
            admin.setActivo(true);
            profesionalRepositorio.save(admin);
        }
    }
}
