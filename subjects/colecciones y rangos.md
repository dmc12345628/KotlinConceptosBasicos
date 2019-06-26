# Colecciones

Son similares que las colecciones en Java salvo que son mucho más poderosas. Vienen con una gran cantidad de operaciones incluidas que nos permiten manipularlas de acuerdo a nuestras necesidades.

```kotlin
items.filter { it.Type == PHOTO}
    .orderBy { it.title }
    .map { it.thumbUrl }
```

# Rangos

Son como una colección de elementos de un valor inicial a un valor final.

## Tipos

```kotlin
for (i in 1..4) print(i)

(1..4).forEach(::print)

(1 until 4).forEach(::print)

(4 downTo 1).forEach(::print)

val x: String = "c"

val y = when (x) {
    in ("a".."e") -> 1
    in ("f".."z") -> 2
    else -> 3
}
```

### infix

until y downTo son funciones de extensión infix. Lo que hace es permitir que dado un objeto el mismo se manipule con el objeto a la derecha.
```kotlin
fun Int.addition(other: Int) = this + other

public fun <A, B> A.to(that: B): Pair<A, B> = Pair(this, that)
```