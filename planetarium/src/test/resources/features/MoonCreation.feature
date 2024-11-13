@MoonCreation
Feature: MoonCreation

	@PLAN-TC-24 @JREQ-PLAN-14 @AIO-FOLDER-Automated/Moon_Creation
	Scenario Outline: Moon Creation: Moons should have names that are 30 characters or less
	Test case to check if moons are valid if their names are 30 characters long or shorter (Boundary Analysis testing).
		Given the user has logged into Planetarium and is on the home page
		When the user has enabled the Moon tag on the home page
		When the user inputs moon "<Moon name>" into Moon Name Input textbox
		When the user inputs Planet ID <Planet ID>
		When the user attaches <Moon image>
		When the user clicks Submit Moon
		Then the user should see "<Result>"
			"""
			"<Moon name>, <Planet ID>"
			"""
		Then the user should see that moon created is <Moon created?>
			"""
			"<Moon name>, <Planet ID>"
			"""

	Examples: 
		| Home page                         | Moon name                       | Moon image                     | Moon created? | Result                                                               | Planet ID |
		| https://localhost/8080/plantarium |                                 | .\Celestial Images\moon-3.jpeg | true          | no alert, table refresh                                              | 1         |
		| https://localhost/8080/plantarium | E                               | .\Celestial Images\moon-3.jpeg | true          | no alert, table refresh                                              | 1         |
		| https://localhost/8080/plantarium | Experiment Liquidated Embrion1  | .\Celestial Images\moon-3.jpeg | true          | no alert, table refresh                                              | 1         |
		| https://localhost/8080/plantarium | Adamance Offensive Assurance 32 | .\Celestial Images\moon-3.jpeg | false         | Failed to create Moon orbiting planet ${Planet ID} with ${Moon name} | 1         |

	@PLAN-TC-25 @JREQ-PLAN-14 @AIO-FOLDER-Automated/Moon_Creation
	Scenario Outline: Moon Creation: Name of the moons have to be unique
	Test case to check if the name of the moon needs to be unique in order to be successful created (Equivalence Partitioning testing).
		Given the user has logged into Planetarium and is on the home page
		When the user has enabled the Moon tag on the home page
		When the user inputs moon "<Moon name>" into Moon Name Input textbox
		When the user inputs Planet ID <Planet ID>
		When the user attaches <Moon image>
		When the user clicks submit moon
		Then the user should see "<Result>"
			"""
			"<Moon name>"
			"""
		And the user should see that moon created is <Moon Created?>
			"""
			"<Moon name>"
			"""

	Examples: 
		| Home page                         | Moon name | Moon image                     | Moon Created? | Result                                                                    | Planet ID |
		| https://localhost/8080/plantarium | Deimos    | ./Celestial Images/moon-3.jpeg | true          | No alert, table refresh                                                   | 2         |
		| https://localhost/8080/plantarium | Luna      | ./Celestial Images/moon-3.jpeg | false         | Failed to create Moon orbiting planet ${Planet ID} with name ${Moon name} | 2         |

	@PLAN-TC-26 @JREQ-PLAN-14 @AIO-FOLDER-Automated/Moon_Creation
	Scenario Outline: Moon Creation: Planet provided for the moon needs to be owned by the user
	Test case to check if the planet provided for the moon to be created needs to be owned by the user and not someone else's (Equivalence Partitioning testing).
		Given The user is on the login page
		When The user inputs username "<Username>"
		When The user inputs password "<Password>"
		When The user clicks on the Login button
		When the user has enabled the Moon tag on the home page
		When the user inputs moon "<Moon name>" into Moon Name Input textbox
		When the user inputs Planet ID <Planet ID>
		When the user attaches <Moon image>
		When the user clicks submit moon
		Then the user should see "<Result>"
			"""
			<Planet ID>, "<Moon Name>"
			"""
		Then the user should see that moon created is <Moon Created?> with owner as <Planet ID>

	Examples: 
		| Login Page              | Moon Name | Moon Created? | Result                                                              | Planet ID | Username | Password    | Moon image                    |
		| https://localhost/8080/ | Deimos    | true          | (no alert, table refresh)                                           | 2         | Batman   | Iamthenight | ./Celestial Images/moon-2.jpg |
		| https://localhost/8080/ | Deimos    | false         | Failed to create Moon orbiting planet ${planetId} with name ${name} | 2         | Toby     | Butter      | ./Celestial Images/moon-2.jpg |
		| https://localhost/8080/ | Deimos    | false         | Failed to create Moon orbiting planet ${planetId} with name ${name} | 3         | Toby     | Butter      | ./Celestial Images/moon-2.jpg |

	@PLAN-TC-27 @JREQ-PLAN-14 @AIO-FOLDER-Automated/Moon_Creation
	Scenario Outline: Moon Creation: User can create moons with or without an image associated with it
	Test case to check if a user can create a moon with or without an image associated with it (Error Guess testing).
		Given the user has logged into Planetarium and is on the home page
		When the user inputs moon "<Moon name>" into Moon Name Input textbox
		When the user inputs Planet ID <Planet ID>
		When the user attaches <Moon image>
		When the user clicks submit moon
		Then the user should see that moon created is <Moon Created?>

	Examples: 
		| Home page                         | Moon name | Planet ID | Moon image                     | Moon Created? |
		| https://localhost/8080/plantarium | Deimos    | 2         | ./Celestial Images/moon-1.jpeg | true          |
		| https://localhost/8080/plantarium | Deimos    | 2         | realistic-moon.png             | true          |
		| https://localhost/8080/plantarium | Deimos    | 2         | (none)                         | true          |

	@PLAN-TC-28 @JREQ-PLAN-14 @AIO-FOLDER-Automated/Moon_Creation
	Scenario Outline: Moon Creation: Newly created moon is only visible to owner
	Test case to check if newly created moon is only visible to the owner and not to any other users (Error Guess testing).
		Given the user has logged into Planetarium and is on the home page
		When the user has enabled the Moon tag on the home page
		When the user inputs moon "<Moon name>" into Moon Name Input textbox
		When the user inputs Planet ID <Planet ID>
		When the user attaches <Moon image>
		When the user clicks submit moon
		When the user should see the moon created is <Moon Created?>
		When the user clicks on the "Logout" button
		When The user clicks on the Create Account button
		When The user registers the username "<Username>"
		When The user registers the password "<Password>"
		When the user acknowledges the account creation alert
		When The user is on the login page
		When The user inputs username "<Username>"
		When The user inputs password "<Password>"
		When The user clicks on the Login button
		Then The user should be redirected to "<https://localhost/8080/plantarium>"
		And the user should see that the moon "<Moon name>" visibility is <Moon Visible to Nonowner?>

	Examples: 
		| Home page                         | Moon Created? | Planet ID | Image                         | Moon Visible to Nonowner? | Username | Password | Moon name |
		| https://localhost/8080/plantarium | true          | 2         | ./Celestial Images/moon-1.jpg | false                     | toby     | butter   | Deimos    |