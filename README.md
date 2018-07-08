# Weak References

```kotlin
class Example(context: Context) {
    val context by weak(context)
    var fragment by weak<Fragment>()
    
    fun use() {
        context?.let { ctx ->
            //...
        } ?: throw ObjectHasAlreadyReleasedException()
    }
}

class Use: Fragment() {
    init {
        Example(context).fragment = this
    }
}

```

# SharedPreferences

```kotlin
class ExampleContext: Context() {
    var hasLogin by sp("login", false)
    
    fun login() {
        hasLogin = true
        Log.d(hasLogin)
    }
    
    fun logout() {
        hasLogin = false
        Log.d(hasLogin)
    }
    
    fun removeFromSP() {
        hasLogin = null
    }
}

class Example {
    var hasLogin by sp("login", false)
    
    init {
        initToolKit(appContext)
    }
}

```

# Fragment Arguments
```kotlin
class ExampleFragment: Fragment() {
    var showTitle by args("show_title", false)
    
    // update UI after variable set
    var showLogo by args("show_logo", false) {
        updateUI()
    }
    
    // do before set or after set
    var data by args("data", "ABC", willSet = {/*...*/}, didSet = {/*...*/})
    
    fun valid() {
        Log.d(showTitle)
        showTitle = false
        Log.d(showTitle)
        showTitle = null
        Log.d(showTitle)
    }
}

fun use() {
    ExampleFragment()
        .apply {
            arguments = Bundle().apply {
                putBoolean("show_title", true)
            }
        }
        .valid()
}
```

# Activity state
```kotlin
class ExampleActivity: Activity {
    var showTitle by state("show_title", false)
    
    // update UI after variable set
    var showLogo by state("show_logo", false) {
        updateUI()
    }
    
    // do before set or after set
    var data by state("data", "ABC", willSet = {/*...*/}, didSet = {/*...*/})
    
    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        (intent.extras ?: Bundle().also(intent::putExtras))
                .putAll(outState)
    }
    
    fun valid() {
        Log.d(showTitle)
        showTitle = false
        Log.d(showTitle)
        showTitle = null
        Log.d(showTitle)
    }
    
    private fun updateUI() { /*...*/ }
}
```

# Kotlin with Pattern Match
```kotlin
fun matches(): Int? {
    
    // will return matched branch 3
    
    return switch(1, 2, null, 4)[
            case(1, any, 2, 4) {
                println("matched branch 1")
                1
            },
            case (1, 2, 3, any) {
                println("matched branch 2")
                2
            },
            case(1, type(Int::class), maybe(3), 4) {
                println("matched branch 3")
                3
            },
            default {
                println("matched branch default")
                0
            }
    ]
}

open class A
class B: A()

fun matchesType(): Int? {

    // will return matched branch 2

    return switch(B()) [
        case(type(A::class)) {
            println("matched branch 1")
            1
        },
        case(instanceOf(A::class)) {
            println("matched branch 1")
            2
        }
    ]
}

fun matchesWithDefault(): Int {
    // will return a non-null result
    return switch(1) (
        case(0) {
            1        
        },
        default = {
            0
        }
    )
}

```

# Manifest
```xml
 <meta-data 
     android:name="com.exyui.tools"
     android:value="123" /> 
```

```kotlin
val tools1 by manifest("com.exyui.tools", 0) // == 123
val tools2 by manifest("com.exyui.tools", "0") // == "123"
val tools3 by manifest("com.exyui.tools", 0F) // == 123F
val tools4 by manifest("com.exyui.tools1", "0") // == "0"
```