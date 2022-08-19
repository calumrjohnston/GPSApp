package com.example.gpsapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buttonCurrent(view: View) {
        //Generate a set of random numbers
        var randCurrent:Int = Random.nextInt(20, 40)
        var randOtherOne:Int = Random.nextInt(20, 40)
        var randOtherTwo:Int = Random.nextInt(20, 40)
        var randOtherThree:Int = Random.nextInt(20, 40)

        var randCurrentString:String = randCurrent.toString()
        val currentString = "Current/" + randCurrentString + ".txt"
        //Create a filepath string so it can be accessed
        val currentStream = assets.open(currentString)
        val currentReader = currentStream.bufferedReader()
        val currentPart: List<String> = currentReader.readLines()
        //Retrieve variables from the file
        val line1 = currentPart[0]
        val line2 = currentPart[1]
        val line3 = currentPart[2]
        val line4 = currentPart[3]
        val line5 = currentPart[4]
        val line6 = currentPart[5]
        val line7 = currentPart[6]
        val line8 = currentPart[7]
        val line9 = currentPart[8]
        val line10 = currentPart[9]
        val songTitle = currentPart[10]
        val artist = currentPart[11]
        val genre = currentPart[12]
        val year = currentPart[13]

        //Algorithm to make sure all random numbers are not repeating
        while(randOtherOne == randCurrent){
            randOtherOne = Random.nextInt(20, 40)
         }
        while(randOtherTwo == randCurrent || randOtherTwo == randOtherOne){
            randOtherTwo = Random.nextInt(20, 40)
        }
        while(randOtherThree == randCurrent || randOtherThree == randOtherOne || randOtherThree == randOtherTwo){
            randOtherThree = Random.nextInt(20, 40)
        }

        //Generate file paths from other random numbers
        var randOtherOneString:String = randOtherOne.toString()
        val currentStringOne = "Current/" + randOtherOneString + ".txt"
        var randOtherTwoString:String = randOtherTwo.toString()
        val currentStringTwo = "Current/" + randOtherTwoString + ".txt"
        var randOtherThreeString:String = randOtherThree.toString()
        val currentStringThree = "Current/" + randOtherThreeString + ".txt"

        val currentStreamOne = assets.open(currentStringOne)
        val currentStreamTwo = assets.open(currentStringTwo)
        val currentStreamThree = assets.open(currentStringThree)

        //Use other file paths and streams to obtain info for titles and artists
        //to use for incorrect guesses
        val currentReaderOne = currentStreamOne.bufferedReader()
        val currentPartOne: List<String> = currentReaderOne.readLines()
        val currentReaderTwo = currentStreamTwo.bufferedReader()
        val currentPartTwo: List<String> = currentReaderTwo.readLines()
        val currentReaderThree = currentStreamThree.bufferedReader()
        val currentPartThree: List<String> = currentReaderThree.readLines()

        //Create incorrect variables
        val wrongsongTitleOne = currentPartOne[10]
        val wrongArtistOne = currentPartOne[11]
        val wrongsongTitleTwo = currentPartTwo[10]
        val wrongArtistTwo = currentPartTwo[11]
        val wrongsongTitleThree = currentPartThree[10]
        val wrongArtistThree = currentPartThree[11]

        val map = Intent(this, MapsActivity::class.java)

        map.putExtra("line1", line1)
        map.putExtra("line2", line2)
        map.putExtra("line3", line3)
        map.putExtra("line4", line4)
        map.putExtra("line5", line5)
        map.putExtra("line6", line6)
        map.putExtra("line7", line7)
        map.putExtra("line8", line8)
        map.putExtra("line9", line9)
        map.putExtra("line10", line10)
        map.putExtra("songTitle", songTitle)
        map.putExtra("artist", artist)
        map.putExtra("genre", genre)
        map.putExtra("year", year)
        map.putExtra("wrongsongTitleOne", wrongsongTitleOne)
        map.putExtra("wrongsongTitleTwo", wrongsongTitleTwo)
        map.putExtra("wrongsongTitleThree", wrongsongTitleThree)
        map.putExtra("wrongArtistOne", wrongArtistOne)
        map.putExtra("wrongArtistTwo", wrongArtistTwo)
        map.putExtra("wrongArtistThree", wrongArtistThree)
        //Load variables into intent
        startActivity(map)
    }

    fun buttonClassic(view: View) {
        //Generate a set of random numbers
        var randClassic:Int = Random.nextInt(1, 20)
        var randOtherOne:Int = Random.nextInt(1, 20)
        var randOtherTwo:Int = Random.nextInt(1, 20)
        var randOtherThree:Int = Random.nextInt(1, 20)

        var randClassicString:String = randClassic.toString()
        var classicString:String = "Classic/" + randClassicString + ".txt"
        //Create a filepath string so it can be accessed
        val classicStream = assets.open(classicString)
        val classicReader = classicStream.bufferedReader()
        val classicPart: List<String> = classicReader.readLines()
        //Retrieve variables from the file
        val line1 = classicPart[0]
        val line2 = classicPart[1]
        val line3 = classicPart[2]
        val line4 = classicPart[3]
        val line5 = classicPart[4]
        val line6 = classicPart[5]
        val line7 = classicPart[6]
        val line8 = classicPart[7]
        val line9 = classicPart[8]
        val line10 = classicPart[9]
        val songTitle = classicPart[10]
        val artist = classicPart[11]
        val genre = classicPart[12]
        val year = classicPart[13]

        //Algorithm to make sure all random numbers are not repeating
        while(randOtherOne == randClassic){
            randOtherOne = Random.nextInt(20, 40)
        }
        while(randOtherTwo == randClassic || randOtherTwo == randOtherOne){
            randOtherTwo = Random.nextInt(20, 40)
        }
        while(randOtherThree == randClassic || randOtherThree == randOtherOne || randOtherThree == randOtherTwo){
            randOtherThree = Random.nextInt(20, 40)
        }
        //Generate file paths from other random numbers
        var randOtherOneString:String = randOtherOne.toString()
        val classicStringOne = "Classic/" + randOtherOneString + ".txt"
        var randOtherTwoString:String = randOtherTwo.toString()
        val classicStringTwo = "Classic/" + randOtherTwoString + ".txt"
        var randOtherThreeString:String = randOtherThree.toString()
        val classicStringThree = "Classic/" + randOtherThreeString + ".txt"
        //Use other file paths and streams to obtain info for titles and artists
        //to use for incorrect guesses
        val classicStreamOne = assets.open(classicStringOne)
        val classicStreamTwo = assets.open(classicStringTwo)
        val classicStreamThree = assets.open(classicStringThree)

        val classicReaderOne = classicStreamOne.bufferedReader()
        val classicPartOne: List<String> = classicReaderOne.readLines()
        val classicReaderTwo = classicStreamTwo.bufferedReader()
        val classicPartTwo: List<String> = classicReaderTwo.readLines()
        val classicReaderThree = classicStreamThree.bufferedReader()
        val classicPartThree: List<String> = classicReaderThree.readLines()

        val wrongsongTitleOne = classicPartOne[10]
        val wrongArtistOne = classicPartOne[11]
        val wrongsongTitleTwo = classicPartTwo[10]
        val wrongArtistTwo = classicPartTwo[11]
        val wrongsongTitleThree = classicPartThree[10]
        val wrongArtistThree = classicPartThree[11]
        //Create incorrect variables

        val map = Intent(this, MapsActivity::class.java)

        map.putExtra("line1", line1)
        map.putExtra("line2", line2)
        map.putExtra("line3", line3)
        map.putExtra("line4", line4)
        map.putExtra("line5", line5)
        map.putExtra("line6", line6)
        map.putExtra("line7", line7)
        map.putExtra("line8", line8)
        map.putExtra("line9", line9)
        map.putExtra("line10", line10)
        map.putExtra("songTitle", songTitle)
        map.putExtra("artist", artist)
        map.putExtra("genre", genre)
        map.putExtra("year", year)
        map.putExtra("wrongsongTitleOne", wrongsongTitleOne)
        map.putExtra("wrongsongTitleTwo", wrongsongTitleTwo)
        map.putExtra("wrongsongTitleThree", wrongsongTitleThree)
        map.putExtra("wrongArtistOne", wrongArtistOne)
        map.putExtra("wrongArtistTwo", wrongArtistTwo)
        map.putExtra("wrongArtistThree", wrongArtistThree)
        //Load variables into intent
        startActivity(map)
    }
}
