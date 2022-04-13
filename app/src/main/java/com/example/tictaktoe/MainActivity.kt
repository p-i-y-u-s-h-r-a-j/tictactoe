package com.example.tictaktoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    var PLAYER = true
    var TURN_COUNT = 0
    var boardStatus = Array(3){IntArray(3)}

    lateinit var board: Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1 = findViewById<Button>(R.id.button1)
        val btn2 = findViewById<Button>(R.id.button2)
        val btn3 = findViewById<Button>(R.id.button3)
        val btn4 = findViewById<Button>(R.id.button4)
        val btn5 = findViewById<Button>(R.id.button5)
        val btn6 = findViewById<Button>(R.id.button6)
        val btn7 = findViewById<Button>(R.id.button7)
        val btn8 = findViewById<Button>(R.id.button8)
        val btn9 = findViewById<Button>(R.id.button9)
        val resetbtn = findViewById<Button>(R.id.Resetbtn)
        var textview = findViewById<TextView>(R.id.textview)




        board = arrayOf(
            arrayOf(btn1, btn2, btn3),
            arrayOf(btn4, btn5, btn6),
            arrayOf(btn7, btn8, btn9)
        )

        for(i in board){
            for (button in i){
                button.setOnClickListener(this)
            }

            intializeBoardStatus()
        }
        resetbtn.setOnClickListener {
            TURN_COUNT = 0;
            PLAYER = true
            textview.text = "Player X Turn"
            intializeBoardStatus()

        }
    }

    private fun intializeBoardStatus() {
        for(i in 0..2){
            for(j in 0..2){
                boardStatus[i][j] = -1

            }
        }
        for(i in board){
            for(button in i){
                button.isEnabled = true
                button.text = ""
            }
        }
    }


    override fun onClick(view: View) {
        when(view.id){
            R.id.button1 ->{
                updateValue(row = 0, col = 0, player = PLAYER)
            }
            R.id.button2 ->{
                updateValue(row = 0, col = 1, player = PLAYER)
            }
            R.id.button3 ->{
                updateValue(row = 0, col = 2, player = PLAYER)

            }
            R.id.button4 ->{
                updateValue(row = 1, col = 0, player = PLAYER)

            }
            R.id.button5 ->{
                updateValue(row = 1, col = 1, player = PLAYER)

            }
            R.id.button6 ->{
                updateValue(row = 1, col = 2, player = PLAYER)

            }
            R.id.button7 ->{
                updateValue(row = 2, col = 0, player = PLAYER)

            }
            R.id.button8 ->{
                updateValue(row = 2, col = 1, player = PLAYER)

            }
            R.id.button9 ->{
                updateValue(row = 2, col = 2, player = PLAYER)
            }
        }
        PLAYER = !PLAYER
        TURN_COUNT++

        if(PLAYER){
            updateDisplay("Player X Turn")
        }else{
            updateDisplay("Player 0 Turn")
        }
        if(TURN_COUNT == 9){
            updateDisplay("Game Drawn..!")
        }
        checkWinner()
    }

    private fun checkWinner() {
//        Horizontal Winner Check
        for(i in 0..2){
            if(boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2]){
                if(boardStatus[i][0] == 1){
                    updateDisplay("Player X is Winner..!!")
                    break
                }else if(boardStatus[i][0] == 0){
                    updateDisplay("Player 0 is Winner..!!")
                    break
                }
            }
        }
//        vertical winner check
        for(i in 0..2){
            if(boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i]){
                if(boardStatus[0][i] == 1){
                    updateDisplay("Player X is Winner..!!")
                    break
                }else if(boardStatus[0][i] == 0){
                    updateDisplay("Player 0 is Winner..!!")
                    break
                }
            }
        }
//        1st Diagonal winner check
        if(boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2]){
            if(boardStatus[0][0] == 1){
                updateDisplay("Player X is Winner..!!")

            }else if(boardStatus[0][0] == 0){
                updateDisplay("Player 0 is Winner..!!")

            }
        }
//               2nd Diagonal winner check
        if(boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0]){
            if(boardStatus[0][2] == 1){
                updateDisplay("Player X is Winner..!!")

            }else if(boardStatus[0][2] == 0){
                updateDisplay("Player 0 is Winner..!!")

            }
        }

    }

    private fun updateDisplay(s:String){
        textview.text = s
        if(s.contains("Winner")){
            disableButton()
        }
    }

    private fun disableButton() {
        for(i in board){
            for(button in i){
                button.isEnabled = false
            }
        }
    }

    private fun updateValue(row:Int, col:Int, player:Boolean){
        val text = if(player) "X" else "0"
        val value: Int = if(player) 1 else 0

        board[row][col].apply {
            isEnabled = false
            setText(text)
        }
        boardStatus[row][col] = value

    }
}