# Interfaces

La diferencia con Java es que les podemos agregar funcionalidad.

Las interfaces pueden tener propiedades.

```kotlin
interface Logger {
    val tag: String

    fun d(message: String) = Log.d(tag, message)
}
```

Para implementarlas hacemos lo siguiente:

```kotlin
class MainActivity : AppCompatActivity(), Logger {
    override tag: String = MainActivity::class.java.simpleName
}
```

Tambien podemos modificar el getter de las propiedades:

```kotlin
val tag: String
    get() = javaClass.simpleName
```

Así, podemos utilizar el Logger para lo siguiente:

```kotlin
...
    d("Hello")
}
```