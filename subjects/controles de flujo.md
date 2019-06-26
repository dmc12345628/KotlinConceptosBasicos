# Control de flujo 

## WHEN

Similar a switch, pero no está limitado a tipos de datos primitivos.

```kotlin
when(view) {
    is TextView -> view.text = "Hello"
    is ViewGroup -> view.childCount
}
```

También podemos asignar `when` o `if` a una variable.

```kotlin
val stringType = when(view) {
    is TextView -> "TextView"
    is ViewGroup -> "ViewGroup"
    else -> "unknown"
}
```