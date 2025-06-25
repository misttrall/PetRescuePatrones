# 🐾 PetRescue

**PetRescue** es una aplicación de consola desarrollada en Java que simula un sistema de gestión de adopciones para mascotas en un refugio.

---

## 📋 Descripción del Sistema

PetRescue resuelve el problema de organización y comunicación entre refugios de animales y potenciales adoptantes. Permite:

- Registrar mascotas disponibles para adopción.
- Registrar usuarios interesados en adoptar mascotas según especie.
- Notificar automáticamente a los usuarios cuando hay coincidencias.
- Listar, buscar y adoptar mascotas dentro del sistema.

El sistema promueve una gestión clara y automatizada de los procesos comunes en refugios.

---

## 🧩 Justificación de Patrones de Diseño

### ✅ Singleton – Refugio.java
**¿Por qué?**: Se requiere una única fuente centralizada para gestionar mascotas, usuarios y adopciones.  
**¿Cómo?**: La clase Refugio implementa un método estático `getInstancia()` que devuelve siempre la misma instancia.  
**¿Dónde?**: Usado desde el `Main.java` y otras clases para acceder de forma global y segura al refugio.

---

### ✅ Adapter – MascotaAdapter.java
**¿Por qué?**: Se necesita mostrar información de mascotas en un formato legible, sin modificar la clase `Mascota`.  
**¿Cómo?**: Se crea una clase adaptadora que implementa la interfaz `Exportable`, proporcionando el método `exportar()`.  
**¿Dónde?**: Utilizado al listar mascotas para convertirlas en un formato adecuado para salida en consola.

---

### ✅ Iterator – IteradorMascotas.java
**¿Por qué?**: Se necesita recorrer la colección de mascotas sin exponer su implementación interna.  
**¿Cómo?**: Se define una interfaz de iterador y su implementación concreta para navegar la lista de mascotas.  
**¿Dónde?**: Usado en los métodos de búsqueda, listado y notificación a usuarios.

---

### ✅ Observer – UsuarioInteresado.java
**¿Por qué?**: Se requiere notificar automáticamente a los usuarios interesados cuando se registre una mascota de su preferencia.  
**¿Cómo?**: Los usuarios se suscriben como observadores del refugio y reciben notificaciones según especie.  
**¿Dónde?**: Activado cuando se registra una nueva mascota o al suscribir un nuevo usuario.

---

## ▶️ Instrucciones de Compilación y Ejecución

1. Asegúrate de tener instalado:
   - Java JDK 8 o superior
   - Un editor como Visual Studio Code con extensión Java

2. Estructura recomendada:
```
PetRescue/
├── README.md
├── /src
│   └── /main
│       └── /java/com/patrones/
│           ├── Main.java
│           └── /modelo/
│               ├── Refugio.java
│               ├── Mascota.java
│               ├── UsuarioInteresado.java
│               ├── Interesado.java
│               ├── Exportable.java
│               ├── MascotaAdapter.java
│               ├── IteradorMascotas.java
│               ├── IteradorMascotasConcreto.java
```

3. Para ejecutar:
   - Abre el proyecto en VS Code.
   - Ejecuta `Main.java`.

---

## 🧪 Ejemplo de Uso

```
1. Registrar mascota
Nombre: Max
Especie: Perro

4. Suscribir usuario interesado
Nombre del usuario: Juan
Preferencia: Perro

> Juan fue notificado de un(a) Perro llamado Max
```

---

## 📌 Notas Finales

- Las adopciones quedan registradas con el nombre del adoptante.
- Las notificaciones funcionan en ambos sentidos: usuario antes o después de registrar mascota.
- El sistema es modular, extensible y mantiene bajo acoplamiento entre componentes.
