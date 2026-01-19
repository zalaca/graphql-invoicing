# graph-ql-invoicing
GraphQL technology for invoices domain (DDD)

---

## Introducción

Hoy vamos a hablar de GraphQL, no solo como una tecnología, sino como una forma diferente de diseñar y consumir APIs. Para entender por qué aparece GraphQL, primero es importante entender los problemas que solemos tener con las APIs tradicionales, especialmente las basadas en REST.

---

## El problema de las APIs tradicionales

Las APIs REST han sido durante muchos años el estándar para comunicar frontend y backend. Son simples, conocidas y funcionan muy bien en muchos contextos. Sin embargo, cuando las aplicaciones crecen, empiezan a aparecer ciertas limitaciones.

Uno de los problemas más comunes es el **overfetching**, es decir, cuando una API devuelve más información de la que realmente necesita el cliente. Por ejemplo, una petición para obtener un usuario puede devolver nombre, email, dirección, roles, permisos, pedidos, etc., cuando el frontend solo necesita mostrar el nombre y una foto. Esto genera tráfico innecesario y acopla demasiado al cliente con el backend.

Otro problema es el **underfetching**, que ocurre cuando una sola llamada no es suficiente. Para construir una pantalla completa, el cliente necesita hacer varias peticiones: una para el usuario, otra para sus pedidos y otra para los productos de cada pedido. Esto aumenta la latencia y la complejidad del código.

Además, en REST suele existir un fuerte acoplamiento entre frontend y backend. Cada cambio en la interfaz puede requerir modificar un endpoint o crear uno nuevo. Con el tiempo aparecen versiones como `/v1`, `/v2`, `/v3`, lo que complica el mantenimiento.

Todo esto no significa que REST sea malo, sino que no fue diseñado pensando en interfaces modernas, muy dinámicas y con múltiples clientes.

---

## Por qué surge GraphQL

GraphQL nace en Facebook alrededor de 2012 y se libera públicamente en 2015. Surge para resolver precisamente estos problemas: aplicaciones con múltiples clientes, necesidades cambiantes y redes no siempre rápidas.

La idea principal detrás de GraphQL es sencilla pero poderosa:

> Dar al cliente el control sobre los datos que necesita.

En lugar de que el servidor decida qué estructura devolver, el cliente describe exactamente qué datos quiere recibir.

---

## ¿Qué es GraphQL?

GraphQL es un lenguaje de consultas y un runtime para APIs.

No es una base de datos, no reemplaza tu backend y no define cómo guardar la información. Es una capa intermedia entre el cliente y tu lógica de negocio.

Permite que el cliente describa la forma exacta de la respuesta que desea, y el servidor se encarga de resolverla.

---

## Concepto general de GraphQL

GraphQL se apoya en tres ideas fundamentales:

- Existe normalmente un único endpoint.
- Toda la API está definida mediante un esquema (*schema*).
- El cliente define la estructura de la respuesta.

Por ejemplo, una consulta puede pedir solo el nombre y el email de un usuario, y la respuesta tendrá exactamente esos campos, ni más ni menos.

---

## Diferencias con REST

En REST solemos tener muchos endpoints, cada uno con una respuesta fija. Las APIs se versionan y el backend decide qué devuelve.

En GraphQL:

- normalmente hay un solo endpoint
- no hay versionado tradicional
- las consultas son declarativas
- el cliente decide qué datos necesita
- se reduce el número de llamadas

Una forma sencilla de resumirlo es:

> En REST el servidor decide qué devuelve.  
> En GraphQL el cliente decide qué necesita.

---

## El Schema: el contrato de la API

El corazón de GraphQL es el **schema**.

El schema define todo lo que la API permite hacer. En él se especifican:

- los tipos disponibles
- los campos de cada tipo
- las consultas posibles
- las mutaciones disponibles

Por ejemplo, un tipo `User` puede definirse con campos como `id`, `nombre` o `email`.

El schema funciona como:

- contrato entre frontend y backend
- documentación viva
- sistema de validación automática

---

## Tipos, Queries y Mutations

En GraphQL existen tres conceptos fundamentales.

### Tipos

Representan el modelo de datos. Definen qué campos tiene cada entidad y de qué tipo son.

### Queries

Sirven para leer datos. Son equivalentes a las operaciones de consulta.

Por ejemplo, obtener un usuario o una lista de pedidos.

### Mutations

Sirven para modificar datos: crear, actualizar o eliminar información.

Separar queries y mutations ayuda a tener una intención clara en cada operación.

---

## Tipado fuerte e introspección

GraphQL es fuertemente tipado. Eso significa que todos los campos, argumentos y respuestas tienen un tipo definido.

Esto permite detectar errores antes de ejecutar la aplicación y facilita muchísimo el desarrollo.

Además, GraphQL soporta **introspección**, lo que significa que el propio schema puede ser consultado. Gracias a esto existen herramientas como GraphiQL o GraphQL Playground, que ofrecen:

- autocompletado
- documentación automática
- exploración de la API

Esto mejora notablemente la experiencia de desarrollo.

---

## Queries en GraphQL

Una query en GraphQL describe exactamente qué datos queremos.

Por ejemplo, podemos pedir una lista de usuarios solicitando únicamente el nombre.

La gran ventaja es que podemos cambiar la forma de la respuesta sin tocar el backend, simplemente modificando la consulta.

---

## Selección dinámica de campos

Podemos pedir campos anidados, combinando información relacionada en una sola consulta. Por ejemplo, pedir un usuario junto con sus pedidos y los productos de cada pedido.

Esto evita múltiples llamadas y permite construir respuestas adaptadas a cada pantalla.

---

## Queries avanzadas

GraphQL permite usar argumentos para filtrar, ordenar o paginar datos.

Por ejemplo:

- traer solo usuarios activos
- limitar resultados
- buscar por ciertos criterios

Además, una sola query puede componer varios recursos distintos, devolviendo una estructura compleja en una única respuesta.

---

## Relaciones entre entidades

GraphQL modela muy bien las relaciones entre entidades. Un usuario puede tener pedidos, un pedido puede tener productos, y así sucesivamente.

Estas relaciones no se resuelven automáticamente: se implementan mediante **resolvers**.

---

## Resolvers

Los resolvers son funciones encargadas de obtener los datos reales.

Cada campo del schema puede tener un resolver asociado que define:

- cómo obtener la información
- desde dónde
- con qué lógica

Un resolver puede:

- consultar una base de datos
- llamar a otro servicio
- ejecutar lógica de negocio
- combinar varias fuentes

En resumen:

> el schema define el “qué” y los resolvers definen el “cómo”.

---

## Integración con arquitectura hexagonal

En una arquitectura hexagonal, GraphQL encaja muy bien como **adaptador de entrada**.

El flujo típico sería:

1. GraphQL recibe la petición
2. el resolver llama a un caso de uso o servicio de aplicación
3. el dominio ejecuta la lógica
4. los adaptadores de salida acceden a la base de datos u otros sistemas

De esta forma, GraphQL no contiene lógica de negocio, solo orquesta llamadas hacia el dominio.

---

## Mutations

Las mutations se usan para crear, modificar o eliminar datos.

A diferencia de REST, donde usamos `POST`, `PUT` o `DELETE`, en GraphQL todas estas operaciones se expresan como mutations con nombres claros.

Por ejemplo:

- `createUser`
- `updateInvoice`
- `approveOrder`

Esto hace que las operaciones sean más expresivas y fáciles de entender.

---

## Mutations y Commands

Las mutations encajan muy bien con el concepto de **Command** en arquitectura.

Un command representa una intención: *crear*, *aprobar*, *cancelar*.

Normalmente no devuelven estructuras complejas, sino el resultado de la operación o un identificador.

Esto permite mantener una separación clara entre lectura y escritura.

---

## Introducción a CQRS

CQRS significa **Command Query Responsibility Segregation**.

La idea es separar:

- las operaciones de lectura
- de las operaciones de escritura

Porque tienen objetivos distintos, modelos distintos y necesidades distintas.

---

## CQRS aplicado a GraphQL

GraphQL encaja de forma natural con CQRS:

- Las **Queries** representan los modelos de lectura.
- Las **Mutations** representan comandos del dominio.

Las queries suelen estar optimizadas para el frontend, incluso con modelos denormalizados.

Las mutations ejecutan reglas de negocio y modifican el estado del sistema.

---

## Casos de uso de GraphQL

GraphQL es especialmente útil cuando:

- hay múltiples clientes (web, móvil, etc.)
- el frontend cambia con frecuencia
- se necesita flexibilidad en las respuestas
- se construyen BFFs
- se trabaja con microservicios
- se requiere orquestación de datos

---

## Cuándo no usar GraphQL

No siempre es la mejor opción.

Por ejemplo:

- APIs muy simples tipo CRUD
- servicios internos muy pequeños
- descarga de archivos grandes
- casos donde el caching HTTP tradicional es clave
- equipos sin experiencia en el paradigma

---

## Conclusiones

GraphQL ofrece muchas ventajas:

- flexibilidad
- menos llamadas
- tipado fuerte
- documentación automática
- evolución sin versionado agresivo
- mejor experiencia para frontend

Pero más allá de la tecnología, GraphQL debe entenderse como una **capa de orquestación**.

No reemplaza tu arquitectura ni tu dominio, sino que se apoya en ellos para exponer capacidades de forma clara y flexible.

---

## Cierre

Para cerrar, una idea clave:

> **GraphQL no es solo una forma diferente de hacer APIs, sino una forma diferente de pensar cómo los clientes consumen información.**
