# Directors

## Requirements

* Java 21
* Maven (tested with 3.9.3)

## Running

1. On terminal, run:
    ```bash
        ./mvnw clean spring-boot:run -Dspring-boot.run.jvmArguments="-DDEFAULT_BOUNDED_ELASTIC_ON_VIRTUAL_THREAD=true"
    ``` 
2. On browser, open: http://localhost:8080/webjars/swagger-ui/index.html
3. Then play with the swagger-ui in order to test the game

## Mocking the movies API

I decided to mock the [`Movies Api`](./src/main/java/com/domus/controller/MoviesController.java) to return movies.

How to check it:

```bash
curl -i -H "Accept: application/json" "http://localhost:8080/api/movies/search?page=1"
```

then you should see the headers:

```
HTTP/1.1 200 OK
Content-Type: application/json
Content-Length: 409
```

and the payload:

```json
{
  "page": 1,
  "per_page": 5,
  "total": 15,
  "total_pages": 3,
  "data": [
    {
      "Title": "Movie 1",
      "Director": "Director 1",
      "Year": null,
      "ImdbID": null
    },
    {
      "Title": "Movie 1",
      "Director": "Director 2",
      "Year": null,
      "ImdbID": null
    },
    {
      "Title": "Movie 2",
      "Director": "Director 2",
      "Year": null,
      "ImdbID": null
    },
    {
      "Title": "Movie 1",
      "Director": "Director 3",
      "Year": null,
      "ImdbID": null
    },
    {
      "Title": "Movie 2",
      "Director": "Director 3",
      "Year": null,
      "ImdbID": null
    }
  ]
}
```

If you check the real target api:

```bash
curl -i -H "Accept: application/json" "https://eroninternational-movies.wiremockapi.cloud/api/movies/search?page=1"
```

then you should see the headers:

```
HTTP/2 200 
vary: Origin
matched-stub-id: 4eb5231c-3e88-433a-8341-e3038cd292cf
matched-stub-name: GET search page 1
content-length: 2823
```

### **Please notice: the Content-Type header is missing!**

and the payload:

```json
{
  "page": 1,
  "per_page": 10,
  "total": 26,
  "total_pages": 3,
  "data": [
    {
      "Title": "Midnight in Paris",
      "Year": "2011",
      "Rated": "PG-13",
      "Released": "10 Jun 2011",
      "Runtime": "94 min",
      "Genre": "Comedy, Fantasy, Romance",
      "Director": "Woody Allen",
      "Writer": "Woody Allen",
      "Actors": "Owen Wilson, Rachel McAdams, Kathy Bates"
    },
    {
      "Title": "The Hateful Eight",
      "Year": "2015",
      "Rated": "R",
      "Released": "30 Dec 2015",
      "Runtime": "168 min",
      "Genre": "Crime, Drama, Mystery",
      "Director": "Quentin Tarantino",
      "Writer": "Quentin Tarantino",
      "Actors": "Samuel L. Jackson, Kurt Russell, Jennifer Jason Leigh"
    },
    {
      "Title": "Silence",
      "Year": "2016",
      "Rated": "R",
      "Released": "13 Jan 2017",
      "Runtime": "161 min",
      "Genre": "Drama, History",
      "Director": "Martin Scorsese",
      "Writer": "Jay Cocks, Martin Scorsese, Shûsaku Endô",
      "Actors": "Andrew Garfield, Adam Driver, Liam Neeson"
    },
    {
      "Title": "The Skin I Live In",
      "Year": "2011",
      "Rated": "R",
      "Released": "02 Sep 2011",
      "Runtime": "120 min",
      "Genre": "Drama, Horror, Thriller",
      "Director": "Pedro Almodóvar",
      "Writer": "Pedro Almodóvar, Agustín Almodóvar, Thierry Jonquet",
      "Actors": "Antonio Banderas, Elena Anaya, Jan Cornet"
    },
    {
      "Title": "Pain and Glory",
      "Year": "2019",
      "Rated": "R",
      "Released": "04 Oct 2019",
      "Runtime": "113 min",
      "Genre": "Drama",
      "Director": "Pedro Almodóvar",
      "Writer": "Pedro Almodóvar",
      "Actors": "Antonio Banderas, Asier Etxeandia, Leonardo Sbaraglia"
    },
    {
      "Title": "The Last Airbender",
      "Year": "2010",
      "Rated": "PG",
      "Released": "01 Jul 2010",
      "Runtime": "103 min",
      "Genre": "Action, Adventure, Family",
      "Director": "M. Night Shyamalan",
      "Writer": "M. Night Shyamalan",
      "Actors": "Noah Ringer, Nicola Peltz, Jackson Rathbone"
    },
    {
      "Title": "Blue Jasmine",
      "Year": "2013",
      "Rated": "PG-13",
      "Released": "23 Aug 2013",
      "Runtime": "98 min",
      "Genre": "Comedy, Drama",
      "Director": "Woody Allen",
      "Writer": "Woody Allen",
      "Actors": "Cate Blanchett, Alec Baldwin, Peter Sarsgaard"
    },
    {
      "Title": "J. Edgar",
      "Year": "2011",
      "Rated": "R",
      "Released": "11 Nov 2011",
      "Runtime": "137 min",
      "Genre": "Biography, Drama, Romance",
      "Director": "Clint Eastwood",
      "Writer": "Dustin Lance Black",
      "Actors": "Leonardo DiCaprio, Armie Hammer, Naomi Watts"
    },
    {
      "Title": "Midnight in Paris",
      "Year": "2011",
      "Rated": "PG-13",
      "Released": "10 Jun 2011",
      "Runtime": "94 min",
      "Genre": "Comedy, Fantasy, Romance",
      "Director": "Woody Allen",
      "Writer": "Woody Allen",
      "Actors": "Owen Wilson, Rachel McAdams, Kathy Bates"
    },
    {
      "Title": "After Earth",
      "Year": "2013",
      "Rated": "PG-13",
      "Released": "31 May 2013",
      "Runtime": "100 min",
      "Genre": "Action, Adventure, Sci-Fi",
      "Director": "M. Night Shyamalan",
      "Writer": "Gary Whitta, M. Night Shyamalan, Will Smith",
      "Actors": "Jaden Smith, David Denman, Will Smith"
    },
    {
      "Title": "Hugo",
      "Year": "2011",
      "Rated": "PG",
      "Released": "23 Nov 2011",
      "Runtime": "126 min",
      "Genre": "Adventure, Drama, Family",
      "Director": "Martin Scorsese",
      "Writer": "John Logan, Brian Selznick",
      "Actors": "Asa Butterfield, Chloë Grace Moretz, Christopher Lee"
    }
  ]
}
```

So since I started with a mocked implementation, when trying to consume the real movies app, I got errors since the
Content-Type header is missing. 

I could work around it, but then I would have to change a lot of things.

## Notes

* The code is fully tested: near 100% of code coverage.
* It has
  an [`ArchUnit`](https://www.archunit.org/use-cases) [`ArchUnitTest`](./src/test/java/com/domus/ArchUnitTest.java) to
  assert basic architecture
* It has a [`DirectorsApiE2eTest`](./src/test/java/com/domus/DirectorsApiE2eTest.java) 
* It has a [`DirectorsServiceTest`](./src/test/java/com/domus/DirectorsServiceTest.java)
* It has a [`K6`](https://k6.io/) [`Load test`](./k6_script.js)
* For the sake of simplicity and time to deliver:
    * It's far from production ready: cache (since the data source is not so volatile), logs, errors, containers, 
  circuit breakers, retries, rate limiter, observability, etc

## Running load test

* Install https://grafana.com/docs/k6/latest/set-up/install-k6/
* On terminal, run:
    ```bash
        k6 run k6_script.js
    ```
  Then you will see something like:

```

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: k6_script.js
     output: -

  scenarios: (100.00%) 1 scenario, 10 max VUs, 1m0s max duration (incl. graceful stop):
           * default: 10 looping VUs for 30s (gracefulStop: 30s)


running (0m30.0s), 00/10 VUs, 9609 complete and 0 interrupted iterations
default ✓ [======================================] 10 VUs  30s

     ✓ status was 200
     ✓ transaction time OK

     checks.........................: 100.00% ✓ 134526      ✗ 0    
     data_received..................: 6.7 MB  224 kB/s
     data_sent......................: 7.1 MB  235 kB/s
     http_req_blocked...............: avg=1.05µs  min=0s      med=1µs     max=1.5ms    p(90)=1µs     p(95)=2µs    
     http_req_connecting............: avg=61ns    min=0s      med=0s      max=486µs    p(90)=0s      p(95)=0s     
   ✓ http_req_duration..............: avg=4.42ms  min=899µs   med=4.1ms   max=237.59ms p(90)=5.61ms  p(95)=6.22ms 
       { expected_response:true }...: avg=4.42ms  min=899µs   med=4.1ms   max=237.59ms p(90)=5.61ms  p(95)=6.22ms 
   ✓ http_req_failed................: 0.00%   ✓ 0           ✗ 67263
     http_req_receiving.............: avg=14.99µs min=3µs     med=11µs    max=481µs    p(90)=25µs    p(95)=38µs   
     http_req_sending...............: avg=3.81µs  min=1µs     med=3µs     max=872µs    p(90)=5µs     p(95)=8µs    
     http_req_tls_handshaking.......: avg=0s      min=0s      med=0s      max=0s       p(90)=0s      p(95)=0s     
     http_req_waiting...............: avg=4.4ms   min=882µs   med=4.08ms  max=237.5ms  p(90)=5.59ms  p(95)=6.2ms  
     http_reqs......................: 67263   2240.752337/s
     iteration_duration.............: avg=31.22ms min=17.57ms med=29.07ms max=268.01ms p(90)=35.81ms p(95)=39.97ms
     iterations.....................: 9609    320.107477/s
     vus............................: 10      min=10        max=10 
     vus_max........................: 10      min=10        max=10 

```

Thanks!
