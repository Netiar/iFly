package com.example.wingstofly.database

import com.example.wingstofly.R
import com.example.wingstofly.models.Job
import java.util.*
import kotlin.collections.ArrayList

class JobsDataManager {
    var allJobs = ArrayList<Job>()

    init {
        getAlJobs()
    }

    private fun getAlJobs() {
        var job = Job("Kernel Engineer - Ubuntu Linux", "Software Engineer")
        job.apply {
            companyImage = R.drawable.canoical
            companyName = "Canonical"
            jobPlace = "Nairobi-Kenya"
            jobType = "Full- time"
            jobLevel = "Entry Level"
            jobUrl = "https://boards.greenhouse.io/canonicaljobs/jobs/4817718?gh_src=86e005431us"
            description = "Are you passionate about the Linux kernel?  Or maybe you’ve mastered a different low-level software development environment and are ready for a new challenge in Linux?  Are you looking to work on a great team, where you can apply and grow your kernel development skills?  This is an exciting time at Canonical, where due to a period of unprecedented growth we are seeking a number of  software engineers to join our Kernel Team to play an integral part in the delivery of the heart of the most popular Linux distribution in the world.The Canonical Linux Kernel Team is responsible for developing and maintaining all supported Ubuntu Linux kernels, which run on a variety of platforms and architectures from small embedded devices to standard desktop/server systems to large scale cloud environments.  The Kernel Team has openings for software engineers in a number of aspects that include but are not limited to:"
            postDate = Date()
            deadline = Date()

            jobAbout.add("Ubuntu Linux distribution")
            jobAbout.add("Optimized Ubuntu solutions for key Silicon and Cloud partners")
            jobAbout.add("Developer platforms such as Raspberry Pi and RISC-V")
            jobAbout.add("Enabling Ubuntu on a wide range of embedded systems")
            jobAbout.add("Livepatch service, which allows Ubuntu customers to apply critical kernel (CVE) security fixes")
            jobAbout.add("FIPS compliance")

            requirements.add("Earned a bachelor’s (or equivalent university level) degree, preferably in a technology field")
            requirements.add("Significant programming experience in C, with Python and Bash scripting also highly regarded")
            requirements.add("Broad knowledge of the Linux kernel and kernel subsystems")
            requirements.add("Demonstrated experience with kernel patching and debugging")
            requirements.add("Knowledge/experience with Linux kernel upstream processes and release cycle")
            requirements.add("Strong grasp of device drivers, BSP’s, and other low level system engineering")
            requirements.add("Prior experience with Ubuntu/Debian/Snap packaging would be highly regarded")
        }
        allJobs.add(job)

        var job1 = Job("Relationship Officer", "Operations")
        job1.apply {
            companyImage = R.drawable.jumia
            companyName = "Jumia-Kenya"
            jobType = "Full Time"
            jobPlace = "Nairobi Kenya"
            jobLevel = "Entry Level"

            jobAbout.add("You will be part of an agile team that develops e-commerce, smart logistics, and payment solutions.")
            jobAbout.add("Your work will integrate a service-oriented architecture using state-of-the-art technologies and software development practices; You will own your services and be responsible for end-to-end")
            jobAbout.add("You will take ownership for your code to be qualitatively high and you will support your colleagues in code reviews")
            jobAbout.add("You will change the Internet landscape in Africa and the World one line of code at a time.")
            jobUrl = "https://www.linkedin.com/jobs/collections/recommended/?currentJobId=3447808034"
            description = "Jumia is the leading pan-Africa e-commerce platform. Founded in 2012, Jumia's mission is to improve the quality of everyday life in Africa by leveraging technology to deliver innovative, convenient and affordable online services to consumers, while helping businesses grow as they use our platform to reach and serve consumers."
            postDate = Date()
            deadline = Date()
            requirements.add("Knowledgeable of SQL and relational databases")
            requirements.add("Knowledgeable of software design and architecture patterns")
            requirements.add("Acquainted with building testable code, unit tests and/or integration tests")
            requirements.add("Experience with development in Java (and ideally Spring boot framework)")
            requirements.add("Experienced with object Oriented Programming")
        }
        allJobs.add(job1)

        var job2 = Job("Risk Attachees", "Risk")
        job2.apply {
            companyImage = R.drawable.bank
            companyName = "Equity Bank LTD"
            jobPlace = "Multiple Locations"
            jobType = "Temporary"
            jobLevel = "Entry Level"
            jobUrl = "https://equitybank.taleo.net/careersection/ext_new/jobdetail.ftl?job=220000F6&tz=GMT%2B03%3A00&tzname=Africa%2FNairobi"
            description = "Equity Bank is one of the region’s leading banks whose purpose is to transform the lives and livelihoods of the people of Africa socially and economically by availing them modern and inclusive financial services that maximize their opportunities. With a strong footprint in Kenya, Uganda, Tanzania, Rwanda, South Sudan and DRC Congo, Equity Bank is now home to over 12 million customers - the largest customer base in Africa. Currently the Bank is seeking additional talent to serve in the role outlined below within Risk Department"
            postDate = Date()
            deadline = Date()

            jobAbout.add("Key Duties and Responsibilities for the new Staff")
            jobAbout.add("Conduct risk assessments and develop key risk indicators for monitoring and reporting")
            jobAbout.add("stress testing of the various key risks facing the bank")
            jobAbout.add("Participate in developing and updating models used to manage bank’s credit risk, operational risk, market risk, liquidity risks.")
            jobAbout.add("Any other tasks as directed by the risk leadership")

            requirements.add( "Bachelor’s degree in a quantitative field [economics, statistics, mathematics, financial engineering, actuarial science etc.]")
            requirements.add( "Professional programs such as FRM, FRR, CFA, CPA, CISA, SCR etc. will be desired.")
        }
        allJobs.add(job2)

        var job3 = Job("Group Financial Reporting Manager ", "Operations")
        job3.apply {
            companyImage = R.drawable.bank
            companyName = "Equity Bank LTD"
            jobPlace = "Nairobi-Kenya"
            jobType = "Regular"
            jobLevel = "Manager"
            jobUrl = "https://equitybank.taleo.net/careersection/ext_new/jobdetail.ftl?job=23000013&tz=GMT%2B03%3A00&tzname=Africa%2FNairobi"
            description = " Equity Bank is one of the region’s leading banks whose purpose is to transform the lives and livelihoods of the people of Africa socially and economically by availing them modern and inclusive financial services that maximize their opportunities. With a strong footprint in Kenya, Uganda, Tanzania, Rwanda, South Sudan and DRC Congo, Equity Bank is now home to over 12 million customers - the largest customer base in Africa. Currently the Bank is seeking additional talent to serve in the role outlined below."
            postDate = Date()
            deadline = Date()

            jobAbout.add("Preparation and submission of periodic financial reports in accordance with IFRS, statutory and regulatory requirements (CBK & KDIC)")
            jobAbout.add("Consolidation of Group Financial statements Monthly, Quarterly and Annually")
            jobAbout.add("Coordinate preparation and submission to management of daily performance reports and insights into material movement in key parameters.")
            jobAbout.add("Establish and maintain effective internal controls over financial reporting process")
            jobAbout.add("Regular monitoring and review of all GLs for accuracy and appropriateness of business transactions recorded across the group")
            jobAbout.add("Review of group wide GL reconciliations and ensure outstanding items are investigated and cleared promptly")

            requirements.add("Bachelor of Commerce (Bcom) majoring in Accounting or equivalent degree")
            requirements.add("Relevant Professional Qualifications eg CPA (K) or ACCA")
            requirements.add("MBA (added advantage)")
            requirements.add("Experience in data analysis;")
            requirements.add("In-depth knowledge of IFRSs.")
            requirements.add("At least 8 years in Finance and Financial Reporting")
            requirements.add("In depth knowledge in Central Bank regulations")
        }
        allJobs.add(job3)

        var job4 = Job("Relationship Officer", "Operations")
        job4.apply {
            companyImage = R.drawable.job
            companyName = "Arhs Developments"
            jobPlace = "Boulevard du Jazz, 13\n" +
                    "4370 Luxembourg"
            jobType = "Permanent"
            jobLevel = "Manager"
            jobUrl = "https://www.ictjob.lu/en/job/arhs-developments-java-developer/1-14848?jobOfferId=14730"
            description = "Arhs is a fully independent group of companies specialized in managing complex IT projects and systems for large organisations, focusing on state-of-the-art software development, business intelligence and infrastructure services.\n" +
                    "We are composed of nine entities (Arhs Developments, Arhs Developments Belgium, Arhs Consulting, Arhs Spikeseed, Arhs Cube, Arhs Digital, Arhs Technologies, Arhs Developments Hellas and Arhs Developments Italia) that are unified by the Arhs Group, with more than 1000 consultants.\n" +
                    "This corporate structure enables us to respond quickly to market changes and customer requests, and to communicate and make decisions without layers of bureaucracy."
            postDate = Date()
            deadline = Date()

            jobAbout.add("Build and maintain efficient and reliable Java code")
            jobAbout.add("Support continuous improvement by investigating alternatives and technologies")
            jobAbout.add("Ensure the best quality and responsiveness of the applications")
            jobAbout.add("Be part of all the global improvements")
            jobAbout.add("Be a driver of change and innovation within the company")

            requirements.add("Java 8 +")
            requirements.add("JEE")
            requirements.add("The Spring ecosystem including Hibernate")
            requirements.add("Scrum, Agile practices (continuous integration, automated testing, pair programming...")
            requirements.add("You're interested in state-of-the-art technologies")

            jobBenefits.add("An informal hierarchy and work environment")
            jobBenefits.add("An attractive salary package:")
            jobBenefits.add("A strong corporate culture:")
            jobBenefits.add("Learning & development opportunities")
            jobBenefits.add("Exciting projects")
        }
        allJobs.add(job4)

        var job5 = Job("Telesales Representative", "Marketing")
        job5.apply {
            companyImage = R.drawable.advert
            companyName = "Branded Communication agency"
            jobPlace = "Kitale, Trans-Nzoia County, Kenya"
            jobType = "Contract"
            jobLevel = "Entry Level"
            jobUrl = "https://www.linkedin.com/jobs/search/?currentJobId=3454058631&keywords=marketing&refresh=true"
            description = "Arhs is a fully independent group of companies specialized in managing complex IT projects and systems for large organisations, focusing on state-of-the-art software development, business intelligence and infrastructure services."

            postDate = Date()
            deadline = Date()

            jobAbout.add("Initiating sales with potential customers over the phone.")
            jobAbout.add("Asking questions to engage customers and keep the conversation going.")
            jobAbout.add( "Listening to the customers' needs to generate repeat sales.")
            jobAbout.add("Gathering and documenting customer information, payment methods, purchases, and reactions to products.")
            jobAbout.add("Keeping up to date on all products and informing customers of new products.")
            jobAbout.add("Answering customers' questions on the products")

            requirements.add("Excellent interpersonal and problem solving skills.")
            requirements.add("Excellent communication skills.")
            requirements.add("should have dip /degree in digital marketing ,IT")
            requirements.add("Telecommunication background is an added advantage")
            requirements.add("The patience and ability to engage customers in conversation.")
        }
        allJobs.add(job5)
    }
}