# Macro Pay Challenge
Example Sign In with Firebase and Movie listing

## :scroll: Motivation and Context

This project simulates the Sign in of a user and a movie listing, things to consider:
- For Sing In was used Firebase Authentication
- For Sing in, there is validation of the fields, like email, passord, etc.
- The [TMBD endpoint](https://developer.themoviedb.org/docs/getting-started) was used to list the movies.


## :paperclip:  Technologies and topics used

### Technologies
- Kotlin
- Corrutines like Flow
- Hilt
- Firebase
- Compose

#### For testing:
- JUnit
- Mockito
- Hamcrest
- Corrutines test

### Topics
- Clean Architecture
- Clean Code
- SOLID
- MVVM patten
- Repository pattern
- Dependency injection

## Firebase Authentication
To manage user login, Firebase Authentication was used with email and password.

![image](https://github.com/salvadormaurilio/MacroPay-Challenge/assets/4513422/373f59ab-f9f8-4e41-b64d-fa6e18b719c4)


## :art: UI Test

https://github.com/salvadormaurilio/MacroPay-Challenge/assets/4513422/fb32f5ff-c3a2-4a3f-a493-811ee196fd68


## :green_heart: How did you test it?

To test and see movies you can use the following credentials

```
email: salvador@macropay.com
password: Admin_123
```

**Also you can run the Unit Tests, recommended command:**

```
/gradlew test
```


