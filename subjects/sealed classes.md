# Sealed classes

Son clases que tienen un número definido de subclases. Esto nos sirve para que los when sirvan igual que los enumerados.

```kotlin
sealed class Operation {
    class Add(val value: Int) : Operation()
    class Subtract(val value: Int) : Operation()
    class Multiply(val value: Int) : Operation()
    class Divide(val value: Int) : Operation()
}
```