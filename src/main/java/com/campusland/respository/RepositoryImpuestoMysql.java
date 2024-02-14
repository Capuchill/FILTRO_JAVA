package com.campusland.respository;

import com.campusland.respository.models.Impuesto;

public interface RepositoryImpuestoMysql {
    
    Impuesto buscarImpuesto(String date);

}
