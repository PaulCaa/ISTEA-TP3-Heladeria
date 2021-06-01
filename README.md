# Heladeria

Aplicación Android (Kotlin) que permite realizar compla de helados seleccionando el tipo dn embase, los gustos y el medio de pago (mock). La pantalla final muestra el numero de pedido y un acceso a estadisticas de ventas segmentada por cada medio de pago (lectura en SQLite).

## Pantallas

1) Selección de helado (solo uno)
![Pantalla de la actividad inicial de la App](./src/readme/screen1.png)

2) Selección de sabores, la cantidad dependerá del tipo de helado
![Segunta actividad, se puede volver atras y cambiar el tipo de helado](./src/readme/screen2.png)

3) Resumen de pedido, aquí se verán todos los items y el importe total
![Si se presiona en finalizar pasa al pago, si se presionar Agregar Producto repite el flujo](./src/readme/screen3.png)

4) Medio de pago, solo se puede elegir uno y se mostrará un error si llegó al limite de cobros
![Prresionando atras vuelve al resumen del pedido y se puede seguir agregando productos](./src/readme/screen4.png)

5) Pantalla de confirmacion de compra, entregará numero de pedido
![El boton estadisticas lo lleva al resumen de ventas por cajas, volver a comprar genera un pedido nuevo](./src/readme/screen5.png)

6) Estadisticas, aquí se podrán ver las cajas y el volumen de cobros
![Salir redirecciona la app a la actividad principal](./src/readme/screen6.png)

