package com.example.wingstofly.database

import com.example.wingstofly.models.Event
import java.util.*
import kotlin.collections.ArrayList

class EventsDataManager {
    var allEvents = ArrayList<Event>()

    init {
        getAllEvents()
    }

    private fun getAllEvents(){
        val event = Event("Wings To Fly conference")
        event.apply {
            id = 1
            description = "" +
                    "The Equity scholarship program was formed to aid high performing children hailing from less fortunate backgrounds. The program beneficiaries are selected from across Kenya’s 47 counties, based on their performance in the Kenya Certificate of Primary Education (KCPE) examination. Awareness of the program is conveyed through public forums i.e. administrative chief’s forums, places of worship, through radio announcements and social media channels. Application is made at all Equity Bank branches, Equity Agents and on the Equity Group Foundation’s Website. Beneficiaries of the scholarship are selected by the Community Scholarship Selection Board (CSSB), which is made up of key leaders in the community and is chaired by the County or sub-County Director of Education."
            venue = "Online Event"
            date = Date()
            eventOwner = "Equity Bank"
            eventUrl = "https://equitygroupfoundation.com/wings-to-fly/"
        }
        allEvents.add(event)

        val event1 = Event("Equity Hackathon")
        event1.apply {
            id = 2
            description = "A hackathon, also known as a codefest, is a social coding event that brings computer programmers and other interested people together to improve upon or build a new software program.\n" +
                    "\n" +
                    "The word hackathon is a portmanteau of the words hacker, which means clever programmer, and marathon, an event marked by endurance.\n" +
                    "\n" +
                    "The concept of the hackathon, also called a hack day or hack fest, was born out of the open source community. The first event labeled a hackathon was the OpenBSD Hackathon in Calgary, Canada, on June 4, 1999."
            venue = "Online Event"
            date = Date()
            eventOwner = "Equity Bank"
            eventUrl = "https://equity.hackerearth.com/"
        }
        allEvents.add(event1)

        val event2 = Event("MasterCard alumni Meeting")
        event2.apply {
            id = 3
            description = "After years of discussion with young people, months of rigorous recruitment, onboarding, training and planning, the Mastercard Foundation is pleased to announce the launch of the Mastercard Foundation Alumni Network with chapters in Ghana, Kenya, North America, Rwanda, Senegal, and Uganda.\n" +
                    "\n" +
                    "The Alumni Network is the culmination of our vision to mobilize the alumni from our programs — which include the Young African Leaders Initiative (YALI), Ashoka, the Youth Think Tank (YTT), the Scholars Program, the Anzisha Prize, and Youth Forward — to be a force for change.  In time the network will expand to include the other countries where the Foundation is implementing its Young Africa Works strategy connecting alumni to each other, resources, opportunities, and mentors.\n" +
                    "\n"
            venue = "Online Event"
            date = Date()
            eventOwner = "MasterCard Foundation"
            eventUrl = "https://equity.hackerearth.com/"

        }
        allEvents.add(event2)

        val event3 = Event("Tree Planting Event")
        event3.apply {
            id = 4
            description = "In the short rains of 2018 (October to December), NETFUND together with the Ministry of Environment and Forestry 12,000 tree seedlings in Machokos, Narok, Isiolo and Marsabit counties of Kenya. Another 7,000 tree seedlings were distributed to community groups for planting during the short rains tree planting season, whose theme was “Panda miti, boresha maisha” which means “plant trees, improve life”.  \n" +
                    "\n" +
                    "In 2017 NETFUND participated in the National Tree planting campaign whose theme was “Panda miti, penda Kenya” which loosely translated means “Plant trees, love Kenya”. The Ministry of Environment and Forestry’s target is to plant at least 235 million trees by 2022 with a minimum of 1,000,000 per county per year for five years."
            venue = "Online Event"
            date = Date()
            eventOwner = "The Government of Kenya"
            eventUrl = "https://equity.hackerearth.com/"

        }
        allEvents.add(event3)

        val event4 = Event("Equity Leaders Program meetup")
        event4.apply {
            id = 5
            description = "We recognize that a country can never be better than its leaders and as such, prioritized nurturing leadership skills in the youth who demonstrated great promise. ELP was established in 1998 as a rigorous leadership development program for top-performing Kenyan students with the aim of creating a community of transformative leaders who work together across borders and various sectors to drive sustainable economic growth and social progress in Africa.\n" +
                    "\n" +
                    "The scholars are exposed to a high-performing environment and are taught values of hard work, work ethics, customer service, communication skills, integrity and professionalism. Since inception, 6,713 scholars have benefitted from internships at Equity Bank prior to joining local or global universities."
            venue = "Lang'ata Carnivore"
            date = Date()
            eventOwner = "Equity Bank"
            eventUrl = "https://equity.hackerearth.com/"

        }
        allEvents.add(event4)

        val event5 = Event("Inua jamii initiative")
        event5.apply {
            id = 6
            description = "The government has released KES 8.6bn for elderly people under the Inua Jamii scheme after a six-month wait.The Inua Jamii is a Government cash transfer programme that supports the most vulnerable members of the community, such as the elderly people, children and persons with disabilities, by providing them with some payments to cushion them from poverty and hunger with the aim of improving their lives.Labour and Social Protection Cabinet Secretary Florence Bore said beneficiaries will start to receive their payments from Monday next week."
            venue = "City Stadium"
            date = Date()
            eventOwner = "The Government of Kenya"
            eventUrl = "https://equity.hackerearth.com/"

        }
        allEvents.add(event5)
    }
}