# Lambdas

Es una representación de funciones. Dentro de una lambda, no necesitamos escribir una función.

Ejemplo:

```kotlin
sum: { x, y -> x + y }
```

Las lambdas pueden ser utilizadas como tipo de dato.

Ejemplo:

```kotlin
val sum = { a: Int, b: Int -> a + b }
applyOp(2, 3, sum) // 5

val mul = { a: Int, b: Int -> a * b }
applyOp(2, 3, mul) // 6

applyOp(3, 2) { a, b -> a - b }

fun applyOp(x: Int, y: Int, f: (Int, Int) -> Int): Int = f(x, y)
```

Las lambdas sustituyen la creación de interfaces para los callbacks de funciones asyncronas.

Por ejemplo nos podemos evitar esto:
```kotlin
interface Callback {
    fun onFinish(result: String)
}

fun doAsync(x: Int, callback: Callback) {
    // do something with x
    callback.onFinish("finished")
}
```

De la siguiente manera:
```kotlin
fun doAsync(x: Int, f: (result: String) -> Unit) {
    // do something with x
    f("finished")
}
...
doAsync(20) { result -> print(result)}
```

Cuando una lambda sólo tiene un elemento por parámetro podemos utilizar la paralabra reservada `it`

```kotlin
doAsync(20) { print(it) }
...
recycler.setOnItemClickListener { print(it) }
```

Si el argumento es un data class, la podemos desestructurar.

```kotlin
doAsync(20) { (arg1, arg2) -> print("$arg1, $arg2") }
```

Incluso, si hay parámetros que no usamos, los marcamos con `_`

```kotlin
doAsync(20) { (arg1, _) -> print(arg1) }
```

## Lambdas + Genéricos + Extension funciones: Función Apply

Qué tal si aplicamos todo lo aprendido he intentamos comprender cómo funciona la función apply?

La función apply funciona desde un objeto creado por nosotros, inicializa el objeto con los valores que le asignemos y retorna el mismo objeto con los valores asignados.

Vamos a crear un apply dos para enteder cómo funciona el apply original.

Apply es una función de extensión sobre cualquier objeto, así que nuestra función apply2 la escribiriamos de la siguiente manera:

```kotlin
fun <T> T.apply2() {
    // TODO
}
```

El equivalente de Object de Java en Kotlin es Any, pero no hace falta que indiquemos que T es de tipo Any, pues el compilador lo deduce automaticamente.

El siguiente paso es retornar el tipo Any, pues sabemos que el valor de retorno es del mismo tipo que el valor que lo llama.

```kotlin
fun <T> T.apply2(): T {
    // TODO
}
```

Ahora, para conseguir el efecto de la inicialización, notemos que es una función que no recibe nada y no regresa nada. Así, lo podemos escribir de la siguiente manera:

```kotlin
fun <T> T.apply2(f: () -> Unit): T {
    // TODO
}
```

Pero todavía nos falta que el bloque se comporte como una función de extensión. Para ello lo único que tenemos que hacer es agregar la `T` al tipo de dato de la función:

```kotlin
fun <T> T.apply2(f: T.() -> Unit): T {
    // TODO
}
```

Finalmente, lo único que necesitamos hacer es que nuestro apply2 llame a `f` y devuelva `this`:

```kotlin
fun <T> T.apply2(f: T.() -> Unit): T {
    f()
    return this
}
```
