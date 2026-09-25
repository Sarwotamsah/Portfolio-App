package com.example.data

import com.example.model.EstimateOption
import com.example.model.ExperienceItem
import com.example.model.Project
import com.example.model.ProjectCategory
import com.example.model.ServiceItem
import com.example.model.SkillCategoryGroup
import com.example.model.SkillItem
import com.example.model.Testimonial

object PortfolioData {

    const val NAME = "Sarwotam Sah"
    const val ALTERNATE_NAME = "Sarwotam Amit"
    const val TAGLINE = "Software Developer • AI Automation Analyst • SEO Expert"
    const val MOTTO = "Build once, inspire infinitely."
    const val SECONDARY_QUOTE = "Everything will be alright at the end, if it's not alright, it's not the end."
    const val BIO_SUMMARY = "In this tech era, I build at the intersection of engineering, education, and entrepreneurship. My mission is to design systems, content, and products that help people and organizations grow faster with clarity."
    const val LOCATION = "Kathmandu, Nepal (Available Globally)"
    const val EMAIL = "info@sarwotamsah.com.np"
    const val SECONDARY_EMAIL = "sahm7854@gmail.com"
    const val WEBSITE_URL = "https://sarwotamsah.com.np/"
    const val GITHUB_URL = "https://github.com/sarwotamsah"
    const val LINKEDIN_URL = "https://www.linkedin.com/in/sarwotam-sah"
    const val INSTAGRAM_URL = "https://www.instagram.com/sarwotam.sah"
    const val YOUTUBE_URL = "https://www.youtube.com/@sarwotamvlog"

    val STATS = listOf(
        Triple("20+", "Delivered Projects", "Web, AI & mobile solutions"),
        Triple("15k+", "Audience Reach", "Blog views & learners"),
        Triple("2+", "Years Experience", "Production engineering"),
        Triple("99%", "Client Satisfaction", "Quality & reliability")
    )

    val PROJECTS = listOf(
        Project(
            id = "smart-task-manager",
            title = "Smart Task Manager",
            tagline = "Kanban productivity app with role-based access & team sync",
            category = ProjectCategory.WEB_DEV,
            description = "A full-featured Kanban productivity platform engineered for modern agile squads. Features granular role-based access controls, interactive drag-and-drop swimlanes, real-time activity feeds, and notification pipelines.",
            keyFeatures = listOf(
                "Multi-column Kanban boards with intuitive status transitions",
                "Role-based permissions (Admin, Member, Viewer)",
                "Real-time task synchronization across active collaborators",
                "Automated due-date reminders & productivity analytics",
                "Responsive dark & light mode dashboard"
            ),
            techStack = listOf("React", "TypeScript", "Node.js", "Express", "PostgreSQL", "Tailwind CSS"),
            liveDemoUrl = "https://sarwotamsah.com.np/projects",
            githubUrl = "https://github.com/sarwotamsah",
            isFeatured = true,
            impactMetric = "Used by 12+ distributed teams"
        ),
        Project(
            id = "edutrack-lms",
            title = "EduTrack LMS",
            tagline = "Lightweight Learning Management System for instructors & students",
            category = ProjectCategory.WEB_DEV,
            description = "A fast, streamlined LMS tailored for modern educators and learners. Built to replace bloated legacy education tools with swift course navigation, interactive quizzes, video streaming, and detailed student progress analytics.",
            keyFeatures = listOf(
                "Structured modular course builder with video streaming",
                "Interactive self-assessment quizzes with instant scoring",
                "Student completion tracking and automated digital badges",
                "Instructor analytics dashboard with engagement metrics",
                "Ultra-fast page loads optimized for varying network conditions"
            ),
            techStack = listOf("Next.js", "Node.js", "MongoDB", "Tailwind CSS", "AWS S3", "REST APIs"),
            liveDemoUrl = "https://sarwotamsah.com.np/projects",
            githubUrl = "https://github.com/sarwotamsah",
            isFeatured = true,
            impactMetric = "500+ active learners trained"
        ),
        Project(
            id = "ai-resume-analyzer",
            title = "AI Resume Analyzer",
            tagline = "Intelligent ATS scoring, keyword alignment & career feedback",
            category = ProjectCategory.AI_ML,
            description = "An AI-powered career tool that parses PDF/DOCX resumes, scans against target job descriptions, identifies critical skill gaps, and recommends precise keyword enhancements to bypass Applicant Tracking Systems (ATS).",
            keyFeatures = listOf(
                "Automated PDF parsing and semantic text extraction",
                "Job description similarity matching & ATS compatibility score",
                "Actionable recommendations for formatting and impactful phrasing",
                "Skill gap visualization with industry benchmark comparison",
                "Privacy-first processing with zero permanent resume retention"
            ),
            techStack = listOf("Python", "FastAPI", "OpenAI / Gemini API", "React", "Tailwind CSS", "Docker"),
            liveDemoUrl = "https://sarwotamsah.com.np/projects",
            githubUrl = "https://github.com/sarwotamsah",
            isFeatured = true,
            impactMetric = "94% ATS match improvement rate"
        ),
        Project(
            id = "sewalink",
            title = "Sewalink Platform",
            tagline = "Connecting hyper-local services and trusted providers in Nepal",
            category = ProjectCategory.STARTUPS,
            description = "Founded by Sarwotam Sah, Sewalink bridges local service providers (technicians, educators, mechanics, freelancers) with customers across Nepal, empowering small business growth with easy digital discovery and booking.",
            keyFeatures = listOf(
                "Hyper-local geo-discovery of verified service providers",
                "Direct quote requests and structured inquiry management",
                "Provider portfolio showcases with customer ratings and reviews",
                "Bilingual interface tailored for regional ease of use",
                "Admin validation dashboard for provider credentials"
            ),
            techStack = listOf("React Native", "Next.js", "Node.js", "PostgreSQL", "Cloudinary"),
            liveDemoUrl = "https://sarwotamsah.com.np/",
            githubUrl = "https://github.com/sarwotamsah",
            isFeatured = true,
            impactMetric = "Founded startup with 100+ registered services"
        ),
        Project(
            id = "seo-crawler-suite",
            title = "SEO Performance & Audit Suite",
            tagline = "Real-time web crawler, keyword rank tracker & meta auditor",
            category = ProjectCategory.SEO_TOOLS,
            description = "Automated SEO analysis system that runs deep audits on webpage metadata, Core Web Vitals, Schema.org structured data, and broken links, generating actionable technical remediation roadmaps.",
            keyFeatures = listOf(
                "High-speed recursive website crawling and status code detection",
                "Schema.org JSON-LD validator and structured data tester",
                "Core Web Vitals diagnostic reporting (LCP, FID, CLS)",
                "Keyword density and competitive SERP ranking overview",
                "Automated PDF executive report generation"
            ),
            techStack = listOf("Python", "Playwright", "FastAPI", "Next.js", "Tailwind CSS"),
            liveDemoUrl = "https://sarwotamsah.com.np/services",
            githubUrl = "https://github.com/sarwotamsah",
            isFeatured = false,
            impactMetric = "Boosted organic traffic by +180% on client sites"
        ),
        Project(
            id = "portfolio-mobile-app",
            title = "Sarwotam Sah Native Portfolio",
            tagline = "High-performance Android Jetpack Compose companion app",
            category = ProjectCategory.WEB_DEV,
            description = "A responsive, modern Android application showcasing Sarwotam's engineering achievements, interactive project quote estimator, offline bookmarking with Room Database, and direct client consultation gateway.",
            keyFeatures = listOf(
                "100% Jetpack Compose with Material Design 3 and dynamic animations",
                "Offline-first Room database for project bookmarks & inquiry drafts",
                "Interactive Project Cost & Delivery Timeline Estimator",
                "Direct email intent integration and social channel triggers",
                "Optimized dark and light themes reflecting tech aesthetics"
            ),
            techStack = listOf("Kotlin", "Jetpack Compose", "Room DB", "Coroutines & Flow", "Material 3"),
            liveDemoUrl = "https://sarwotamsah.com.np/",
            githubUrl = "https://github.com/sarwotamsah",
            isFeatured = false,
            impactMetric = "Native 60fps fluid experience"
        )
    )

    val EXPERIENCES = listOf(
        ExperienceItem(
            id = "exp-technova",
            role = "Full-Stack Developer",
            organization = "TechNova Solutions",
            period = "Feb 2024 - Present",
            location = "Kathmandu, Nepal (Hybrid)",
            description = "Lead frontend and backend development for production-grade web applications. Focus heavily on resilient UX, microservices architecture, and cloud deployment pipelines.",
            keyDeliverables = listOf(
                "Engineered responsive web applications supporting 50,000+ monthly active sessions",
                "Refactored legacy REST endpoints, slashing response latencies by 35%",
                "Collaborated with cross-functional product and design teams in agile bi-weekly sprints",
                "Implemented rigorous automated unit and integration test suites"
            ),
            skills = listOf("React", "Next.js", "Node.js", "TypeScript", "PostgreSQL", "Docker"),
            isCurrent = true
        ),
        ExperienceItem(
            id = "exp-instructor",
            role = "Technical Instructor & Mentor",
            organization = "Independent Education Platform",
            period = "Jun 2023 - Present",
            location = "Remote",
            description = "Designed comprehensive curriculum and practical coding bootcamps for aspiring software engineers and university students.",
            keyDeliverables = listOf(
                "Taught foundational and advanced full-stack development to 200+ students",
                "Created hands-on capstone projects simulating real-world engineering environments",
                "Provided 1-on-1 code reviews, resume feedback, and technical interview prep",
                "Authored open-source guides and code starter templates"
            ),
            skills = listOf("Python", "JavaScript", "Algorithms", "System Design", "Teaching"),
            isCurrent = true
        ),
        ExperienceItem(
            id = "exp-consultant",
            role = "Product & Technology Consultant",
            organization = "Early-Stage Startups",
            period = "Jan 2023 - Dec 2024",
            location = "Remote / Nepal",
            description = "Advised startup founders on minimum viable product (MVP) scoping, technology selection, cloud cost reduction, and go-to-market execution.",
            keyDeliverables = listOf(
                "Helped 4 early-stage founders take concepts from wireframes to functional launches",
                "Established cost-effective cloud architectures on AWS and Vercel",
                "Designed SEO-optimized landing pages driving initial organic traction",
                "Established scalable database schemas anticipating high user growth"
            ),
            skills = listOf("MVP Scoping", "Architecture Review", "SEO Strategy", "Cloud Optimization"),
            isCurrent = false
        ),
        ExperienceItem(
            id = "exp-creator",
            role = "Content Creator & Blogger",
            organization = "Personal Brand / Sarwotam Amit",
            period = "Jan 2022 - Present",
            location = "Online",
            description = "Publishing in-depth technical blogs, programming tutorials, and career roadmaps for the South Asian and global tech community.",
            keyDeliverables = listOf(
                "Grew reader community to 15,000+ views across tech articles",
                "Covered modern web frameworks, AI automation workflows, and practical DevOps",
                "Organized community Q&A webinars helping college graduates break into tech"
            ),
            skills = listOf("Technical Writing", "SEO Content", "Video Production", "Community"),
            isCurrent = true
        ),
        ExperienceItem(
            id = "exp-education",
            role = "BE in Computer Engineering",
            organization = "Kathmandu University / Affiliated Institutions",
            period = "Academic Career",
            location = "Kathmandu, Nepal",
            description = "Solid theoretical and hands-on grounding in computer engineering, data structures, computer networks, database systems, and software engineering principles.",
            keyDeliverables = listOf(
                "Ranked high in competitive engineering entrance examinations (KUCAT-CBT)",
                "Conducted research and coursework in systems programming, OS, and algorithms",
                "Active participant in tech hackathons, coding contests, and campus tech clubs"
            ),
            skills = listOf("Computer Architecture", "Algorithms", "C/C++", "Database Systems"),
            isCurrent = false
        )
    )

    val SKILL_GROUPS = listOf(
        SkillCategoryGroup(
            categoryName = "Languages & Core",
            iconKey = "code",
            skills = listOf(
                SkillItem("Python", 0.92f, "Expert", "Backend & AI"),
                SkillItem("JavaScript / TypeScript", 0.95f, "Expert", "Full-Stack"),
                SkillItem("Kotlin", 0.82f, "Proficient", "Android & Compose"),
                SkillItem("SQL (Postgres, MySQL)", 0.88f, "Advanced", "Data Persistence"),
                SkillItem("HTML5 / CSS3 / Tailwind", 0.96f, "Expert", "Modern UI"),
                SkillItem("C / C++", 0.78f, "Solid", "Core Systems")
            )
        ),
        SkillCategoryGroup(
            categoryName = "Frameworks & Libraries",
            iconKey = "layers",
            skills = listOf(
                SkillItem("React & Next.js", 0.94f, "Expert", "Frontend & SSR"),
                SkillItem("Node.js & Express", 0.90f, "Advanced", "API & Microservices"),
                SkillItem("FastAPI", 0.88f, "Advanced", "High-performance Python"),
                SkillItem("Jetpack Compose", 0.85f, "Advanced", "Declarative Android"),
                SkillItem("Redux & Zustand", 0.88f, "Advanced", "State Management")
            )
        ),
        SkillCategoryGroup(
            categoryName = "Cloud, DevOps & Data",
            iconKey = "cloud",
            skills = listOf(
                SkillItem("AWS (EC2, S3, RDS)", 0.84f, "Advanced", "Cloud Hosting"),
                SkillItem("Docker & Containers", 0.82f, "Proficient", "Containerization"),
                SkillItem("Git & GitHub Actions", 0.92f, "Expert", "CI/CD & Version Control"),
                SkillItem("Firebase / App Check", 0.86f, "Advanced", "Mobile Backend"),
                SkillItem("Room & SQLite", 0.88f, "Advanced", "Local Storage")
            )
        ),
        SkillCategoryGroup(
            categoryName = "AI Automation & Growth",
            iconKey = "bolt",
            skills = listOf(
                SkillItem("AI Workflow Automation", 0.90f, "Advanced", "LLM Pipelines"),
                SkillItem("SEO Technical Audits", 0.94f, "Expert", "Rank Growth"),
                SkillItem("Core Web Vitals Tuning", 0.90f, "Advanced", "Speed Optimization"),
                SkillItem("Schema & Structured Data", 0.95f, "Expert", "Search Visibility")
            )
        )
    )

    val SERVICES = listOf(
        ServiceItem(
            id = "freelance-build",
            title = "Freelance Build",
            tagline = "Turn your concept into a high-performance web or mobile product",
            priceTag = "$499+",
            description = "End-to-end development of custom web applications, SaaS MVPs, admin dashboards, or mobile apps with clean code, scalable architecture, and delightful UI.",
            features = listOf(
                "Full-stack custom development (React, Next.js, Node, Kotlin)",
                "Responsive design optimized for mobile, tablet, and desktop",
                "Authentication, role permissions & database setup",
                "Production cloud deployment & CI/CD pipeline",
                "30 days post-launch support & bug fixing"
            ),
            deliveryTime = "2 - 4 Weeks",
            cta = "Book for Freelance"
        ),
        ServiceItem(
            id = "consulting",
            title = "Technical Consulting",
            tagline = "Strategic architecture reviews, growth strategy & dev acceleration",
            priceTag = "$129/hr",
            description = "Get expert advice on technology choices, architecture restructuring, performance bottleneck resolution, and engineering roadmap planning.",
            features = listOf(
                "Comprehensive code and architecture audit",
                "Database optimization and bottleneck diagnostics",
                "Cloud infrastructure cost-reduction strategies",
                "Technical hiring and team workflow advisory",
                "Written executive report with prioritized recommendations"
            ),
            deliveryTime = "Immediate Booking",
            cta = "Book for Consulting"
        ),
        ServiceItem(
            id = "courses-mentorship",
            title = "Courses & Mentorship",
            tagline = "Structured, hands-on coaching for students & budding engineers",
            priceTag = "$79+",
            description = "Accelerate your career with tailored 1-on-1 mentorship sessions, code reviews, algorithm walkthroughs, and portfolio building.",
            features = listOf(
                "1-on-1 dedicated live coaching session",
                "Portfolio and resume ATS review with actionable feedback",
                "Practical full-stack project building guidance",
                "Mock technical interview and problem-solving tips",
                "Curated learning roadmap tailored to your career goal"
            ),
            deliveryTime = "Flexible Scheduling",
            cta = "Book for Mentorship"
        ),
        ServiceItem(
            id = "ai-seo-automation",
            title = "AI Automation & SEO",
            tagline = "Supercharge business efficiency and dominate search engines",
            priceTag = "$299+",
            description = "Automate tedious business processes using LLM integrations and execute data-backed technical SEO to skyrocket organic visibility.",
            features = listOf(
                "End-to-end technical SEO audit & schema implementation",
                "AI document analyzer or chatbot workflow integration",
                "Core Web Vitals performance tuning (90+ mobile score)",
                "Competitor keyword gap analysis & strategy",
                "Monthly rank tracking & analytics dashboard"
            ),
            deliveryTime = "1 - 2 Weeks",
            cta = "Book for AI & SEO"
        )
    )

    val TESTIMONIALS = listOf(
        Testimonial(
            id = "t1",
            quote = "Delivered our product faster than expected with excellent communication.",
            author = "Aarav Sharma",
            role = "Startup Founder",
            relation = "Client (Web Application MVP)"
        ),
        Testimonial(
            id = "t2",
            quote = "The teaching style is practical, motivating, and easy to follow. Made complex topics simple.",
            author = "Nisha Karki",
            role = "Student",
            relation = "Mentee (Full-Stack Bootcamp)"
        ),
        Testimonial(
            id = "t3",
            quote = "Strong ownership mindset with deep technical understanding. A true collaborator.",
            author = "Rahul Mehta",
            role = "Product Manager",
            relation = "Industry Colleague"
        ),
        Testimonial(
            id = "t4",
            quote = "Great storyteller with real execution experience behind every insight.",
            author = "Sofia Lin",
            role = "Content Collaborator",
            relation = "Tech Creator Network"
        ),
        Testimonial(
            id = "t5",
            quote = "Helped us modernize our stack and improve performance noticeably. The site is flying now.",
            author = "Dylan Cruz",
            role = "Client",
            relation = "Full-Stack Refactor & SEO"
        )
    )

    val ESTIMATE_OPTIONS = listOf(
        EstimateOption(
            id = "opt_web_mvp",
            title = "Web Application MVP",
            description = "Full-stack React / Next.js app with modern database & auth",
            priceUsd = 450,
            days = 14,
            category = "Development"
        ),
        EstimateOption(
            id = "opt_mobile_app",
            title = "Native Android App",
            description = "Smooth Jetpack Compose app with offline Room database",
            priceUsd = 400,
            days = 12,
            category = "Development"
        ),
        EstimateOption(
            id = "opt_ai_integration",
            title = "AI Automation Pipeline",
            description = "LLM resume parsing, prompt workflow or chatbot integration",
            priceUsd = 250,
            days = 7,
            category = "AI / Automation"
        ),
        EstimateOption(
            id = "opt_seo_overhaul",
            title = "Comprehensive SEO Overhaul",
            description = "Technical SEO, Core Web Vitals optimization & Schema.org",
            priceUsd = 180,
            days = 5,
            category = "Growth / SEO"
        ),
        EstimateOption(
            id = "opt_cloud_cicd",
            title = "Cloud Infrastructure & CI/CD",
            description = "AWS/Vercel setup, Docker containerization & GitHub Actions",
            priceUsd = 150,
            days = 4,
            category = "DevOps"
        ),
        EstimateOption(
            id = "opt_maintenance",
            title = "1-Month Dedicated Support",
            description = "Priority bug fixes, feature tweaks & performance checks",
            priceUsd = 120,
            days = 30,
            category = "Support"
        )
    )
}
