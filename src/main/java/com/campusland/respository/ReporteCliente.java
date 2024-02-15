package com.campusland.respository;

import java.util.List;

import com.campusland.respository.models.Cliente;

public interface ReporteCliente {

    List<Cliente> reporteClientes();

    List<Double> reporteClientesCompra();

}
