package com.note11.schoolinfoapp.ui.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.note11.schoolinfoapp.data.LunchModel
import com.note11.schoolinfoapp.data.SchoolModel
import com.note11.schoolinfoapp.data.SubjectModel
import com.note11.schoolinfoapp.ui.screen.first.search.SearchAdapter
import com.note11.schoolinfoapp.ui.screen.main.LunchAdapter
import com.note11.schoolinfoapp.ui.screen.main.SubjectAdapter

object BindingOptions {
    @BindingAdapter("bindSearchItems")
    @JvmStatic
    fun bindSearchItems(rcv: RecyclerView, list: List<SchoolModel>) {
        (rcv.adapter as SearchAdapter).run { submitList(list) }
    }

    @BindingAdapter("bindSubjectItems")
    @JvmStatic
    fun bindSubjectItems(rcv: RecyclerView, list: List<SubjectModel>) {
        (rcv.adapter as SubjectAdapter).run { submitList(list) }
    }

    @BindingAdapter("bindLunchItems")
    @JvmStatic
    fun bindLunchItems(rcv: RecyclerView, list: List<LunchModel>) {
        (rcv.adapter as LunchAdapter).run { submitList(list) }
    }
}