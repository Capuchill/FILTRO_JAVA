package com.campusland.views;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.campusland.exceptiones.facturaexceptions.FacturaExceptionInsertDataBase;
import com.campusland.respository.models.Cliente;
import com.campusland.respository.models.Factura;
import com.campusland.respository.models.ItemFactura;
import com.campusland.respository.models.Producto;
import com.campusland.utils.Formato;
import com.campusland.views.logic_views.GenerarDian;

public class ViewFactura extends ViewMain {
    private static final int EXIT = 6;

    public static void startMenu() throws FacturaExceptionInsertDataBase {

        int op = 0;

        do {

            op = mostrarMenu();
            switch (op) {
                case 1:
                    crearFactura();
                    break;
                case 2:
                    listarFactura();
                    break;
                case 3:
                    GenerarDian.init();
                    break;
                case 4:
                    repotarClientes();
                    break;
                case 5:
                    repotarProductos();
                    break;
                default:
                    System.out.println("Opción no valida");
                    break;
            }

        } while (op != EXIT);

    }

    public static int mostrarMenu() {
        System.out.println("----Menu--Factura----");
        System.out.println("1. Crear factura.");
        System.out.println("2. Listar factura.");
        System.out.println("3. Generar DIAN.");
        System.out.println("4. Listar clientes por compras.");
        System.out.println("5. Listar productos mas vendidos.");
        System.out.println(EXIT+". Salir ");
        return leer.nextInt();
    }

    private static void listarFactura() {
        System.out.println("Lista de Facturas");
        for (Factura factura : serviceFactura.listar()) {
            factura.display();
            System.out.println();
        }
    }

    private static void repotarClientes() {
        System.out.println();
        System.out.println("Clientes por compras:");
        System.err.println();
        for (int i = 0; i < serviceCliente.generarReporte().size(); i++) {
            System.out.print("Cliente " + i + 1   + ": ");
            System.out.println(serviceCliente.generarReporte().get(i).getFullName());
            System.out.print("Monto acumulado: ");
            System.out.println(Formato.formatoMonedaPesos(serviceCliente.generarReporteMontos().get(i)));
            System.out.println();
            
        }
    }


    private static void repotarProductos() {
        System.out.println();
        System.out.println("Productos mas vendidos:");
        System.err.println();
        for (int i = 0; i < serviceProducto.generarReporte().size(); i++) {
            System.out.println(i+1);
            System.out.println(serviceProducto.generarReporte().get(i).getNombre());
            System.out.print("Ventas del producto: ");
            System.out.println(serviceProducto.generarReporteMontos().get(i));
            System.out.println();
            
        }
    }

    private static void crearFactura() {
        System.out.println("-- Creación de Factura ---");

        Cliente cliente;
        do {
            cliente = ViewCliente.buscarGetCliente();
        } while (cliente == null);

        Factura factura = new Factura(LocalDateTime.now(), cliente);
        System.out.println("-- Se creó la factura -----------------");
        System.out.println("-- Seleccione los productos a comprar por código");
     

        do {
            Producto producto = ViewProducto.buscarGetProducto();

            if (producto != null) {
                System.out.print("Cantidad: ");
                int cantidad = leer.nextInt();
                ItemFactura item = new ItemFactura(cantidad, producto);
                factura.agregarItem(item);

                System.out.println("Agregar otro producto: si o no");
                String otroItem = leer.next();
                if (!otroItem.equalsIgnoreCase("si")) {
                    break;
                }
            }

        } while (true);
        

        try {
            validarImpuesto(factura);
            serviceFactura.crear(factura);
            System.out.println("Se creó la factura");
            factura.display();
        } catch (FacturaExceptionInsertDataBase e) {
            System.out.println(e.getMessage());
        }
    }

    private static void validarImpuesto(Factura factura) {
        try {
            String date = String.valueOf(LocalDate.now().getYear());
            double impuesto = serviceimpuestoMySQL.buscarImpuesto(date).getPorcentaje();
            factura.setTotalImpuesto(impuesto);
        } catch (Exception e) {
            System.out.println("Por favor establecer un impuesto para éste año.");
        }
    }

}
