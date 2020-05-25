package nsu.manasyan.buildingcompany.util.filters

import kotlin.reflect.KProperty

class FilterStringDelegate(initial: String? = null) {
    private var backingString: String? = formatString(initial)

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String? {
        return backingString
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String?) {
        backingString = formatString(value)
    }

    private fun formatString(value: String?) : String?{
        if(value == "" || value == null){
            return null
        }
        return "%${value.toLowerCase()}%"
    }
}