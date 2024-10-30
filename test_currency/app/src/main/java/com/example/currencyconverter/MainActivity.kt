import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Khai báo và xử lý các thành phần UI trong `activity_main.xml`
        val currencyFromSpinner = findViewById<Spinner>(R.id.currency_from_spinner)
        val currencyToSpinner = findViewById<Spinner>(R.id.currency_to_spinner)
        val amountFromEditText = findViewById<EditText>(R.id.amount_from_edittext)
        val resultTextView = findViewById<TextView>(R.id.result_textview)
        val swapButton = findViewById<Button>(R.id.swap_button)

        // Thiết lập dữ liệu cho Spinner, lắng nghe sự kiện, và viết logic chuyển đổi ở đây
        setupSpinners(currencyFromSpinner, currencyToSpinner)
        setupListeners(amountFromEditText, currencyFromSpinner, currencyToSpinner, resultTextView, swapButton)
    }

    private fun setupSpinners(fromSpinner: Spinner, toSpinner: Spinner) {
        val currencies = listOf("USD", "EUR", "VND")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        fromSpinner.adapter = adapter
        toSpinner.adapter = adapter
    }

    private fun setupListeners(
        amountEditText: EditText,
        fromSpinner: Spinner,
        toSpinner: Spinner,
        resultTextView: TextView,
        swapButton: Button
    ) {
        amountEditText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                convertCurrency(amountEditText, fromSpinner, toSpinner, resultTextView)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        })

        fromSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                convertCurrency(amountEditText, fromSpinner, toSpinner, resultTextView)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        toSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                convertCurrency(amountEditText, fromSpinner, toSpinner, resultTextView)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        swapButton.setOnClickListener {
            val fromPosition = fromSpinner.selectedItemPosition
            fromSpinner.setSelection(toSpinner.selectedItemPosition)
            toSpinner.setSelection(fromPosition)
            convertCurrency(amountEditText, fromSpinner, toSpinner, resultTextView)
        }
    }

    private fun convertCurrency(
        amountEditText: EditText,
        fromSpinner: Spinner,
        toSpinner: Spinner,
        resultTextView: TextView
    ) {
        val amountText = amountEditText.text.toString()
        if (amountText.isNotEmpty()) {
            val amount = amountText.toDouble()
            val fromCurrency = fromSpinner.selectedItem.toString()
            val toCurrency = toSpinner.selectedItem.toString()
            val exchangeRate = getExchangeRate(fromCurrency, toCurrency)
            val result = amount * exchangeRate
            resultTextView.text = String.format("%.2f %s", result, toCurrency)
        }
    }

    private fun getExchangeRate(fromCurrency: String, toCurrency: String): Double {
        // Giả định một số tỷ giá, có thể thay bằng API thực
        return when {
            fromCurrency == "USD" && toCurrency == "VND" -> 25000.0
            fromCurrency == "VND" && toCurrency == "USD" -> 1 / 25000.0
            else -> 1.0
        }
    }
}
