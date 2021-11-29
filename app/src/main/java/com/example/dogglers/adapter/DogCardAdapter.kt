/*
* Copyright (C) 2021 The Android Open Source Project.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.dogglers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogglers.R
import com.example.dogglers.const.Layout.GRID
import com.example.dogglers.data.DataSource.dogs

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context?,
    private val layout: Int
): RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {

    //Initialize the data using the List found in data/DataSource
    private var dogDataList = dogs

    /**
     * Initialize view elements
     */
    class DogCardViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        // list item UI components
        val textViewDogName: TextView = view!!.findViewById(R.id.dog_name)
        val textViewDogAge: TextView = view!!.findViewById(R.id.dog_age)
        val textViewHobbies: TextView = view!!.findViewById(R.id.dog_hobbies)
        val imageView: ImageView = view!!.findViewById(R.id.dog_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {
        //  Conditional to determine the layout type and set it accordingly.
        //  if the layout variable is Layout.GRID the grid list item should be used. Otherwise the
        //  the vertical/horizontal list item should be used.

        val adapterLayout = if (layout == GRID) {
            LayoutInflater.from(parent.context).inflate(
                R.layout.grid_list_item, parent, false
            )
        } else {
            LayoutInflater.from(parent.context).inflate(
                R.layout.vertical_horizontal_list_item, parent, false
            )
        }

        return DogCardViewHolder(adapterLayout)
    }

    override fun getItemCount() = dogDataList.size

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        val item = dogDataList[position]

        //Image resource for the current dog
        holder.imageView.setImageResource(item.imageResourceId)
        holder.textViewDogName.text = item.name

        val resources = context?.resources
        holder.textViewDogAge.text = resources?.getString(R.string.dog_age, item.age)
        holder.textViewHobbies.text = resources?.getString(R.string.dog_hobbies, item.hobbies)
    }
}
