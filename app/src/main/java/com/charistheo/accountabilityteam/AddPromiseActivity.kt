package com.charistheo.accountabilityteam

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.res.Resources
import android.support.v7.app.AppCompatActivity
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.activity_add_promise.*
import kotlinx.android.synthetic.main.fragment_add_promise.view.*
import java.text.SimpleDateFormat
import java.util.*

class AddPromiseActivity() : AppCompatActivity(), ViewPager.OnPageChangeListener {
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
//        TODO("not implemented")
    }

    override fun onPageScrollStateChanged(state: Int) {
//        TODO("not implemented")
    }

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    private var fragmentPosition = 1

    //on change page listener for updating the discrete seek bar
    override fun onPageSelected(position: Int) {
        seekBar.progress = position + 1
        fragmentPosition = position
        if (position == 2) {
            next.alpha = 0.35F
        } else if (position == 0){
            previous.alpha = 0.35F
        } else {
            next.alpha = 1F
            previous.alpha = 1F
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_promise)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter

        //set on change page listener to update the discrete seek bar
        container.addOnPageChangeListener(this)

        //all of the pages will stay in memory all the time cached
        container.offscreenPageLimit = 2

        previous.setOnClickListener {
            container.currentItem = fragmentPosition--
        }
        next.setOnClickListener {
            container.currentItem = fragmentPosition++
        }


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_add_promise, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1)
        }

        override fun getCount(): Int {
            // Show 3 total pages.
            return 3
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    class PlaceholderFragment : Fragment(), View.OnClickListener, NumberPicker.OnValueChangeListener {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            var rootView: View? = null
            var numberPicker: NumberPicker? = null
            var promisesSpinner: Spinner? = null

            when (arguments.getInt(ARG_SECTION_NUMBER)) {
                1 -> {
                    rootView = inflater.inflate(R.layout.fragment_add_promise, container, false)
                    val arrayAdapter = ArrayAdapter.createFromResource(rootView.context, R.array.promises, R.layout.support_simple_spinner_dropdown_item)
                    arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
                    promisesSpinner = rootView.findViewById<Spinner>(R.id.promisesSpinner) as Spinner
                    promisesSpinner.adapter = arrayAdapter
                    promisesSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(p0: AdapterView<*>?) {
                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        }
                        override fun onItemSelected(parent: AdapterView<*>?, v: View?, position: Int, id: Long) {
                            arguments.putString("promise", parent!!.getItemAtPosition(position).toString())

                        }
                    }
                    rootView.dateFrom.setOnClickListener(this)
                    rootView.dateUntil.setOnClickListener(this)
                }
                2 -> {
                    rootView = inflater.inflate(R.layout.fragment_add_stake, container, false)
                    numberPicker = rootView.findViewById<View>(R.id.numberPicker) as NumberPicker
                    numberPicker.minValue = 1
                    numberPicker.maxValue = 100
                    numberPicker.value = 5
                    numberPicker.setOnValueChangedListener(this)
                }
                3 -> {
                    rootView = inflater.inflate(R.layout.fragment_overview, container, false)
//                    rootView.stake.text = arguments["stake"]
                }
            }
//            rootView.section_label.text = getString(R.string.section_format, arguments.getInt(ARG_SECTION_NUMBER))
            return rootView
        }

        override fun onValueChange(picker: NumberPicker?, oldVal: Int, newVal: Int) {
            arguments.putInt("stake", newVal)
        }

        override fun onClick(v: View?) {
            val datePickerFragment: DialogFragment = DatePickerFragment()
            val b = Bundle()
            b.putInt("id", v!!.id)
            datePickerFragment.arguments = b
            datePickerFragment.show(activity.supportFragmentManager, "datePicker")
        }

        companion object {
            /**
             * The fragment argument representing the section number for this
             * fragment.
             */
            private val ARG_SECTION_NUMBER = "section_number"

            /**
             * Returns a new instance of this fragment for the given section
             * number.
             */
            fun newInstance(sectionNumber: Int): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }
    }

    class DatePickerFragment: DialogFragment(), DatePickerDialog.OnDateSetListener {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val calendar = SimpleDateFormat.getInstance().calendar
//            TODO(calendar.get(Calendar.YEAR) returns 1937)
//            val year = calendar.get(Calendar.YEAR)
            val year = 2017
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            return DatePickerDialog(activity, this, year, month, day)
        }
        override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
            val fullDate = "$day/$month/$year"
            when (this.arguments["id"].toString()) {
                R.id.dateUntil.toString() -> activity.findViewById<TextView>(R.id.dateUntil).text = fullDate
                R.id.dateFrom.toString() -> activity.findViewById<TextView>(R.id.dateFrom).text = fullDate
            }
        }
    }
}