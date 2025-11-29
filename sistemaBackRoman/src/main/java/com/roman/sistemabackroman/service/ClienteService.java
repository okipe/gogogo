package com.roman.sistemabackroman.service;

import com.roman.sistemabackroman.dtos.ClienteCreateDTO;
import com.roman.sistemabackroman.dtos.ClienteDTO;
import com.roman.sistemabackroman.dtos.ClienteUpdateDTO;
import java.util.List;

public interface ClienteService {

    List<ClienteDTO> listarClientes();

    ClienteDTO obtenerClientePorId(Integer id);

    ClienteDTO obtenerClientePorDocumento(String numDocumento);

    ClienteDTO registrarCliente(ClienteCreateDTO clienteCreateDTO);

    ClienteDTO actualizarCliente(ClienteUpdateDTO clienteUpdateDTO);

    String eliminarCliente(Integer id);

    List<ClienteDTO> buscarPorNombre(String busqueda);

    List<ClienteDTO> buscarPorTipoDocumento(Integer tipoDoc);
}
