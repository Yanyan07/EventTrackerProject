### EventTrackerProject

### Overview
This weekends project was sprintREST project where I used JSON and SpringREST to complete a Hiker Tracker application.
This application used Spring Data JPA to interact with databases, and implemented operations of create read update and delete data from databases.
This application tracked a hiker's activities. We can track a hiker's hiking distance, what day the hiking happened and where this happened. We can also track how frequently the hiker went hiking and what the favorite trails the hiker liked to go. What's more, from the history of people's hiking, we can get the information which trails are very popular in Colorado.

### Technologies Used
* Spring Data JPA
* SpringREST
* Postman
* JSON
* Github

### Lessons Learned
It was a challenge to draw the EER Diagram of different tables. I spent a while to learn this tool and make it work perfect.

### Illustration
*18.118.127.156:8080/HikerTracker/api/hikings ----> Get all hiking events

*18.118.127.156:8080/HikerTracker/api/hiking/{hikerId} ----> Get all hiking events by hiker id

*18.118.127.156:8080/HikerTracker/api/hikings/{hikerId}/{trailId}/hiking ----> Create a new SingleHiking

*18.118.127.156:8080/HikerTracker/api/hikings/{hikerId}/{trailId}/{hikingId}/hiking ----> Update an existing SingleHiking by hiker id trail id and SingleHiking id

*18.118.127.156:8080/HikerTracker/api/hikings/{hikerId}/{hikingId}/hiking ----> Delete an existing SingleHiking by hiker id and SingleHiking id

*18.118.127.156:8080/HikerTracker/api/hikers ----> Gets all hikers

*18.118.127.156:8080/HikerTracker/api/hikers/distance/{hikerId} ----> Get total hiking distance by hiker id

*18.118.127.156:8080/HikerTracker/api/hikers/favorite/{hikerId} ----> Get favorite trail by hiker id (the favorite trail is decided by the hiking times, if the hiking times is the same, then it is decided by the hiking distance)

*18.118.127.156:8080/HikerTracker/api/trails ----> Get all trails

*18.118.127.156:8080/HikerTracker/api/trails.popular ----> Get the popular trail(decided by hiking times)
