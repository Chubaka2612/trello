Feature: Board
  As a registered user
  I want to interact with boards

@Board  
@Get
Scenario: Verify that user is able to get an existing board by ID and is not able to get board by invalid ID GET /boards/{id}
	When I get board by ID 123
	Then I should see board with ID 123 is absent

@Get
Scenario Outline: Verify that user is able to get an existing board by ID and is not able to get board by invalid ID
GET /boards/{id}
	When I get board by ID <boardID>
	Then I should see board with ID <boardID> is <status>
	Examples:
	| boardID 		           | status   |
	| 59a6ceaa1522ccd3686ad4da | present  |
	| 59a6ceaa1522ccd3686ad4dA | absent   | 
	