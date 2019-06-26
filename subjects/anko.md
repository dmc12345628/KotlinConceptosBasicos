# Anko

Es una libreria desarrollada por el equipo de kotlin que nos permite crear vistas sin la necesidad de xml.

Al ser codigo, es más fácil de reutilizar en diferentes layouts.

```kotlin
verticalLayout {
    padding = dip(30)
    editText {
        hint = "Name"
        textSize = 24f
    }
    editText {
        hint = "Password"
        textSize = 24f
    }
    button("Loggin") {
        textSize = 26f
    }
}
```

Además, Anko nos permite funciones de asincronía.

La ventaja de esto es que al impelentarlo en un Activity por ejemplo, uiThread no será llamado si la Activity ya no está visible.

```kotlin
doAsync {
    // Background Thread

    uiThread {
        // UI Thread
    }
}
```

Anko nos da acceso a servicios de una manera más sencilla por medio de properties de extension.

```kotlin
with(context) {
    val nm = notificationManager
    val cm = connectivityManager
    val am = audioManager
    val nfm = nfcManager
}
```

Incluso nos ofrece versatibilidad para crear AlerDialogs.

```kotlin
alert {
    title("Anko alert")
    message("This is an Anko alert")
    okButton { toast("OK!") }
    cancelButton { toast("Cancel") }
}.show()
```

Tambien nos ofrece una utilidad para base de datos.

```kotlin
database.use {
    insert("Person",
        "_id" to 1,
        "name" to "John",
        "surname" to "Smith",
        "age" to 20)
}
```