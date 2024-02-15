-- Active: 1707625446905@@127.0.0.1@3306@facturacion
ALTER Table factura ADD COLUMN total_pagar_iva FLOAT;
ALTER Table factura DROP COLUMN iva;


SELECT SUM(f.total_pagar_iva) as pago, c.nombre,c.id, c.apellido , c.email, c.direccion, c.celular, c.documento
FROM cliente c 
INNER JOIN factura f on f.cliente_id = c.id  
GROUP BY (c.id)
ORDER BY pago DESC;

SELECT SUM(it.cantidad) as cantidad, p.nombre, p.codigo 
FROM item_factura it 
INNER JOIN producto p ON p.codigo = it.producto_codigo 
GROUP BY p.codigo ORDER BY cantidad DESC;




