package com.campusland.respository.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Impuesto {

    private int id;
    private String anio;
    private double porcentaje;
    
    

    @Override
    public String toString() {
        String eso = 
        "\n-------------------------------\n" +
        "|  ID: " + getId() +
        "\n|  Año: " + getAnio() +
        "\n|  Iva: " + getPorcentaje() +
        "\n---------------------------------";

        return eso;
    }
    
}
