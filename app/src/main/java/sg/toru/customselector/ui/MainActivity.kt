package sg.toru.customselector.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.slybeaver.slycalendarview.SlyCalendarDialog
import sg.toru.customselector.R
import sg.toru.customselector.view.CustomDepartureForm
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var departureForm: CustomDepartureForm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        departureForm = findViewById(R.id.departure_form)
        departureForm.callback = { firstCalendar, secondCalendar ->
            SlyCalendarDialog()
                .setSingle(false)
                .setCallback(object:SlyCalendarDialog.Callback{
                    override fun onDataSelected(
                        firstDate: Calendar?,
                        secondDate: Calendar?,
                        hours: Int,
                        minutes: Int
                    ) {
                        firstDate?.let { firstCalendar ->
                            secondDate?.let { secondCalendar ->
                                departureForm.setFirstDateAndLastDate(firstCalendar, secondCalendar)
                            }
                        }
                    }

                    override fun onCancelled() {}
                })
                .setStartDate(firstCalendar?.time)
                .setEndDate(secondCalendar?.time)
                .show(supportFragmentManager, "CALENDAR")
        }
    }
}