package com.example.timkiemtrongdanhsach

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var studentAdapter: StudentAdapter
    private lateinit var students: List<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Tạo danh sách sinh viên mẫu
        students = listOf(
            Student("Nguyen Van A", "20210001"),
            Student("Le Thi B", "20210002"),
            Student("Tran Van C", "20210003"),
            Student("Pham Thi D", "20210004"),
            Student("Do Van E", "20210005")
        )

        // Thiết lập RecyclerView và Adapter
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        studentAdapter = StudentAdapter(students)
        recyclerView.adapter = studentAdapter

        // Thiết lập sự kiện tìm kiếm
        val etSearch = findViewById<EditText>(R.id.etSearch)
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString()
                if (query.length > 2) {
                    filterList(query)
                } else {
                    studentAdapter.updateList(students) // Hiển thị toàn bộ danh sách nếu từ khóa tìm kiếm ít hơn 3 ký tự
                }
            }
        })
    }

    // Hàm lọc danh sách sinh viên
    private fun filterList(query: String) {
        val filteredStudents = students.filter {
            it.name.contains(query, ignoreCase = true) || it.mssv.contains(query, ignoreCase = true)
        }
        studentAdapter.updateList(filteredStudents)
    }
}
