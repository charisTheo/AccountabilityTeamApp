package com.charistheo.accountabilityteam.models

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.charistheo.accountabilityteam.R

class DummyPromise {
    var title: String? = null
    var description: String? = null
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
            title.text = dummyPromise.title
            description.text = dummyPromise.description
        }
    }

}