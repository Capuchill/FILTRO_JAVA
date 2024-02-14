package com.campusland.services;

import java.util.List;

import com.campusland.respository.models.Factura;

public interface ServiceImpuesto {

    void generarFileDIAN(List<Factura> facturas);

    Factura verFileDIAN();

    List<Factura> verFilesDIAN();

}
