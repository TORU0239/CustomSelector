package sg.toru.customselector.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import sg.toru.customselector.R
import java.lang.IllegalStateException

class CustomSelector : ConstraintLayout {
    constructor(context:Context):super(context){
        init()
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        init()
        getAttrs(attrs)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
    {
        init()
        getAttrs(attrs)
    }

    private val currentSelectorType = KindOfSeelector.TRANSPORT
    private var currentSelectedSubType = Transport.SEA


    private lateinit var typeOfTranportTxt:TextView
    private lateinit var numberOfTransportTxt:TextView
    private lateinit var image1:ImageView
    private lateinit var image2:ImageView
    private lateinit var image3:ImageView


    private fun init(){
        val view = LayoutInflater.from(context).inflate(R.layout.layout_cusom_selector, this, false)
        addView(view)

        image1 = view.findViewById(R.id.img_text1)
        image2 = view.findViewById(R.id.img_text2)
        image3 = view.findViewById(R.id.img_text3)

        image1.setOnClickListener {
            currentSelectedSubType = Transport.AIR
            setType()
            setUnselectedToOtherButton(image1)
        }
        image2.setOnClickListener {
            currentSelectedSubType = Transport.LAND
            setType()
            setUnselectedToOtherButton(image2)
        }
        image3.setOnClickListener {
            currentSelectedSubType = Transport.SEA
            setType()
            setUnselectedToOtherButton(image3)
        }

        typeOfTranportTxt = view.findViewById(R.id.txt_selector_transport)
        numberOfTransportTxt = view.findViewById(R.id.txt_selector_transport_number)

        image1.performClick()
        setType()
    }

    private fun setUnselectedToOtherButton(button:ImageView){
        when(button){
            image1 ->{
                image1.background = ContextCompat.getDrawable(context, R.drawable.rect_cornered_box_selected)
                image2.background = ContextCompat.getDrawable(context, R.drawable.rect_cornered_box_unselected)
                image3.background = ContextCompat.getDrawable(context, R.drawable.rect_cornered_box_unselected)
            }
            image2 ->{
                image1.background = ContextCompat.getDrawable(context, R.drawable.rect_cornered_box_unselected)
                image2.background = ContextCompat.getDrawable(context, R.drawable.rect_cornered_box_selected)
                image3.background = ContextCompat.getDrawable(context, R.drawable.rect_cornered_box_unselected)
            }
            image3 ->{
                image1.background = ContextCompat.getDrawable(context, R.drawable.rect_cornered_box_unselected)
                image2.background = ContextCompat.getDrawable(context, R.drawable.rect_cornered_box_unselected)
                image3.background = ContextCompat.getDrawable(context, R.drawable.rect_cornered_box_selected)
            }
            else -> throw IllegalStateException("WTF?")
        }
    }

    private fun setType(){
        when(currentSelectedSubType){
            Transport.AIR -> {
                typeOfTranportTxt.text = Transport.AIR.typeOfTransport
                numberOfTransportTxt.text = Transport.AIR.numberOfTransport
            }
            Transport.LAND ->{
                typeOfTranportTxt.text = Transport.LAND.typeOfTransport
                numberOfTransportTxt.text = Transport.LAND.numberOfTransport
            }
            Transport.SEA ->{
                typeOfTranportTxt.text = Transport.SEA.typeOfTransport
                numberOfTransportTxt.text = Transport.SEA.numberOfTransport
            }
        }
    }

    private fun getAttrs(attrs:AttributeSet?){

    }
}

enum class KindOfSeelector{
    TRANSPORT, RESIDENCE
}

// AIR : Type of Air Transport, Flight Number
// LAND : Type of Vehicle, Vehicle Number
// SEA : Type of Vessel, Vessel Number

enum class Transport(val kindOfTransport:String,
                     val typeOfTransport:String,
                     val numberOfTransport:String){
    AIR("Air", "Type of Air Transport", "Flight Number"),
    LAND("Land","Type of Vehicle", "Vehicle Number"),
    SEA("Land","Type of Vessel", "Vessel Number")
}