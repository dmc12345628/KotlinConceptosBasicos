# Tratamiento de nulos

Kotlin es seguro frente a nulos, quiere decir que el compilador no ejecutará el código si detecta algún posible nulo.

```kotlin
val item: MediaItem? = null
item.print() // does not run
```

Podemos evitar lo anterior de la misma manera que en otros lenguajes:

```kotlin
if (item != null) {
    item.print()
}
```

Pero en Kotlin, esto puede ser más simple:

```kotlin
item?.print()
```

Aún así, si quisieramos asegurarle al compilador que nunca será nulo, podemos hacerlo de la siguiente manera:

```kotlin
item!!.print()
```

Ahora bien, kotlin tiene un operador que nos permite asignar un valor por defecto en el caso de que alguna variable sea nula.

```kotlin
val myInt: Int? = null

val myLong: Long = myInt?.toLong() ?: 0
```