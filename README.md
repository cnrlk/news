# Bundle - Android Engineer Assessment
Hey, thanks for taking the time to try out this test! 👋

The goal of this test is to assert (to some degree) your technical, architectural, and problem-solving skills. You're given a simple problem so you can focus on showing off your technique.

We're hoping to hear back from you in 2 days, but if you need more time then no problem as long as you let us know.

# Problem Statement
This is a simple news app that allows users to view a list of news and read the details of each one in either web or reader mode. 
You should use JSON files to fetch the list and details of the news.

** News List Page ** 

Displays a list of news sorted by "pubDate" in descending order, with the newest one at the top. 

- Fetch news from the "news.json" file and cached to avoid reloading the file each time the app is opened.
- There should be a button or switch to sort the news list by "pubDate" in ascending or descending order. 
- App should remember the user's last selection for sorting and display the news list accordingly when reopened.

** News Detail Page **

Displays the details of a news item when the user taps on it in the list screen. 

- The news detail information should be read from the news-detail.json file.
- There should be two modes on the page, "Web and Reader". 
- News detail should open in Web mode by default. Users should be able to switch between these modes.

Web mode: The news detail should be displayed in a WebView using the "link" parameter under news-detail.json.
Reader mode: The news detail should be displayed using the "title", "description", and "imageUrl" parameters under news-detail.json.

# Requirements
- Jetpack Compose library should be used for UI design. 
- Navigation Component with Jetpack Compose should be used
- You should adopt the "Single Activity Approach" principle and Fragments should not be used.
- MVVM (Model-View-ViewModel) architecture pattern should be used
- Coroutines & Flows should be used
- SharedPref or DataStore can be used for caching
- Dependency Injection (DI) should be used

PS: If you are not familiar with Jetpack Compose, you can use Fragments

# Bonus
- Dark/Light Theme support based on device settings
- Clean Architecture principles

# UX/UI
It is up to the developer, there is no specific requirement. Don't waste your time with the fancy UI

# Rules
- You must not commit to the master branch of this repository.
- Create a pull request with your solution. The pull request body should contain instructions for testing your solution, talk about any improvements you would make in a production setup, and justify some of your decisions.
- As this is a code review process, you must not add generated code to the project.
- You should reply to any questions asked in comments on the pull request.

# Evaluation Criteria
To give you an idea of what we are looking for in a successful solution:

- Efficient problem-solving skills. You know when complexity is required, and when to keep things simple.
- Easy for us to run the solution locally, with detailed instructions and/or a setup script.
- The solution is logically organised. Your code is documented, well-formatted, and easy to follow.
- Your PR description is clear and informative. You will be assessed on communication skills as well as technical prowess.
- A tidy Git commit history (even if it's brief).
