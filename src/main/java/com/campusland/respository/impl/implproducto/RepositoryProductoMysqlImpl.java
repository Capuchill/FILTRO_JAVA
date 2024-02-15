package com.campusland.respository.impl.implproducto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.campusland.respository.ReporteProducto;
import com.campusland.respository.RepositoryProducto;
import com.campusland.respository.models.Cliente;
import com.campusland.respository.models.Producto;
import com.campusland.utils.conexionpersistencia.conexionbdmysql.ConexionBDMysql;

public class RepositoryProductoMysqlImpl implements RepositoryProducto, ReporteProducto{

    private Connection getConnection() throws SQLException {
        return ConexionBDMysql.getInstance();
    }

    @Override
    public List<Producto> listar() {
         List<Producto> listProducto = new ArrayList<>();

        try (Connection conn = getConnection();
            Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM producto")) {
            while (rs.next()) {
                listProducto.add(crearProducto(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listProducto;
       
    }

    @Override
    public Producto porCodigo(int codigo) {
        Producto producto = null;

        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM producto WHERE codigo=?")) {
            stmt.setInt(1, codigo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = crearProducto(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    
    }

    @Override
    public void crear(Producto producto) {
        String sql = "INSERT INTO producto(nombre, descripcion,precioVenta,precioCompra) VALUES(?,?,?,?)";

        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setDouble(3, producto.getPrecioVenta());
            stmt.setDouble(4, producto.getPrecioCompra());           
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    
    }

    @Override
    public void editar(Producto producto) {       
        String sql = "UPDATE producto SET nombre=?, descripcion=?,precioVenta=?,precioCompra=? WHERE codigo=? ";

        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setDouble(3, producto.getPrecioVenta());
            stmt.setDouble(4, producto.getPrecioCompra());
            stmt.setInt(5,producto.getCodigo());           
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void eliminar(Producto producto) {
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM producto WHERE codigo=?")) {
            stmt.setInt(1, producto.getCodigo());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Producto crearProducto(ResultSet rs) throws SQLException {
        Producto producto = new Producto();
        producto.setCodigo(rs.getInt("codigo"));
        producto.setNombre(rs.getString("nombre"));
        producto.setDescripcion(rs.getString("descripcion"));
        producto.setPrecioVenta(rs.getDouble("precioVenta"));
        producto.setPrecioCompra(rs.getDouble("precioCompra"));
        return producto;

    }

    private Producto crearProductoPorNombre(ResultSet rs) throws SQLException {
        Producto producto = new Producto();
        producto.setNombre(rs.getString("nombre"));
        return producto;
    }

    @Override
    public List<Producto> reporteProductos() {
        
        List<Producto> listProducto = new ArrayList<>();

        try (Connection conn = getConnection();
            Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT SUM(it.cantidad) as cantidad, p.nombre, p.codigo FROM item_factura it INNER JOIN producto p ON p.codigo = it.producto_codigo GROUP BY p.codigo ORDER BY cantidad DESC")) {
            while (rs.next()) {
                listProducto.add(crearProductoPorNombre(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listProducto;
    }


    @Override
    public List<Integer> reporteProductosCompra() {
        List<Integer> listClientes = new ArrayList<>();

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT SUM(it.cantidad) as cantidad, p.nombre, p.codigo FROM item_factura it INNER JOIN producto p ON p.codigo = it.producto_codigo GROUP BY p.codigo ORDER BY cantidad DESC")) {
            while (rs.next()) {
                listClientes.add(rs.getInt("cantidad"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listClientes;
    }


   

}
