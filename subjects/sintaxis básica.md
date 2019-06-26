# Funciones

La declaración de funciones se realiza de la siguiente manera.

```kotlin
// common
fun myFunction(myInt: Int) {
    return 2 * myInt
}
```

Las funciones pueden inferir en su valor de retorno

```kotlin
// Expression body
fun myFunction(myInt: Int) = 2 * myInt
```

Si una función no regresa nada, en realidad regresa Unit. El void de Java no existe en Kotlin.

# Variables

Las variables pueden ser mutables o inmutables.

```kotlin
val a: Int = 1  // Asignación inmediata
val b = 2   // `Int` es inferido
val c: Int  // El tipo es requerido cuando no se asigna un valor
c = 3       // Asignación diferida
```

Las variables mutables se declaran utilizando el keyword `var`.

```kotlin
var x = 5
x += 1
```

# String templates

```kotlin
var a = 1
// Nombres simples en templates:
val s1 = "a is $a" 

a = 2
// Expresión arbitraria:
val s2 = "${s1.replace("is", "was")}, but now is $a"
```

# Más información de sintaxis

La sintaxis básica de kotlin se encuentra en el siguiente [link](https://kotlinlang.org/docs/reference/basic-syntax.html)
