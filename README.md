
# 🚢 Hundir la Flota – Proyecto POO + Swing

Este es un proyecto de programación en Java orientado a objetos, donde desarrollamos el clásico juego "Hundir la Flota" contra una CPU, usando interfaz gráfica (Swing) y una arquitectura modular.

---

##  Reparto de tareas

| Nombre      | Rol principal                                 |
|-------------|-----------------------------------------------|
| Javi        | Lógica principal + Interfaz (Swing)           |
| Josep       | Modelo (Tablero, Celdas, Barcos)              |
| Jose Luis   | (Turnos, Coordenadas, Estadísticas, Mensajes) |

---

##  Estructura del proyecto

```
src/
│
├── modelo/      → Representación del tablero y sus datos
├── logica/      → Gestión del flujo del juego y reglas
├── vista/       → Interfaz gráfica con Swing
└── Main.java    → Punto de entrada del programa
```

---

##  Flujo de trabajo en GitHub

Seguimos una estructura de ramas simple y profesional para evitar conflictos y mantener el código limpio:

###  Ramas principales:
- `main` → Rama estable (¡no se programa aquí directamente!).
- `develop` → Rama donde se integran todas las nuevas funciones.
- `feature/nombre-tarea` → Ramas individuales por cada funcionalidad.

### 🛠️ Cómo colaborar correctamente:

1. **Clona el repositorio**:  
   `git clone https://github.com/usuario/repositorio.git`

2. **Crea una nueva rama para tu tarea** (nunca programes directo en `develop`):
   ```bash
   git checkout develop
   git checkout -b feature/celda-barco
   ```

3. **Haz tus commits de forma clara**:
   ```bash
   git add .
   git commit -m "feat: implementada la clase CeldaBarco"
   ```

4. **Sube tu rama al repositorio remoto**:
   ```bash
   git push origin feature/celda-barco
   ```

5. **Haz un Pull Request (PR) hacia `develop`**  
   Desde GitHub → Comparar y hacer PR → Asignar revisor (otro compañero)

6. **Cuando esté revisado y funcione**, se hace merge a `develop`.

7. **Sólo cuando todo el juego esté listo**, se hace merge `develop → main`.

---

## 📋 Organización con GitHub Projects (Kanban)

Usamos un tablero estilo **Kanban** para gestionar el trabajo y que todos sepamos qué hacer.

📍 [Enlace al Kanban del proyecto](https://github.com/usuario/repositorio/projects/1)

### Columnas:
- ✅ **To Do**: tareas pendientes (se asignan a personas).
- 🛠️ **In Progress**: tareas que se están haciendo.
- 🔍 **Review**: tareas terminadas a revisar antes de merge.
- 🎉 **Done**: tareas finalizadas y mergeadas a `develop`.

---

##  Buenas prácticas

- Haz commits pequeños y claros (mejor 3 commits cortos que uno gigante).
- Nombra tus ramas de forma descriptiva:  
  Ej: `feature/celda-barco`, `fix/bug-tablero`, `test/estadisticas`
- No subas `.class`, `.idea`, ni carpetas del IDE (usa `.gitignore`).
- Si no sabes cómo hacer algo, **pregunta** antes de romper el repo 😅

---

## 📚Requisitos técnicos

- Java 17+
- Uso de Swing para la GUI
- Código organizado por paquetes (`modelo/`, `logica/`, `vista/`)
- Programación orientada a objetos

---

##  Extra (opcional)

- Añadir sonidos, imágenes o animaciones si sobra tiempo.
- Estadísticas por jugador (disparos, aciertos, %).
- IA con algo más de inteligencia (modo fácil/difícil).