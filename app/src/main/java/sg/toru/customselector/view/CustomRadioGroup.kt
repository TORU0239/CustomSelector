package sg.toru.customselector.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.children
import sg.toru.customselector.R

class CustomRadioGroup:ConstraintLayout {
    constructor(context: Context):super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun addView(child: View?,
                         index: Int,
                         params: ViewGroup.LayoutParams?
    ) {
        if(child is ImageView){
            child.setOnClickListener {
                val button = child as ImageView
                setAllButtonsToUnselectedState()
                setSelectedButtonToSelectedState(button)
            }
        }
        super.addView(child, index, params)
    }

    private fun setAllButtonsToUnselectedState(){
        val container = this
        container.children.iterator().forEach {
            setSelectedButtonToUnSelectedState(it as ImageView)
        }
    }

    private fun setSelectedButtonToSelectedState(selectedButton:ImageView){
        selectedButton.background = ContextCompat.getDrawable(context, R.drawable.rect_cornered_box_selected)
    }

    private fun setSelectedButtonToUnSelectedState(selectedButton:ImageView){
        selectedButton.background = ContextCompat.getDrawable(context, R.drawable.rect_cornered_box_unselected)
    }
}