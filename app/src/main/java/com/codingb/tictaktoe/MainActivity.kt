package com.codingb.tictaktoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , OnClickListener {

    lateinit var board: Array<Array<Button>>    //declaring a 2d array to store id of each button in the app
    var PLAYER = true
    var turn_count = 0
    var board_status = Array(3){ IntArray(3)}   //2d array to store the status of the board during gameplay

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board = arrayOf(                           //initializing this 2d array contaning each button reference
            arrayOf(button , button2 , button3),
            arrayOf(button4 , button5 , button6),
            arrayOf(button7 , button8 , button9)
        )

        for(i:Array<Button> in board){      //seting onClickListner for each tile
            for(button:Button in i){
                button.setOnClickListener(this)
            }
        }

        initializeBoardStatus()

        resetBtn.setOnClickListener {
            PLAYER = true                   //function to reset the game
            turn_count = 0
            initializeBoardStatus()
        }

        //oncreate ending
    }

    //all functions defined below that are used above
    private fun initializeBoardStatus() {
        for(i in 0..2) {
            for (j in 0..2) {
                board_status[i][j] = -1     //intitializing board status -1
            }
        }
        for(i:Array<Button> in board) {
            for(button:Button in i) {
                button.isEnabled = true     //enabling all the buttons
                button.text = ""
            }
        }
    }

    override fun onClick(p0: View) {    //function to perform actions for click on individual tile
        when(p0.id){
            R.id.button ->{
                updateValue(row = 0, col = 0 , player = PLAYER )
            }
            R.id.button2 ->{
                updateValue(row = 0, col = 1 , player = PLAYER )
            }
            R.id.button3 ->{
                updateValue(row = 0, col = 2 , player = PLAYER )
            }
            R.id.button4 ->{
                updateValue(row = 1, col = 0 , player = PLAYER )
            }
            R.id.button5 ->{
                updateValue(row = 1, col = 1 , player = PLAYER )
            }
            R.id.button6 ->{
                updateValue(row = 1, col = 2 , player = PLAYER )
            }
            R.id.button7 ->{
                updateValue(row = 2, col = 0 , player = PLAYER )
            }
            R.id.button8 ->{
                updateValue(row = 2, col = 1 , player = PLAYER )
            }
            R.id.button9 ->{
                updateValue(row = 2, col = 2 , player = PLAYER )
            }
        }

        PLAYER = !PLAYER    //make the button disable after clicking once
        turn_count++        //couting on the no of clicks

        if(PLAYER){
            updateDisplay("Player X turn")
        }else{
            updateDisplay("Player 0 turn")
        }

        if(turn_count == 9){
            updateDisplay("Game Draw")
        }

        checkWinner()
    }

    private fun checkWinner() {
        //horizontally is rows
        for(i in 0..2){
            if(board_status[i][0] == board_status[i][1] && board_status[i][0] == board_status[i][2] )   {
                if(board_status[i][0] == 1){
                    updateDisplay("Player X won")
                    break
                }else if(board_status[i][0] == 0) {
                    updateDisplay("Player 0 won")
                    break
                }
            }
        }

        //vertically for coloumns
        for(i in 0..2){
                if(board_status[0][i] == board_status[1][i] && board_status[0][i] == board_status[2][i] ){
                    if(board_status[0][i] == 1) {
                        updateDisplay("Player X won")
                        break
                    } else if(board_status[0][i] == 0) {
                        updateDisplay("Player 0 won")
                        break
                    }
                }
        }
        //for first diagnol
        if(board_status[0][0] == board_status[1][1] && board_status[0][0]== board_status[2][2]){
            if(board_status[0][0] == 1) {
                updateDisplay("Player X won")
            } else if(board_status[0][0] == 0) {
                updateDisplay("Player 0 won")
            }
        }
        //for first diagnol
        if(board_status[0][2] == board_status[1][1] && board_status[0][2]== board_status[2][0]){
            if(board_status[0][2] == 1) {
                updateDisplay("Player X won")
            } else if(board_status[0][2] == 0) {
                updateDisplay("Player 0 won")
            }
        }
    }

    private fun updateDisplay(text: String) {   //for updating the text view at the top
        displayTv.text = text
        if(text.contains("won")){
            disableButton()
        }
    }

    private fun disableButton() {       //for disabling the buttons after the game ends
        for(i:Array<Button> in board){
            for(button:Button in i){
                button.isEnabled = false
            }
        }
    }

    //updating the board after every click on each tile
    private fun updateValue(row: Int, col: Int, player: Boolean) {  //updating each tile after the click
        val text = if(player) "X" else "0"
        val value = if(player) 1 else 0
         board[row][col].apply{
             isEnabled = false
             setText(text)
         }
        board_status[row][col] = value

    }


}