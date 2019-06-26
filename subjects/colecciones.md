# Colecciones y rangos

Son similares que las colecciones en Java salvo que son mucho más poderosas. Vienen con una gran cantidad de operaciones incluidas que nos permiten manipularlas de acuerdo a nuestras necesidades.

```kotlin
items.filter { it.Type == PHOTO}
    .orderBy { it.title }
    .map { it.thumbUrl }
```