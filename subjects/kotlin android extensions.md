# Kotlin Android extensions

Son como subplugins que vienen en el plugin de Kotlin.

```kotlin
apply plugin: 'kotlin-android-extensions'
```

Una de las funciones principales es la eliminacion definitiva del `findViewById`. Utilizando un import, podemos acceder directamente a los widgets escribiendo el id que les asignemos en el XML.

```xml
...
  <android.support.v7.widget.RecyclerView
      android:id="@+id/recycler"
...
```

```kotlin
import kotlinx.android.synthetic.main.activity_main.*
...
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    recycler.adapter = MediaAdapter(emptyList())
}
```

También se puede acceder a las vistas por medio de otro view. Esto nos es útil al momento de utilizar adaptadores.

```kotlin
import kotlinx.android.synthetic.main.item_recycler.view.*
...
val v = itemView

v.txvMediaTitle.text = item.title
```

La diferencia entre estos dos casos de uso es que si lo utilizamos en una activity, internamente solo se llamará al `findViewById` una sola vez, en cambio en un adaptador, se llamará cada vez que utilicemos la referencia.

Para evitar problemas de rendimiento en vistas muy complejas, es recomendable mandar a llamar primero los `findViewById` y hacer el binding despues.