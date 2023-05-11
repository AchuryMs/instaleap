package dev.kawaiidevs.instaleap.modules.content.view

import android.content.Context
import android.view.ViewGroup
import dev.kawaiidevs.instaleap.R
import dev.kawaiidevs.instaleap.adapter.ItemView
import dev.kawaiidevs.instaleap.databinding.ItemMultimediaBinding
import dev.kawaiidevs.instaleap.modules.content.entities.MultimediaItemModelUI
import dev.kawaiidevs.instaleap.utils.itemViewBinding
import dev.kawaiidevs.instaleap.utils.setSingleClickListener

class ContentItemView(
    override val context: Context,
    onClickListener: ((MultimediaItemModelUI) -> Unit)
) : ItemView<MultimediaItemModelUI> {

    private val binding by itemViewBinding<ItemMultimediaBinding>(R.layout.item_multimedia)


    override val view = binding.root

    override lateinit var data: MultimediaItemModelUI

    init {
        view.apply {
            layoutParams = ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            binding.cardViewTokenHome.setSingleClickListener {
                onClickListener(data)
            }
        }
    }

    override fun bind(item: MultimediaItemModelUI) {
        data = item
        with(binding) {
            this.item = item
            executePendingBindings()
        }
    }
}