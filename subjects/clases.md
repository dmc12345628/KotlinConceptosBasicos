# Clases

```kotlin
class Developer : Person()
open class Person

val dev: Person = Developer()
```

En Kotlin, no necesitamos que todo esté en una clase.

Para declarar el constructor, sólo necesitamos agregar los parentesis.

```kotlin
class Person(name: String, age: Int)
```

Las clases son cerradas por defecto. Para heredar necesitamos la palabra reservada open

```kotlin
open class Person(name: String, age: Int)

class Developer(name: String) : Person(name, 20)
```

Tambien podemos crear clases abstractas, las cuales serán siempre públicas.

```kotlin
abstract class Person(name: String, age: Int)
```

## Propiedades

Sustituyen los getters y los setters.

Tenemos varias opciones para declarar nuestras propiedades.

Si la asignación es directa podemos hacer lo siguiente:

```kotlin
open class Person(name: String, age: Int) {
    val name: String = name
    val age: String = age
}
```

Tambien podemos definir nuestras propiedades en el constructor:

```kotlin
open class Person(val name: String, val age: Int)
```

En el caso de querer cambiar el getter o setter, hacemos lo siguiente:

```kotlin
open class Person(name: String, val age: Int) {
    var name: String = name
        get() = "Name: $field"
        set(value) {
            if (value != field) {
                field = value
            }
        }
}
```

Los parametros de los constructores y los métodos pueden tener valores por defecto.

```kotlin
open class Person(name: String = "Peter", val age: Int = 10) {
    ...
}
```

Incluso podemos indicar qué argumento deseamos cambiar:

```kotlin
val person = Person(age = 20)
```

O cambiar el orden de los argumentos:

```kotlin
val person = Person(age = 20, name = "Thomas")
```

Si necesitamos varios constructores, podemos hacer lo siguiente:

```kotlin
class Developer : Person {
    constructor(name: String) : super(name)
    constructor(age: Int) : super(age = age)
}
```

## Data classes

Todas las propiedades deben de estar definidas en el constructor.

Además, podemos desestructorarlas en variables.

```kotlin
data class MediaItem(val title: String, val thumbUrl: String)

val mediaItem = MediaItem("Title", "url")
val (title, url) = mediaItem
```

Podemos comparar objetos directamente haciendo lo siguiente:

```kotlin
data class Person(val name: String, val age: Int)

val p1 = Person("John", 20)
val p2 = Person("John", 20)

p1 == p2
$ true

p1 === p2
$ false
```

Incluso podemos obtener una copia directa de un objeto haciendo lo siguiente:

```kotlin
val myCopy = p1.copy(age = 30)
```