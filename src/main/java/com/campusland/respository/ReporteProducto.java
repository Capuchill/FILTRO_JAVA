package com.campusland.respository;

import java.util.List;

import com.campusland.respository.models.Cliente;
import com.campusland.respository.models.Producto;

public interface ReporteProducto {

    List<Producto> reporteProductos();

    List<Integer> reporteProductosCompra();
}