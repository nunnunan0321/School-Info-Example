package com.note11.schoolinfoapp.ui.screen.first.select

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.note11.schoolinfoapp.R
import com.note11.schoolinfoapp.data.ClassModel
import com.note11.schoolinfoapp.data.SchoolModel
import com.note11.schoolinfoapp.data.UserModel
import com.note11.schoolinfoapp.databinding.ActivitySelectBinding
import com.note11.schoolinfoapp.ui.base.BaseActivity
import com.note11.schoolinfoapp.ui.screen.first.time.SetTimeActivity
import com.note11.schoolinfoapp.util.HintSpinnerAdapter

class SelectActivity : BaseActivity<ActivitySelectBinding>(R.layout.activity_select) {
    override val viewModel: SelectViewModel by viewModels()
    private lateinit var receivedInfo: SchoolModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity()
    }

    private fun initActivity() = let { act ->
        receivedInfo = intent.getParcelableExtra("schoolInfo")!!
        // todo : question 17. 전 액티비티에서 가져온 데이터를 이용해 반 데이터를 여기서 불러오려 합니다.
        viewModel.getClassList(receivedInfo)

        val gradeAdapter = HintSpinnerAdapter(act, "학년")
        val classAdapter = HintSpinnerAdapter(act, "반")

        binding.run {
            spnSelectGrade.adapter = gradeAdapter
            spnSelectClass.adapter = classAdapter

            btnSelectNext.setOnClickListener {
                // todo : question 18. 여기에 들어갈 함수로 적절한 것은?
                nextStep()
            }
        }

        viewModel.run {
            gradeList.observe(act, { gradeAdapter.setList(it) })
            classList.observe(act, { classAdapter.setList(it) })
        }
    }

    private fun nextStep() {
        val grade = (binding.spnSelectGrade.selectedItem as String).replace("학년", "")
        val classNum = (binding.spnSelectClass.selectedItem as String).replace("반", "")

        if (grade.isEmpty() || classNum.isEmpty()) {
            Toast.makeText(this, "입력하지 않은 정보가 있어요", Toast.LENGTH_SHORT).show()
            return
        }

        val info = UserModel(receivedInfo, ClassModel(grade, classNum))

        val intent = Intent(this, SetTimeActivity::class.java).putExtra("userInfo", info)
        startActivity(intent)
    }
}