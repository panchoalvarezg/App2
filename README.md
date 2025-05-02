# 🌱 App2 - Sistema de Gestión Agrícola

![Java](https://img.shields.io/badge/Java-16%2B-orange)
![Estado](https://img.shields.io/badge/Estado-En%20Desarrollo-green)
![Licencia](https://img.shields.io/badge/Licencia-MIT-blue)

Aplicación desarrollada en **Java 16+** que implementa un sistema de gestión integral para cultivos, parcelas y actividades agrícolas. Este proyecto aplica principios sólidos de Programación Orientada a Objetos (POO) y utiliza persistencia de datos en formato CSV.

## ✨ Características principales

- Gestión completa del ciclo de vida de cultivos
- Administración de parcelas y sus asignaciones
- Seguimiento de actividades agrícolas (riego, fertilización, cosecha)
- Generación de reportes e indicadores de rendimiento
- Persistencia de datos mediante archivos CSV

## 👥 Integrantes del equipo

| Nombre         | Correo electrónico        | GitHub                                 |
| -------------- | ------------------------- | -------------------------------------- |
| Cristobal Segu | csegu@alumnos.uai.cl      | [@usuario](https://github.com/usuario) |
| Diego Soler    | disoler@alumnos.uai.cl    | [@Dxeg0o](https://github.com/Dxeg0o)   |
| Alonso Paniate | apaniate@alumnos.uai.cl   | [@usuario](https://github.com/usuario) |
| Felipe Retamal | felretamal@alumnos.uai.cl | [@usuario](https://github.com/usuario) |

## 📝 Equipo académico

### Profesores

- María Loreto Arriagada - [loreto.arriagada.v@edu.uai.cl](mailto:loreto.arriagada.v@edu.uai.cl)
- Paulina González - [paulina.gonzalez.p@edu.uai.cl](mailto:paulina.gonzalez.p@edu.uai.cl)
- Justo Vargas - [justo.vargas@edu.uai.cl](mailto:justo.vargas@edu.uai.cl)

### Ayudante

- Diego Duhalde - [dduhalde@alumnos.uai.cl](mailto:dduhalde@alumnos.uai.cl)

## 🚀 Instalación y ejecución

### Requisitos previos

- Java JDK 16 o superior
- Git

### Pasos para compilar y ejecutar

1. **Clonar el repositorio**

   ```bash
   git clone https://github.com/<usuario>/App2.git
   cd App2
   ```

2. **Compilar el proyecto**

   ```bash
   mkdir -p bin
   javac -d bin src/models/*.java src/services/*.java src/ui/*.java src/App2.java
   ```

3. **Ejecutar la aplicación**
   ```bash
   java -cp bin App2 cultivos.csv
   ```

## 📂 Estructura del proyecto

```
App2/
├── src/
│   ├── models/          # Clases de dominio (Cultivo, Parcela, Actividad...)
│   ├── services/        # Lógica de negocio y manejo de CSV
│   ├── ui/              # Menús y control de interacción por consola
│   └── App2.java        # Clase principal con punto de entrada
├── bin/                 # Archivos compilados (.class)
├── docs/
│   ├── diagrama_clases.png
│   └── informe_diseno.pdf
├── cultivos.csv         # Archivo de persistencia de datos
└── README.md
```

## 📚 Funcionalidades

### Gestión de Cultivos

- **Listar cultivos**: Visualización completa del inventario de cultivos
- **Crear cultivos**: Registro de nuevas variedades con sus características
- **Editar cultivos**: Modificación de propiedades de cultivos existentes
- **Eliminar cultivos**: Borrado condicional (sólo si no existen actividades pendientes)

### Gestión de Parcelas

- **Listar parcelas**: Vista detallada incluyendo cultivos asignados
- **Agregar parcelas**: Registro de nuevas unidades de terreno
- **Editar parcelas**: Modificación de propiedades de parcelas existentes
- **Eliminar parcelas**: Borrado condicional (sólo si no hay cultivos activos)
- **Asignar cultivo**: Vinculación de cultivos a parcelas específicas

### Gestión de Actividades

- **Registrar actividades**: Creación de tareas como riego, fertilización o cosecha
- **Listar por cultivo**: Filtrado de actividades por tipo de cultivo
- **Eliminar actividades**: Borrado de tareas programadas
- **Marcar completadas**: Seguimiento del estado de las actividades

### Búsquedas y Reportes

- **Búsqueda avanzada**: Localización de cultivos por nombre o variedad
- **Reportes dinámicos**: Generación de informes de cultivos activos, en riesgo o cosechados

### Persistencia de Datos

- Carga inicial desde archivo CSV
- Guardado automático de cambios al finalizar

## 📊 Diagramas y documentación

### Diagrama de clases

![Diagrama de Clases](docs/diagrama_clases.png)

Para ver el diagrama completo y la documentación detallada, consulte los siguientes archivos:

- [Informe de diseño (PDF)](docs/informe_diseno.pdf)
- [Reflexiones y autoevaluación](docs/reflexiones.pdf)

## 🤔 Reflexiones y aprendizajes

El proyecto ha presentado diversos desafíos técnicos que han sido abordados mediante:

- Aplicación de patrones de diseño adecuados
- Implementación rigurosa de principios SOLID
- Uso estratégico de herramientas de desarrollo

_Nota: La sección de reflexiones incluye detalles sobre el uso de IA como herramienta de apoyo durante el desarrollo._

## 🙏 Agradecimientos

Agradecemos al profesor y ayudante por su orientación durante el desarrollo de este proyecto.

---

© 2025 | Desarrollado para el curso de Lenguajes y Paradigmas
