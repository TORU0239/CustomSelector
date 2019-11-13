package sg.toru.customselector.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageView
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

    private lateinit var arriveForm:ConstraintLayout
    private lateinit var departureForm: ConstraintLayout
    private lateinit var filledArriveForm:ConstraintLayout
    private lateinit var filledDepartureForm:ConstraintLayout

    private lateinit var calendarArrive: ImageView
    private lateinit var filledCalendarArrive: ImageView
    private lateinit var calendarDepart: ImageView
    private lateinit var filledCalendarDepart: ImageView

    // callback
    var onCallback:(()->Unit)? = null

    private val calendarClickListener = OnClickListener { view ->
        when(view.tag){
            "calendarArrive" -> {
                Log.i("CustomDeparture", "Arrive Clicked")
                onCallback?.invoke()
            }
            "calendarDepart" -> {
                Log.i("CustomDeparture", "Depart Clicked")
                showDifferentContainer(true, view.tag as String)
            }
            "filledCalendarArrive" -> {
                Log.i("CustomDeparture", "Arrive Clicked")
                showDifferentContainer(false, view.tag as String)
            }
            "filledCalendarDepart" -> {
                Log.i("CustomDeparture", "Depart Clicked")
                showDifferentContainer(false, view.tag as String)
            }

            else -> throw IllegalArgumentException("WTF the other one?")
        }
    }

    private fun showDifferentContainer(isFilled:Boolean, tag:String){
        when(tag){
            "calendarArrive"->{
                if(isFilled){
                    arriveForm.visibility = View.GONE
                    filledArriveForm.visibility = View.VISIBLE
                }
                else{
                    arriveForm.visibility = View.VISIBLE
                    filledArriveForm.visibility = View.GONE
                }
            }
            "calendarDepart"->{
                if(isFilled){
                    departureForm.visibility = View.GONE
                    filledDepartureForm.visibility = View.VISIBLE
                }
                else{
                    departureForm.visibility = View.VISIBLE
                    filledDepartureForm.visibility = View.GONE
                }
            }
            "filledCalendarArrive"->{
                if(!isFilled){
                    arriveForm.visibility = View.VISIBLE
                    filledArriveForm.visibility = View.GONE
                }
                else{
                    arriveForm.visibility = View.GONE
                    filledArriveForm.visibility = View.VISIBLE
                }
            }
            "filledCalendarDepart"->{
                if(!isFilled){
                    departureForm.visibility = View.VISIBLE
                    filledDepartureForm.visibility = View.GONE
                }
                else{
                    departureForm.visibility = View.GONE
                    filledDepartureForm.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun init(){
        val view = LayoutInflater.from(context).inflate(R.layout.layout_arrival_date, this, false)
        addView(view)

        arriveForm = view.findViewById(R.id.container_arrival)
        departureForm = view.findViewById(R.id.container_departure)

        filledArriveForm = view.findViewById(R.id.container_arrival_filled)
        filledDepartureForm = view.findViewById(R.id.container_departure_filled)

        calendarArrive = view.findViewById(R.id.btn_open_calendar_arrival)
        calendarDepart = view.findViewById(R.id.btn_open_calendar_departure)

        filledCalendarArrive = view.findViewById(R.id.btn_open_calendar_arrival_filled)
        filledCalendarDepart = view.findViewById(R.id.btn_open_calendar_departure_filled)

        calendarArrive.apply {
            tag = "calendarArrive"
            setOnClickListener(calendarClickListener)
        }

        calendarDepart.apply {
            tag = "calendarDepart"
            setOnClickListener(calendarClickListener)
        }

        filledCalendarArrive.apply {
            tag = "filledCalendarArrive"
            setOnClickListener(calendarClickListener)
        }

        filledCalendarDepart.apply {
            tag = "filledCalendarDepart"
            setOnClickListener(calendarClickListener)
        }
    }
}