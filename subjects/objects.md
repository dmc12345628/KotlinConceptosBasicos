# Objects

Los object son los equivalentes a los singletons en java, pero implementados de tal forma que son seguros en entornos multi-hilo.

```kotlin
object MySqlOpenHandler : SQLiteOpenHelper(App.instance,...) {
    override fun onCreate(db: SQLiteDatabase?) {}

    override fun onUpgrade(db: SQLiteDatabase?. oldVersion...)
}
```

## funcionalidades de los objects

En kotlin, lo equivalente a static es companion object, que es un objeto que comparte propiedades con las clases del mismo tipo.

```kotlin
class User {
    companion object {
        var credential: String = "- - -"
    }
    ...
}
```

Tambien podemos utilizar los objects como clases anonimas.

```kotlin
button.setOnClickListener(object : View.OnClickListener {
    override fun onClick(v: View?) { ... }
})
```

Por último, podemos crear objetos de manera dinamica.

```kotlin
val x = object {
    val myInt = 20
}
```