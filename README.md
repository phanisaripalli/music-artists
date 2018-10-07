# music-artists

## Background
This REST base API is about getting artists"s information from MusicBrainz, Cover Art Archive and Discogs using their APIs.

The API should be called using **mbid**, artist id at Musicbrainz, which returns the following info:

- name
- gender
- type (band or person)
- disambiguation (if an artist is popularly called - king of pop, father of jazz etc.)

All the album related details like 

- title
- id
- front cover image 
- front cover image thumbails - large and small

are obtained from the API provided by Cover Art Archive.

The following info is obtained from Discogs
- description
- urls (facebook, homepage etc.)
- 
**API Endpoint**

_v1/artist?mbid=<mbid>_

a Json is returned

```javascript
mbid:	"e0bba708-bdd3-478d-84ea-c706413bedab"
name:	"A. R. Rahman"
gender:	"Male"
type:	"Person"
disambiguation:	""
albums: 
    0: 
        id	"23c7fd53-69ed-4ddb-8516-2bdd0a89a841"
        title	"The Definitive Collection"
        coverSrc	
            frontCoverSrc   "http://coverartarchive.org/release/f0330426-4fa4-4703-8f98-206947504b67/19005297332.jpg"
            thumbnailSmall	"http://coverartarchive.org/release/f0330426-4fa4-4703-8f98-206947504b67/19005297332-250.jpg"
            thumbnailLarge	"http://coverartarchive.org/release/f0330426-4fa4-4703-8f98-206947504b67/19005297332-500.jpg"
profile:	
    description: "Tamil name: அல்லா ரக்கா ரகுமான்\r\nGujarati name: એ. આર. રહેમાન\r\nHindi name: अल्लाह रक्खा रहमान\r\nBangla name: এ আর রহমান\r\nMalayalam name: എ.ആർ. റഹ്‌മാൻ\r\nTelugu name: ఎ.ఆర్.రెహమాన్\r\n\r\nBorn: 6 January 1966 in Madras, Tamil Nadu, India as [b]A. S. Dileep Kumar[/b]. \r\nFamous singer and songwriter, especially for movie soundtracks and indian pop music. \r\nIn 1989 he converted from Hinduism to Islam and so he changed his name to Rahman. \r\n"
    urls:	
        0:	"http://www.arrahman.com"
        1:	"http://en.wikipedia.org/wiki/A._R._Rahman"
    id:	"4459"            
```

- If an artist is not found using an mbid, 400 error code is returned. 
- If somethign goes wrong, 500 is returned.
- It takes a bit of time for to return results for an artist for the first time. Then it is cached and the subsequent calls are faster.

## Approcah

- Maven
- Spring 2.0.5.RELEASE
- Maven 4
- Java 10
- Junit for unit testing
- Calls to Cover Art Archive and Discogs are done in parallel using java's concurrent CompletableFuture. Tried to achieve this using reactor approach but the result was being returned before subscribe process was completed.
- Used **gson** for parsing json retuerned from the APIs and **JsonDeserializer** based approach to 
parse json into POJOs.


## Build
- You can clone the project and **_cd_** to the direcotory root
- run 
 ```
mvn clean install spring-boot:repackage
 ```
 - It is already built
 
## Run

- You can clone the project and **_cd_** to the direcotory root
- run 
 ```
mvn spring-boot:run
 ```
 
 ## Issues
 
 - Coverart may not give not give results and cached result will suffer from this
 - I wanted to use caching system like Redis that can be scaled.
 
