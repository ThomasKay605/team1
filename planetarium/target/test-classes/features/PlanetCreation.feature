@PlanetCreation
Feature: PlanetCreation

	@PLAN-TC-41 @JREQ-PLAN-12 @AIO-FOLDER-Automated/Planet_Creation
	Scenario Outline: Planet Creation: Planets should have names that are 30 characters or less
	Automated test case checking that creating a planet is valid if the name of the planet is not empty and less than or equal to 30 characters (Boundary Analysis testing)
		Given The user has logged into the planetarium and is on the Home Page
			"""
			"<Username>", "<Password>", "<Host>"
			"""
		When The user clicks on the dropdown
		And The user selects Planet
		Then The user inputs the Planet name "<Planet name>"
			"""
			"<Planet name>"
			"""
		And The user clicks on the file upload button
		And The user uploads an image "<Path to File>"
			"""
			"<Path to File>"
			"""
		When The user clicks on the Submit Planet button
		Then the user should see "<Result>"
			"""
			"<Result>"
			"""

	Examples: 
		| Host                   | Username | Password       | Planet name                     | Result                                                                                | Path to File                            |
		| https://localhost:8080 | Batman   | I am the night | (empty)                         | User receives a notification that planet creation with their planet's name has failed | C:/path/Celestial Images/planet-1.jpeg  |
		| https://localhost:8080 | Batman   | I am the night | a                               | Planet is created and the user's table is refreshed to display new planet             | C:/path/Celestial Images/planet-1.jpeg  |
		| https://localhost:8080 | Batman   | I am the night | Do not listen to the Narrator!  | Planet is created and the user's table is refreshed to display new planet             | C:/path/Celestial Images/planet-1.jpeg  |
		| https://localhost:8080 | Batman   | I am the night | I am the Narrator of this story | User receives a notification that planet creation with their planet's name has failed | C:/path/Celestial Images/planet-1.jpeg |

	@PLAN-TC-48 @JREQ-PLAN-12 @AIO-FOLDER-Automated/Planet_Creation
	Scenario Outline: Planet Creation: Planets can only be created with unique names
	Automated test case checking that planets are only created if the name for the planet is unique (Equivalence Partitioning testing).
		Given The user has logged into the planetarium and is on the Home Page
			"""
			"<Host>", "<Username>", "<Password>"
			"""
		When The user clicks on the dropdown
		And The user selects Planet
		Then The user inputs the Planet name "<Planet name>"
			"""
			"<Planet Name>"
			"""
		And The user clicks on the file upload button
		And The user uploads an image "<Path to File>"
			"""
			"<Path to File>"
			"""
		When The user clicks on the Submit Planet button
		Then the user should see "<Result>"
			"""
			"<Result>"
			"""

	Examples: 
		| Host                   | Username | Password       | Planet Name | Result                                                                                | Path to File                           |
		| https://localhost:8080 | Batman   | I am the night | Venus       | Planet is created and the user's table is refreshed to display new planet             | C:/path/Celestial Images/planet-3.jpeg |
		| https://localhost:8080 | Batman   | I am the night | Mars        | User receives a notification that planet creation with their planet's name has failed | C:/path/Celestial Images/planet-3.jpeg |

	@PLAN-TC-49 @JREQ-PLAN-12 @AIO-FOLDER-Automated/Planet_Creation
	Scenario Outline: Planet Creation: Planets can be created with uploaded images from an user or with not image required
	Automated test case to check if a user can create a planet with or without an associated image (Error Guess testing).
		Given The user has logged into the planetarium and is on the Home Page
			"""
			"<Valid Username>", "<Valid Password>"
			"""
		When The user clicks on the dropdown
		And The user selects Planet
		Then The user inputs the Planet name "<Planet name>"
			"""
			"<Planet name>"
			"""
		And The user clicks on the file upload button
		And The user uploads an image "<Path to File>"
			"""
			"<Path to File>"
			"""
		When The user clicks on the Submit Planet button
		Then the user should see "<Planet result>"
			"""
			"<Planet result>"
			"""

	Examples: 
		| Home page                         | Planet name | Path to File                           | Planet result                                                      | Valid Username | Valid Password |
		| https://localhost:8080/plantarium | Neptune     | C:/path/Celestial Images/planet-5.jpeg | The new planet can be seen with the associated image with it       | Batman         | I am the night |
		| https://localhost:8080/plantarium | Uranus      | C:/path/Celestial Images/planet-7.png  | The new planet can be seen with the associated image with it       | Batman         | I am the night |
		| https://localhost:8080/plantarium | Pluto       | (empty)                                | The new planet can be seen with a default image associated with it | Batman         | I am the night |

	@PLAN-TC-50 @JREQ-PLAN-11 @JREQ-PLAN-12 @AIO-FOLDER-Automated/Planet_Creation
	Scenario Outline: Planet Creation: Planet created should be only visible to the user that created the planet
	Automated test case to see if a newly created planet is only visible to the owner of the planet and to no one else (Error Guess testing).
		Given The user has logged into the planetarium and is on the Home Page
			"""
			"<Valid Username>", "<Valid Password>"
			"""
		And The user has created a new planet with the name "<New planet>" and the file path "<Path to File>"
			"""
			"<New planet>", "<Path to File>"
			"""
		When the user clicks on the "Logout" button
		Then The user is redirected and clicks on the Create Account button
		And The user types a new username "<New Username>"
			"""
			"<New Username>"
			"""
		And The user types a new password "<New Password>"
			"""
			"<New Password>"
			"""
		When The user clicks on the Create Account button
		Then The user goes back to the login screen
			"""
			"<Host>"
			"""
		And The user inputs their username "<New Username>" and password "<New Password>" and logs in
			"""
			"<New Username>", "<New Password>"
			"""
		Then The user should not see a Planet with the name "<New planet>"
			"""
			"<New planet>"
			"""

	Examples: 
		| Host           | New planet | New Username | New Password | Valid Username | Valid Password | Path to File                           |
		| localhost:8080 | Venus      | Toby         | Butter       | Batman         | I am the night | C:/path/Celestial Images/planet-1.jpeg |