Introduction:
First, I would like to thank you for your time checking my assignment.
When I saw the task, even though it wasn't specified, my assumption was that I need to create some sort of API call to a weather provider.
I found the site https://openweathermap.org/ who offers a free API.
Since it takes longitude and latitude as input, I used https://geocode.maps.co/ to convert string addresses to coordinates.
https://openweathermap.org/ and https://geocode.maps.co/ probably not the best on the market, but they offer a free sufficient API for this task.

There is a lot that can be added, changed and improved in a real life application.
Since I have been told it should be a few hours task I tried to keep it simple.

Implementation:
1. There is no front end UI, I just used console printouts to communicate with the user.
2. Simple jUnit tests used to test the different modules. In a real world project I would put much more tests to cover every scenario.
3. The Util folder contains several modules that perform different tasks like communication with APIs and other helper classes.
4. For cache storage I use a Cache with expireAfterWrite option to make it time oriented. I have never used it before, but I found it online, and it was easy to learn how to use it.
   The Storage using singleton design pattern and synchronized to be thread safe. We have only 1 user but why not think into the future...
5. A configuration file is used to store constants like API_KEYs URLs etc.
6. https://geocode.maps.co/ API try to guess the address even when random characters are provided. It also gives back different json results based on input.
   I didn't check all possible inputs. I tested a full address with and without zip and a bad entry address. I made some null protections, but there is plenty that can be added.
   Many hours can be spent just testing the result jsons it provides based on different inputs and protecting it from nullPointerExceptions.
