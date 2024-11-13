@PlanetDeletion
Feature: PlanetDeletion

	@PLAN-TC-51 @JREQ-PLAN-13 @AIO-FOLDER-Automated/Planet_Deletion
	Scenario Outline: Planet Deletion: The user can only delete planets that they own
	Automated test case to check if a user can only delete planets that they own and not planets that either do not exist or ones that are owned by another user (Equivalence Partitioning testing).
		Given The user has logged into the planetarium and is on the Home Page
			"""
			"<Host>", "<Valid Username>", "<Valid Password>"
			"""
		When The user clicks on the dropdown and selects Planet
		Then The user inputs the Planet name "<Planet name>"
			"""
			"<Planet name>"
			"""
		And The user clicks on the Delete Planet button
		Then the user should see "<Result>"
			"""
			"<Result>"
			"""

		Examples:
			| Host                       | Planet name | Result                                                                                      | Valid Username | Valid Password |
			| localhost:8080/planetarium | Mars        | The planet was deleted and the table refreshes to no longer have the deleted planet         | Batman         | I am the night |
			| localhost:8080/planetarium | Jupiter     | The user receives a notification that deleting the planet with the inputted name has failed | Batman         | I am the night |
			| localhost:8080/planetarium | Venus       | The user receives a notification that deleting the planet with the inputted name has failed | Batman         | I am the night |