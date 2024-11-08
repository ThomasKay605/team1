@PlanetandMoonVisiblity
Feature: PlanetandMoonVisiblity

	@PLAN-TC-42 @JREQ-PLAN-11 @AIO-FOLDER-Automated/Planet_and_Moon_Visiblity
	Scenario Outline: Planet and Moon Visibility: User should see their planets and moons upon logging into their home page
	Test case to check if the user can see their planets and moons upon logging into Planetarium (Error Guess testing).
		Given The user is on the login page "<Host>"
			"""
			<Host>
			"""
		When The user inputs username "<Username>"
			"""
			<Username>
			"""
		And The user inputs password "<Password>"
			"""
			<Password>
			"""
		And The user clicks on the Login button
		Then The user has logged into Planetarium and is on the home page
		And The user's planets and moons should be visible to on the home page
			"""
			Planet1:<Planet1>
			Planet2:<Planet2>
			Moon1:<Moon1>
			Moon2:<Moon2>
			"""

	Examples: 
		| Host                   | Password | Login Password | Planet1 | Planet2 | Moon1 | Moon2 |
		| https://localhost:8080 | Batman   | I am the night | Earth   | Mars    | Luna  | Titan |

	@PLAN-TC-43 @JREQ-PLAN-11 @AIO-FOLDER-Automated/Planet_and_Moon_Visiblity
	Scenario Outline: Planet and Moon Visibility: User should be able to see their planets and moons by using "/planetarium" shortcut
	Test case to check if the user can see all their planets and moons on their home page via "/planetarium" shortcut if the user has previously logged in (Error Guess testing).
		Given The user has logged into the planetarium and has exited the application
		When When the user inputs the url to the home page <url>
			"""
			URL: <url>
			"""
		Then User's planets and moons are visible on the home page: "<Planet1>", "<Planet2>", "<Planet1>", "<Moon2>"
			"""
			Planet1:<Planet1>
			Planet2:<Planet2>
			Moon1:<Planet1>
			Moon2:<Moon2>
			"""

	Examples: 
		| url                               | Planet1 | Planet2 | Moon1 | Moon2 |
		| https://localhost:8080/plantarium | Earth   | Mars    | Titan | Luna  |

	@PLAN-TC-44 @JREQ-PLAN-11 @AIO-FOLDER-Automated/Planet_and_Moon_Visiblity
	Scenario Outline: Planet and Moon Visibility: Planets and moons should be visible after successful planet creation
	Test case to check that, after successfully adding a planet, that the user's planets and moons (including the user's new planet) are visible to see on the home page (Error Guess testing).
		Given The user has logged into Planetarium and is on the home page
			"""
			URL: <url>
			"""
		When User performs a successful planet creating operation with the inputted data
			"""
			Planet name: <New Planet> 
			Planet Image: <Planet Image>
			"""
		Then User's original planets and moons and the new planet should be visible
			"""
			Planet1: <Planet1>
			Planet2: <Planet2>
			New Planet: <New planet name>
			Moon1: <Moon1>
			Moon2: <Moon2>
			"""

	Examples: 
		| url                               | New Planet | Planet Image  | Planet1 | Planet2 | Moon1 | Moon2 |
		| https://localhost:8080/plantarium | Mercury    | Planet-1.jpeg | Earth   | Mars    | Titan | Luna  |

	@PLAN-TC-45 @JREQ-PLAN-11 @AIO-FOLDER-Automated/Planet_and_Moon_Visiblity
	Scenario Outline: Planet and Moon Visibility: Planets and moons visible after successful planet deletion
	Test case to check if, after successful planet deletion, that the user's planets and moons are visible to see (excluding the planet and its associated moons) (Error Guess testing).
		Given The user has logged into Planetarium and is on the home page
			"""
			<url>
			"""
		When User's performs a successful planet deletion operation with the inputted data
			"""
			Planet to be removed: <Planet2>
			"""
		Then User's planets and moons should be visible, excluding the deleted planet and its moons
			"""
			Planet1: <Planet1>
			Moon1: <Moon1>
			Moon2: <Moon2>
			"""

	Examples: 
		| url                               | Planet1 | Planet2 | Moon1 | Moon2 |
		| https://localhost:8080/plantarium | Earth   | Mars    | Luna  | Titan |

	@PLAN-TC-46 @JREQ-PLAN-11 @AIO-FOLDER-Automated/Planet_and_Moon_Visiblity
	Scenario Outline: Planet and Moon Visibility: Planets and Moons visible after successful moon creation
	Test case to check if user's planets and moons are still visible after a successful moon creation operation (Error Guess testing).
		Given The user has logged into Planetarium and is on the home page
			"""
			Url: <url>
			"""
		And User performs a successful moon creation operation
			"""
			Moon name: <New Moon> 
			Moon image: <Moon Image>
			Attracted Planet: <Planet2>
			"""
		Then User's planets and moons should still be visible (including the new moon added).
			"""
			Planet1: <Planet1>
			Planet2: <Planet2>
			Moon1: <Moon1> 
			Moon2: <Moon2>
			New Moon: <New Moon>
			"""

	Examples: 
		| url                               | Planet1 | Planet2 | Moon1 | Moon2 | New Moon | Moon Image  |
		| https://localhost:8080/plantarium | Earth   | Mars    | Luna  | Titan | Dine     | moon-1.jpeg |

	@PLAN-TC-47 @JREQ-PLAN-11 @AIO-FOLDER-Automated/Planet_and_Moon_Visiblity
	Scenario Outline: Planet and Moon Visibility: Planets and moons visible after successful moon deletion
	Test case to check if user's planets and moons are still visible on the home page after a successful moon deletion operation (Error Guess testing).
		Given The user has logged into the planetarium and is on the Home Page
			"""
			<url>
			"""
		When User's performs a successful Moon deletion operation with the inputted data
			"""
			Moon name: <Moon1>
			"""
		Then User's planets and moons should be visible, excluding the deleted planet and its moons
			"""
			Planet1: <Planet1>
			Planet2: <Planet2>
			Moon2: <Moon2>
			"""

	Examples: 
		| url                               | Planet1 | Planet2 | Moon1 | Moon2 |
		| https://localhost:8080/plantarium | Earth   | Mars    | Luna  | Titan |