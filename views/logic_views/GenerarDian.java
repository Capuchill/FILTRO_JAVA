package com.campusland.views.logic_views;

import java.time.LocalDate;
import java.util.List;

import com.campusland.exceptiones.facturaexceptions.FacturaExceptionInsertDataBase;
import com.campusland.exceptiones.impuestosexceptions.ImpuestoException;
import com.campusland.respository.models.Factura;
import com.campusland.respository.models.Impuesto;
import com.campusland.views.ViewFactura;

public class GenerarDian extends ViewFactura {
    
    private GenerarDian(){}

    public static void init() {
            Impuesto imps;
            try {
                String hoy = String.valueOf(LocalDate.now().getYear());
                imps = serviceimpuestoMySQL.buscarImpuesto(hoy);
                System.out.println(imps.toString());
                
                List<Factura> l = serviceFactura.listar();

                for(Factura fact : l) {
                    fact.setTotalImpuesto(fact.getTotalFactura() + (fact.getTotalFactura() * (imps.getPorcentaje() / 100)));
                }

                serviceImpuesto.generarFileDIAN(l);
                /* l.forEach(fact -> {
                    try {
                        serviceFactura.crear(fact);
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }); */


            } catch (ImpuestoException e) {
                System.out.println(e.getMessage());
            }
    }

}
