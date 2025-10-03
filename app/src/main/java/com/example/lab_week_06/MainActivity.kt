package com.example.lab_week_06

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_06.model.CatBreed
import com.example.lab_week_06.model.CatModel
import com.example.lab_week_06.model.Gender

class MainActivity : AppCompatActivity() {

    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.recycler_view)
    }

    private val catAdapter by lazy {
        CatAdapter(layoutInflater, GlideImageLoader(this), object : CatAdapter.OnClickListener {
            override fun onItemClick(cat: CatModel) {
                showSelectionDialog(cat)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup the adapter
        recyclerView.adapter = catAdapter

        // Setup the layout manager
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // ✅ Tambahin ItemTouchHelper untuk swipe-to-delete
        val itemTouchHelper = ItemTouchHelper(catAdapter.swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        // ✅ Add 10 data ke adapter
        catAdapter.setData(
            listOf(
                CatModel(Gender.Male, CatBreed.BalineseJavanese, "Fred", "Silent and deadly", "https://cdn2.thecatapi.com/images/7dj.jpg"),
                CatModel(Gender.Female, CatBreed.ExoticShorthair, "Wilma", "Cuddly assassin", "https://cdn2.thecatapi.com/images/egv.jpg"),
                CatModel(Gender.Unknown, CatBreed.AmericanCurl, "Curious George", "Award winning investigator", "https://cdn2.thecatapi.com/images/bar.jpg"),
                CatModel(Gender.Male, CatBreed.ExoticShorthair, "Tommy", "Sleep all day", "https://cdn2.thecatapi.com/images/8k3.jpg"),
                CatModel(Gender.Female, CatBreed.BalineseJavanese, "Luna", "Chasing butterflies", "https://cdn2.thecatapi.com/images/9de.jpg"),
                CatModel(Gender.Male, CatBreed.AmericanCurl, "Oscar", "Food hunter", "https://cdn2.thecatapi.com/images/ab1.jpg"),
                CatModel(Gender.Female, CatBreed.ExoticShorthair, "Mia", "Loves cuddles", "https://cdn2.thecatapi.com/images/ac2.jpg"),
                CatModel(Gender.Unknown, CatBreed.BalineseJavanese, "Shadow", "Silent stalker", "https://cdn2.thecatapi.com/images/ad3.jpg"),
                CatModel(Gender.Male, CatBreed.AmericanCurl, "Simba", "King of naps", "https://cdn2.thecatapi.com/images/ae4.jpg"),
                CatModel(Gender.Female, CatBreed.ExoticShorthair, "Nala", "Always curious", "https://cdn2.thecatapi.com/images/af5.jpg")
            )
        )
    }

    private fun showSelectionDialog(cat: CatModel) {
        AlertDialog.Builder(this)
            .setTitle("Cat Selected")
            .setMessage("You have selected cat ${cat.name}")
            .setPositiveButton("OK", null)
            .show()
    }
}
