package sg.toru.customselector.view

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import sg.toru.customselector.R

class CustomTransportSelector : ConstraintLayout {
    constructor(context:Context):super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        init(attrs)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
    {
        init(attrs)
    }

    private lateinit var button1:ConstraintLayout
    private lateinit var button2:ConstraintLayout
    private lateinit var button3:ConstraintLayout

    private lateinit var textHeader:TextView
    private lateinit var textSelectorFirstRow:TextView
    private lateinit var textSelectorSecondRow:EditText

    private lateinit var metropolisBold:Typeface
    private lateinit var metropolisRegular:Typeface

    private var headerText = ""
    private var currentMode = 0

    private fun initFont(){
        metropolisBold = ResourcesCompat.getFont(context, R.font.metropolis_bold)!!
        metropolisRegular = ResourcesCompat.getFont(context, R.font.metropolis_regular)!!
    }

    private fun init(attrs:AttributeSet?){
        val view = LayoutInflater.from(context).inflate(R.layout.layout_custom_transport, this, false)
        addView(view)
        initFont()

        textHeader = view.findViewById(R.id.txt_head)

        button1 = view.findViewById(R.id.btn_first)
        button2 = view.findViewById(R.id.btn_second)
        button3 = view.findViewById(R.id.btn_third)

        textSelectorFirstRow = view.findViewById(R.id.txt_selector_first_row)
        textSelectorSecondRow = view.findViewById(R.id.txt_selector_second_row)

        (button1.getChildAt(1) as ImageView).setImageResource(R.drawable.ic_plane)
        (button1.getChildAt(2) as TextView).text = "AIR"
        button1.tag = "btn1"

        (button2.getChildAt(1) as ImageView).setImageResource(R.drawable.ic_car)
        (button2.getChildAt(2) as TextView).text = "LAND"
        button2.tag = "btn2"

        (button3.getChildAt(1) as ImageView).setImageResource(R.drawable.ic_ship)
        (button3.getChildAt(2) as TextView).text = "SEA"
        button3.tag = "btn3"

        button1.setOnClickListener {
            setType(it)
            setUnselectedToOtherButton(button1)
        }
        button2.setOnClickListener {
            setType(it)
            setUnselectedToOtherButton(button2)
        }
        button3.setOnClickListener {
            setType(it)
            setUnselectedToOtherButton(button3)
        }

        button1.performClick()
        setType(button1)

        val ticket = findViewById<ConstraintLayout>(R.id.container_ticket)
        ticket.setOnClickListener {
            textSelectorFirstRow.visibility = View.VISIBLE
            textSelectorSecondRow.visibility = View.VISIBLE
            it.visibility = View.GONE
        }

        textSelectorSecondRow.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                textSelectorFirstRow.visibility = View.GONE
                textSelectorSecondRow.visibility = View.GONE
                ticket.visibility = View.VISIBLE
                typeOfTransportationCallback?.invoke()
            }
            false
        }
    }

    private val onClickListener = OnClickListener {

    }

    var typeOfTransportationCallback:(()->Unit)? = null

    private fun setUnselectedToOtherButton(button:ConstraintLayout){
        when(button){
            button1 ->{
                button1.background = ContextCompat.getDrawable(context, R.drawable.rect_cornered_box_selected)
                (button1.getChildAt(2) as TextView).apply {
                    setTextColor(resources.getColor(R.color.selectedTextColor, null))
                    typeface = metropolisBold
                }

                button2.background = ContextCompat.getDrawable(context, R.drawable.rect_cornered_box_unselected)
                (button2.getChildAt(2) as TextView).apply {
                    setTextColor(resources.getColor(R.color.basicTextColor, null))
                    typeface = metropolisRegular
                }

                button3.background = ContextCompat.getDrawable(context, R.drawable.rect_cornered_box_unselected)
                (button3.getChildAt(2) as TextView).apply {
                    setTextColor(resources.getColor(R.color.basicTextColor, null))
                    typeface = metropolisRegular
                }
            }
            button2 ->{
                button1.background = ContextCompat.getDrawable(context, R.drawable.rect_cornered_box_unselected)
                (button1.getChildAt(2) as TextView).apply {
                    typeface = metropolisRegular
                    setTextColor(resources.getColor(R.color.basicTextColor, null))
                }

                button2.background = ContextCompat.getDrawable(context, R.drawable.rect_cornered_box_selected)
                (button2.getChildAt(2) as TextView).apply {
                    typeface = metropolisBold
                    setTextColor(resources.getColor(R.color.selectedTextColor, null))
                }

                button3.background = ContextCompat.getDrawable(context, R.drawable.rect_cornered_box_unselected)
                (button3.getChildAt(2) as TextView).apply {
                    typeface = metropolisRegular
                    setTextColor(resources.getColor(R.color.basicTextColor, null))
                }
            }
            button3 ->{
                button1.background = ContextCompat.getDrawable(context, R.drawable.rect_cornered_box_unselected)
                (button1.getChildAt(2) as TextView).apply {
                    typeface = metropolisRegular
                    setTextColor(resources.getColor(R.color.basicTextColor, null))
                }

                button2.background = ContextCompat.getDrawable(context, R.drawable.rect_cornered_box_unselected)
                (button2.getChildAt(2) as TextView).apply {
                    typeface = metropolisRegular
                    setTextColor(resources.getColor(R.color.basicTextColor, null))
                }

                button3.background = ContextCompat.getDrawable(context, R.drawable.rect_cornered_box_selected)
                (button3.getChildAt(2) as TextView).apply {
                    typeface = metropolisBold
                    setTextColor(resources.getColor(R.color.selectedTextColor, null))
                }
            }
            else -> throw IllegalStateException("WTF?")
        }
    }

    private fun setType(view:View){
        when(view.tag){
            "btn1" -> {
                textSelectorFirstRow.text = "Type of Air Transport"
                textSelectorSecondRow.hint = "Flight Number"
            }
            "btn2" ->{
                textSelectorFirstRow.text = "Type of Vehicle"
                textSelectorSecondRow.hint = "Vehicle Number"
            }
            "btn3" ->{
                textSelectorFirstRow.text = "Type of Vessel"
                textSelectorSecondRow.hint = "Vessel Number"
            }
        }
    }
}