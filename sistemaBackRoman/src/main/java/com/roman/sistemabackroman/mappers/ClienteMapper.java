package com.roman.sistemabackroman.mappers;

import com.roman.sistemabackroman.dtos.ClienteCreateDTO;
import com.roman.sistemabackroman.dtos.ClienteDTO;
import com.roman.sistemabackroman.dtos.ClienteUpdateDTO;
import com.roman.sistemabackroman.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface ClienteMapper {

    ClienteMapper INSTANCIA = Mappers.getMapper(ClienteMapper.class);

    ClienteDTO clienteAClienteDTO(Cliente cliente);

    Cliente clienteCreateDTOACliente(ClienteCreateDTO clienteCreateDTO);

    Cliente clienteUpdateDTOACliente(ClienteUpdateDTO clienteUpdateDTO);

    List<ClienteDTO> listaClienteAListaClienteDTO(List<Cliente> listaCliente);
}




