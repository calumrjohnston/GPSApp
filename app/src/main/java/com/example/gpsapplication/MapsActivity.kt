package com.example.gpsapplication

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import kotlin.random.Random
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private lateinit var locationRequest: LocationRequest
    var lastLocation = Location("")
    private var locationUpdateState = false
    private lateinit var geofencingClient: GeofencingClient
    private var distance: Float = 0.0f
    val randomiserList = mutableListOf<String>()

    //Used to pass variables into other screens
    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
        val PERMISSION_ID = 42
        private const val REQUEST_CHECK_SETTINGS = 2
        private val markerList: ArrayList<Marker> = ArrayList()
        val collectedSongList = mutableListOf<String>()
        var lives = 3
        var songTitle: String = ""
        var artist: String = ""
        var genre: String = ""
        var year: String = ""
        var wrongsongTitleOne: String = ""
        var wrongsongTitleTwo: String = ""
        var wrongsongTitleThree: String = ""
        var wrongArtistOne: String = ""
        var wrongArtistTwo: String = ""
        var wrongArtistThree: String = ""
    }

    //Initialise lives and set up location objects
    override fun onCreate(savedInstanceState: Bundle?) {
        lives = 3
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            fusedLocationClient.lastLocation
        }
        geofencingClient = LocationServices.getGeofencingClient(this)
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                super.onLocationResult(p0)
                lastLocation = p0.lastLocation
            }
        }
        getLastLocation()
        requestNewLocationData()
    }

    //build map ,set location and animate the camera
    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 17f))
            }
        }
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap
        fusedLocationClient.lastLocation

        //initilisation of variables and load from intent

        val line1 = intent.getStringExtra("line1")
        val line2 = intent.getStringExtra("line2")
        val line3 = intent.getStringExtra("line3")
        val line4 = intent.getStringExtra("line4")
        val line5 = intent.getStringExtra("line5")
        val line6 = intent.getStringExtra("line6")
        val line7 = intent.getStringExtra("line7")
        val line8 = intent.getStringExtra("line8")
        val line9 = intent.getStringExtra("line9")
        val line10 = intent.getStringExtra("line10")
        songTitle = intent.getStringExtra("songTitle")
        artist = intent.getStringExtra("artist")
        genre = intent.getStringExtra("genre")
        year = intent.getStringExtra("year")

        wrongsongTitleOne = intent.getStringExtra("wrongsongTitleOne")
        wrongsongTitleTwo = intent.getStringExtra("wrongsongTitleTwo")
        wrongsongTitleThree = intent.getStringExtra("wrongsongTitleThree")
        wrongArtistOne = intent.getStringExtra("wrongArtistOne")
        wrongArtistTwo = intent.getStringExtra("wrongArtistTwo")
        wrongArtistThree = intent.getStringExtra("wrongArtistThree")

        randomiserList.add(0, line1)
        randomiserList.add(1, line2)
        randomiserList.add(2, line3)
        randomiserList.add(3, line3)
        randomiserList.add(4, line4)
        randomiserList.add(5, line5)
        randomiserList.add(6, line6)
        randomiserList.add(7, line7)
        randomiserList.add(8, line8)
        randomiserList.add(9, line9)
        randomiserList.shuffle()

        //add lyrics to another list to shuffle it

        for (x in 0..9) {

            //Create 10 lyric markers in random locations and add lyric data and change icon
            //add to a list to store markers
            val markLoc = LatLng(randLatGen(),randLonGen())//make random location through functions
            val markerCreate = mMap.addMarker(MarkerOptions().position(markLoc))
            markerCreate.setSnippet(randomiserList.get(x))

            markerList.add(markerCreate)

        }
        setUpMap()
        fusedLocationClient.lastLocation
    }

    //Launch lyric list and hints menu
    fun showMyLyrics(view: View){
        val lyrics = Intent(this, LyricListActivity::class.java)
        startActivity(lyrics)
    }
    //Launch guessing menu
    fun makeAGuess(view: View){
        val guess = Intent(this, GuessActivity::class.java)
        startActivity(guess)
    }

    //Functions used to generate lat and long values within the bay campus boundary
    fun randLatGen(): Double {
        val randLat = Random.nextDouble(51.617345, 51.619716)
        return randLat
    }

    fun randLonGen(): Double {
        val randLon = Random.nextDouble(-3.883920, -3.875598)
        return randLon
    }

    //Refresh and get location
    private fun getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                fusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                    var location: Location? = task.result
                    if (location == null) {
                        requestNewLocationData()
                    } else {
                        var lat = location.latitude
                        var long = location.longitude

                        var accuracy = location.accuracy

                    }
                }
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            //Ask user for location permission
            requestPermissions()
        }
    }

    private fun requestNewLocationData() {
        Log.i("myLocation", "request")
        var mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 2000
        mLocationRequest.fastestInterval = 1000

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient!!.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            Log.i("myLocation", "Callback")
            var mLastLocation: Location = locationResult.lastLocation
            var lat = mLastLocation.latitude
            var long = mLastLocation.longitude

            val lastLoc = LatLng(lat, long)
            Log.i("myLocation", lastLoc.toString())
            fusedLocationClient.lastLocation
            checkDistance(lastLoc)
            mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(lastLoc,17f))

        }
    }


    private fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_ID
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_ID) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLastLocation()
            }
        }
    }

    //Calculate distance between user and all lyric locations, if close enough to collect
    //remove the marker, collect the lyric by adding it to a list and making the lyric
    //now empty
    fun checkDistance(lastLoc: LatLng) {
        for (marker in markerList) {
            val markerLoc = Location(LocationManager.GPS_PROVIDER)
            val userLoc = Location(LocationManager.GPS_PROVIDER)
            markerLoc.latitude = marker.position.latitude
            markerLoc.longitude = marker.position.longitude
            userLoc.latitude = lastLoc.latitude
            userLoc.longitude = lastLoc.longitude
            distance = userLoc.distanceTo(markerLoc)
            var markerData:String = marker.snippet
            if (distance < 12 && markerData != "") {
                marker.remove()
                collectedSongList.add(markerData)
                marker.snippet = ""
            }
        }
    }
}

