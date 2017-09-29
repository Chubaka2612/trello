Feature: Testing POST Service. Board Component

@Board
@Post
@RemoveBoard
Scenario Outline: Verify that user is able to create new single board with valid name and is not able to create board with invalid name
POST /boards/
	When I create new board with name <boardName>
	Then I should see board with name <boardName> is <status>
	Examples:
	| boardName	 | status  |
	| testBoard2 | present |
	| !@#$%^&*   | present |
	|            | absent  |
	
@Board
@Post
Scenario Outline: Verify that user is able to create new list on the board with valid name and can't create list with invalid name
POST /boards/{id}/lists
	When I create new list with name <listName> on board with ID <boardID>
	Then I should see list with name <listName> is <status>
	Examples:
	| boardID                    | listName	| status  |
	| 59a6cb31f3071d7b78dc1847   | testList | present |
	| 59a6cb31f3071d7b78dc1847   | !@$#%^&* | present |
	| 59a6cb31f3071d7b78dc1847   |          | absent  |

@Board
@Post
@RemoveBoard
Scenario Outline: Verify that user is able to create two board with the same name
POST /boards/
	When I create new board with name <boardName>
	When I create new board with name <boardName>
	Then I should see there are 2 boards with name <boardName>
	Examples:
	| boardName	 | status  |
	| testBoard3 | present |
