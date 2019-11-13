package sg.toru.customselector.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import sg.toru.customselector.R
import java.text.SimpleDateFormat
import java.util.*

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

    private lateinit var unfilledForm:ConstraintLayout
    private lateinit var filledForm:ConstraintLayout

    private lateinit var txtArrivalDate: TextView
    private lateinit var txtDepartureDate: TextView

    var callback:((Calendar?, Calendar?)->Unit)? = null
    private fun init(){
        val view = LayoutInflater.from(context).inflate(R.layout.layout_arrival_date, this, false)
        addView(view)

        unfilledForm = view.findViewById(R.id.container_unfilled)
        unfilledForm.setOnClickListener {
            callback?.invoke(firstCalendar, secondCalendar)
        }
        unfilledForm.visibility = View.VISIBLE

        filledForm = view.findViewById(R.id.container_filled)
        filledForm.setOnClickListener {
            callback?.invoke(firstCalendar, secondCalendar)
        }

        filledForm.visibility = View.GONE

        txtArrivalDate = view.findViewById(R.id.txt_arrival_date)
        txtDepartureDate = view.findViewById(R.id.txt_departure_date)
    }

    var firstCalendar:Calendar? = null
    var secondCalendar:Calendar? = null


    private val sdf = SimpleDateFormat("MMM dd, yyyy")
    fun setFirstDateAndLastDate(
        firstCalendar:Calendar,
        secondCalendar: Calendar
    ){
        this.firstCalendar = firstCalendar
        this.secondCalendar = secondCalendar
        txtArrivalDate.text = sdf.format(firstCalendar.time)
        txtDepartureDate.text = sdf.format(secondCalendar.time)
        filledForm.visibility = View.VISIBLE
        unfilledForm.visibility = View.GONE
    }
}