package org.gauss.data.utils

inline fun <T> MutableList<T>.updateItem(predicate: (T) -> Boolean, transform: (T) -> T ) : MutableList<T> {
    val index = indexOfFirst(predicate)
    val item = this[index]
    removeAt(index)
    add(index, transform(item))
    return this
}
