package nsu.manasyan.buildingcompany.util

import nsu.manasyan.buildingcompany.abstracts.model.Post
import kotlin.reflect.KProperty

class PostDelegate(initial: Post? = null) {
    private var backingPost: Post? = initial

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Post? {
        return backingPost
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Post?) {
        backingPost = value
    }
}