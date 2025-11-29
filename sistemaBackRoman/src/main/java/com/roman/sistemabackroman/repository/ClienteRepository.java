package com.roman.sistemabackroman.repository;

import com.roman.sistemabackroman.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Optional<Cliente> findByNumDocumento(String numDocumento);

    Optional<Cliente> findByEmail(String email);

    List<Cliente> findByApePaternoContainingIgnoreCase(String apellido);

    List<Cliente> findByTipoDoc(Integer tipoDoc);

    @Query("SELECT c FROM Cliente c WHERE " +
            "LOWER(c.nombre) LIKE LOWER(CONCAT('%', :busqueda, '%')) OR " +
            "LOWER(c.apePaterno) LIKE LOWER(CONCAT('%', :busqueda, '%')) OR " +
            "LOWER(c.apeMaterno) LIKE LOWER(CONCAT('%', :busqueda, '%'))")
    List<Cliente> buscarPorNombreCompleto(@Param("busqueda") String busqueda);

    boolean existsByNumDocumento(String numDocumento);
}
