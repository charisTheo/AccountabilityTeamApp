package com.charistheo.accountabilityteam.models

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.format.DateUtils
import android.util.EventLogTags
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.charistheo.accountabilityteam.R
import java.text.SimpleDateFormat

class DummyPromise (t: String, d: String?, de: String) {
    var title: String = t
    var description: String? = d
    var dateStart: String? = null
    var dateEnd: String = de
}

class DummyPromiseAdapter(private val list: ArrayList<DummyPromise>,
                          private val context: Context): RecyclerView.Adapter<DummyPromiseAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, position: Int): ViewHolder {
        //Create our view from xml file
        val view = LayoutInflater.from(context).inflate(R.layout.promise_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindDummyPromise(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindDummyPromise(dummyPromise: DummyPromise) {
            val title: TextView = itemView.findViewById<View>(R.id.promiseTitle) as TextView
            val description: TextView = itemView.findViewById<View>(R.id.promiseDescription) as TextView
            val dateStart: EditText = itemView.findViewById<View>(R.id.dateStart) as EditText
            val dateEnd: EditText = itemView.findViewById<View>(R.id.dateEnd) as EditText
            val stringBuilder = StringBuilder(SimpleDateFormat.getDateInstance().format("dd/mm/yy"))
            stringBuilder.append(SimpleDateFormat.getDateInstance().format("dd/mm/yy").toString())

            title.text = dummyPromise.title
            description.text = dummyPromise.description
            dateEnd.setText(dummyPromise.dateEnd, TextView.BufferType.EDITABLE)
            dateStart.setText(stringBuilder, TextView.BufferType.EDITABLE)
            dummyPromise.dateStart = SimpleDateFormat.getDateInstance().format("dd/mm/yy")
        }
    }

}