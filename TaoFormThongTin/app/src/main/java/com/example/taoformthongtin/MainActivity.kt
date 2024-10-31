package com.example.taoformthongtin

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.taoformthongtin.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ các thành phần từ layout
        val etMSSV: EditText = findViewById(R.id.etMSSV)
        val etHoTen: EditText = findViewById(R.id.etHoTen)
        val rgGioiTinh: RadioGroup = findViewById(R.id.rgGioiTinh)
        val etEmail: EditText = findViewById(R.id.etEmail)
        val etSoDienThoai: EditText = findViewById(R.id.etSoDienThoai)
        val btnChonNgaySinh: Button = findViewById(R.id.btnChonNgaySinh)
        val calendarView: CalendarView = findViewById(R.id.calendarView)
        val spinnerTinh: Spinner = findViewById(R.id.spinnerTinh)
        val cbTheThao: CheckBox = findViewById(R.id.cbTheThao)
        val cbDienAnh: CheckBox = findViewById(R.id.cbDienAnh)
        val cbAmNhac: CheckBox = findViewById(R.id.cbAmNhac)
        val cbDongY: CheckBox = findViewById(R.id.cbDongY)
        val btnSubmit: Button = findViewById(R.id.btnSubmit)

        // Hiển thị hoặc ẩn CalendarView khi nhấn nút Chọn Ngày Sinh
        btnChonNgaySinh.setOnClickListener {
            calendarView.visibility = if (calendarView.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }

        // Xử lý sự kiện khi nhấn nút Submit
        btnSubmit.setOnClickListener {
            if (validateForm()) {
                Toast.makeText(this, "Form hợp lệ!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Hàm kiểm tra tính hợp lệ của form
    private fun validateForm(): Boolean {
        val etMSSV: EditText = findViewById(R.id.etMSSV)
        val etHoTen: EditText = findViewById(R.id.etHoTen)
        val rgGioiTinh: RadioGroup = findViewById(R.id.rgGioiTinh)
        val etEmail: EditText = findViewById(R.id.etEmail)
        val etSoDienThoai: EditText = findViewById(R.id.etSoDienThoai)
        val cbDongY: CheckBox = findViewById(R.id.cbDongY)

        return etMSSV.text.isNotEmpty() &&
                etHoTen.text.isNotEmpty() &&
                rgGioiTinh.checkedRadioButtonId != -1 &&
                etEmail.text.isNotEmpty() &&
                etSoDienThoai.text.isNotEmpty() &&
                cbDongY.isChecked
    }
}
