package sg.toru.customselector.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import sg.toru.customselector.R

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
    private val currentSelectedSubType = Transport.SEA

    private fun init(){
        val view = LayoutInflater.from(context).inflate(R.layout.layout_cusom_selector, this, false)
        addView(view)

        val typeOfTranportTxt = view.findViewById<TextView>(R.id.txt_selector_transport)
        val numberOfTransportTxt = view.findViewById<TextView>(R.id.txt_selector_transport_number)
        if(currentSelectorType == KindOfSeelector.TRANSPORT){
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
    }

    private fun getAttrs(attrs:AttributeSet?){

    }
}

enum class KindOfSeelector{
    TRANSPORT, RESIDENCE
}

// AIR : Type of Transport, Flight Number
// LAND : Type of Transport, Vehicle Number
// SEA : Type of Transport, Vessel Number

enum class Transport(val kindOfTransport:String,
                     val typeOfTransport:String,
                     val numberOfTransport:String){
    AIR("Air", "Type of Transport", "Flight Number"),
    LAND("Land","Type of Transport", "Vehicle Number"),
    SEA("Land","Type of Transport", "Vessel Number")
}