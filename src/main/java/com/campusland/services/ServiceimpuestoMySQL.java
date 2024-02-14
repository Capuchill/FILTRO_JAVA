package com.campusland.services;

import com.campusland.exceptiones.impuestosexceptions.ImpuestoException;
import com.campusland.respository.models.Impuesto;

public interface ServiceimpuestoMySQL {
    
    Impuesto buscarImpuesto(String date) throws ImpuestoException;

}
