package com.roman.sistemabackroman.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private Integer id;
    private Integer tipoDoc;
    private String numDocumento;
    private String nombre;
    private String apePaterno;
    private String apeMaterno;
    private LocalDate fechaNacimiento;
    private String email;

    /**
     * Metodo auxiliar para obtener el nombre completo
     */
    public String getNombreCompleto() {
        return nombre + " " + apePaterno + " " + apeMaterno;
    }
}
