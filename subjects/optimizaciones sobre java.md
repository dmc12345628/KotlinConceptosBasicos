# Optimizaciones sobre Java

Kotlin convierte los getters y setters a properties. Además, las interfaces con una sola función las mapea a Lambdas.

En Java
```java
view.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        toast("Message")
    }
})
```

En Kotlin
```kotlin
view.setOnClickListener { toast("Message") }
```

