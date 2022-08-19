package com.example.gpsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_lyric_list.*

class LyricListActivity : AppCompatActivity() {

    //Create form and call functions to populate the textboxes
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lyric_list)
        showLyrics()
        showHints()
    }

    private fun showLyrics(){
        txtLyrics.setText(toString())
    }

    /*Cycle through list adding each element to the textbox and format with a newline
    for readability*/
    override fun toString():String{
        var toString =""
        for(collectedLyric in MapsActivity.collectedSongList){
            toString = toString + collectedLyric + "\n"
        }
        return toString
    }

    /*Show hints to the user, if the user has collected 1 lyric show the user 1 hint
    show another after 5 and another after 8, this is done by checking the size of
    the list that holds the collected lyrics
     */
    private fun showHints(){
        if(MapsActivity.collectedSongList.size > 0){
            txtHintAnswer.setText(MapsActivity.year)
        }
        if(MapsActivity.collectedSongList.size > 4){
            txtHintAnswer.setText(MapsActivity.genre)
        }
        if(MapsActivity.collectedSongList.size > 8) {
            txtHintAnswer.setText(MapsActivity.artist)
        }

    }
}
