package com.campusland.services.impl;

import java.util.List;

import com.campusland.exceptiones.productoexceptions.ProductoNullException;
import com.campusland.respository.ReporteProducto;
import com.campusland.respository.RepositoryProducto;
import com.campusland.respository.models.Producto;
import com.campusland.services.ServiceProducto;

public class ServiceProductoImpl implements ServiceProducto {

    private final RepositoryProducto crudRepositoryProducto;
    private final ReporteProducto reportProducto;

    public ServiceProductoImpl(RepositoryProducto crudRepositoryProducto,ReporteProducto reportProducto ){
        this.crudRepositoryProducto=crudRepositoryProducto;
        this.reportProducto = reportProducto;
    }

    @Override
    public List<Producto> listar() {
         return this.crudRepositoryProducto.listar();
        
    }

    @Override
    public Producto porCodigo(int codigo)throws ProductoNullException  {
        Producto producto = this.crudRepositoryProducto.porCodigo(codigo);
        if (producto != null) {
            return producto;
        } else {
            throw new ProductoNullException("No se encontro producto por codigo");
        }
        
    }

    @Override
    public void crear(Producto producto) {
        this.crudRepositoryProducto.crear(producto);        
    }

    @Override
    public void editar(Producto producto) {
        this.crudRepositoryProducto.editar(producto);
       
    }

    @Override
    public void eliminar(Producto producto) {
        this.crudRepositoryProducto.eliminar(producto);
    }

    @Override
    public List<Integer> generarReporteMontos() {
        return this.reportProducto.reporteProductosCompra();
    }

    @Override
    public List<Producto> generarReporte() {
        return this.reportProducto.reporteProductos();
    }
    
    
}
