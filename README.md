
# Freecharge

I've created this android application which performs CRUD operations from Admin side and display it on the User side with the help of Retrofit API.




## Backend

- PHP MySQL
- Wampserver



## 🛠 Tools Used
- Android Studio
- Microsoft Visual Studio Code
- Postman (For API Testing)


## Gradle Script

You need to add this line of code in your **build.gradle (module :app)** for binding the bottom navigation in your app.


```bash
  buildFeatures{
        viewBinding true
    }
```


## Dependancies

You need to add these dependancies in your **build.gradle (module :app)** to build the project.


```bash
    implementation 'com.squareup.retrofit2:retrofit:2.4.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.4.0'
    implementation 'com.airbnb.android:lottie:3.4.1'
    implementation 'androidx.cardview:cardview:1.0.0'
    implementation 'androidx.viewpager2:viewpager2:1.0.0'
    implementation 'com.makeramen:roundedimageview:2.3.0'
```


## AndroidManifest.xml

Add this two lines for Internet permissions and notification permissions.

```bash
  <uses-permission android:name="android.permission.INTERNET" />
  <uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
```

To prevent an app from using cleartext network traffic, Add this line in Application tag.

```bash
  android:usesCleartextTraffic="true"
```


## Database

Go into **phpmyadmin** panel and create database and name it **android**.

Create following tables in that database.

#### Table: user

| Name | Type     | Description                |
| :-------- | :------- | :------------------------- | 
| id | `int` | **Primary key** |
| username | `varchar(100)` | - |
| email | `varchar(100)` | - |
| password | `varchar(100)` | - |

#### Table: plan

| Name | Type     | Description                |
| :-------- | :------- | :------------------------- | 
| id | `varchar(100)` | **Primary key** |
| validity | `varchar(100)` | - |
| data | `varchar(100)` | - |
| price | `varchar(100)` | - |
