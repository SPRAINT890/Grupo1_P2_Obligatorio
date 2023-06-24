# Informe Obligatorio Progamación2
# Gaspar Morales y Juan Kowalczik

## Índice

1. [Instalacion del Programa](#instalacion-del-programa)
2. [Diagrama UML de Clases](#diagrama-uml-de-clases)
3. [Descripción de los Procesos](#descripción-de-la-carga-de-datos)
4. [Medición de Eficiencia de la Aplicación](#medicion-de-eficiencia-de-la-aplicación)



## Instalacion del Programa

1. Clonar el [repo del obligatorio](https://github.com/SPRAINT890/Grupo1_P2_Obligatorio.git)
2. En Project Structure, marcar el uso del SDK 17 y Language level en SDK Default ![Project_Structure](readme/Project_Structure.png)
3. Agregar a dependencias la libreria Apache CSV Reader 1.9 ![Dependenci Apache CSV Reader 1.9](readme/Dependencies.png)
4. Clonar [repo de tads](https://github.com/SPRAINT890/Grupo1_P2_Tads.git), a la raiz y marcar src como Source Root
5. Extraer los csv y el txt, en la raiz

El projecto nos deberia quedar algo parecido a esta imagen 
![Tads_Java](readme/Tads_Java.png)

## Diagrama UML de Clases
![Diagrama_UML](readme/uml.png)

## Descripción de la Carga de Datos
para cargar los datos del CSV se debe dar valor a una constante que define al iniciar el progrma cual de los 2 archivos se desea cargar, para leer el csv se utilizo la libreria **Apache CSV** y se creo una funcion `load_csv(){...}`. 
Esta funcion se encarga de leer, crear las entidades para cada fila del csv y guardar en un hashtable cerrado para el posterior uso en el programa, tambien maneja algunas condiciones de mal formateo del csv, ya que durante el desarrollo del obligatorio nos dimos cuenta que hay datos que no tienen valor o tienen valores distinto al estipulado, por esta razon hay algunos tweets que no se registran por falta de datos.
Por otro lado hay otra funcion llamada `loadDrivers(){...}` la cual se encarga de leer por linea el archivo txt y guardar en 2 variables el nombre de cada piloto, para el caso de **Nyck de Vries** se limita el spliter para que solo parta los espacios en 2. posteriormente se crea una entidad driver, y se guarda en un array.


## Medicion de eficiencia de la aplicación

### Programa Usados
- **Intellij Profiler**

### Specs usadas para las mediciones
- **Procesador** Ryzen 7 5700g
- **Memoria ram** 16GB Dual Channel

### Resultados
- Idle del programa
    - **Descripcion:** Consumo del programa despues de cargar todos los datos del csv
    - **Memoria RAM consumida:**  730 MB
    - **Tiempo de ejecución promedio:**  5s


- Funcion 1
    - **Descripcion:** Listar los 10 pilotos más mencionados
    - **Memoria RAM consumida:**  881 MB
    - **Tiempo de ejecución promedio:**  2s


- Funcion 2
    - **Descripcion:** Top 15 usuarios con más tweets
    - **Memoria RAM consumida:**  891 MB
    - **Tiempo de ejecución promedio:**  0s


- Funcion 3
    - **Descripcion:** Top 15 usuarios con más tweets
    - **Memoria RAM consumida:**  893 MB
    - **Tiempo de ejecución promedio:**  0s


- Funcion 4
      - **Descripcion:** Hashtag más usado para un día dado
      - **Memoria RAM consumida:**  921 MB
      - **Tiempo de ejecución promedio:**  0s


- Funcion 5
  - **Descripcion:** Top 7 cuentas con más favoritos
  - **Memoria RAM consumida:**  931 MB
  - **Tiempo de ejecución promedio:**  145s


- Funcion 6
    - **Descripcion:** Cantidad de tweets con una palabra o frase específicos
    - **Memoria RAM consumida:**  1157 MB
    - **Tiempo de ejecución promedio:**  0s
