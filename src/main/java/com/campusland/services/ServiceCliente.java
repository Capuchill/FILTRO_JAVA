package com.campusland.services;

import java.util.List;

import com.campusland.exceptiones.clienteexceptions.ClienteNullException;
import com.campusland.respository.models.Cliente;

public interface ServiceCliente {

    List<Cliente> listar();

    List<Double> generarReporteMontos();

    List<Cliente> generarReporte();

    Cliente porDocumento(String documento)throws ClienteNullException ;

    void crear(Cliente producto);

    void editar(Cliente producto);

    void eliminar(Cliente cliente);

}
