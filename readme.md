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

