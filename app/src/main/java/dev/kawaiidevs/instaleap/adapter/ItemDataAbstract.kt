package dev.kawaiidevs.instaleap.adapter

class ItemDataAbstract<T>(
    override val data: T,
    override val type: Int = 0
) : ItemData<T>