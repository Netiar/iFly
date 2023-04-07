package com.example.wingstofly

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.wingstofly.api.ConnectivityInterface
import com.example.wingstofly.databinding.ActivitySingleBinding
import com.example.wingstofly.fragments.*
import com.example.wingstofly.models.NetworkConnectivityObserver
import com.example.wingstofly.models.Scholar
import com.example.wingstofly.models.TriviaCategory
import com.example.wingstofly.models.Upskill
import com.example.wingstofly.repository.QuizRepository
import com.example.wingstofly.viewmodel.QuizProviderFactory
import com.example.wingstofly.viewmodel.QuizViewModel
import com.skydoves.transformationlayout.TransformationAppCompatActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SingleActivity : TransformationAppCompatActivity() {
    private lateinit var bind:ActivitySingleBinding
    lateinit var questViewModel: QuizViewModel
    lateinit var scholars: ArrayList<Scholar>
    private lateinit var contentManager: NetworkConnectivityObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivitySingleBinding.inflate(layoutInflater)
        setContentView(bind.root)
        contentManager = NetworkConnectivityObserver(applicationContext)
        contentManager.observe().onEach {
            if (it == ConnectivityInterface.States.Connected){
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
                }else if (layout == 7){
                    val event = intent.getSerializableExtra("school") as Upskill
                    val fragment = UpskillFragment.newInstance(event)
                    replaceFragment(fragment)

                }else{
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
            }
        }.launchIn(lifecycleScope)

    }

    private fun replaceFragment(fragment: Fragment) {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(bind.frameLayout.id, fragment)
        transaction.commit()
    }
}