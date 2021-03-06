# Recuperacion de Información - Modelo del Espacio Vectorial

Este modelo entiende que los documentos pueden expresarse en función de
unos vectores que recogen la frecuencia de aparición de los términos en
los documentos. Los términos que forman esa matriz serían términos no vacíos,
es decir, dotados de algún significado a la hora de recuperar información y 
por otro lado, estarían almacenados en formato “stemmed”
(reducidos los términos a una raíz común, tras un procedimiento de
aislamiento de la base que agruparía en una misma entrada varios términos). 

Si nuestro SRI contiene los siguientes cuatro documentos: 

+ D1:el río Danubio pasa porViena,su color es azul 
+ D2:el caudal de un río asciende enInvierno 
+ D3:el ríoRhin yel ríoDanubio tienen mucho caudal 
+ D4:si unrío es navegable,es porque tienemucho caudal

![Minion](https://github.com/fresko/IR_lab1/blob/master/img/matriz_td.png)

 Por medio de un proceso denominado stemming
 + eliminar espacios
 + elimina los determinantes, preposiciones y verbos (“el”,“pasa”,“por”,etc.)

Para dar respuesta a una determinada pregunta 
 + Traducir pregunta en su vector de terminos
 + **“¿cuál es el caudal del río Danubio?”**
   su vector de términos sería **Q = (1,1,0,0,0,1,0,0,0)**. 

**Matriz vector de peso**

![Minion](https://github.com/fresko/IR_lab1/blob/master/img/Matriz_peso.png)

**Calculo Similitudes**

![Minion](https://github.com/fresko/IR_lab1/blob/master/img/calculosimilitudes.png)

## Desarrollo del Laboratorio 

Desarrollo en Java 8 con metodos que realizan el proceso stemming

``` java
          //Lee el contenido (los archivos) del directorio 
           File[] vctDocuments = ir.readDirectory(DIR_DOC) ;
           for(File obj:vctDocuments)
            //lee el contenido del archivo y alamacena en lista la palabras claves 
           lstWord.addAll(ir.keyWord(ir.readText(obj.getAbsolutePath())));
           //java 8 forma de limpir los repetidos de la lista , gnera nueva lista con los terminos 
           lstWordClear = lstWord.stream().distinct().collect(Collectors.toList());
          
           //se arma la matrix documentos vs terminos
           matrixDocWord = ir.matrixDocWord(vctDocuments,lstWordClear);
``` 

**Respuesta por consola**


```
run:
 | r�o | Danubio | Viena | color | azul | caudal | Invierno | Rhin | navegable
1 1 1 1 1 0 0 0 0 
1 0 0 0 0 1 1 0 0 
2 1 0 0 0 1 0 1 0 
1 0 0 0 0 1 0 0 1 
Realiza una Pregunta :


```
