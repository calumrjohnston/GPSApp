package com.example.gpsapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_guess.*
import kotlin.random.Random

class GuessActivity : AppCompatActivity() {

    //Create window and populate textboxes
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guess)
        var livesValue:String = MapsActivity.lives.toString()
        txtLivesDisplay.text = livesValue
        nameButtons()
    }

    /*Button functions work by checking if the text in the textbox above is the same as
    the correct title and artist of the song, if they make a correct guess they win the
    game, if it isn't deduct a life and if they are out of lives end the game and they lose
     */
    fun buttonOne(view: View){
        if(txtArtistAndTitle.text.equals(MapsActivity.artist + " - " + MapsActivity.songTitle)){
            win()
        }else{
            MapsActivity.lives = MapsActivity.lives - 1
            var livesValue:String = MapsActivity.lives.toString()
            txtLivesDisplay.text = livesValue
        }
        if (MapsActivity.lives < 1){
            lose()

        }
    }
    fun buttonTwo(view: View){
        if(txtArtistAndTitle2.text.equals(MapsActivity.artist + " - " + MapsActivity.songTitle)){
            win()
        }else{
            MapsActivity.lives = MapsActivity.lives - 1
            var livesValue:String = MapsActivity.lives.toString()
            txtLivesDisplay.text = livesValue
        }
        if (MapsActivity.lives < 1){
            lose()
        }
    }
    fun buttonThree(view: View){
        if(txtArtistAndTitle3.text.equals(MapsActivity.artist + " - " + MapsActivity.songTitle)){
            win()
        }else{
            MapsActivity.lives = MapsActivity.lives - 1
            var livesValue:String = MapsActivity.lives.toString()
            txtLivesDisplay.text = livesValue
        }
        if (MapsActivity.lives < 1){
            lose()
        }
    }

    fun buttonFour(view: View){
        if(txtArtistAndTitle4.text.equals(MapsActivity.artist + " - " + MapsActivity.songTitle)){
            win()
        }else{
            MapsActivity.lives = MapsActivity.lives - 1
            var livesValue:String = MapsActivity.lives.toString()
            txtLivesDisplay.text = livesValue
        }
        if (MapsActivity.lives < 1){
            lose()
        }
    }

    //Win and lose functions handle win and loss cases
    fun win(){
        val win = Intent(this, WinActivity::class.java)
        startActivity(win)
    }

    fun lose(){
        val lose = Intent(this, LoseActivity::class.java)
        startActivity(lose)
    }


    /*Generate a set of random numbers that aren't the same, use these numbers to assort
    text boxes to hold artist and song title

     */
    fun nameButtons(){

        var randButtonOne = Random.nextInt(1, 5)
        var randButtonTwo = Random.nextInt(1, 5)
        var randButtonThree = Random.nextInt(1, 5)
        var randButtonFour = Random.nextInt(1, 5)

        while(randButtonTwo == randButtonOne){
            randButtonTwo = Random.nextInt(1, 5)
        }
        while(randButtonThree == randButtonOne|| randButtonThree == randButtonTwo){
            randButtonThree = Random.nextInt(1, 5)
        }
        while(randButtonFour == randButtonOne || randButtonFour == randButtonTwo || randButtonFour == randButtonThree){
            randButtonFour = Random.nextInt(1, 5)
        }

        if (randButtonOne == 1){
            txtArtistAndTitle.setText(MapsActivity.artist + " - " + MapsActivity.songTitle)
        } else if (randButtonOne == 2){
            txtArtistAndTitle2.setText(MapsActivity.artist + " - " + MapsActivity.songTitle)
        } else if (randButtonOne == 3){
            txtArtistAndTitle3.setText(MapsActivity.artist + " - " + MapsActivity.songTitle)
        }else {
            txtArtistAndTitle4.setText(MapsActivity.artist + " - " + MapsActivity.songTitle)
        }
        if (randButtonTwo == 1){
            txtArtistAndTitle.setText(MapsActivity.wrongArtistOne + " - " + MapsActivity.wrongsongTitleOne)
        } else if (randButtonTwo == 2){
            txtArtistAndTitle2.setText(MapsActivity.wrongArtistOne + " - " + MapsActivity.wrongsongTitleOne)
        } else if (randButtonTwo == 3){
            txtArtistAndTitle3.setText(MapsActivity.wrongArtistOne + " - " + MapsActivity.wrongsongTitleOne)
        }else {
            txtArtistAndTitle4.setText(MapsActivity.wrongArtistOne + " - " + MapsActivity.wrongsongTitleOne)
        }

        if (randButtonThree == 1){
            txtArtistAndTitle.setText(MapsActivity.wrongArtistTwo + " - " + MapsActivity.wrongsongTitleTwo)
        } else if (randButtonThree == 2){
            txtArtistAndTitle2.setText(MapsActivity.wrongArtistTwo + " - " + MapsActivity.wrongsongTitleTwo)
        } else if (randButtonThree == 3){
            txtArtistAndTitle3.setText(MapsActivity.wrongArtistTwo + " - " + MapsActivity.wrongsongTitleTwo)
        }else {
            txtArtistAndTitle4.setText(MapsActivity.wrongArtistTwo + " - " + MapsActivity.wrongsongTitleTwo)
        }

        if (randButtonFour == 1){
            txtArtistAndTitle.setText(MapsActivity.wrongArtistThree + " - " + MapsActivity.wrongsongTitleThree)
        } else if (randButtonFour == 2){
            txtArtistAndTitle2.setText(MapsActivity.wrongArtistThree + " - " + MapsActivity.wrongsongTitleThree)
        } else if (randButtonFour == 3){
            txtArtistAndTitle3.setText(MapsActivity.wrongArtistThree + " - " + MapsActivity.wrongsongTitleThree)
        }else {
            txtArtistAndTitle4.setText(MapsActivity.wrongArtistThree + " - " + MapsActivity.wrongsongTitleThree)
        }

    }
}
