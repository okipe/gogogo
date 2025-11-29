package com.roman.sistemabackroman.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name="tb_cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer id;

    /**
     * Tipo de Documento:
     * 1 = DNI
     * 2 = Carnet de Extranjería
     * 3 = Pasaporte
     */
    @Column(name = "tipoDoc", nullable = false)
    private Integer tipoDoc;

    @Column(name = "num_documento", length = 50, nullable = false, unique = true)
    private String numDocumento;

    @Column(name = "nombre", length = 200, nullable = false)
    private String nombre;

    @Column(name = "ape_pat", length = 200, nullable = false)
    private String apePaterno;

    @Column(name = "ape_mat", length = 200, nullable = false)
    private String apeMaterno;

    @Column(name = "fec_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "email", length = 200, nullable = false)
    private String email;
}
