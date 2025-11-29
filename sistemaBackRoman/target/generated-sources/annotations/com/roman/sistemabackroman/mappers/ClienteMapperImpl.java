package com.roman.sistemabackroman.mappers;

import com.roman.sistemabackroman.dtos.ClienteCreateDTO;
import com.roman.sistemabackroman.dtos.ClienteDTO;
import com.roman.sistemabackroman.dtos.ClienteUpdateDTO;
import com.roman.sistemabackroman.model.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-29T16:52:20-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.8 (Eclipse Adoptium)"
)
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public ClienteDTO clienteAClienteDTO(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setId( cliente.getId() );
        clienteDTO.setTipoDoc( cliente.getTipoDoc() );
        clienteDTO.setNumDocumento( cliente.getNumDocumento() );
        clienteDTO.setNombre( cliente.getNombre() );
        clienteDTO.setApePaterno( cliente.getApePaterno() );
        clienteDTO.setApeMaterno( cliente.getApeMaterno() );
        clienteDTO.setFechaNacimiento( cliente.getFechaNacimiento() );
        clienteDTO.setEmail( cliente.getEmail() );

        return clienteDTO;
    }

    @Override
    public Cliente clienteCreateDTOACliente(ClienteCreateDTO clienteCreateDTO) {
        if ( clienteCreateDTO == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.setTipoDoc( clienteCreateDTO.getTipoDoc() );
        cliente.setNumDocumento( clienteCreateDTO.getNumDocumento() );
        cliente.setNombre( clienteCreateDTO.getNombre() );
        cliente.setApePaterno( clienteCreateDTO.getApePaterno() );
        cliente.setApeMaterno( clienteCreateDTO.getApeMaterno() );
        cliente.setFechaNacimiento( clienteCreateDTO.getFechaNacimiento() );
        cliente.setEmail( clienteCreateDTO.getEmail() );

        return cliente;
    }

    @Override
    public Cliente clienteUpdateDTOACliente(ClienteUpdateDTO clienteUpdateDTO) {
        if ( clienteUpdateDTO == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.setId( clienteUpdateDTO.getId() );
        cliente.setTipoDoc( clienteUpdateDTO.getTipoDoc() );
        cliente.setNumDocumento( clienteUpdateDTO.getNumDocumento() );
        cliente.setNombre( clienteUpdateDTO.getNombre() );
        cliente.setApePaterno( clienteUpdateDTO.getApePaterno() );
        cliente.setApeMaterno( clienteUpdateDTO.getApeMaterno() );
        cliente.setFechaNacimiento( clienteUpdateDTO.getFechaNacimiento() );
        cliente.setEmail( clienteUpdateDTO.getEmail() );

        return cliente;
    }

    @Override
    public List<ClienteDTO> listaClienteAListaClienteDTO(List<Cliente> listaCliente) {
        if ( listaCliente == null ) {
            return null;
        }

        List<ClienteDTO> list = new ArrayList<ClienteDTO>( listaCliente.size() );
        for ( Cliente cliente : listaCliente ) {
            list.add( clienteAClienteDTO( cliente ) );
        }

        return list;
    }
}
