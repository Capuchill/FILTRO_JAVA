package com.campusland.services.impl;

import java.util.List;

import com.campusland.respository.RepositoryImpuesto;
import com.campusland.respository.models.Factura;
import com.campusland.services.ServiceImpuesto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class ServiceImpuesImpl implements ServiceImpuesto {
    RepositoryImpuesto repositoryImpuesto;

    @Override
    public void generarFileDIAN(List<Factura> facturas) {
        this.repositoryImpuesto.generarFileDIAN(facturas);
    }

    @Override
    public Factura verFileDIAN() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'verFileDIAN'");
    }

    @Override
    public List<Factura> verFilesDIAN() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'verFilesDIAN'");
    }
    
}
