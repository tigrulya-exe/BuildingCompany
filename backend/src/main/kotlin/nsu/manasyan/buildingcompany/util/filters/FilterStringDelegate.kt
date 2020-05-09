package nsu.manasyan.buildingcompany.util.filters

import kotlin.reflect.KProperty

class FilterStringDelegate {
    private var backingString: String? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String? {
        return backingString
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String?) {
        backingString = value?.let { "%${it.toLowerCase()}%" }
    }
}