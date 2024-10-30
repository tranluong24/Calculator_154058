package android.app.calculator_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import java.text.DecimalFormat

class CurrencyActivity : AppCompatActivity() {


    private val listCurrConvert = mutableMapOf(
        //don vi so voi USD
        "Việt Nam - Đồng" to 1,
        "Hoa Kỳ - Đô la Mỹ" to 25294.94,
        "Liên minh Châu Âu - Euro" to 27380,
        "Nhật Bản - Yên" to 165.38,
        "Hàn Quốc - Won" to 18.357,
        "Trung Quốc - Nhân dân tệ" to 3549.92,
        "Thái Lan - Baht" to 749.37
    )
    private var indexCurr_1 : Int = 0;
    private var indexCurr_2 : Int = 0;

    //Tao 1 list raw -  don vi tien te
    private lateinit var listCurr : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency)

        listCurr = resources.getStringArray(R.array.currency)

        initEditText(R.id.edtSrc)
        initEditText(R.id.edtDes)

        initSpinner(R.id.spCurSrc)
        initSpinner(R.id.spCurDes)
    }

    private fun initEditText(id: Int){
        val edt_1 = findViewById<EditText>(R.id.edtSrc)
        val edt_2 = findViewById<EditText>(R.id.edtDes)
        val edt = findViewById<EditText>(id)

        edt.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {

                if (edt.text.isEmpty() && edt.isFocused) {
                    edt.setText("0") // Đặt EditText 2 thành 0
                    edt.setSelection(1)
                    return
                }

                if (!this@CurrencyActivity.isInteger(edt.text.toString()) && edt.isFocused) {
                    Log.d("tds","VAO DAY")
                    edt.setText("1")
                    edt.setSelection(edt.text.length)
                    return
                }

                if(edt.text.first() == '0' && edt.text.length >1 && edt.isFocused){
                    edt.setText(edt.text.substring(1))
                    edt.setSelection(edt.text.length)
                }


                if(id == R.id.edtSrc){
                    if(edt_1.isFocused) {
                        val donviSrc = listCurrConvert[listCurr[indexCurr_1]].toString().toFloat()
                        val value_src = edt_1.text.toString().toInt()
                        val donvi_Des = listCurrConvert[listCurr[indexCurr_2]].toString().toFloat()

                        val result = (donviSrc * value_src) / donvi_Des

//                        edt_1.removeTextChangedListener(this) // Gỡ bỏ listener để tránh vòng lặp vô tận
                        edt_2.setText(String.format("%.2f", result))
//                        edt_1.addTextChangedListener(this)
                    }
                }
                else if(id == R.id.edtDes){
                    if(edt_2.isFocused ) {
                        val donviSrc = listCurrConvert[listCurr[indexCurr_2]].toString().toFloat()
                        val value_src = edt_2.text.toString().toInt()
                        val donvi_Des = listCurrConvert[listCurr[indexCurr_1]].toString().toFloat()

                        val result = (donviSrc * value_src) / donvi_Des

//                        edt_2.removeTextChangedListener(this) // Gỡ bỏ listener để tránh vòng lặp vô tận
                        edt_1.setText(String.format("%.2f", result))
//                        edt_2.addTextChangedListener(this) // Thêm lại listener
                    }
                }
            }

        })
    }

    fun isInteger(input: String): Boolean {
        return try {
            input.toInt()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }


    private fun initSpinner(id: Int){
        val spCurr = findViewById<Spinner>(id)
        //Khai bao bo dieu huong (adapter)
        //context - layout - list raw
        val adapterCurr = ArrayAdapter(this,
            android.R.layout.simple_spinner_item,
            listCurr)

        spCurr.adapter = adapterCurr

        //onItemSel.sected listener
        spCurr.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(id == R.id.spCurDes){
                    indexCurr_2 = p2
                }else if (id == R.id.spCurSrc){
                    indexCurr_1 = p2
                }
                Toast.makeText(this@CurrencyActivity, "Selected "+ listCurr[p2],  Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }
}