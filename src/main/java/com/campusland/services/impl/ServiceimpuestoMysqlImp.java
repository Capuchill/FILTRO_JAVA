package com.campusland.services.impl;

import com.campusland.exceptiones.impuestosexceptions.ImpuestoException;
import com.campusland.respository.RepositoryImpuestoMysql;
import com.campusland.respository.models.Impuesto;
import com.campusland.services.ServiceimpuestoMySQL;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class ServiceimpuestoMysqlImp implements ServiceimpuestoMySQL {
    RepositoryImpuestoMysql repositoryImpuestoMysql;

    @Override
    public Impuesto buscarImpuesto(String date) throws ImpuestoException {
        Impuesto impu = this.repositoryImpuestoMysql.buscarImpuesto(date);
        if(impu != null) {
            return impu;
        } 
        else {
            throw new ImpuestoException("No se encontro impuesto establecido.");
        }
    }
    
}
