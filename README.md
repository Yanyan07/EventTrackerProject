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
* List<SingleHiking>  GET api/hikings                                     Get all hiking events
* List<SingleHiking>  GET api/hiking/{hikerId}                            Get all hiking events by hiker id
* SingleHiking        POST api/hikings/{hikerId}/{trailId}/hiking         Create a new SingleHiking
* void                DELETE                                              Delete an existing SingleHiking by hiker id and SingleHiking id

* List<Hiker>         GET api/hikers                                      Gets all hikers
* double              GET api/hikers/distance/{hikerId}                   Get total hiking distance by hiker id
* Trail               Get api/hikers/favorite/{hikerId}                   Get favorite trail by hiker id
* (the favorite trail is decided by the hiking times, if the hiking times is the same, then it is decided by the hiking distance)

* List<Trail>         GET api/trails                                      Get all trails
* Trail               GET api/trails.popular                              Get the popular trail(decided by hiking times)
