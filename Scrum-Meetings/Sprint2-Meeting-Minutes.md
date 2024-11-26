# Scrum Meetings - *Sprint 2*

### *Monday, November 18th, 2024*

**Discussion**: Discussed points we each need to finish from previous sprint: a bit of automation testing across each feature.
We talked about expected deadlines and creating a new timeline document for the current sprint as well as the stakeholder expectations.
The RTM will be relatively the same, but we will need to update the Test Plan Document due to the current sprint including
unit and integration testing. In terms of the split of work, the team will follow the same format with how we divided the
work by features.

- TK: Registration features
- HN: Viewing planetarium features
- II: Planet features
- MF: Moon features

**Blockers**: Team occupied with client interviews

### *Tuesday, November 19th, 2024*

**Accomplishments**: Finished Test Strategies documentation, timeline document, JIRA board for current sprint, and skeleton 
for unit tests together

**Discussion**: Discussed the product's MVP requirements and what files we need to test in order to get to that point. We also 
did a mini sprint retrospective in order to plan our current timeline with more realistic goals. II organized the repo 
and added the source code. TK updated the pom.xml to include new dependencies for the current sprint. MF drove the peer programming today.

**Blockers**: Our understanding of the current project was a bit lacking, but this has been cleared up

**Goal**: Finish our test case writing by Monday, November 25th

**Moving Forward**:
- TK: User controller, repository, and DAO
- HN: View controller
- II: Planet controller, repository, and DAO + fixing automation tests
- MF: Moon controller, repository, and DAO

### *Wednesday, November 20th, 2024*
**Discussion**: Discuss progress on unit tests
- TK: Need to rework Junit tests for User Dao and Service; 
did not implement changes yet to User Registration/Login steps; need to learn more about Postman testing
- HN: Work on the viewer controller, learn API response data in Postman
- MF: Continuing work on implementing unit tests for moon creation/deletion
- II: Finish up automation testing in order to proceed with unit testing

**Blockers**: QC tomorrow which is taking time away from the project

### *Thursday, November 21th, 2024*
**Discussion**: More discussion on unit tests
- TK: Reworked Junit tests for UserDao and UserService; spent studying for QC
- HN: Continuing to work on View Controller Testing, and learning how to API testing with Postman
- MF: Working on adding more tests and converting images to string
- II: Automation testing finished for planet creation + deletion; start working on unit tests

**Blockers**: illness + QC anxiety

### *Friday, November 22th, 2024*
**Discussion**: Discussed when and where to use Mockito + our progression on integration and unit testing; peer programmed
drived by TK to clear up any issues with Postman API Pre-Requests and Testing

- II: working on planetDAOtests; blockers - mockito understanding on if to mock the actual DB
- HN: working on script testing for planet visibility requirement; blockers - script writing
- MF: finished working on MoonDAOTests; next will be working on the service layer; blockers - mockito when / where
- TK: fixed cucumber steps for user login / registration; working on script writing; blockers - Postman documentation

### *Monday, November 25th, 2024*
**Discussion**: Discussed progress with the unit and integration testing

- II: finished unit testing for Planet creation and deletion; starting Postman API testing
- HN: Still working on API Scripting Test and viewer controller
- MF: Finished Dao tests; implemented majority of tests needed for the service and controller layers for moon
- TK: Still working on API testing (refactoring tests and learning to export tests)

**Blockers**: Cumulative QC tomorrow + time management

### *Tuesday, November 26th, 2024*
**Discussion**: Discussed progress with the unit and integration testing + worked on Sprint Retrospective. MF showcased how to
export results onto Jira. TK showcased exporting JUnit tests. Also discussed project presentation for tomorrow.

- II: planet creation and deletion API testing finished, just need to push the tests to AIO and write defect reports
- HN: finished integration for ViewController and just need to export it to AIO
- MF: finished service tests and API tests for Moon creation and deletion + exported to Jira; still need to merge branch
- TK: exported all tests to Jira, need to defect reports on my test cycles, need to reupload test steps for step implementations

**Blockers**: Cumulative QC tomorrow + time management + surefire Maven plugin

**Retrospective**: File located in Planetarium Documentation folder; exporting issues with Jira

/************************************************ END OF SPRINT ****************************************************/