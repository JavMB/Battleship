# Hundir la Flota 

Este es un proyecto de programación en Java orientado a objetos, donde desarrollamos el clásico juego "Hundir la Flota" contra una CPU, usando interfaz gráfica (Swing) y una arquitectura modular.

![image](https://github.com/user-attachments/assets/0f85128b-69b5-42fa-8e53-bf1d60d3b770)

![image](https://github.com/user-attachments/assets/c05f0f4e-1d4b-41b9-ac52-40f65c5f3297)


## Descripción del Proyecto

Este juego fue nuestro proyecto final para el curso de 1º DAM. Aunque las interfaces gráficas no nos dio tiempo de darlo , decidimos aceptar el reto y crear una implementación con interfaz gráfica en lugar de limitarnos a la consola.

### Características Destacadas

- **Interfaz Gráfica con Swing**: Desarrollada tras aprender por nuestra cuenta durante las vacaciones de Pascua, después de practicar con proyectos como Snake y Game of Life.

- **CPU Inteligente**: Implementamos un patrón Strategy con una interfaz que permite que la CPU utilice diferentes estrategias de juego:
  - Modo aleatorio para disparos iniciales
  - Modo "cazar y hundir" cuando detecta un impacto, centrando sus disparos alrededor de las posiciones exitosas

- **Sistema de Audio**: El juego incluye efectos de sonido y música de fondo para mejorar la experiencia de juego.

- **Generación Aleatoria de Barcos**: Algoritmo que posiciona barcos de diferentes tamaños en el tablero de forma aleatoria.

## Estructura del Proyecto

```
src/main/java/com/javier/
│
├── audio/         → Sistema de reproducción de efectos y música
├── controlador/   → Gestión del flujo del juego y reglas
├── modelo/        → Representación del tablero, barcos y jugadores
├── vista/         → Interfaz gráfica con Swing
└── Main.java      → Punto de entrada del programa
```

## Tecnologías Utilizadas

- **Java**: Lenguaje principal con programación orientada a objetos
- **Swing**: Biblioteca gráfica para la interfaz de usuario
- **Gradle**: Sistema de gestión de dependencias y construcción

## Aprendizaje y Desarrollo

Este proyecto representa un gran aprendizaje, ya que nos animamos a implementar características que iban más allá, como:

1. Interfaces gráficas en Java (Swing)
2. Patrones de diseño (Strategy)
3. Sistemas de audio en aplicaciones Java
4. Algoritmos de inteligencia artificial para la CPU



