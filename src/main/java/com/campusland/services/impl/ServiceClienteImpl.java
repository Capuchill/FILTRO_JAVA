package com.campusland.services.impl;

import java.util.List;

import com.campusland.exceptiones.clienteexceptions.ClienteNullException;
import com.campusland.respository.ReporteCliente;
import com.campusland.respository.RepositoryCliente;
import com.campusland.respository.models.Cliente;
import com.campusland.services.ServiceCliente;

public class ServiceClienteImpl  implements ServiceCliente {

    private final RepositoryCliente crudRepositoryCliente;
    private final ReporteCliente reporteC;

    public ServiceClienteImpl(RepositoryCliente crudRepositoryCliente, ReporteCliente reporteC){
         this.crudRepositoryCliente=crudRepositoryCliente;
         this.reporteC = reporteC;
    }

    @Override
    public List<Cliente> listar() {
        return this.crudRepositoryCliente.listar();        
    }

    @Override
    public Cliente porDocumento(String documento) throws ClienteNullException {
        Cliente cliente=this.crudRepositoryCliente.porDocumento(documento);
        if(cliente!=null){
            return cliente;
        }else{
            throw new ClienteNullException("No se encontro cliente por id");
        }  
      
    }

    @Override
    public void crear(Cliente cliente) {
         this.crudRepositoryCliente.crear(cliente);
       
    }

    @Override
    public void editar(Cliente cliente) {
        this.crudRepositoryCliente.editar(cliente);
        
    }

    @Override
    public void eliminar(Cliente cliente) {
       this.crudRepositoryCliente.eliminar(cliente); 
        
    }

    @Override
    public List<Cliente> generarReporte() {
       return this.reporteC.reporteClientes();
    }

    @Override
    public List<Double> generarReporteMontos() {
       return this.reporteC.reporteClientesCompra();
    }
    
}
