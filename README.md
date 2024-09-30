# Weather App
This Weather app is build with Jetpack Compose, navigation & hilt (for dependency injection).
It is a SPA (Single Page Application), where single activity(MainActivity) loads WelcomePage(Splash screen) & WeatherPage components.
Uses navigation compose to route between these two screens.
Hilt is used to inject dependencies like network, weather repository, preference and view models.

# Features
Splash page is displayed for first time for 5sec.  User can can skip via "Get Started" button.
DataStore is used to persists the first timer status.

Weather page is displays weather details like temperature, wind speed, humidity & forecast details in collapsed mode.
By default "Dallas" weather report is displayed for first timer.
Through search feature at top, can change weather location.
This feature can enhance by gaining current user location & by allowing user to manually select set of locations.
The weather page can be improved by displaying weather report of all locations via page viewer

Weather forecast is displayed at bottom of weather page is hidden by default.  It expands on tapping on chevron icon.

Currently, weather end point(url) is saved in constants file, which is not recommended.
We can use build config with build variants and flavours to support various environments like dev, staging & production.
If project integrated with CI/CD, we can take advantage of environment variable or config file.

## 📐✏️ Architecture
The app is built with the Model-View-ViewModel (MVVM) architecture with Repository pattern which is used to separate the app's user interface, logic, and data.

- **Model**: Represents the data and the business logic of the app. It's the actual data and data sources, like databases or network requests.
- **View**: Represents the UI of the app. In your app, it would be the Jetpack Compose components that display the weather data to the user.
- **ViewModel**: It holds the data that the View needs, exposing this data through StateFlow.
- **Repository**: This is an additional layer between the ViewModel and the data source (in this case, the OpenWeatherMap API). The Repository centralizes the data fetching mechanism, so if in the future we decide to add another data source, such as a local database for offline caching, the ViewModel wouldn't need to know where the data is coming from. The Repository will handle data retrieval, whether it's from the API, local database, or other sources.



