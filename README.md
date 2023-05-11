# InstaLeap

ae trata de una aplicación Android que muestra un conjunto de películas y series, distribuidas en tres pantallas distintas. 
En la pantalla principal, se encuentra un listado maestro con una descripción de cada elemento. Además, al seleccionar un elemento, se puede acceder a una pantalla emergente de detalles, donde se muestra información más detallada sobre el elemento seleccionado.

## Descripción

El proyecto implementa una arquitectura MVVM (Modelo-Vista-VistaModelo) potenciada con Clean, utilizando las siguientes tecnologías y bibliotecas:

- Kotlin: Lenguaje de programación utilizado para desarrollar el proyecto.
- MVVM: Arquitectura que separa la lógica de presentación de la lógica de negocio, facilitando la escalabilidad y el mantenimiento del proyecto.
- Clean: Implementación de los principios de Clean Architecture para lograr una estructura modular y mantenible del código.
- StateFlow: Utilización de StateFlow de Kotlin para la comunicación entre el ViewModel y la Vista, permitiendo la propagación de cambios de estado de manera eficiente.
- Coroutines: Empleo de Coroutines para facilitar la programación asíncrona y el manejo de hilos de ejecución de manera sencilla y concisa.
- Hilt: Uso de Hilt para la inyección de dependencias, lo que simplifica la gestión y configuración de las dependencias del proyecto.

![image1](https://github.com/vcode09/instaleap/assets/72045621/446721eb-8303-48e3-8bbd-7d0709e2c9f2)


## Dependencias

Test
JUnit: Framework de pruebas unitarias para Java.
testImplementation 'junit:junit:4.13.2'
testImplementation 'org.junit.jupiter:junit-jupiter'

Mockito: Biblioteca de pruebas que permite crear objetos simulados (mocks) para facilitar las pruebas.
testImplementation 'com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0'
testImplementation 'org.mockito:mockito-inline:3.3.0'

Kotlin Coroutines Test: Biblioteca de pruebas para Kotlin Coroutines.
testImplementation 'org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.5'

Core Testing: Biblioteca de pruebas para componentes de arquitectura de Android.
testImplementation  'androidx.arch.core:core-testing:2.2.0'

Android Test Ext JUnit: Extensión de JUnit para pruebas instrumentadas de Android.
androidTestImplementation 'androidx.test.ext:junit:1.1.5'

Espresso Core: Biblioteca de pruebas de interfaz de usuario para aplicaciones de Android.
androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'

## Contribución
Los ivito a visitar en @kawaiidevs, en donde intentamos crear comunidad y compartir conocimiento. 
@vass_code
