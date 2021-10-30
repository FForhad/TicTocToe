package com.example.tictoctoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.WindowId
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

var p1 = 0
var p2 = 0
class MainActivity : AppCompatActivity() {
    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btPt.text = "P1 = $p1       P2 = $p2"
        rtSet.setOnClickListener{
            p1 = 0
            p2 = 0
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun btClick(view: View){
        val btSelected = view as Button
        var callId = 0
        when(btSelected.id){
            R.id.bk1 -> callId = 1
            R.id.bk2 -> callId = 2
            R.id.bk3 -> callId = 3
            R.id.bk4 -> callId = 4
            R.id.bk5 -> callId = 5
            R.id.bk6 -> callId = 6
            R.id.bk7 -> callId = 7
            R.id.bk8 -> callId = 8
            R.id.bk9 -> callId = 9
        }

        //Log.d("btClick:", btSelected.id.toString())
        //Log.d("btClick: CallId:",callId.toString())

        playGame(callId,btSelected)
    }

    var activePlayer = 1

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()

    var tap = 0
    fun playGame(callId:Int,btSelected:Button){

        if(activePlayer == 1){
            btSelected.text = "x"
            btSelected.setBackgroundResource(R.color.tapBlocks)
            player1.add(callId)
            activePlayer = 2
        }
        else{
            btSelected.text = "0"
            btSelected.setBackgroundResource(R.color.cuty)
            player2.add(callId)
            activePlayer = 1
        }

        btSelected.isEnabled = false
        tap += 1

        checkWinner(tap)
    }
    fun checkWinner(tap : Int){
        var winner = -1
        val pointView = findViewById(R.id.btPt) as TextView

        //row1
        if(player1.contains(1) && player1.contains(2) && player1.contains(3)){
            winner = 1
        }
        if(player2.contains(1) && player2.contains(2) && player2.contains(3)){
            winner = 2
        }

        //row2
        if(player1.contains(4) && player1.contains(5) && player1.contains(6)){
            winner = 1
        }
        if(player2.contains(4) && player2.contains(5) && player2.contains(6)){
            winner = 2
        }

        //row3
        if(player1.contains(7) && player1.contains(8) && player1.contains(9)){
            winner = 1
        }
        if(player2.contains(7) && player2.contains(8) && player2.contains(9)){
            winner = 2
        }

        //col1
        if(player1.contains(1) && player1.contains(4) && player1.contains(7)){
            winner = 1
        }
        if(player2.contains(1) && player2.contains(4) && player2.contains(7)){
            winner = 2
        }

        //col2
        if(player1.contains(2) && player1.contains(5) && player1.contains(8)){
            winner = 1
        }
        if(player2.contains(2) && player2.contains(5) && player2.contains(8)){
            winner = 2
        }

        //col3
        if(player1.contains(3) && player1.contains(6) && player1.contains(9)){
            winner = 1
        }
        if(player2.contains(3) && player2.contains(6) && player2.contains(9)){
            winner = 2
        }

        //rightCross
        if(player1.contains(1) && player1.contains(5) && player1.contains(9)){
            winner = 1
        }
        if(player2.contains(1) && player2.contains(5) && player2.contains(9)){
            winner = 2
        }

        //leftCross
        if(player1.contains(3) && player1.contains(5) && player1.contains(7)){
            winner = 1
        }
        if(player2.contains(3) && player2.contains(5) && player2.contains(7)){
            winner = 2
        }


        if(winner == 1){
            Toast.makeText(this,"player 1 wins",Toast.LENGTH_LONG).show()
            p1 += 1
            this.tap = 0

            handler = Handler()
            handler.postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            //btPt.text = "P1 = $p1       P2 = $p2"
        },1500)}
        else if(winner == 2){
            Toast.makeText(this,"player 2 wins",Toast.LENGTH_LONG).show()
            p2 += 1
            this.tap = 0

            handler = Handler()
            handler.postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            //btPt.text = "P1 = $p1       P2 = $p2"
        },1500)}
        else if((winner != 1 || winner != 2) && tap == 9){
            this.tap = 0

            Toast.makeText(this,"Match draw",Toast.LENGTH_LONG).show()

            handler = Handler()
            handler.postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        },1500)}
    }

}