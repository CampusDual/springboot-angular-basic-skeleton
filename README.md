# springboot-angular-basic-skeleton

TODO:

- Problemas con el sistema de pestañas para visualizar las tablas de datos y los formularios de insercion -> Cambiarlo por sistema de formulario convencional
- Problemas en la maquetacion del formulario, columnas de tablas y alguna cosilla mas

- Revisar tanto en front como en back el uso de cosillas deprecadas e ir cambiandolo
- Que no de ningun warning ni errores de sonarlint, para dar un modelo de codigo lo mas limpio posible
- Insertar traducciones que vayan faltando
- Hay que introducir un mecanismo que compruebe la validez del token contra back, para que la sesion no se quede abierta sin limite pero el token no sea valido en back y por tanto fallen las peticiones contra este (si esto pasa, de momento basta con cerrar sesion y volver a iniciarla)
- Angular 10 introdujo la funcion renderRows() para actualizar solo una fila de una tabla despues de actualizar un registro, por lo que habria que ver de incluirlo
- Añadir un breadcrumb de navegacion aprovechando el sistema de routing
- Arreglar pantalla de login para que el fondo llene todo y no solo el cacho que se ve ahora