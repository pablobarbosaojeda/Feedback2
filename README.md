# FEEDBACK 4 Pablo Barbosa Ojeda

## Link al Repositorio
[https://github.com/pablobarbosaojeda/Feedback2.git](https://github.com/pablobarbosaojeda/Feedback2.git)

## Descripción del Proyecto

**Novel Manager** es una aplicación Android diseñada para gestionar una biblioteca personal de novelas. Permite a los usuarios organizar y mantener un registro de sus lecturas, facilitando la adición, eliminación y revisión de novelas. Los usuarios pueden destacar sus novelas favoritas y agregar reseñas para enriquecer su experiencia de lectura.

## Funcionalidades

### 1. Agregar Novelas
- Los usuarios pueden añadir nuevas novelas proporcionando información esencial como:
  - **Título**: El nombre de la novela.
  - **Autor**: El autor de la novela.
  - **Año de publicación**: El año en que se publicó la novela.
  - **Sinopsis**: Una breve descripción del contenido de la novela.

### 2. Eliminar Novelas
- Posibilidad de eliminar novelas de la lista para mantener una colección actualizada y relevante.

### 3. Ver Detalles de las Novelas
- Al seleccionar una novela, se presentan detalles completos, incluyendo:
  - Título
  - Autor
  - Año de publicación
  - Sinopsis
  - Opción para marcarla como favorita.

### 4. Marcar Novelas Favoritas
- Los usuarios pueden destacar novelas como favoritas, que se mostrarán de manera prominente en la lista principal.

### 5. Añadir Reseñas
- Los usuarios pueden escribir y guardar reseñas para cada novela, mejorando su registro personal y proporcionando un recurso útil para futuras lecturas.

### 6. Configuración
- Los usuarios pueden acceder a una pantalla de configuración donde pueden:
  - Realizar copias de seguridad de la base de datos de novelas.
  - Restaurar la base de datos de novelas desde una copia de seguridad.

### 7. Sincronización en Segundo Plano
- La aplicación realiza sincronizaciones periódicas en segundo plano para mantener los datos actualizados.

## Funcionalidades Adicionales

### 8. Widgets
- La aplicación incluye un widget de pantalla de inicio que permite a los usuarios ver una lista de sus novelas favoritas de manera rápida y accesible. 
- **Características del Widget**:
  - Muestra las novelas marcadas como favoritas en un formato compacto.
  - Permite abrir la aplicación directamente desde el widget para ver más detalles de las novelas.
  - **Actualización Automática**: El widget se actualiza automáticamente cada vez que el usuario agrega o elimina favoritos en la aplicación. Utiliza `WorkManager` para controlar la frecuencia de actualización y evitar un consumo innecesario de recursos del dispositivo.

### 9. Fragments
- La aplicación usa fragments para dividir la interfaz de usuario en secciones más manejables, permitiendo una experiencia adaptativa y modular.
- **Fragments Implementados**:
  - **FragmentListaNovelas**: Muestra la lista de todas las novelas en una vista adaptable que puede integrarse en múltiples configuraciones de pantalla.
  - **FragmentDetailNovelas**: Presenta los detalles de una novela seleccionada, como título, autor, año de publicación y sinopsis, y permite al usuario marcar la novela como favorita.
- **Ventajas de Usar Fragments**:
  - Facilita la creación de interfaces adaptativas, especialmente en dispositivos de pantalla grande.
  - Mejora la reutilización de componentes, ya que los fragments se pueden integrar en distintas actividades o configuraciones de layout.
