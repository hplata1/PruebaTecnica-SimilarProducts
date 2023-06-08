# SimilarProducts

Este servicio recibe como dato de entrada el ID de un producto. A partir de este dato, el servicio realiza una consulta en la API "similarids" para obtener los IDs de los productos similares. Posteriormente se toman los diferentes Ids y se envia una petición a la API "productDetails" por cada  uno de los IDs devueltos.  La respuesta es un arreglo JSON que contiene la información del ID, nombre, precio y disponibilidad de cada unos de los productos similares.

## Requerimientos

- Java 17 o superior
- Maven 3.6.6

## Instalación

1. Clonar el repositorio: `git clone`
2. Ejecutar el comando `mvn clean install` en la carpeta del proyecto
4. Ejecutar el comando `mvn spring-boot:run` en la carpeta del proyecto
3. Acceder a la API en `http://localhost:5000`

## Endpoints

### `/product`

- `GET /{productId}/similar`: Consulta la lista de productos similares

## Autor

Harby Arturo Plata Serrano

