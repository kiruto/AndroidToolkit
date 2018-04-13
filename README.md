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