# MusicWiki
MusicWiki is an unofficial Last.fm app that contains information about different music genres, the albums, artists and tracks listed under a particular genre.

## Built using - 
- Kotlin Programming Language
- [lastfm api](https://www.last.fm/api)
- Glide Image Library
- Android Studio

## Features of the app - 
- The App uses MVVM architecure & coroutines to asynchronously call the API. 
- Then on the HomeScreen/Mainactivity it displays the list of the available genres. 
- Clicking on the genre it takes the user to the 'GenreInfo Activity' which contains information regarding it like - the title, description.
- In this very screen or activity there is a viewpager which displays the list of top albums, top tracks and top artists as different sections.
> - Each item listed under the album shows the title, artist name and the cover image if available or the default launcher image.
> - Each item listed under the artists shows the artist name and the cover image if available or the default launcher image.
> - Each item listed under tracks shows the title, artist name and the cover image if available or the default launcher image.
- On click AlbumItem -> it takes you to the 'AlbumInfo Activity' 
> The 'AlbumInfo Activity' displays the cover image, title and the artist information which includes the the description and the list of genres in a horizontal recyclerview. Clicking on the genre it takes you to the 'GenreInfo Activity' as explained above and the flow continues. 
- On click ArtistItem -> it takes you to the 'ArtistInfo Activity' 
> The 'ArtistInfo Activity' displays the image, title ,play count and listeners, description. Adding to this it shows a list of top tracks and top albums and the genres displayed using a horizontal recyclerview. Clicking on the genre it takes you to the 'GenreInfo Activity' and clicking on the album it takes you the previously explained 'AlbumInfo Activity' and the flow remains constant.

## Concepts used - 
- **MVVM Architecture** : Followed clean architecture and MVVM design pattern. Followed the respositoy pattern where API calls happen through repostiory and it becomes the single source of truth for the app. The ViewModels can access the repostiory and then provide the Livedata to the activities and fragments to observe.
- **Coroutines** : Used coroutines to asynchronously call the API in background. Also used coroutines while displaying and observing the Livedata. 
- **Glide Image Library** : Used the famous Glide Library to parse the url of the images that are getting fetched from the API and then display it.
- **ViewPager and Adapter** : USed the concept of VIewPager and ViewPager Adapter to show the split of 'Albums', 'Artists' & 'Tracks', of a particular genre.

## Decisions & Assumptions -
- Changed the intial UI of the app. Instead of going with the TabLayout I went with the normal RecyclerView to display all the topTags/topGenres on the homescreen. 
- Did not work properly on the UI as time was pretty less so tried to make it as minimalistic as possible. 
- Had to create many dataclasses because the API is not well maintained and changes its parameters. Making dataclasses un-reusable. 
- For the Artisits and Tracks section the images are not visible because the API itself doesn't provide the right image URL. 
- In the 'ArtistInfo Activity' its the number of listenres and not the number of followers. 

## Working of the app - 


## Screenshots -

