@UserRegistration
Feature: UserRegistration

	@PLAN-TC-35 @JREQ-PLAN-9 @AIO-FOLDER-Automated/User_Registration
	Scenario Outline: User Registration: Password invisible when user creates a new account
	Test case for checking if the user's password is invisible when a user creates an account (Error Guess testing).
		Given The user is on the login page "<Host>"
		When The user clicks on the Create Account button
		And The user registers the username "<Username>"
		And The user registers the password "<Password>"
		And The user's password is not visible on the register input field
		And The user clicks on the Create button
		Then The user's account is "<Account Created>" via a notification and user's password "<Password>" should not be visible
		And The user should be "<Redirected>" back to the login page

	Examples: 
		| Host                   | Username | Password | Account Created | Redirected     |
		| localhost:8080 | Example  | Butter   | created         | redirected     |
		| localhost:8080 | (empty)  | Butter   | not created     | not redirected |

	@PLAN-TC-36 @JREQ-PLAN-9 @AIO-FOLDER-Automated/User_Registration
	Scenario Outline: User Registration: Length of usernames and passwords 
	Test case checking the valid length of an username and password for creating a user for Plantarium (Boundary Analysis testing).
		Given The user is on the login page "<Host>"
		When The user clicks on the Create Account button
		And The user registers the username "<Username>"
		And The user registers the password "<Password>"
		And The user clicks on the Create button
		Then The user should receive a notification that their account is "<Account Created>" for "<Username>"
		And The user should be "<Redirected>" back to the login page

	Examples: 
		| Host                   | Username                        | Password                        | Account Created | Redirected     |
		| localhost:8080 | (empty)                         | (empty)                         | not created     | not redirected |
		| localhost:8080 | (empty)                         | b                               | not created     | not redirected |
		| localhost:8080 | (empty)                         | ICannotThinkOfAGoodPassword123  | not created     | not redirected |
		| localhost:8080 | (empty)                         | ICannotThinkOfAGoodPassword1234 | not created     | not redirected |
		| localhost:8080 | a                               | (empty)                         | not created     | not redirected |
		| localhost:8080 | a                               | b                               | created         | redirected     |
		| localhost:8080 | a                               | ICannotThinkOfAGoodPassword123  | created         | redirected     |
		| localhost:8080 | a                               | ICannotThinkOfAGoodPassword1234 | not created     | not redirected |
		| localhost:8080 | IHaveTheTwoBestDogsInTheWorld!  | (empty)                         | not created     | not redirected |
		| localhost:8080 | IHaveTheTwoBestDogsInTheWorld!  | b                               | created         | redirected     |
		| localhost:8080 | IHaveTheTwoBestDogsInTheWorld!  | ICannotThinkOfAGoodPassword123  | created         | redirected     |
		| localhost:8080 | IHaveTheTwoBestDogsInTheWorld!  | ICannotThinkOfAGoodPassword1234 | not created     | not redirected |
		| localhost:8080 | IHaveTheTwoBestDogsInTheWorld!! | (empty)                         | not created     | not redirected |
		| localhost:8080 | IHaveTheTwoBestDogsInTheWorld!! | b                               | not created     | not redirected |
		| localhost:8080 | IHaveTheTwoBestDogsInTheWorld!! | ICannotThinkOfAGoodPassword123  | not created     | not redirected |
		| localhost:8080 | IHaveTheTwoBestDogsInTheWorld!! | ICannotThinkOfAGoodPassword1234 | not created     | not redirected |

	@PLAN-TC-37 @JREQ-PLAN-9 @AIO-FOLDER-Automated/User_Registration
	Scenario Outline: User Registration: Usernames must be unique for account creation
	Test case to check that only user accounts with unique usernames are created in Plantarium (Equivalence Partition testing).
		Given The user is on the login page "<Host>"
		When The user clicks on the Create Account button
		And The user registers the username "<Username>"
		And The user registers the password "<Password>"
		And The user clicks on the Create button
		Then The user should receive a notification that their account is "<Account Created>" for "<Username>"
		And The user should be "<Redirected>" back to the login page

	Examples: 
		| Host                   | Username | Password            | Account Created | Redirected     |
		| localhost:8080 | Robin    | BatmanAndRobinRocks | created         | redirected     |
		| localhost:8080 | Batman   | BatmanAndRobinRocks | not created     | not redirected |