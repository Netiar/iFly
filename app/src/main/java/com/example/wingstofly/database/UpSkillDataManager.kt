package com.example.wingstofly.database

import com.example.wingstofly.models.Upskill

class UpSkillDataManager {
    val schools = ArrayList<Upskill>()

    init{
        getSchools()
    }

    private fun getSchools() {
        val school = Upskill("PluralSight")
        school.apply {
            description = "Technology teams are only as successful as their skills are relevant. Give them access to Pluralsight Skills—courses, assessments, learning paths, and labs—and you give them the tech fluency they need to build business-critical skills. Empower your tech teams to produce key business outcomes by making upskilling and reskilling as easy as powering up their laptop. Tap into the power of curated learning paths to guide teams through the exact skills they need to progress from novice to guru across a variety of tech skills."
            type = "Software Development"
        }
        schools.add(school)
        val school1 = Upskill("Moringa School")
        school1.apply {
            description = "In Moringa we are transforming the way tech education is done in African Markets. All our programs are delivered through a blended learning approach that combines market-aligned courses, a classroom team of talented mentors with the skills and knowledge to deliver growth and results for learners, and an environment that supports student creativity, job market preparation in a fun, open, and transformative learning experience. We celebrate our diversity and value strong, professional relationships that help our students build their futures with greater confidence, capability, and possibility."
            type = "Software Development"
        }
        schools.add(school1)
        val school2 = Upskill("Self Learning")
        school2.apply {
            description = "Self-learning is the method of gathering, processing, and retaining knowledge without the help of another person. Any knowledge you get outside of a formal educational setting, such as through self-study or experience, is self-driven learning."
            type = "Software Development"
        }
        schools.add(school2)
    }
}