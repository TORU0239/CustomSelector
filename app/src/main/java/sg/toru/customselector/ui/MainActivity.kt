package sg.toru.customselector.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.Pair
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import sg.toru.customselector.R
import sg.toru.customselector.view.CustomDepartureForm
import sg.toru.customselector.view.CustomTransportSelector
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var departureForm: CustomDepartureForm

    private lateinit var transportForm:CustomTransportSelector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        transportForm = findViewById(R.id.transport_selector)

//        departureForm = findViewById(R.id.departure_form)
//
//        val currentTimeStamp = System.currentTimeMillis()
//        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
//        calendar.timeInMillis = currentTimeStamp
//
//        val currentMonth = calendar.get(Calendar.MONTH)
//        val picker = MaterialDatePicker.Builder.dateRangePicker()
//            .setTitleText("Round Trip")
//            .setCalendarConstraints(
//                CalendarConstraints.Builder()
//                    .setStart(currentMonth.toLong())
//                    .build()
//            )
//        departureForm.callback = { firstCalendar, secondCalendar ->
//            val builder = picker.setSelection(Pair(firstCalendar?.timeInMillis, secondCalendar?.timeInMillis))
//                .build()
//            builder.addOnPositiveButtonClickListener {
//                Log.e("TORU", "${builder.selection?.first} ${builder.selection?.second}")
//                builder.selection?.let {
//                    it.first?.let { first ->
//                        it.second?.let { second ->
//                            val firstDateCalendar = calendar.clone() as Calendar
//                            firstDateCalendar.timeInMillis = first
//
//                            val secondDateCalendar = calendar.clone() as Calendar
//                            secondDateCalendar.timeInMillis = second
//
//                            departureForm.setFirstDateAndLastDate(firstDateCalendar, secondDateCalendar)
//                        }
//                    }
//                }
//            }
//            builder.showNow(supportFragmentManager, "CALENDAR")
//        }
    }
}