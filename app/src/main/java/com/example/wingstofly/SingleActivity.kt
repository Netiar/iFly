package com.example.wingstofly

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.wingstofly.database.DataScholarManager
import com.example.wingstofly.databinding.ActivitySingleBinding
import com.example.wingstofly.fragments.*
import com.example.wingstofly.models.*
import com.example.wingstofly.repository.QuizRepository
import com.example.wingstofly.utils.Constants
import com.example.wingstofly.viewmodel.QuizProviderFactory
import com.example.wingstofly.viewmodel.QuizViewModel
import com.skydoves.transformationlayout.TransformationAppCompatActivity
import java.util.function.Predicate

class SingleActivity : TransformationAppCompatActivity() {
    private lateinit var bind:ActivitySingleBinding
    lateinit var questViewModel: QuizViewModel
    lateinit var scholars: ArrayList<Scholar>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivitySingleBinding.inflate(layoutInflater)
        setContentView(bind.root)

        scholars = DataScholarManager().scholars


        val intent = intent
        val layout = intent.getIntExtra("layout", 0)

        if (layout == 2 ){
            val scholar = intent.getSerializableExtra("scholar") as Scholar
            val fragment = ProfileFragment.newInstance(scholar)
            replaceFragment(fragment)
        }else if(layout == 3){
            val scholar = intent.getSerializableExtra("scholar") as Scholar
            val fragment = MessagesFragment.newInstance(scholar)
            replaceFragment(fragment)
        }else if (layout == 5){
            val event = intent.getSerializableExtra("event") as Event
            val fragment = NewsFragment.newInstance(event)
            replaceFragment(fragment)

        }else if (layout == 7){
            val event = intent.getSerializableExtra("school") as Upskill
            val fragment = UpskillFragment.newInstance(event)
            replaceFragment(fragment)

        }else if (layout == 8){

            val category = intent.getIntExtra("category", 16)
            val categoryName = intent.getStringExtra("catName")
            val triviaCategory = TriviaCategory(category, categoryName!!)

            //initialising the quiz repository and provider
            val repo = QuizRepository()
            val provider = QuizProviderFactory(category.toString(), repo)
            questViewModel = ViewModelProvider(this, provider)[QuizViewModel::class.java]
            val fragment = QuestionsFragment.newInstance(triviaCategory)
            replaceFragment(fragment)

        }
        else{
            val job = intent.getSerializableExtra("job") as Job
            val fragment = SingleJobFragment.newInstance(job)
            replaceFragment(fragment)
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(bind.frameLayout.id, fragment)
        transaction.commit()
    }
}