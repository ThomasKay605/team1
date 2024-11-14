@MoonCreation
Feature: MoonCreation

  @PLAN-TC-24 @JREQ-PLAN-14 @AIO-FOLDER-Automated/Moon_Creation
  Scenario Outline: Moon Creation: Moons should have names that are 30 characters or less
  Test case to check if moons are valid if their names are 30 characters long or shorter (Boundary Analysis testing).
    Given the user has logged into Planetarium and is on the home page
    When the user has enabled the Moon tag on the home page
    When the user inputs moon "<Moon name>" into Moon Name Input textbox
    When the user inputs Planet ID "<Planet ID>"
    When the user attaches "<Moon image>"
    When the user clicks submit moon
    Then the user should see "<Result>" in moon creation
			"""
			<Moon name>, <Planet ID>
			"""
    Then the user should see that moon created is <Moon created?>
			"""
			<Moon name>, <Planet ID>
			"""

    Examples:
      | Home page                         | Moon name                       | Moon image | Moon created? | Result                                                               | Planet ID |
      | https://localhost/8080/plantarium |                                 | moon-1.jpg | true          | (no alert, table refresh)                                            | 1         |
      | https://localhost/8080/plantarium | E                               | moon-2.jpg | true          | (no alert, table refresh)                                            | 1         |
      | https://localhost/8080/plantarium | Experiment Liquidated Embrion1  | moon-3.jpg | true          | (no alert, table refresh)                                            | 1         |
      | https://localhost/8080/plantarium | Adamance Offensive Assurance 32 | moon-4.jpg | false         | Failed to create Moon orbiting planet ${Planet ID} with name ${Moon name} | 1         |

  @PLAN-TC-25 @JREQ-PLAN-14 @AIO-FOLDER-Automated/Moon_Creation
  Scenario Outline: Moon Creation: Name of the moons have to be unique
  Test case to check if the name of the moon needs to be unique in order to be successful created (Equivalence Partitioning testing).
    Given the user has logged into Planetarium and is on the home page
    When the user has enabled the Moon tag on the home page
    When the user inputs moon "<Moon name>" into Moon Name Input textbox
    When the user inputs Planet ID "<Planet ID>"
    When the user attaches "<Moon image>"
    When the user clicks submit moon
    Then the user should see "<Result>" in moon creation
			"""
			<Moon name>, <Planet ID>
			"""
    And the user should see that moon created is <Moon Created?>
			"""
			<Moon name>, <Planet ID>
			"""

    Examples:
      | Home page                         | Moon name | Moon image | Moon Created? | Result                                                                    | Planet ID |
      | https://localhost/8080/plantarium | Deimos    | moon-3.jpg | true          | (no alert, table refresh)                                                 | 2         |
      | https://localhost/8080/plantarium | Luna      | moon-3.jpg | false         | Failed to create Moon orbiting planet ${Planet ID} with name ${Moon name} | 2         |

  @PLAN-TC-26 @JREQ-PLAN-14 @AIO-FOLDER-Automated/Moon_Creation
  Scenario Outline: Moon Creation: Planet provided for the moon needs to be owned by the user
  Test case to check if the planet provided for the moon to be created needs to be owned by the user and not someone else's (Equivalence Partitioning testing).
    Given The user is on the login page
    When The user inputs username "<Username>"
    When The user inputs password "<Password>"
    When The user clicks on the Login button
    When the user has enabled the Moon tag on the home page
    When the user inputs moon "<Moon name>" into Moon Name Input textbox
    When the user inputs Planet ID "<Planet ID>"
    When the user attaches "<Moon image>"
    When the user clicks submit moon
    Then the user should see "<Result>" in moon creation
			"""
			<Moon name>, <Planet ID>
			"""
    Then the user should see that moon created is <Moon Created?> with owner as "<Planet ID>"

    Examples:
      | Login Page              | Moon name | Moon Created? | Result                                                              | Planet ID | Username | Password    | Moon image |
      | https://localhost/8080/ | Deimos    | true          | ((no alert, table refresh))                                         | 2         | Batman   | Iamthenight | moon-2.jpg |
      | https://localhost/8080/ | Deimos    | false         | Failed to create Moon orbiting planet ${planetId} with name ${name} | 2         | Toby     | Butter      | moon-2.jpg |
      | https://localhost/8080/ | Deimos    | false         | Failed to create Moon orbiting planet ${planetId} with name ${name} | 3         | Toby     | Butter      | moon-2.jpg |

  @PLAN-TC-27 @JREQ-PLAN-14 @AIO-FOLDER-Automated/Moon_Creation
  Scenario Outline: Moon Creation: User can create moons with or without an image associated with it
  Test case to check if a user can create a moon with or without an image associated with it (Error Guess testing).
    Given the user has logged into Planetarium and is on the home page
    When the user inputs moon "<Moon name>" into Moon Name Input textbox
    When the user inputs Planet ID "<Planet ID>"
    When the user attaches "<Moon image>"
    When the user clicks submit moon
    Then the user should see that moon created is <Moon Created?>
			"""
			<Moon name>, <Planet ID>
			"""

    Examples:
      | Home page                         | Moon name | Planet ID | Moon image         | Moon Created? |
      | https://localhost/8080/plantarium | Deimos    | 2         | moon-1.jpg         | true          |
      | https://localhost/8080/plantarium | Deimos    | 2         | realistic-moon.png | true          |
      | https://localhost/8080/plantarium | Deimos    | 2         |                    | true          |

  @PLAN-TC-28 @JREQ-PLAN-14 @AIO-FOLDER-Automated/Moon_Creation
  Scenario Outline: Moon Creation: Newly created moon is only visible to owner
  Test case to check if newly created moon is only visible to the owner and not to any other users (Error Guess testing).
    Given the user has logged into Planetarium and is on the home page
    When the user has enabled the Moon tag on the home page
    When the user inputs moon "<Moon name>" into Moon Name Input textbox
    When the user inputs Planet ID "<Planet ID>"
    When the user attaches "<Moon image>"
    When the user clicks submit moon
    When the user should see the moon created is <Moon Created?>
        """
			<Moon name>, <Planet ID>
	    """
    When the user clicks on the Logout button
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
		"""
			<Moon name>, <Planet ID>
		"""

    Examples:
      | Home page                         | Moon Created? | Planet ID | Moon image | Moon Visible to Nonowner? | Username | Password | Moon name |
      | https://localhost/8080/plantarium | true          | 2         | moon-1.jpg | false                     | toby     | butter   | Deimos    |