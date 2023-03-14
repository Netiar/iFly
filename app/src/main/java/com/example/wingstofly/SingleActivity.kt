package com.example.wingstofly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.wingstofly.databinding.ActivitySingleBinding
import com.example.wingstofly.fragments.*
import com.example.wingstofly.models.Event
import com.example.wingstofly.models.Job
import com.example.wingstofly.models.Scholar
import com.example.wingstofly.models.Upskill

class SingleActivity : AppCompatActivity() {
    private lateinit var bind:ActivitySingleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivitySingleBinding.inflate(layoutInflater)
        setContentView(bind.root)

        val intent = intent
        val layout = intent.getIntExtra("layout", 0)

        if (layout == 2 ){
            val scholar = intent.getSerializableExtra("scholar") as Scholar
            Toast.makeText(this, "scholar data", Toast.LENGTH_SHORT).show()
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

        }else{
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