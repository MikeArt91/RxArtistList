# RxArtistList
#### A sample project using RxJava and Clean Architecture approach

![rxartislist](https://cloud.githubusercontent.com/assets/15881137/20467245/da7d995a-af94-11e6-837a-e7a6db70313e.png)

APK is availiable for download via that link:
<a href="https://www.dropbox.com/s/ml657xlwuyicq60/RxArtistList.apk?dl=0">RxArtistList.apk</a> 

The main purpose of this project was to make a simple app using RxJava and Clean Architecture approach.<br>
This project includes: 

* <b>Patterns</b> Clean Architecture, Reactive, MVP
* <b>Technologies</b> *REST, OkHTTP, Json, RxJava, Dependency Injection*
* <b>Libraries</b> *Dagger, Butterknife, Picasso, GSON, Support Library etc*
* <b>Tests</b> *JUnit, Mockito, Roboelectric, Espresso, Leak Canary*

Clean Architecture approach is inspired by <a href="http://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/">series of articles</a>
written by *Fernando Cejas*. <br>
Also all credits goes to him for the schemes presented below.

## Here is the basic scheme of this architectural approach:

![Clean Architecture](https://github.com/android10/Sample-Data/raw/master/Android-CleanArchitecture/clean_architecture_layers_details.png)

## How does it work?
Let's briefly walk through the three main layers of this application: *Data, Domain, Presentation*
####Data
Data is the repository level of the application. It's responsible for retreiving data from public JSON provided by Yandex Company,
caching and providing other layers with the most up-to-date list of artists. List of obtained artists is emitted by observable 
to the next level.
####Domain
Domain layer cathes data from the data layer (obviously). The flow of data out of the domain to presentation is due to the use cases.
In this application there are two particular use cases: *provide list of artists, provide particular artist by id.*
####Presentation
Presentation layer is very straightforward. Presenters are preparing data for the view. View is based on the fragments,
but their lifecycle is controlled by allocated activities.

## Dependency Injection (Dagger 2)
In this project I used Dagger 2 for dependency injection. It is indeed very useful to get rid out of bolerplate code. <br>
Basics of dependecy injection model used in this app are very well described in 
<a href="http://fernandocejas.com/2015/04/11/tasting-dagger-2-on-android/">this article</a> <br>
I will just leave here this scheme as a brief description

![Dependency Injection](http://fernandocejas.com/wp-content/uploads/2015/04/composed_dagger_graph1.png)

## What about testing?
Good question. Here is a bunch of tests on the each layer:
- <b>Data</b> *Robolectric* + *JUnit* + *Mockito* for unit tests 
- <b>Domain</b> *JUnit* + *Mockito* for unit tests
- <b>Presentation</b> *Android Instrumentation* for unit tests + *Espresso* for UI tests

## Error Handling
Error messages are sent by the callbacks for exception classes (look for them on each layer)

## Developed by
Mikhail Artamonov <br>
<a href="http://facebook.com/mikeart91">Facebook</a> <br><br>

Special thanks to <a href="http://fernandocejas.com/about-me/">Fernando Cejas</a> and his brilliant articles




