package sg.toru.customselector.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import sg.toru.customselector.R
import java.lang.IllegalStateException

class CustomSelector : ConstraintLayout {
    constructor(context:Context):super(context){}
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

    private var requiredRow = 2
    private var headerText = ""

    private var currentSelectorType = KindOfSeelector.TRANSPORT

    private lateinit var button1:ConstraintLayout
    private lateinit var button2:ConstraintLayout
    private lateinit var button3:ConstraintLayout

    private lateinit var textHeader:TextView
    private lateinit var textSelectorFirstRow:TextView
    private lateinit var textSelectorSecondRow:TextView

    private var currentSelectedSubType:String = ""

    private fun init(attrs:AttributeSet?){
        val view = LayoutInflater.from(context).inflate(R.layout.layout_cusom_selector, this, false)
        addView(view)
        textHeader = view.findViewById(R.id.txt_head)

        button1 = view.findViewById(R.id.btn_first)
        button2 = view.findViewById(R.id.btn_second)
        button3 = view.findViewById(R.id.btn_third)

        textSelectorFirstRow = view.findViewById(R.id.txt_selector_first_row)
        textSelectorSecondRow = view.findViewById(R.id.txt_selector_second_row)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomSelector)
        requiredRow = typedArray.getInt(R.styleable.CustomSelector_requiredRowCount, 2)
        if(requiredRow > 2){
            requiredRow = 2
        }

        if(requiredRow == 1){
            textSelectorSecondRow.visibility = View.GONE
        }

        val header = typedArray.getString(R.styleable.CustomSelector_headerText)
        header?.let { str ->
            headerText = str
            textHeader.text = headerText
        }

        currentSelectorType = when(typedArray.getString(R.styleable.CustomSelector_dataSetName)){
            "transport", "TRANSPORT"-> KindOfSeelector.TRANSPORT
            "residence", "RESIDENCE"-> KindOfSeelector.RESIDENCE
            else -> KindOfSeelector.TRANSPORT
        }
        typedArray.recycle()

        when(currentSelectorType){
            KindOfSeelector.TRANSPORT->{
                (button1.getChildAt(0) as ImageView).setImageResource(R.drawable.ic_plane)
                (button2.getChildAt(0) as ImageView).setImageResource(R.drawable.ic_car)
                (button3.getChildAt(0) as ImageView).setImageResource(R.drawable.ic_ship)
            }

            KindOfSeelector.RESIDENCE->{
                (button1.getChildAt(0) as ImageView).setImageResource(R.drawable.ic_hotel)
                (button2.getChildAt(0) as ImageView).setImageResource(R.drawable.ic_residential)
                (button3.getChildAt(0) as ImageView).setImageResource(R.drawable.ic_options)
            }
        }

        button1.setOnClickListener {
            currentSelectedSubType = if(currentSelectorType == KindOfSeelector.TRANSPORT){
                Transport.AIR.name
            } else{
                Residence.HOTEL.name
            }

            setType()
            setUnselectedToOtherButton(button1)
        }
        button2.setOnClickListener {
            currentSelectedSubType = if(currentSelectorType == KindOfSeelector.TRANSPORT){
                Transport.LAND.name
            } else{
                Residence.RESIDENTIAL.name
            }
            setType()
            setUnselectedToOtherButton(button2)
        }
        button3.setOnClickListener {
            currentSelectedSubType = if(currentSelectorType == KindOfSeelector.TRANSPORT){
                Transport.AIR.name
            } else{
                Residence.OTHERS.name
            }
            setType()
            setUnselectedToOtherButton(button3)
        }

        button1.performClick()
        setType()
    }

    private fun setUnselectedToOtherButton(button:ConstraintLayout){
        when(button){
            button1 ->{
                button1.background = ContextCompat.getDrawable(context, R.drawable.rect_cornered_box_selected)
                button2.background = ContextCompat.getDrawable(context, R.drawable.rect_cornered_box_unselected)
                button3.background = ContextCompat.getDrawable(context, R.drawable.rect_cornered_box_unselected)
            }
            button2 ->{
                button1.background = ContextCompat.getDrawable(context, R.drawable.rect_cornered_box_unselected)
                button2.background = ContextCompat.getDrawable(context, R.drawable.rect_cornered_box_selected)
                button3.background = ContextCompat.getDrawable(context, R.drawable.rect_cornered_box_unselected)
            }
            button3 ->{
                button1.background = ContextCompat.getDrawable(context, R.drawable.rect_cornered_box_unselected)
                button2.background = ContextCompat.getDrawable(context, R.drawable.rect_cornered_box_unselected)
                button3.background = ContextCompat.getDrawable(context, R.drawable.rect_cornered_box_selected)
            }
            else -> throw IllegalStateException("WTF?")
        }
    }

    private fun setType(){
        if(currentSelectorType == KindOfSeelector.TRANSPORT){
            when(currentSelectedSubType){
                Transport.AIR.name -> {
                    textSelectorFirstRow.text = Transport.AIR.typeOfTransport
                    textSelectorSecondRow.text = Transport.AIR.numberOfTransport
                }
                Transport.LAND.name ->{
                    textSelectorFirstRow.text = Transport.LAND.typeOfTransport
                    textSelectorSecondRow.text = Transport.LAND.numberOfTransport
                }
                Transport.SEA.name ->{
                    textSelectorFirstRow.text = Transport.SEA.typeOfTransport
                    textSelectorSecondRow.text = Transport.SEA.numberOfTransport
                }
            }
        }
        else{
            when(currentSelectedSubType){
                Residence.HOTEL.name -> {
                    textSelectorFirstRow.text = Residence.HOTEL.otherInfo
                }
                Residence.RESIDENTIAL.name ->{
                    textSelectorFirstRow.text = Residence.RESIDENTIAL.otherInfo
                }
                Residence.OTHERS.name ->{
                    textSelectorFirstRow.text = Residence.OTHERS.otherInfo
                }
            }
        }
    }
}

enum class KindOfSeelector{
    TRANSPORT,
    RESIDENCE
}

// AIR : Type of Air Transport, Flight Number
// LAND : Type of Vehicle, Vehicle Number
// SEA : Type of Vessel, Vessel Number

enum class Transport(val kindOfTransport:String,
                     val typeOfTransport:String,
                     val numberOfTransport:String){
    AIR("Air", "Type of Air Transport", "Flight Number"),
    LAND("Land","Type of Vehicle", "Vehicle Number"),
    SEA("Sea","Type of Vessel", "Vessel Number")
}

enum class Residence(val kindOfResidence:String,
                     val otherInfo:String){
    HOTEL("Hotel", "Hotel Name"),
    RESIDENTIAL("Residential", "Postal Code"),
    OTHERS("Others", "Others")
}