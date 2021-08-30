package com.note11.schoolinfoapp.ui.screen.main

import com.note11.schoolinfoapp.BR
import com.note11.schoolinfoapp.R
import com.note11.schoolinfoapp.data.LunchModel
import com.note11.schoolinfoapp.databinding.ItemLunchBinding
import com.note11.schoolinfoapp.ui.base.BaseRcv

class LunchAdapter : BaseRcv.Adapter<LunchModel, ItemLunchBinding>(
    {old, new -> old.mealDate == new.mealDate},
    R.layout.item_lunch,
    BR.lunch
)