# FEEDBACK 5 Pablo Barbosa Ojeda

## Link al Repositorio
[https://github.com/pablobarbosaojeda/Feedback2.git](https://github.com/pablobarbosaojeda/Feedback2.git)

## Descripción del Proyecto

**Novel Manager** es una aplicación Android diseñada para gestionar una biblioteca personal de novelas. Permite a los usuarios organizar y mantener un registro de sus lecturas, facilitando la adición, eliminación y revisión de novelas. Los usuarios pueden destacar sus novelas favoritas, agregar reseñas y ahora asociar ubicaciones con sus novelas.

## Funcionalidades

### 1. Agregar Novelas
- Los usuarios pueden añadir nuevas novelas proporcionando información esencial como:
  - **Título**: El nombre de la novela.
  - **Autor**: El autor de la novela.
  - **Año de publicación**: El año en que se publicó la novela.
  - **Sinopsis**: Una breve descripción del contenido de la novela.
  - **Ubicación**: La ubicación geográfica asociada con la novela.

### 2. Eliminar Novelas
- Posibilidad de eliminar novelas de la lista para mantener una colección actualizada y relevante.

### 3. Ver Detalles de las Novelas
- Al seleccionar una novela, se presentan detalles completos, incluyendo:
  - Título
  - Autor
  - Año de publicación
  - Sinopsis
  - Ubicación asociada
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

---

## Nuevas Funcionalidades de Ubicación

### 1. Mostrar Ubicación Actual
- La aplicación utiliza el GPS del dispositivo para mostrar la ubicación actual del usuario en tiempo real.
- Los usuarios pueden visualizar sus coordenadas de latitud y longitud en la interfaz principal.

### 2. Agregar Ubicación a las Novelas
- Permite a los usuarios asociar una ubicación geográfica con cada novela.
- Esta funcionalidad puede ser útil para recordar dónde compraron o leyeron una novela.

### 3. Mostrar Novelas en un Mapa
- Visualiza todas las novelas agregadas en un mapa, utilizando marcadores para indicar las ubicaciones asociadas.
- Cada marcador muestra información básica de la novela asociada, como su título y autor.

### 4. Geocodificación y Geocodificación Inversa
- Implementación de funcionalidades para convertir:
  - Direcciones en coordenadas de latitud/longitud (geocodificación).
  - Coordenadas de latitud/longitud en direcciones comprensibles (geocodificación inversa).

---

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

### 10. Optimización del Rendimiento y Uso de la Red
- **Optimización de la Red**:
  - Utiliza técnicas de compresión de datos para reducir el consumo de datos móviles y optimizar el rendimiento.
  - Se utiliza el **Network Profiler** para analizar y optimizar el uso de la red, minimizando el consumo de datos y el tiempo de respuesta.

- **Optimización de la Batería**:
  - Implementación de controles de consumo de batería con la ayuda de **Battery Historian** para detectar y corregir posibles problemas de eficiencia de batería.
  - Se limita la frecuencia de actualizaciones en segundo plano para reducir el impacto en el uso de la batería.

### 11. Optimización de la Memoria
- **Reducción de Fugas de Memoria**:
  - Uso del **Memory Profiler** para identificar y solucionar posibles fugas de memoria.
  - Implementación de técnicas para el uso eficiente de la memoria, como el reciclaje de vistas y el uso eficiente de `Bitmap`.

### 12. Mejoras de la Interfaz de Usuario
- **Interfaz Adaptativa**: La aplicación usa **Fragments** para crear interfaces modulares y adaptativas, mejorando la experiencia en dispositivos de diferentes tamaños de pantalla.

---

## Requisitos Técnicos
- **Mínimo SDK**: 21 (Android 5.0 Lollipop)
- **Librerías utilizadas**:
  - `Material3` para diseño moderno y adaptativo.
  - `WorkManager` para manejar tareas en segundo plano.
  - `LocationManager` para obtener la ubicación en tiempo real.

---

Con estas nuevas funcionalidades, **Novel Manager** ofrece a los usuarios una experiencia completa para gestionar su biblioteca personal, ahora enriquecida con capacidades de ubicación geográfica.
