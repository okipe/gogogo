package com.roman.sistemabackroman.service;

import com.roman.sistemabackroman.dtos.ClienteCreateDTO;
import com.roman.sistemabackroman.dtos.ClienteDTO;
import com.roman.sistemabackroman.dtos.ClienteUpdateDTO;
import com.roman.sistemabackroman.mappers.ClienteMapper;
import com.roman.sistemabackroman.model.Cliente;
import com.roman.sistemabackroman.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ClienteDTO> listarClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return ClienteMapper.INSTANCIA.listaClienteAListaClienteDTO(clientes);
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteDTO obtenerClientePorId(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.map(ClienteMapper.INSTANCIA::clienteAClienteDTO).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteDTO obtenerClientePorDocumento(String numDocumento) {
        Optional<Cliente> cliente = clienteRepository.findByNumDocumento(numDocumento);
        return cliente.map(ClienteMapper.INSTANCIA::clienteAClienteDTO).orElse(null);
    }

    @Override
    public ClienteDTO registrarCliente(ClienteCreateDTO clienteCreateDTO) {
        // 🔍 Validar que no exista el documento
        if (clienteRepository.existsByNumDocumento(clienteCreateDTO.getNumDocumento())) {
            throw new RuntimeException("Ya existe un cliente con el documento: "
                    + clienteCreateDTO.getNumDocumento());
        }

        Cliente cliente = ClienteMapper.INSTANCIA.clienteCreateDTOACliente(clienteCreateDTO);

        Cliente clienteGuardado = clienteRepository.save(cliente);

        return ClienteMapper.INSTANCIA.clienteAClienteDTO(clienteGuardado);
    }

    @Override
    public ClienteDTO actualizarCliente(ClienteUpdateDTO clienteUpdateDTO) {
        // 🔍 Verificar que el cliente existe
        Optional<Cliente> clienteExistente = clienteRepository.findById(clienteUpdateDTO.getId());

        if (clienteExistente.isEmpty()) {
            throw new RuntimeException("No se encontró el cliente con ID: "
                    + clienteUpdateDTO.getId());
        }

        Cliente clienteActual = clienteExistente.get();
        if (!clienteActual.getNumDocumento().equals(clienteUpdateDTO.getNumDocumento())) {
            if (clienteRepository.existsByNumDocumento(clienteUpdateDTO.getNumDocumento())) {
                throw new RuntimeException("Ya existe otro cliente con el documento: "
                        + clienteUpdateDTO.getNumDocumento());
            }
        }

        Cliente cliente = ClienteMapper.INSTANCIA.clienteUpdateDTOACliente(clienteUpdateDTO);

        Cliente clienteActualizado = clienteRepository.save(cliente);

        return ClienteMapper.INSTANCIA.clienteAClienteDTO(clienteActualizado);
    }


    @Override
    public String eliminarCliente(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isEmpty()) {
            return "Cliente no encontrado con ID: " + id;
        }

        clienteRepository.delete(cliente.get());
        return "Cliente eliminado exitosamente";
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteDTO> buscarPorNombre(String busqueda) {
        List<Cliente> clientes = clienteRepository.buscarPorNombreCompleto(busqueda);
        return ClienteMapper.INSTANCIA.listaClienteAListaClienteDTO(clientes);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteDTO> buscarPorTipoDocumento(Integer tipoDoc) {
        List<Cliente> clientes = clienteRepository.findByTipoDoc(tipoDoc);
        return ClienteMapper.INSTANCIA.listaClienteAListaClienteDTO(clientes);
    }
}
