package com.campusland.respository;

import java.util.List;

import com.campusland.respository.models.Factura;

public interface RepositoryImpuesto {

    void generarFileDIAN(List<Factura> facturas);

    Factura verFileDIAN();

    List<Factura> verFilesDIAN();
    
}
