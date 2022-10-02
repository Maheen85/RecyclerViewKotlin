package com.maheenshaikh85.recyclerviewkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var newRecyclerview : RecyclerView
    private lateinit var newArrayList : ArrayList<News>
    private lateinit var tempArrayList : ArrayList<News>
    lateinit var imageId : Array<Int>
    lateinit var heading : Array<String>
    lateinit var news: Array<String>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initialize array of images and news text

        imageId = arrayOf(
            R.drawable.happy_face,
            R.drawable.graduation_hat,
            R.drawable.flower,
            R.drawable.star
        )

        heading = arrayOf(
            "A Yellow Happy Face",
            "A Black Graduation Hat",
            "A Pink Flower",
            "A Blue Star"

        )




        // Refer to Recycler view


        newRecyclerview = findViewById(R.id.recyclerView)
        newRecyclerview.layoutManager = LinearLayoutManager(this)
        newRecyclerview.setHasFixedSize(true)



        newArrayList = arrayListOf<News>()
        tempArrayList = arrayListOf<News>()
        getUserdata()
    }


    // function for inflating options Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_item,menu)

        var item: MenuItem = menu!!.findItem(R.id.search_action)

        if(item != null){
            var searchView = item.actionView as SearchView

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    tempArrayList.clear()
                    var searchText = newText!!.lowercase(Locale.getDefault())

                    if(searchText.isNotEmpty()){


                        newArrayList.forEach{

                            if(it.heading.lowercase(Locale.getDefault()).contains(searchText)){
                                tempArrayList.add(it)
                            }
                        }

                        newRecyclerview.adapter!!.notifyDataSetChanged()

                    }

                    else{
                        tempArrayList.clear()
                        tempArrayList.addAll(newArrayList)
                        newRecyclerview.adapter!!.notifyDataSetChanged()
                    }

                    return false

                }


            })

        }

        return super.onCreateOptionsMenu(menu)
    }



    // function to fetch data
    private fun getUserdata(){

        for (i in imageId.indices){
            val news = News(imageId[i],heading[i])
            newArrayList.add(news)
        }

        tempArrayList.addAll(newArrayList)

        newRecyclerview.adapter = MyAdapter(tempArrayList)
    }




}