@MoonDeletion
Feature: MoonDeletion

	@PLAN-TC-29 @JREQ-PLAN-15 @AIO-FOLDER-Automated/Moon_Deletion
	Scenario Outline: Moon Deletion: User can only delete moons that they own or exist
	Test case to check if the user can only delete moons that they own (Equivalence Partitioning testing).
		Given the user has logged into Planetarium and is on the home page
		When the user has enabled the Moon tag on the home page
		And the user inputs moon "<Moon name>"
		And the user clicks the Delete button
		Then the user should see "<Result>" in moon deletion
			"""
			"<Moon name>"
			"""
		And the user should see that moon deleted is <Moon Deleted?>
			"""
			"<Moon name>"
			"""

	Examples: 
		| Home page                         | Moon name   | Moon Deleted? | Result                                  |
		| https://localhost/8080/plantarium | Titan       | true          | (no alert, table refresh)               |
		| https://localhost/8080/plantarium | Deimos      | false         | Failed to delete Moon with name ${name} |
		| https://localhost/8080/plantarium | Lunar Whale | false         | Failed to delete Moon with name ${name} |