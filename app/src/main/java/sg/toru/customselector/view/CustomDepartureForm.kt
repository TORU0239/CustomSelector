package sg.toru.customselector.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import sg.toru.customselector.R

class CustomDepartureForm:ConstraintLayout {
    constructor(context: Context?) : super(context){
        init()
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        init()
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
    {
        init()
    }

    private fun init(){
        val view = LayoutInflater.from(context).inflate(R.layout.layout_arrival_date, this, false)
        addView(view)
    }
}