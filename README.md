# springboot-angular-basic-skeleton

## Servicios
### demo-backend: Servidor Spring boot sobre Java 17. 
- IDE recomendado: Eclipse, con plugin Spring tools instalado para poder arrancar el servidor desde el "Boot dashboard". 

### demo-frontend: Cliente web sobre Angular 14. 
- Versiones de Node con las que funciona: 
  - v14.19.0
  - v16.16.0
- IDE recomendado Visual Studio Code.
- Para poner a funcionar, ejecutar los siguientes comandos:
	- npm install
	- ng serve

## TODO:

- Falta por maquetar bien los botones del formulario de anadir/editar contacto
- Añadir un breadcrumb de navegacion aprovechando el sistema de routing
- Insertar traducciones que vayan faltando
- Revisar tanto en front como en back el uso de cosillas deprecadas e ir cambiandolo
- Que no de ningun warning ni errores de sonarlint, para dar un modelo de codigo lo mas limpio posible
- Arreglar pantalla de login para que el fondo llene todo y no solo el cacho que se ve ahora

## Notas

- Evaluar la posibilidad de utilizar [Lombok](https://projectlombok.org/) para eliminar las configuraciones de hibernate o la definicion de getters/setters. Esto a nivel formacion puede ser un problema, asi que primero enseñamos a crearlo todo sin Lombok pero luego podemos meterlo y enseñar las facilidades que proporciona
- Evaluar también la inclusion de [mapstruct](https://mapstruct.org/) para minimizar los mapeos, por ejemplo de DTOs a DAOS y viceversa
