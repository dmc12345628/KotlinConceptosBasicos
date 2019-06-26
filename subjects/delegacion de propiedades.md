# Delegación de propiedades

En Kotlin podemos delegar la funcionalidad de las propiedades a otra clase.

## Lazy

Con lazy podemos asignar la inicilización de una propiedad para que se ejecute al momento de llamar la propiedad la primera vez.

```kotlin
val recyclerView by lazy {
    findViewById(R.id.recycler) as RecyclerView
}
```

## Observable

Con Observable podemos detectar cuando una propiedad es modificada y asignar una acción para ese evento. La función retorna como argumentos la propiedad misma, el valor anterior y el nuevo valor.

```kotlin
var items: List<MediaItem> by Delegates.observable(items) {
    prop, old, new -> notifyDataSetChanged()
}
```

## Vetoable

Vetoable es parecido, pero este se produce antes de que la propiedad sea modificada. Si la función regresa un verdadero, el valor será asignado.

```kotlin
val positiveNumber by Delegates.vetoable(0) { p, old, new -> new >= 0 }
```