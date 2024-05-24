# Stori Login Challenge
Example of Sing Up and Sign In with Firebase

## :scroll: Motivation and Context

This project simulates the Sign Up and Sign in of a user, things to consider:
- For Sing In and Sign Auth, was used Firebase Authentication
- All Userdata is saved in Firebase Storage and Firestore
- For Sign Up and Sing in, there is validation of the fields, like email, passord, etc.


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

## :floppy_disk: Firebase

### Firebase Authentication
To manage user login, Firebase Authentication was used with email and password.

![image](https://github.com/salvadormaurilio/Stori-Challenge/assets/4513422/f51ed1bc-1d09-4df9-a9da-b5280bf5597e)

### Firebase Storage
All user picture identification are stored in Firebase Storage, and the picture url is saved in Firestorage like user data

![image](https://github.com/salvadormaurilio/Stori-Challenge/assets/4513422/5cda0d09-2b7e-4030-aae6-06fab270ca18)


### Firestore
Now to store the user data Firestore is used, having a collection `users` to save the user data and another collection intended `movements` to save the movements

**Users table**

![image](https://github.com/salvadormaurilio/Stori-Challenge/assets/4513422/b38f65ef-a3c8-4790-aea0-8be372a37f7e)


**Movements table**

![image](https://github.com/salvadormaurilio/Stori-Challenge/assets/4513422/0ff22771-829c-424d-a031-52188ed60b93)

## :art: UI Test

### Sign In

Sign In Success | Some Fiel is Wrong | User Invalid
--- | ---  | --- 
<img src="https://github.com/salvadormaurilio/Stori-Challenge/assets/4513422/a1f85b87-1680-4212-b879-364bab1fa61b" width="320"> | <img src="https://github.com/salvadormaurilio/Stori-Challenge/assets/4513422/41e0bf18-859f-4ad7-88c8-d844368eeba6" width="320"> | <img src="https://github.com/salvadormaurilio/Stori-Challenge/assets/4513422/699b03e5-2910-4af4-9c85-ee7f4d877f97" width="320">

### Sign Up

Sign Up Success | Some Fiel is Wrong | User al readey Exist
--- | ---  | --- 
<img src="https://github.com/salvadormaurilio/Stori-Challenge/assets/4513422/3a096102-9b5b-4de5-b41c-22290f9ad87a" width="320"> | <img src="https://github.com/salvadormaurilio/Stori-Challenge/assets/4513422/586f4874-2ae9-4314-a90b-e429c617cb05" width="320"> | <img src="https://github.com/salvadormaurilio/Stori-Challenge/assets/4513422/615c0f8f-c8c1-4ed2-b34d-c7f0e10f557c" width="320">


### Home

Home Success | Home Fails 
--- | --- 
<img src="https://github.com/salvadormaurilio/Stori-Challenge/assets/4513422/a1f85b87-1680-4212-b879-364bab1fa61b" width="320"> | <img src="https://github.com/salvadormaurilio/Stori-Challenge/assets/4513422/ae615d9a-3c83-41a5-8372-c669c6aeab61" width="320"> 


### Movement Detail

Movement Detail Success | Movement Detail Fails 
--- | --- 
<img src="https://github.com/salvadormaurilio/Stori-Challenge/assets/4513422/0717d23f-f8f0-43bc-9b63-affb2ca1766d" width="320"> | <img src="https://github.com/salvadormaurilio/Stori-Challenge/assets/4513422/86fc8f57-7302-477a-a738-8de7a40ca8b8" width="320"> 


## :green_heart: How did you test it?

To test and see movements you can use the following credentials

```
email: salvador@stori.mx
password: Admin_123
```
You can also create a user from scratch to test Sign Up


**Also you can run the Unit Tests, recommended command:**

```
/gradlew test
```


