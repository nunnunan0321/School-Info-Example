package com.note11.schoolinfoapp.ui.screen.main

import com.note11.schoolinfoapp.BR
import com.note11.schoolinfoapp.R
import com.note11.schoolinfoapp.data.SubjectModel
import com.note11.schoolinfoapp.databinding.ItemSubjectBinding
import com.note11.schoolinfoapp.ui.base.BaseRcv

class SubjectAdapter() : BaseRcv.Adapter<SubjectModel, ItemSubjectBinding>(
    {old, new -> old.period == new.period},
    R.layout.item_subject,
    BR.subject,
)