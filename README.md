# Beginning TaskMaster (Lab 26)

Features added in this lab :

1. Homepage : create image to mock the “my tasks” view, and buttons at the bottom of the page to allow going to the “add tasks” and “all tasks” page.

2. Add a Task : allow users to type in details about a new task, specifically a title and a body. When users click the “submit” button, show a “submitted!” label on the page.

3. All Task : Create page and add image with a back button


![Screenshot1.jpg](screenshots/ScreenshotLab2601.jpg)
![Screenshot2.jpg](screenshots/ScreenshotLab2602.jpg)
![Screenshot3.jpg](screenshots/ScreenshotLab2603.jpg)

# Data in TaskMaster (Lab 27)

Features added in this lab :

1. Task Detail Page : Create a Task Detail page. It should have a title at the top of the page 

2. Settings Page : Create a Settings page. It should allow users to enter their username and hit save

3. Homepage : add new three different buttons and when a user taps one of the titles, it should go to the Task Detail page, and the title at the top of the page should match the task title that was tapped on the previous page.



![Screenshot1.jpg](screenshots/ScreenshotLab2701.jpg)
![Screenshot2.jpg](screenshots/ScreenshotLab2702.jpg)
![Screenshot3.jpg](screenshots/ScreenshotLab2703.jpg)
![Screenshot4.jpg](screenshots/ScreenshotLab2704.jpg)
![Screenshot5.jpg](screenshots/ScreenshotLab2705.jpg)

# RecyclerView (Lab 28)

Features added in this lab :

1. Task Model : Create a Task class. A Task should have a title, a body, and a state. The state should be one of “new”, “assigned”, “in progress”, or “complete”.

2. Homepage : 

* Refactor your homepage to use a RecyclerView for displaying Task data. This should have hardcoded Task data for now and Create a ViewAdapter class that displays data from a list of Tasks.

* Ensure that you can tap on any one of the Tasks in the RecyclerView, and it will appropriately launch the detail page with the correct Task title displayed.

![Screenshot1.jpg](screenshots/ScreenshotLab2801.jpg)
![Screenshot2.jpg](screenshots/ScreenshotLab2802.jpg)