# springboot-angular-basic-skeleton

## Servicios
### demo-backend: Servidor Spring boot sobre Java 17. 
- IDE recomendado: Eclipse, con plugin Spring tools instalado para poder arrancar el servidor desde el "Boot dashboard". 
- Debido a la inclusion de la libreria MapStruct para el mapeado rápido de Entities a DTOs, es necesario hacer un "mvn clean install" al arrancar el proyecto para que genere todo el codigo del que nos abstrae a nosotros, si no posiblemente tengamos errores del tipo "Handler dispatch failed; nested exception is java.lang.ExceptionInInitializerError" o "cannot find implementation"

### demo-frontend: Cliente web sobre Angular 14. 
- Versiones de Node con las que funciona: 
  - v14.19.0
  - v16.16.0
- IDE recomendado Visual Studio Code.
- Para poner a funcionar, ejecutar los siguientes comandos:
	- npm install
	- ng serve

## TODO:

- Crear algun ejemplo de inputs especificos. Por ejemplo, uno que valide telefonos, de manera que, por ejemplo, pasando al input un parametro como type='telf' o algo asi, que sea una extension de input, pero llamando a un metodo que valide el email y controle que lleva @, el punto del dominio... La idea es que sirva de ejemplo para que luego en las formaciones puedan crear los suyos propios cuando lo necesiten, complicando mas la validacion del telf, email, nif, etc. Ontimize Web proporciona este tipo de campos, por lo que sería hacer algo similar en medida de lo posible (complicandonos poco, un ejemplo básico): https://try.imatia.com/ontimizeweb/v8/playground/main/inputs/email
- Insertar traducciones que vayan faltando
- Podriamos quitar la vista de contactos y mostrar la de usuarios, ya que al final tiene como mas sentido, puesto que siempre se gestionarán usuarios. Si da tiempo y se puede hacer, mejor, si no, no pasa nada.

- Revisar tanto en front como en back el uso de cosillas deprecadas e ir cambiandolo
- Que no de ningun warning ni errores de sonarlint, para dar un modelo de codigo lo mas limpio posible
- Arreglar pantalla de login para que el fondo llene todo y no solo el cacho que se ve ahora

## Notas

~~- Evaluar la posibilidad de utilizar [Lombok](https://projectlombok.org/) para eliminar las configuraciones de hibernate o la definicion de getters/setters. Esto a nivel formacion puede ser un problema, asi que primero enseñamos a crearlo todo sin Lombok pero luego podemos meterlo y enseñar las facilidades que proporciona~~ DESCARTAMOS COMPLETAMENTE EL USO DE LOMBOK YA QUE NO TIENE SENTIDO EN FORMACIONES
