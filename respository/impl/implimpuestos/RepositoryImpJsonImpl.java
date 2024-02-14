package com.campusland.respository.impl.implimpuestos;

import java.util.List;

import com.campusland.respository.RepositoryImpuesto;
import com.campusland.respository.models.Factura;
import com.campusland.respository.models.Impuesto;
import com.campusland.utils.conexionpersistencia.conexionbdjson.ConexionBDJsonImpuestos;

public class RepositoryImpJsonImpl implements RepositoryImpuesto {

    ConexionBDJsonImpuestos conexion = ConexionBDJsonImpuestos.getConexion();

    @Override
    public void generarFileDIAN(List<Factura> facturas) {
        conexion.saveData(facturas);
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
