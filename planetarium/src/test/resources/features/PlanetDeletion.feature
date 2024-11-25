@PlanetDeletion
Feature: PlanetDeletion

	@PLAN-TC-51 @JREQ-PLAN-13 @AIO-FOLDER-Automated/Planet_Deletion
	Scenario Outline: Planet Deletion: The user has the ability to delete a planet
	Automated test case to check if a user has the functionality to delete a planet that exists in the planetarium (Equivalence Partitioning testing).
		Given The user has logged into the planetarium with username "<Username>" and password "<Password>"
			"""
			"<Username>", "<Password>", "<Host>"
			"""
		And The user has created a new planet with the name "<New planet>" and the file path "<Path to File>"
			"""
			"<New planet>", "<Path to File>"
			"""
		Then The user inputs the Planet name "<Planet name>" in the Planet Delete field
			"""
			"<Planet name>"
			"""
		And The user clicks on the Delete Planet button
		Then The user should see a result "<Result>" reflected from deleting a Planet "<Planet name>"
			"""
			"<Result>", "<Planet name>"
			"""

		Examples:
			| Host                       | Planet name | Result                                                                                      | Username | Password       | New planet | Path to File |
			| localhost:8080/planetarium | Salamence   | The planet was deleted and the table refreshes to no longer have the deleted planet         | Batman   | I am the night | Salamence  | PLANET_PATH  |
			| localhost:8080/planetarium | (empty)     | The user receives a notification that deleting the planet with the inputted name has failed | Batman   | I am the night | Metagross  | PLANET_PATH  |

	@PLAN-TC-52 @JREQ-PLAN-13 @AIO-FOLDER-Automated/Planet_Deletion
	Scenario Outline: Planet Deletion: The user can only delete planets that they own
	Automated test case to check if a user can only delete planets that they own and not planets that either do not exist or ones that are owned by another user (Equivalence Partitioning testing).
		Given The user has logged into the planetarium with username "<Username>" and password "<Password>"
			"""
			"<Username>", "<Password>", "<Host>"
			"""
		And The user has created a new planet with the name "<Planet name>" and the file path "<Path to File>"
			"""
			"<Planet name>", "<Path to File>"
			"""
		When the user clicks on the Logout button
		Then The user is redirected and clicks on the Create Account button
		And The user types a new username "<New Username>"
			"""
			"<New Username>"
			"""
		And The user types a new password "<New Password>"
			"""
			"<New Password>"
			"""
		When The user clicks on the Create button
		Then The user goes back to the login screen
		And The user inputs their username "<New Username>" and password "<New Password>" and logs in
			"""
			"<New Username>", "<New Password>"
			"""
		When The user clicks on the dropdown and selects Planet
		Then The user inputs the Planet name "<Planet name>" in the Planet Delete field
			"""
			"<Planet name>"
			"""
		And The user clicks on the Delete Planet button
		Then The user should expect an error to be thrown
			"""
			"<Result>"
			"""

		Examples:
			| Host                       | Planet name | Result                                                                                      | Username | Password       | Path to File | New Username | New Password |
			| localhost:8080/planetarium | Articuno    | The user receives a notification that deleting the planet with the inputted name has failed | Batman   | I am the night | PLANET_PATH  | Rock         | Lee          |