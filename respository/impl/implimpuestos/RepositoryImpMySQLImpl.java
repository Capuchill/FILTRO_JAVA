package com.campusland.respository.impl.implimpuestos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.campusland.respository.RepositoryImpuestoMysql;
import com.campusland.respository.models.Impuesto;
import com.campusland.utils.conexionpersistencia.conexionbdmysql.ConexionBDMysql;

public class RepositoryImpMySQLImpl implements RepositoryImpuestoMysql {

    private RepositoryImpuestoMysql repositoryImpuestMysqlImpl;

    private Connection getConexion() throws SQLException {
        return ConexionBDMysql.getInstance();
    }

    @Override
    public Impuesto buscarImpuesto(String date) {
        String query = "SELECT * FROM impuestos WHERE anio = ?";
        Impuesto impuesto = null;

        try (
            Connection conect = getConexion();
            PreparedStatement stmt = conect.prepareStatement(query);
        ) {
            stmt.setString(1, date);
            try (ResultSet res = stmt.executeQuery()) {
                while(res.next()) {
                    impuesto = crearImpuesto(res);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return impuesto;
    }

    private Impuesto crearImpuesto(ResultSet res) throws SQLException {
        return new Impuesto(
            res.getInt(1),
            String.valueOf(LocalDate.parse(res.getString(2)).getYear()), 
            res.getDouble(3)
        );
    }
    
}
