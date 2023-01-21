package com.example.wingstofly.database

import com.example.wingstofly.models.Event
import com.example.wingstofly.models.Job
import java.util.*
import kotlin.collections.ArrayList

class DataManager {
    var events  = ArrayList<Event>()
    var jobs = ArrayList<Job>()

    init {
        getEvents()
        getJobs()
    }

    private fun getEvents(){
        val event = Event("Wings to fly Annual Conference")
        event.apply {
            description = "The Wings to Fly scholarship program, an initiative of the Equity Group and Mastercard Foundation (MCF), was established to support secondary education for top performing children from financially challenged backgrounds. With support from other partners, the program offers access to leadership training to previously marginalized children in all counties.\n" +
                    "\n" +
                    "The program offers comprehensive support for the scholars through provision of tuition fees, accommodation, books, uniform, shopping, pocket money and transport to and from school during their 4 years of secondary education. It has so far supported 26,304 bright but economically challenged scholars. MCF has so far committed to support 10,000 scholars through two phases of funding with the last intake joining the program in 2021."
            venue = "Kenyatta University Ampitheatre"
            date = Date(2023,8,8)
        }
        events.add(event)

        val event1 = Event("Equity Leaders Program Launch")
        event1.apply {
            description = "The Wings to Fly scholarship program, an initiative of the Equity Group and Mastercard Foundation (MCF), was established to support secondary education for top performing children from financially challenged backgrounds. With support from other partners, the program offers access to leadership training to previously marginalized children in all counties.\n" +
                    "\n" +
                    "The program offers comprehensive support for the scholars through provision of tuition fees, accommodation, books, uniform, shopping, pocket money and transport to and from school during their 4 years of secondary education. It has so far supported 26,304 bright but economically challenged scholars. MCF has so far committed to support 10,000 scholars through two phases of funding with the last intake joining the program in 2021."
            venue = "Kenyatta University Ampitheatre"
            date = Date(2023,2,8)
        }
        events.add(event1)

        val event2 = Event("Muvkin Experience Introduction")
        event2.apply {
            description = "The Wings to Fly scholarship program, an initiative of the Equity Group and Mastercard Foundation (MCF), was established to support secondary education for top performing children from financially challenged backgrounds. With support from other partners, the program offers access to leadership training to previously marginalized children in all counties.\n" +
                    "\n" +
                    "The program offers comprehensive support for the scholars through provision of tuition fees, accommodation, books, uniform, shopping, pocket money and transport to and from school during their 4 years of secondary education. It has so far supported 26,304 bright but economically challenged scholars. MCF has so far committed to support 10,000 scholars through two phases of funding with the last intake joining the program in 2021."
            venue = "Kenyatta University Ampitheatre"
            date = Date(2023,12,8)
        }
        events.add(event2)

    }

    private fun getJobs(){

        val job = Job("Software Engineer")
        job.apply {
            company = "Equity Group Foundation"
            description = "We are looking for a motivated Software Engineer to join our team based in Nairobi, Kenya. As a Software Engineer you will collaborate with the rest of the engineering team to build our products.\n" +
                    "\n" +
                    "The ideal candidate will have a passion for technology and a strong background in software development, specifically in backend programming languages such as Kotlin, Python and TypeScript."
            requirements.add("Write reusable, testable, maintainable and efficient code.")
            requirements.add("Develop and maintain backend systems and services using the specified engineering stack.")
            requirements.add("Collaborate with the product and design teams to build new features and improve existing ones using the specified engineering stack.")
            requirements.add("Participate in code reviews to ensure the quality of our code bases.")
            requirements.add("Researching, investigating and fixing a wide variety of technical issues.")
            date = Date(2023,8,8)

        }

        jobs.add(job)

        val job1 = Job("Software Engineer")
        job1.apply {
            company = "Equity Group Foundation"
            description = "We are looking for a motivated Software Engineer to join our team based in Nairobi, Kenya. As a Software Engineer you will collaborate with the rest of the engineering team to build our products.\n" +
                    "\n" +
                    "The ideal candidate will have a passion for technology and a strong background in software development, specifically in backend programming languages such as Kotlin, Python and TypeScript."
            requirements.add("Write reusable, testable, maintainable and efficient code.")
            requirements.add("Develop and maintain backend systems and services using the specified engineering stack.")
            requirements.add("Collaborate with the product and design teams to build new features and improve existing ones using the specified engineering stack.")
            requirements.add("Participate in code reviews to ensure the quality of our code bases.")
            requirements.add("Researching, investigating and fixing a wide variety of technical issues.")
            date = Date(2023,8,8)

        }

        jobs.add(job1)

        val job2 = Job("Software Engineer")
        job2.apply {
            company = "Equity Group Foundation"
            description = "We are looking for a motivated Software Engineer to join our team based in Nairobi, Kenya. As a Software Engineer you will collaborate with the rest of the engineering team to build our products.\n" +
                    "\n" +
                    "The ideal candidate will have a passion for technology and a strong background in software development, specifically in backend programming languages such as Kotlin, Python and TypeScript."
            requirements.add("Write reusable, testable, maintainable and efficient code.")
            requirements.add("Develop and maintain backend systems and services using the specified engineering stack.")
            requirements.add("Collaborate with the product and design teams to build new features and improve existing ones using the specified engineering stack.")
            requirements.add("Participate in code reviews to ensure the quality of our code bases.")
            requirements.add("Researching, investigating and fixing a wide variety of technical issues.")
            date = Date(2023,8,8)

        }

        jobs.add(job2)

        val job3 = Job("Software Engineer")
        job3.apply {
            company = "Equity Group Foundation"
            description = "We are looking for a motivated Software Engineer to join our team based in Nairobi, Kenya. As a Software Engineer you will collaborate with the rest of the engineering team to build our products.\n" +
                    "\n" +
                    "The ideal candidate will have a passion for technology and a strong background in software development, specifically in backend programming languages such as Kotlin, Python and TypeScript."
            requirements.add("Write reusable, testable, maintainable and efficient code.")
            requirements.add("Develop and maintain backend systems and services using the specified engineering stack.")
            requirements.add("Collaborate with the product and design teams to build new features and improve existing ones using the specified engineering stack.")
            requirements.add("Participate in code reviews to ensure the quality of our code bases.")
            requirements.add("Researching, investigating and fixing a wide variety of technical issues.")
        }

        jobs.add(job3)

        val job4 = Job("Software Engineer")
        job4.apply {
            company = "Equity Group Foundation"
            description = "We are looking for a motivated Software Engineer to join our team based in Nairobi, Kenya. As a Software Engineer you will collaborate with the rest of the engineering team to build our products.\n" +
                    "\n" +
                    "The ideal candidate will have a passion for technology and a strong background in software development, specifically in backend programming languages such as Kotlin, Python and TypeScript."
            requirements.add("Write reusable, testable, maintainable and efficient code.")
            requirements.add("Develop and maintain backend systems and services using the specified engineering stack.")
            requirements.add("Collaborate with the product and design teams to build new features and improve existing ones using the specified engineering stack.")
            requirements.add("Participate in code reviews to ensure the quality of our code bases.")
            requirements.add("Researching, investigating and fixing a wide variety of technical issues.")
            date = Date(2023,8,8)

        }

        jobs.add(job4)

    }

}