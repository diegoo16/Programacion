Diego
diegolgns.16_55820
En línea

Hipo
ha añadido a
Diego
al grupo. — 03/03/2026 18:43
Tienes una llamada perdida de
Hipo
que ha durado unos segundos. — 03/03/2026 18:43
Hipo
ha iniciado una llamada que ha durado un minuto. — 03/03/2026 18:43
eldanx
ha iniciado una llamada que ha durado unos segundos. — 03/03/2026 18:44
Hipo
ha iniciado una llamada que ha durado 13 minutos. — ayer a las 18:04
Hipo — ayer a las 19:47
https://teams.microsoft.com/l/message/19:meeting_ZDA4MTZlMWYtZmJmNy00MDFlLWE0MzUtM2Y1ZTUyNjA0OTVi@thread.v2/1772649815343?context=%7B"contextType"%3A"chat"%7D
Microsoft Teams
Imagen
eldanx — ayer a las 20:14
Tipo de archivo adjunto: acrobat
Memoria Sistemas Informaticos - Documentos de Google.pdf
291.96 KB
eldanx — ayer a las 23:17
ES EL UML QUE LO HE ESCRITO MAL SIN QUERER
Imagen
eldanx — 9:34
# ⚽ XTART LEAGUE – Simulador de Ligas de Fútbol en Java

## 📖 Descripción

**XTART LEAGUE** es una aplicación desarrollada en **Java** que simula una competición de fútbol entre distintos equipos pertenecientes a diferentes ligas.
El programa permite al usuario interactuar mediante un **menú en consola**, explorar equipos, visualizar plantillas, simular partidos y consultar la clasificación de la liga.

message.txt
7 KB
﻿
# ⚽ XTART LEAGUE – Simulador de Ligas de Fútbol en Java

## 📖 Descripción

**XTART LEAGUE** es una aplicación desarrollada en **Java** que simula una competición de fútbol entre distintos equipos pertenecientes a diferentes ligas.
El programa permite al usuario interactuar mediante un **menú en consola**, explorar equipos, visualizar plantillas, simular partidos y consultar la clasificación de la liga.

El sistema incluye tres competiciones principales:

* 🇪🇸 **Delux League**
* 🏴 **Premier League**
* 🇫🇷 **Ligue One**

Cada liga está compuesta por equipos con jugadores reales o inspirados en jugadores actuales, junto con entrenadores, patrocinadores y estadísticas de temporada.

El programa simula resultados de partidos basándose en la **media de los jugadores del equipo y factores aleatorios**, generando goleadores, MVP del partido y actualización de estadísticas.

---

# 🚀 Características principales

✔ Simulación automática de partidos
✔ Gestión de ligas y equipos
✔ Plantillas completas con jugadores y posiciones
✔ Sistema de clasificación con puntos y estadísticas
✔ Generación de MVP del partido
✔ Sistema de patrocinadores
✔ Menú interactivo por consola
✔ Colores en terminal para mejorar la experiencia del usuario

---

# 🧱 Estructura del Proyecto

```
proyectoLiga
│
├── competicion
│   ├── Equipo.java
│   ├── Jornada.java
│   ├── Liga.java
│   ├── Partido.java
│   ├── Patrocinador.java
│   ├── Posicion.java
│   └── Premio.java
│
├── personas
│   ├── Persona.java
│   ├── Jugador.java
│   ├── Entrenador.java
│   └── Arbitro.java
│
└── principal
    ├── Main.java
    └── Menu.java
```

---

# 🧩 Explicación de las Clases

## ⚽ Competición

### `Liga`

Representa una competición completa.

Contiene:

* Información de la liga
* Lista de equipos
* Lista de jornadas

Funciones principales:

* Añadir equipos
* Añadir jornadas
* Gestionar la temporada

---

### `Equipo`

Representa un equipo de fútbol.

Atributos principales:

* Nombre
* Ciudad
* Estadio
* Entrenador
* Jugadores
* Patrocinador
* Estadísticas de temporada

Funciones importantes:

```java
calcularMediaEquipo()
```

Calcula la media de nivel de los jugadores del equipo.

```java
resetEstadisticas()
```

Reinicia las estadísticas de la temporada.

---

### `Partido`

Simula un partido entre dos equipos.

Incluye:

* Equipo local y visitante
* Árbitro
* Fecha
* Resultado
* MVP del partido

Función clave:

```java
simularPartido()
```

La simulación se basa en:

* Media del equipo
* Factor aleatorio
* Posición de los jugadores para elegir goleadores

También actualiza:

* Goles
* Clasificación
* Estadísticas del equipo

---

### `Jornada`

Representa una jornada de la liga.

Contiene:

* Número de jornada
* Lista de partidos

---

### `Patrocinador`

Representa un patrocinador del equipo.

Incluye:

* Nombre
* País
* Cantidad de dinero aportada

---

### `Premio`

Representa premios de la liga como:

* MVP
* Máximo goleador
* Mejor jugador

---

### `Posicion (Enum)`

Enum que define las posiciones de los jugadores:

```java
PORTERO
DEFENSA
CENTROCAMPISTA
DELANTERO
```

---

# 👤 Personas

## `Persona`

Clase abstracta base para todas las personas del sistema.

Atributos:

* nombre
* apellido
* nacionalidad

---

## `Jugador`

Extiende `Persona`.

Incluye:

* dorsal
* posición
* media
* goles
* equipo
* fecha de nacimiento

Funciones:

```java
sumarGol()
```

Incrementa el número de goles del jugador.

---

## `Entrenador`

Representa al entrenador del equipo.

Extiende `Persona`.

---

## `Arbitro`

Representa al árbitro de los partidos.

Extiende `Persona`.

---

# 🖥 Sistema de Menú

El programa se ejecuta mediante un **menú interactivo en consola** que permite:

1️⃣ Elegir la liga
2️⃣ Ver equipos
3️⃣ Consultar partidos
4️⃣ Ver premios
5️⃣ Ver clasificación
6️⃣ Cambiar de liga

El menú utiliza **colores ANSI** para mejorar la experiencia visual.

---

# ▶ Cómo ejecutar el proyecto

## Requisitos

* Java **JDK 17 o superior**
* IDE recomendado:

    * IntelliJ IDEA
    * Eclipse
    * VS Code

---

## Ejecutar desde el IDE

1. Clonar el repositorio o descargar el proyecto
2. Abrir el proyecto en tu IDE
3. Ejecutar la clase:

```
Main.java
```

Ruta:

```
proyectoLiga.principal.Main
```

---

# 🎮 Ejemplo de flujo del programa

```
========================================
XTART LEAGUE
========================================

El fútbol nunca ha sido solo un juego
Es constancia, sacrificio y momentos inolvidables

ELIGE LA LIGA

1. Delux League
2. Premier League
3. Ligue One
```

Después podrás:

* Explorar equipos
* Ver jugadores
* Simular partidos
* Consultar la clasificación

---

# 🔮 Posibles mejoras futuras

El proyecto podría ampliarse con:

* 📅 Calendario completo de jornadas
* 🧠 IA más avanzada para simulación de partidos
* 💰 Sistema de fichajes
* 📊 Estadísticas de jugadores más detalladas
* 🏆 Sistema completo de premios
* 💾 Guardado de partidas
* 🖥 Interfaz gráfica (JavaFX / Swing)

---

# 🛠 Tecnologías utilizadas

* **Java**
* **Programación Orientada a Objetos (POO)**
* **Colecciones de Java (ArrayList, List)**
* **LocalDate**
* **Random**
* **ANSI Console Colors**

---

# 👨‍💻 Autor

Proyecto desarrollado como práctica de programación en **Java orientada a objetos**.

Autor:
**Álvaro**

---

# 📜 Licencia

Proyecto educativo.
Uso libre para aprendizaje y mejora.

---

# ⚽ “El fútbol no es solo un deporte… es una historia que se escribe en cada partido.”
message.txt
7 KB