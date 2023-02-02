package datamanager

import models.Job
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
            companyName = "Canonical"
            jobPlace = "Nairobi-Kenya"
            jobType = "Full- time"
            jobLevel = "Entry Level"
            jobUrl = "https://boards.greenhouse.io/canonicaljobs/jobs/4817718?gh_src=86e005431us"
            description = "Are you passionate about the Linux kernel?  Or maybe you’ve mastered a different low-level software development environment and are ready for a new challenge in Linux?  Are you looking to work on a great team, where you can apply and grow your kernel development skills?  This is an exciting time at Canonical, where due to a period of unprecedented growth we are seeking a number of  software engineers to join our Kernel Team to play an integral part in the delivery of the heart of the most popular Linux distribution in the world.\n" +
                    "\n" +
                    "The Canonical Linux Kernel Team is responsible for developing and maintaining all supported Ubuntu Linux kernels, which run on a variety of platforms and architectures from small embedded devices to standard desktop/server systems to large scale cloud environments.  The Kernel Team has openings for software engineers in a number of aspects that include but are not limited to:"
            postDate = Date()
            deadline = Date()

            jobAbout[0] = "Ubuntu Linux distribution"
            jobAbout[1] = "Optimized Ubuntu solutions for key Silicon and Cloud partners"
            jobAbout[2] = "Developer platforms such as Raspberry Pi and RISC-V"
            jobAbout[3] = "Enabling Ubuntu on a wide range of embedded systems"
            jobAbout[4] = "Livepatch service, which allows Ubuntu customers to apply critical kernel (CVE) security fixes"
            jobAbout[5] = "FIPS compliance"

            requirements[0] = "Earned a bachelor’s (or equivalent university level) degree, preferably in a technology field"
            requirements[1] = "Significant programming experience in C, with Python and Bash scripting also highly regarded"
            requirements[2] = "Broad knowledge of the Linux kernel and kernel subsystems"
            requirements[3] = "Demonstrated experience with kernel patching and debugging"
            requirements[4] = "Knowledge/experience with Linux kernel upstream processes and release cycle"
            requirements[5] = "Strong grasp of device drivers, BSP’s, and other low level system engineering"
            requirements[6] = "Prior experience with Ubuntu/Debian/Snap packaging would be highly regarded"
        }
        allJobs.add(job)

        var job1 = Job("Relationship Officer", "Operations")
        job.apply {
            companyName = "Jumia-Kenya"
            jobType = "Full Time"
            jobPlace = "Nairobi Kenya"
            jobLevel = "Entry Level"
            jobAbout[0] = "You will be part of an agile team that develops e-commerce, smart logistics, and payment solutions."
            jobAbout[1] = "Your work will integrate a service-oriented architecture using state-of-the-art technologies and software development practices; You will own your services and be responsible for end-to-end"
            jobAbout[2] = "You will take ownership for your code to be qualitatively high and you will support your colleagues in code reviews"
            jobAbout[3] = "You will change the Internet landscape in Africa and the World one line of code at a time."
            jobUrl = "https://www.linkedin.com/jobs/collections/recommended/?currentJobId=3447808034"
            description = "Jumia is the leading pan-Africa e-commerce platform. Founded in 2012, Jumia's mission is to improve the quality of everyday life in Africa by leveraging technology to deliver innovative, convenient and affordable online services to consumers, while helping businesses grow as they use our platform to reach and serve consumers."
            postDate = Date()
            deadline = Date()
            requirements[0] = "Knowledgeable of SQL and relational databases"
            requirements[1] = "Knowledgeable of software design and architecture patterns"
            requirements[2] = "Acquainted with building testable code, unit tests and/or integration tests"
            requirements[3] = "Experience with development in Java (and ideally Spring boot framework)"
            requirements[4] = "Experienced with object Oriented Programming"
        }
        allJobs.add(job1)

        var job2 = Job("Risk Attachees", "Risk")
        job.apply {
            companyName = "Equity Bank LTD"
            jobPlace = "Multiple Locations"
            jobType = "Temporary"
            jobLevel = "Entry Level"
            jobUrl = "https://equitybank.taleo.net/careersection/ext_new/jobdetail.ftl?job=220000F6&tz=GMT%2B03%3A00&tzname=Africa%2FNairobi"
            description = "Equity Bank is one of the region’s leading banks whose purpose is to transform the lives and livelihoods of the people of Africa socially and economically by availing them modern and inclusive financial services that maximize their opportunities. With a strong footprint in Kenya, Uganda, Tanzania, Rwanda, South Sudan and DRC Congo, Equity Bank is now home to over 12 million customers - the largest customer base in Africa. Currently the Bank is seeking additional talent to serve in the role outlined below within Risk Department"
            postDate = Date()
            deadline = Date()

            jobAbout[0] = "Key Duties and Responsibilities for the new Staff"
            jobAbout[1] = "Conduct risk assessments and develop key risk indicators for monitoring and reporting"
            jobAbout[2] = "stress testing of the various key risks facing the bank"
            jobAbout[3] = "Participate in developing and updating models used to manage bank’s credit risk, operational risk, market risk, liquidity risks."
            jobAbout[4] = "Any other tasks as directed by the risk leadership"

            requirements[0] = "Bachelor’s degree in a quantitative field [economics, statistics, mathematics, financial engineering, actuarial science etc.]"
            requirements[1] = "Professional programs such as FRM, FRR, CFA, CPA, CISA, SCR etc. will be desired."
        }
        allJobs.add(job2)

        var job3 = Job("Group Financial Reporting Manager ", "Operations")
        job.apply {
            companyName = "Equity Bank LTD"
            jobPlace = "Nairobi-Kenya"
            jobType = "Regular"
            jobLevel = "Manager"
            jobUrl = "https://equitybank.taleo.net/careersection/ext_new/jobdetail.ftl?job=23000013&tz=GMT%2B03%3A00&tzname=Africa%2FNairobi"
            description = " Equity Bank is one of the region’s leading banks whose purpose is to transform the lives and livelihoods of the people of Africa socially and economically by availing them modern and inclusive financial services that maximize their opportunities. With a strong footprint in Kenya, Uganda, Tanzania, Rwanda, South Sudan and DRC Congo, Equity Bank is now home to over 12 million customers - the largest customer base in Africa. Currently the Bank is seeking additional talent to serve in the role outlined below."
            postDate = Date()
            deadline = Date()

            jobAbout[0] = "Preparation and submission of periodic financial reports in accordance with IFRS, statutory and regulatory requirements (CBK & KDIC)"
            jobAbout[1] = "Consolidation of Group Financial statements Monthly, Quarterly and Annually"
            jobAbout[2] = "Coordinate preparation and submission to management of daily performance reports and insights into material movement in key parameters."
            jobAbout[3] = "Establish and maintain effective internal controls over financial reporting process"
            jobAbout[4] = "Regular monitoring and review of all GLs for accuracy and appropriateness of business transactions recorded across the group"
            jobAbout[5] = "Review of group wide GL reconciliations and ensure outstanding items are investigated and cleared promptly"

            requirements[0] = "Bachelor of Commerce (Bcom) majoring in Accounting or equivalent degree"
            requirements[1] = "Relevant Professional Qualifications eg CPA (K) or ACCA"
            requirements[2] = "MBA (added advantage)"
            requirements[3] = "Experience in data analysis;"
            requirements[4] = "In-depth knowledge of IFRSs."
            requirements[4] = "At least 8 years in Finance and Financial Reporting"
            requirements[4] = "In depth knowledge in Central Bank regulations"
        }
        allJobs.add(job3)

        var job4 = Job("Relationship Officer", "Operations")
        job.apply {
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

            jobAbout[0] = "Build and maintain efficient and reliable Java code"
            jobAbout[1] = "Support continuous improvement by investigating alternatives and technologies"
            jobAbout[2] = "Ensure the best quality and responsiveness of the applications"
            jobAbout[3] = "Be part of all the global improvements"
            jobAbout[4] = "Be a driver of change and innovation within the company"

            requirements[0] = "Java 8 +"
            requirements[1] = "JEE"
            requirements[2] = "The Spring ecosystem including Hibernate"
            requirements[3] = "Scrum, Agile practices (continuous integration, automated testing, pair programming...)"
            requirements[4] = "You're interested in state-of-the-art technologies"

            jobBenefits[0] = "An informal hierarchy and work environment"
            jobBenefits[1] = "An attractive salary package:"
            jobBenefits[2] = "A strong corporate culture:"
            jobBenefits[3] = "Learning & development opportunities"
            jobBenefits[4] = "Exciting projects"
        }
        allJobs.add(job4)

        var job5 = Job("Telesales Representative", "Marketing")
        job.apply {
            companyName = "Branded Communication agency"
            jobPlace = "Kitale, Trans-Nzoia County, Kenya"
            jobType = "Contract"
            jobLevel = "Entry Level"
            jobUrl = "https://www.linkedin.com/jobs/search/?currentJobId=3454058631&keywords=marketing&refresh=true"
            description = ""
            postDate = Date()
            deadline = Date()

            jobAbout[0] = "Initiating sales with potential customers over the phone."
            jobAbout[1] = "Asking questions to engage customers and keep the conversation going."
            jobAbout[2] = "Listening to the customers' needs to generate repeat sales."
            jobAbout[3] = "Gathering and documenting customer information, payment methods, purchases, and reactions to products."
            jobAbout[4] = "Keeping up to date on all products and informing customers of new products."
            jobAbout[5] = "Answering customers' questions on the products"

            requirements[0] = "Excellent interpersonal and problem solving skills."
            requirements[1] = "Excellent communication skills."
            requirements[2] = "should have dip /degree in digital marketing ,IT"
            requirements[3] = "Telecommunication background is an added advantage"
            requirements[4] = "The patience and ability to engage customers in conversation."
        }
        allJobs.add(job5)
    }
}