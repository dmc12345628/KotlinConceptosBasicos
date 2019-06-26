# Funciones ninja

## With

Nos permite aplicar acciones como si estuvieramos dentro del objeto. Así, las llamadas a `this` hacen referencia al objeto del `with`

```kotlin
with(itemView) {
    txvMediaTitle.text = item.title
    imvMediaImage.loadUrl(item.thumbUrl)

    imvPlay.visibility = when (item.type) {
        PHOTO -> View.GONE
        VIDEO -> View.VISIBLE
    }
}
```

Y así como la mayoría de las funciones en Kotlin, podemos asignarlas a una variable. De esta manera, with retornará lo que retorne la última línea del cuerpo.

## Apply

Similar a with, sólo que esta es una función de extensión. Funciona como un inicializador que retorna un objeto de la misma clase.

```kotlin
val textView = TextView(this).apply {
    text = "apply rules!"
    textSize = 20f
    setOnClickListener {
        toast("Hello")
    }
}
```