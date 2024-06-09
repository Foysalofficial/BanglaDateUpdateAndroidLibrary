# Bangla Date Time App

## Overview
This Android application allows users to display Bangla and English dates and times. It leverages utility functions from the `BanglaDateUpdate` class to retrieve and format the dates and times in both languages.
Updated to utilize 2019 Bangla calendar modification by Bangla Academy
This Date Converter follows the Bangla Calendar system defined by "Bangla Academy" and followed by the Govt. of Bangladesh.
Because of this nature, the corresponding Bangla date displayed by this software will be few days deviated from the date of West Bengal calendar.


## Features
- Display Bangla full date, day, month, year and season
- Display English date
- Continuous updating of Bangla time

## Installation
To use this application, follow these steps:
1. Clone the repository to your local machine.
2. Open the project in Android Studio.
3. Build and run the application on an Android device or emulator.

## Usage
To integrate the date and time display functionality into your Android application, follow these steps:
1. Add the necessary layout components (`BanglaTimeTv`, `BanglaDateTv`, `EnglishDateTv`) to your XML layout file.
2. In your Java or Kotlin file, find and assign the corresponding views using `findViewById`.
3. Utilize the `BanglaDateUpdate` class to retrieve Bangla and English dates and times.
4. Set the retrieved dates and times to the respective TextViews using `setText`.

# How to To get a Git project into your build:
> Step 1. Add it in your root build.gradle at the end of repositories

```gradle
  dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url = uri("https://jitpack.io") }
		}
	}
  ```
  
  >Step 2. Add the dependency
  
  ```gradle
  
  dependencies {
	        implementation ("com.github.Foysalofficial:BanglaDateUpdateAndroidLibrary:3.0")
	}
  
  ```

```java
        BanglaTimeTv = findViewById(R.id.BanglaTimeTv);
        BanglaDateTv = findViewById(R.id.BanglaDateTv);
        EnglishDateTv = findViewById(R.id.EnglishDateTv);

        BanglaDateUpdate banglaDateUpdate = new BanglaDateUpdate();

        String weekDay = banglaDateUpdate.getWeekDay();
        String bangladay = banglaDateUpdate.getBanglaDays();
        String BanglaMonth = banglaDateUpdate.getBanglaMonths();
        String BanglaYear = banglaDateUpdate.getBanglaYears();
        String banglaSeason = banglaDateUpdate.getBanglaSeason();
        String englishDate = banglaDateUpdate.getEnglishDate();


        BanglaDateTv.setText(banglaDateUpdate.getFullBanglaDate());
        banglaDateUpdate.startUpdatingTime(BanglaTimeTv, false, true);
        EnglishDateTv.setText(englishDate);


Dependencies
This project does not have any external dependencies.

Contributing
Contributions are welcome! Feel free to fork the repository and submit pull requests.

License
This project is licensed under the MIT License.


This Markdown will render as formatted text on GitHub, providing clear instructions and information about your Bangla Date Time App.

Credits
This project utilizes the BanglaDateUpdate class developed by Foysal Tech.
