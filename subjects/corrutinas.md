# Corrutinas

Con las corrutinas puedes escribir codigo asíncrono, que tradicionalmente se hacía utilizando el patrón callback, pero usando un estilo secuencial. El valor de retorno de una función proporcionará el resultado de la llamada asíncrona.

Las corrutinas son como los hilos, pero mejor, puesto que puedes escribir código asíncrono de forma secuencial. Además, podemos ejecutar varias corrutinas en un mismo hilo.

Las corrutinas se basan en funciones de suspención, las cuales pueden suspender ela ejecución de la corrutina y permitirla recuperar la ejecución una vez que termina. Las corrutinas son un lugar seguro donde las funciones de suspención (normalmente) no bloquearán el hilo actual.

## Funciones de suspención

Son funciones que bloquean la ejecución de la corrutina y que pueden ejecutarse en un hilo distinto. Una vez que finalizan la tarea, devuelven el flujo a la corrutina principal. 

```kotlin
suspend fun suspendingFunction(): Int {
    // Long running task
    return 0
}
```

Las funciones de suspención sólo se pueden utilizar dentro de una corrutina.

### WithContext

Es una función de suspención que nos permite cambiar de contexto de forma sencilla. Nos permite movernos del hilo principal a un hilo secundario.

## CoroutineContext

Cada corrutina tiene un contexto de ejecución específico. Este contexto es, a nivel lógico, es una especie de mapa que definen la configuración de ejecución de la corrutina.

El más importante sería el `Dispatcher`

### Dispatcher

Es una clase que extiende de CoroutineContext y que especifícan el hilo en el que la corrutina se ejecuta.

Los dispatcher se pueden proporcionar explícitamente o mediante un Scope.

En kotlin tenemos cuatro tipos de dispatchers.

#### Default

El cual se utiliza para tareas intensivas en CPU, como procesamiento de datos, algoritmos...

#### IO

Se utilizará para operaciones de entrada y salida.

#### Unconfined

Para corrutinas que nos da igual en donde se ejecutan.

#### Main

Específico para librerías que utilizan UI.

## CoroutineBuilder

### runBlocking

Bloquea el hilo actual para ejecutar las corrutinas. Útil en cuestiones de testing. Lo que hace es impedir que la función finalice hasta que se termine la función de suspención y se ejecute el assert.

### launch

No bloquea el hilo principal (Si usamos el dispatchers adecuados). Es el builder principal, el cual devuelve un Job.

### Async

Necesita una corrutina padre. Inicia una tarea en segundo plano sin bloquear la corrutina, porque no es una función de extensión. Podemos cambiar el comportamiento utilizando el argumento start.

Devuelve un objeto Deferred(que extiende de Job) pero tiene una función llamada await, la cuál es una función de suspención que podemos llamar para bloquear la ejecución. Cuando necesitemos el resultado de ese async, esperará hasta que termine, y si ya temrinó, lo devolverá.

## Jobs

Es el objeto devuelto por una corrutina. Nos permite cancelar la corrutina o esperar a que todas se terminen. Puede tener jobs padres; si un padre es cancelado, los hijos también lo serán.

### join()

Es una función de suspención espera a que todos los trabajos hijos (funciones de suspención) se terminen. Es necesario llamarla desde un contexto de corrutinas.

```kotlin
val job = GlobalScope.launch(Dispatchers.Main) {
    doCoroutineTask()

    val res1 = supendingTask1()
    val res2 = supendingTask2()

    process(res1, res2)
}

job.join()
```

### cancel()

Cancela todos los trabajos que ese trabajo está ejecutando.

## Scopes

Son los limites en donde se aplican las corrutinas. Gracias a los scopes, podemos cancelar todas las corrutinas asociadas a este scope cuando ya no esté disponible. Son muy útiles para componentes que tienen su propio ciclo de vida.

Un ejemplo muy claro es la Activity. Imagina que queremos cargar datos en una corrutina y actualizar el UI al finalizar la ejecución. Si la Activity ha terminado su ciclo de vida, nuestra corrutina no funcionará.

Gracias a los escopes, podemos enganchar el job al ciclo de vida de la Activity para cuando sea destruida, el job se cancele.

Tenemos dos opciones para utilizar los scopes:

* GlobalScope: El cual es usado para corrutinas que pueden seguir ejecutándose mientras la App esté viva.
* Extender CoroutineScope: El cual nos permitirá cancelar la corrutina cuando el componente que se quiere actualizar sea destruido.

Para utilizar el segundo, debemos implementar la interface `CoroutineScope`, la cuál nos obligará a implementar la propiedad `coroutineContext`. El contexto es un mapa con claves de valor en la cuál podemos especificar distintos valores para esas claves. El primer elemento es el hilo de ejecución asignado por los dispatchers y el otro es el job, que será el job padre que utilicen cada una de las corrutinas. 

```kotlin
class MainActivity : AppCompatActivity(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job

    private lateinit var job: Job
}
```

El operador suma crea un context combinado con cada una de estas configuraciones. Si combinamos dos dispatchers o dos jobs, se quederá automaticamente con el segundo.

El job lo declaramos como lateinit porque lo instanciaremos en `onCreate` y lo cancelaremos en `onDestroy`. Un Job cancelado no puede ser reiniciado, por lo tanto tenemos que crear uno nuevo en el `onCreate`.


## Convertir callbacks a corrutinas

Ahora bien, imaginemos que tenemos una función que utiliza un callback pues es asíncrona. Si queremos devolver el valor que retorna esa función asíncrona, podemos utilizar las funciones `suspendCancellableCoroutine` o `suspendCoroutine`, que nos permiten devolver el valor deseado por medio de un objeto llamado continuation.

```kotlin
private suspend fun getDataSuppor(): List<MediaItem> = suspendCancellableCoroutine { continuation ->
    MediaProvider.dataAsync { media ->
        continuation.resume(media)
    }
}
```