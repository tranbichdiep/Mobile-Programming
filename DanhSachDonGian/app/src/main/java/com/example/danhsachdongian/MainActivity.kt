package com.example.danhsachdongian

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.danhsachdongian.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Tham chiếu tới các thành phần UI trong layout
        val inputNumber = findViewById<EditText>(R.id.input_number)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val buttonShow = findViewById<Button>(R.id.button_show)
        val listView = findViewById<ListView>(R.id.list_view)
        val textViewError = findViewById<TextView>(R.id.text_view_error)

        // Thiết lập sự kiện khi nhấn nút Show
        buttonShow.setOnClickListener {
            val numberText = inputNumber.text.toString()

            // Kiểm tra nếu đầu vào là rỗng hoặc không hợp lệ
            if (numberText.isEmpty()) {
                textViewError.text = "Vui lòng nhập số hợp lệ."
                textViewError.visibility = View.VISIBLE
                listView.adapter = null
                return@setOnClickListener
            }

            val n = numberText.toIntOrNull()
            if (n == null || n < 0) {
                textViewError.text = "Vui lòng nhập số nguyên dương.textViewError.visibility = View.VISIBLE
                listView.adapter = null
                return@setOnClickListener
            }

            // Nếu đầu vào hợp lệ, ẩn thông báo lỗi
            textViewError.visibility = View.GONE

            // Lấy lựa chọn từ RadioButton
            val selectedRadio = radioGroup.checkedRadioButtonId
            val numbers = when (selectedRadio) {
                R.id.radio_even -> (0..n).filter { it % 2 == 0 }
                R.id.radio_odd -> (1..n).filter { it % 2 != 0 }
                R.id.radio_square -> (0..n).filter { Math.sqrt(it.toDouble()).toInt().let { sq -> sq * sq == it } }
                else -> listOf()
            }

            // Hiển thị danh sách số thoả mãn trong ListView
            listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
        }
    }
}
