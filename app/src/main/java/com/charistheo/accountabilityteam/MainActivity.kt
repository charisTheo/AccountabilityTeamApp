package com.charistheo.accountabilityteam

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.charistheo.accountabilityteam.models.DummyPromise
import com.charistheo.accountabilityteam.models.DummyPromiseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.dashboard_main.*
import java.text.SimpleDateFormat
import java.time.Month
import kotlin.collections.ArrayList

//private var mePromises: Int? = null
//private var buddyPromises: Int? = null


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var myAdapter: DummyPromiseAdapter? = null
    private var myDummyPromiseList: ArrayList<DummyPromise>? = null
    private var myLayoutManager: RecyclerView.LayoutManager? = null
    private var myDummyPromise: DummyPromise? = null

    private var buddyAdapter: DummyPromiseAdapter? = null
    private var buddyPromiseList: ArrayList<DummyPromise>? = null
    private var buddyLayoutManager: RecyclerView.LayoutManager? = null
    private var buddyDummyPromise: DummyPromise? = null

    private var dummyDate: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        myDummyPromiseList = ArrayList<DummyPromise>()
        myLayoutManager = LinearLayoutManager(this)
        myAdapter = DummyPromiseAdapter(myDummyPromiseList!!, this)

        buddyPromiseList = ArrayList<DummyPromise>()
        buddyLayoutManager = LinearLayoutManager(this)
        buddyAdapter = DummyPromiseAdapter(buddyPromiseList!!, this)

        //setup lists (RecyclerView)
        meRecyclerView.layoutManager = myLayoutManager
        meRecyclerView.adapter = myAdapter

        buddyRecyclerView.layoutManager = buddyLayoutManager
        buddyRecyclerView.adapter = buddyAdapter

        dummyDate = SimpleDateFormat.getDateInstance().toString()

        //add dummy promises
        myDummyPromise = DummyPromise("I promise not to smoke", "I will not smoke for a week", dummyDate!!)
        myDummyPromiseList!!.add(myDummyPromise!!)
        myAdapter!!.notifyDataSetChanged()


        buddyDummyPromise = DummyPromise("I promise to wash my teeth", "Twice a day, one in the morning one at night", dummyDate!!)
        buddyPromiseList!!.add(buddyDummyPromise!!)
        buddyAdapter!!.notifyDataSetChanged()



//        buddyPromises = buddyColumn.childCount - 1
//        mePromises = meColumn.childCount - 1 // minus one due to the button profile at the top
//
//        for (child in 1..buddyPromises!!) {
//            buddyColumn.getChildAt(child).setBackgroundColor(Color.rgb(Random().nextInt(200), Random().nextInt(200), Random().nextInt(200)))
//            buddyColumn.getChildAt(child).layoutParams.height = Random().nextInt(70 - 10) + 10
//        }
//
//        for (child in 1..mePromises!!) {
//            meColumn.getChildAt(child).setBackgroundColor(Color.rgb(Random().nextInt(200), Random().nextInt(200), Random().nextInt(200)))
//            meColumn.getChildAt(child).layoutParams.height = Random().nextInt(70 - 10) + 10
//        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
                this.snackBar("Not yet implemented")
            }
            R.id.nav_gallery -> {
                this.snackBar("Nope!")
            }
            R.id.nav_slideshow -> {
                this.snackBar("As well..")
            }
            R.id.nav_manage -> {
                this.snackBar("Not yet implemented")
            }
            R.id.nav_share -> {
                this.snackBar("Not yet implemented")
            }
            R.id.nav_send -> {
                this.snackBar("Not yet implemented")
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun Context.snackBar(text: String) = Snackbar.make(contentMainLayout, text, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()

    //Open AddPromiseActivity on click
    fun openAddPromise(v: View) {
        startActivity(Intent(baseContext, AddPromiseActivity::class.java))
    }
}

