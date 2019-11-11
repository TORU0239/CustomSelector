package sg.toru.customselector.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
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
    private var currentSelectedSubType = Transport.SEA


    private lateinit var typeOfTranportTxt:TextView
    private lateinit var numberOfTransportTxt:TextView

    private fun init(){
        val view = LayoutInflater.from(context).inflate(R.layout.layout_cusom_selector, this, false)
        addView(view)

        val image1 = view.findViewById<ImageView>(R.id.img_text1)
        val image2 = view.findViewById<ImageView>(R.id.img_text2)
        val image3 = view.findViewById<ImageView>(R.id.img_text3)

        image1.setOnClickListener {
            currentSelectedSubType = Transport.AIR
            setType()
        }
        image2.setOnClickListener {
            currentSelectedSubType = Transport.LAND
            setType()
        }
        image3.setOnClickListener {
            currentSelectedSubType = Transport.SEA
            setType()
        }

        typeOfTranportTxt = view.findViewById(R.id.txt_selector_transport)
        numberOfTransportTxt = view.findViewById(R.id.txt_selector_transport_number)
        setType()
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