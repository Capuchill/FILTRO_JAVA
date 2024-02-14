package com.campusland.views.logic_views;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.campusland.exceptiones.impuestosexceptions.ImpuestoException;
import com.campusland.respository.models.Factura;
import com.campusland.respository.models.Impuesto;
import com.campusland.views.ViewFactura;

public class GenerarDian extends ViewFactura {
    
    private GenerarDian(){}

    public static void init() {
            Impuesto imps;
            try {
                LocalDate date = LocalDate.now();
                
                imps = serviceimpuestoMySQL.buscarImpuesto(String.valueOf(date.getYear()));
                System.out.println(imps.toString());
                
                List<Factura> l = serviceFactura.listar();
                List<Factura> nl = new ArrayList<>();

                for(Factura fact : l) {
                    if(fact.getFecha().getYear() == date.getYear() && 
                        fact.getFecha().getMonthValue() >= 1 && fact.getFecha().getMonthValue() <= 12
                    ) {
                        fact.setTotalImpuesto(imps.getPorcentaje());
                        nl.add(fact);
                    }
                }

                serviceImpuesto.generarFileDIAN(nl);


            } catch (ImpuestoException e) {
                System.out.println(e.getMessage());
            }
    }

}
