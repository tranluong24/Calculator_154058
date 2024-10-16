package android.app.calculator_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.core.text.isDigitsOnly

class MainActivity : AppCompatActivity() {

    //Ky phap nghich dao Ba Lan - chuyen tu trung to -> hau to
    /*
    * - Toan hang
    * + queue.back() != number -> push queue
    * + queue.b
    *
    * - Toan tu :
    * + rStack Empty -> push rStack
    * + rStack.top() = '(' -> push rStack
    * + Do uu tien: ^,nhan chia, +-, =
    * Lap:
    * + Lon hon rStack.top() -> push rStack
    * + Nho hon bang rStack.top()-> push rStack.top vao queue
    *
    * - '(' -> push Stack
    * - ')': (Lap )
    * + rStack.top() = '(' -> pop()
    * + rStack.top() !='(' -> push (rStack.pop()) vao queue
    *
    * - End -> rStack.top() -> queue -> DONE
    * // 5*(7-4) + 6*(3+2)
    * */

    private val calStack = ArrayDeque<Char>()
    private val calQueue = ArrayDeque<String>()
    private val rStack = ArrayDeque<Int>()

    private lateinit var num1: Button
    private lateinit var num2: Button
    private lateinit var num3: Button
    private lateinit var num4: Button
    private lateinit var num5: Button
    private lateinit var num6: Button
    private lateinit var num7: Button
    private lateinit var num8: Button
    private lateinit var num9: Button
    private lateinit var num0: Button
    private lateinit var numAdd: Button
    private lateinit var numSub: Button
    private lateinit var numNhan: Button
    private lateinit var numDiv: Button
    private lateinit var numR: Button

    private lateinit var board: TextView

    private lateinit var numCE: Button
    private lateinit var numC: Button

    private var result : Int = 0
    private var expOld: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator_constraintlayout)

        num0 = findViewById(R.id.button0)
        num1 = findViewById(R.id.button1)
        num2 = findViewById(R.id.button2)
        num3 = findViewById(R.id.button3)
        num4 = findViewById(R.id.button4)
        num5 = findViewById(R.id.button5)
        num6 = findViewById(R.id.button6)
        num7 = findViewById(R.id.button7)
        num8 = findViewById(R.id.button8)
        num9 = findViewById(R.id.button9)
        numAdd = findViewById(R.id.buttonAdd)
        numSub = findViewById(R.id.buttonSub)
        numDiv = findViewById(R.id.buttonDiv)
        numNhan = findViewById(R.id.buttonX)
        numR = findViewById(R.id.buttonS)
        numC = findViewById(R.id.buttonC)
        numCE = findViewById(R.id.buttonCE)

        board = findViewById(R.id.board)

        numC.setOnClickListener {
            board.text = ""
        }

        numCE.setOnClickListener {
            if(board.text.toString().contains('=')){
                board.text = board.text.toString().substringAfter('=').substring(1).dropLast(1)
            }else {
                board.text = board.text.toString().dropLast(1)
            }
        }

        num0.setOnClickListener {
            if(board.text.isEmpty()){
                board.text = board.text.toString() + "0"
            }else if(board.text.toString().substringAfter('=').substring(1) == result.toString()){
                board.text = "0"
            }else{
                board.text = board.text.toString() + "0"
            }
        }

        num1.setOnClickListener {
            if(board.text.isEmpty()){
                board.text = board.text.toString() + "1"
            }else if(board.text.toString().substringAfter('=').substring(1) == result.toString()){
                board.text = "1"
            }else{
                board.text = board.text.toString() + "1"
            }
        }

        num2.setOnClickListener {
            if(board.text.isEmpty()){
                board.text = board.text.toString() + "2"
            }else if(board.text.toString().substringAfter('=').substring(1) == result.toString()){
                board.text = "2"
            }else{
                board.text = board.text.toString() + "2"
            }
        }

        num3.setOnClickListener {
            if(board.text.isEmpty()){
                board.text = board.text.toString() + "3"
            }else if(board.text.toString().substringAfter('=').substring(1) == result.toString()){
                board.text = "3"
            }else{
                board.text = board.text.toString() + "3"
            }
        }

        num4.setOnClickListener {
            if(board.text.isEmpty()){
                board.text = board.text.toString() + "4"
            }else if(board.text.toString().substringAfter('=').substring(1) == result.toString()){
                board.text = "4"
            }else{
                board.text = board.text.toString() + "4"
            }
        }

        num5.setOnClickListener {
            if(board.text.isEmpty()){
                board.text = board.text.toString() + "5"
            }else if(board.text.toString().substringAfter('=').substring(1) == result.toString()){
                board.text = "5"
            }else{
                board.text = board.text.toString() + "5"
            }
        }

        num6.setOnClickListener {
            if(board.text.isEmpty()){
                board.text = board.text.toString() + "6"
            }else if(board.text.toString().substringAfter('=').substring(1) == result.toString()){
                board.text = "6"
            }else{
                board.text = board.text.toString() + "6"
            }
        }

        num7.setOnClickListener {
            if(board.text.isEmpty()){
                board.text = board.text.toString() + "7"
            }else if(board.text.toString().substringAfter('=').substring(1) == result.toString()){
                board.text = "7"
            }else{
                board.text = board.text.toString() + "7"
            }
        }

        num8.setOnClickListener {
            if(board.text.isEmpty()){
                board.text = board.text.toString() + "8"
            }else if(board.text.toString().substringAfter('=').substring(1) == result.toString()){
                board.text = "8"
            }else{
                board.text = board.text.toString() + "8"
            }
        }

        num9.setOnClickListener {
            if(board.text.isEmpty()){
                board.text = board.text.toString() + "9"
            }else if(board.text.toString().substringAfter('=').substring(1) == result.toString()){
                board.text = "9"
            }else{
                board.text = board.text.toString() + "9"
            }
        }

        numAdd.setOnClickListener {
            if(board.text.isEmpty()){
                board.text = board.text.toString() + "0+"
            }else if(board.text.toString().substringAfter('=').substring(1) == result.toString()){
                board.text = result.toString() + "+"
            } else if(board.text.last().isDigit()) {
                board.text = board.text.toString() + "+"
            }
        }

        numSub.setOnClickListener {
            if(board.text.isEmpty()){
                board.text = board.text.toString() + "0-"
            }else if(board.text.toString().substringAfter('=').substring(1) == result.toString()){
                board.text = result.toString() + "-"
            } else if(board.text.last().isDigit()) {
                board.text = board.text.toString() + "-"
            }
        }

        numNhan.setOnClickListener {
            if(board.text.isEmpty()){
                board.text = board.text.toString() + "0x"
            }else if (board.text.contains('=')){
                board.text = result.toString() + "x"
            }else if(board.text.last().isDigit()) {
                board.text = board.text.toString() + "x"
            }
        }

        numDiv.setOnClickListener {
            if(board.text.isEmpty()){
                board.text = board.text.toString() + "0/"
            }else if (board.text.contains('=')){
                board.text = result.toString() + "/"
            }else if(board.text.last().isDigit()) {
                board.text = board.text.toString() + "/"
            }
        }

        numR.setOnClickListener {
            if(board.text.last().isDigit()) {
                expOld = board.text.toString() + "="
                changePostFix(board.text.toString())
                Log.d("TESSw", calQueue.toString())
                for (i in calQueue) {
                    if (i.toIntOrNull() != null) {
                        rStack.addLast(i.toInt())
                    } else {
                        val a: Int = rStack.removeLast()
                        val b: Int = rStack.removeLast()
                        if (i[0] == '+') {
                            rStack.addLast(a + b);
                        } else if (i[0] == '-') {
                            rStack.addLast(b - a);
                        } else if (i[0] == 'x') {
                            rStack.addLast(a * b);
                        } else if (i[0] == '/') {
                            rStack.addLast(b / a);
                        }
                    }
                }
                board.text = expOld + "\n" + rStack.last()
                result = rStack.last().toString().toInt()
            }
        }
    }

    private fun changePostFix(exp: String) {
        exp.replace(" ", "");
        var numberLast: Boolean = false;
        var number: String = "";


        for (c in exp) {
            Log.d("STT", "Stack: $calStack $c");
            Log.d("STT", "Queue: $calQueue $c");
            if (c.isDigit()) {
                if (numberLast) {
                    number += c
                } else {
                    number = c + ""
                    Log.d("TG", number)
                }
                numberLast = true;

            } else {
                if (numberLast) {
                    calQueue.addLast(number);
                    numberLast = false;
                    number = "";
                }
                if (c == '(') {
                    calStack.addLast(c);
                } else if (c == ')') {
                    while (calStack.last() != '(') {
                        calQueue.addLast(calStack.removeLast().toString())
                    }
                    calStack.removeLast();
                } else {
                    if (calStack.isEmpty() || calStack.last() == '(') {
                        calStack.addLast(c);
                    } else {
                        while (doUuTien(c) <= doUuTien(calStack.last())) {
                            calQueue.addLast(calStack.removeLast().toString());
                            if (calStack.isEmpty()) {
                                break
                            }
                        }
                        calStack.addLast(c);
                    }
                }
            }
        }
        if (numberLast) {
            calQueue.addLast(number);
        }

        while (!calStack.isEmpty()) {
            calQueue.addLast(calStack.removeLast().toString())
        }

        Log.d("FF",calQueue.toString())
    }

    private fun doUuTien(c: Char): Int {
        return when (c) {
            '+', '-' -> 1
            'x', '/' -> 2
            else -> 0
        }
    }
}