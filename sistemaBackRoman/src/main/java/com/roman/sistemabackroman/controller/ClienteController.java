package com.roman.sistemabackroman.controller;

import com.roman.sistemabackroman.dtos.ClienteCreateDTO;
import com.roman.sistemabackroman.dtos.ClienteDTO;
import com.roman.sistemabackroman.dtos.ClienteUpdateDTO;
import com.roman.sistemabackroman.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@Tag(name = "cliente-contoller", description = "Cliente Contoller")
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    @Operation(
            summary = "listarClientes",
            description = "Obtiene la lista completa de todos los clientes registrados en el sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        List<ClienteDTO> clientes = clienteService.listarClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/{clienteId}")
    @Operation(
            summary = "obtenerClientePorId",
            description = "Obtiene los datos de un cliente específico mediante su ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ResponseEntity<ClienteDTO> obtenerClientePorId(
            @Parameter(description = "ID del cliente a buscar", required = true)
            @PathVariable("clienteId") Integer clienteId) {
        ClienteDTO cliente = clienteService.obtenerClientePorId(clienteId);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/documento/{numDocumento}")
    @Operation(
            summary = "obtenerClientePorDocumento",
            description = "Busca un cliente por su número de documento (DNI, CE, etc.)"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ResponseEntity<ClienteDTO> obtenerClientePorDocumento(
            @Parameter(description = "Número de documento del cliente", required = true)
            @PathVariable("numDocumento") String numDocumento) {
        ClienteDTO cliente = clienteService.obtenerClientePorDocumento(numDocumento);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buscar")
    @Operation(
            summary = "buscarPorNombre",
            description = "Busca clientes por nombre o apellidos (búsqueda parcial)"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente")
    })
    public ResponseEntity<List<ClienteDTO>> buscarPorNombre(
            @Parameter(description = "Texto a buscar en nombre o apellidos", required = true)
            @RequestParam("busqueda") String busqueda) {
        List<ClienteDTO> clientes = clienteService.buscarPorNombre(busqueda);
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/tipo-documento/{tipoDoc}")
    @Operation(
            summary = "buscarPorTipoDocumento",
            description = "Lista clientes filtrados por tipo de documento (1=DNI, 2=CE, 3=Pasaporte)"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    public ResponseEntity<List<ClienteDTO>> buscarPorTipoDocumento(
            @Parameter(description = "Tipo de documento: 1=DNI, 2=CE, 3=Pasaporte", required = true)
            @PathVariable("tipoDoc") Integer tipoDoc) {
        List<ClienteDTO> clientes = clienteService.buscarPorTipoDocumento(tipoDoc);
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @PostMapping
    @Operation(
            summary = "registrarCliente",
            description = "Registra un nuevo cliente en el sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o documento duplicado")
    })
    public ResponseEntity<ClienteDTO> registrarCliente(
            @Parameter(description = "Datos del cliente a registrar", required = true)
            @RequestBody ClienteCreateDTO clienteCreateDTO) {
        try {
            ClienteDTO nuevoCliente = clienteService.registrarCliente(clienteCreateDTO);
            return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    @Operation(
            summary = "actualizarCliente",
            description = "Actualiza los datos de un cliente existente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<ClienteDTO> actualizarCliente(
            @Parameter(description = "Datos del cliente a actualizar", required = true)
            @RequestBody ClienteUpdateDTO clienteUpdateDTO) {
        try {
            ClienteDTO clienteActualizado = clienteService.actualizarCliente(clienteUpdateDTO);
            return new ResponseEntity<>(clienteActualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{clienteId}")
    @Operation(
            summary = "eliminarCliente",
            description = "Elimina un cliente del sistema mediante su ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ResponseEntity<String> eliminarCliente(
            @Parameter(description = "ID del cliente a eliminar", required = true)
            @PathVariable("clienteId") Integer clienteId) {
        String resultado = clienteService.eliminarCliente(clienteId);
        if (resultado.contains("✅")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }
}
