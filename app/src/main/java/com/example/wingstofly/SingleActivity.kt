package com.example.wingstofly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.example.wingstofly.database.JobsDataManager
import com.example.wingstofly.databinding.ActivitySingleBinding
import com.example.wingstofly.fragments.MessagesFragment
import com.example.wingstofly.fragments.ProfileFragment
import com.example.wingstofly.fragments.SingleJobFragment
import com.example.wingstofly.models.Scholar

class SingleActivity : AppCompatActivity() {
    private lateinit var bind:ActivitySingleBinding
    val args: SingleActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivitySingleBinding.inflate(layoutInflater)
        setContentView(bind.root)
        val jobs = JobsDataManager().allJobs

        val intent = intent
        val layout = intent.getIntExtra("layout", 0)

        if (layout == 2 ){
            val scholar = intent.getSerializableExtra("scholar") as Scholar
            Toast.makeText(this, "scholar data", Toast.LENGTH_SHORT).show()
            val fragment = ProfileFragment.newInstance(scholar)
            replaceFragment(fragment)
        }else if(layout == 3){
            val fragment = MessagesFragment()
            replaceFragment(fragment)
        }else{
            val fragment = SingleJobFragment.newInstance(jobs[0])
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