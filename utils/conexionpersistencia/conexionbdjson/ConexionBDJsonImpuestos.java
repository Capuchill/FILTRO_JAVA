package com.campusland.utils.conexionpersistencia.conexionbdjson;

import com.campusland.respository.models.Factura;

public class ConexionBDJsonImpuestos extends ConexionBDJsonBase<Factura> {

    private static ConexionBDJsonImpuestos conexionImpuestos;

    private ConexionBDJsonImpuestos() {
        super("impuesto.json");
    }

    public static ConexionBDJsonImpuestos getConexion() {
        if (conexionImpuestos != null) {
            return conexionImpuestos;
        } else {
            conexionImpuestos = new ConexionBDJsonImpuestos();
            return conexionImpuestos;
        }
    }
    
}
