# Weather app
What's the weather like outside? Don't look out the window. Program an app instead.
## Base functionality
- Fetch data for your city from openweathermap.org API or similar
- Display information that you find relevant
- Current data & forecast
- Use this opportunity to play with CSS animations
- Explore frameworks like tailwind, bootstrap or similar

## Database
- Make sure to use REST API
- User model
- List of my cities
- I can add and remove cities I care about
- Authentication
  - I can login to see forecast for my cities
  - Use JWT
- Authorisation
  - Make sure, I can edit only my own list of cities

## Tests
- At least one unit test
- At least one integration test
- At least one end to end test
- Setup CI pipeline
- Setup linter

## Deploy
- Readme.md
  - Describe step by step how to deploy/run app after cloning the repo.
  - If you like, describe architecture of the app so a new programer can easily understand the code base and be productive ASAP
- Deploy app
  - Deploy application so it's accessible online to anyone (look for free solutions)

## Bonus
- Instead of using API (like a human) for weather data, use some web scraper and fetch data from some public website (like a psycho)
- Send notification to registered users for notable events (it will be raining in their location or similar)
- Send notification with the forecast at a predefined time
  - It can send email or use something like pushover.net, ifttt.com or similar
- Dockerize it
- Implement (whole) project using TDD
- Explore and implement custom SVG
