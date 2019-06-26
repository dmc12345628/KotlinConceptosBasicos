# Funciones de extensión

Sustituyen las funciones de utilidad que necesitamos en nuestros proyectos. Un ejemplo es la siguiente funciones:

En Java, para crear un Toast, normalmente hariamos lo siguiente:
```java
public void showToast(String message, int duration) {
    Toast.makeText(this, message, duration).show();
}
```

En cambio, en Kotlin podemos hacer lo siguiente:
```kotlin
fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}
```

## Ejemplos

```kotlin
fun Context.toast(message: String, duration: Int = LENGTH_SHORT) = Toast.makeText(this, message, duration).show()

fun RecyclerView.ViewHolder.toast(message: String, duration: Int = LENGTH_SHORT) = itemView.context.toast(message, duration)

fun ViewGroup.inflate(layoutRes: Int): View = LayoutInflater.from(context).inflate(layoutRes, this, false)

fun ImageView.loadUrl(thumbUrl: String) = Picasso.get().load(thumbUrl).into(this)
```

## Funciones reified

Nos permiten utilizar los tipos genéricos dentro de las funciones.

```kotlin
inline fun <reified T: View> View.find(idRes: Int): T = findViewById(idRes)
```

