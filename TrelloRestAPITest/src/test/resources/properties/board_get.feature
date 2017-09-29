Feature: Testing GET Service. Board Component")

@1
Scenario Outline: Verify that user is able to get an existing board by ID and is not able to get board by invalid ID
GET /boards/{id}
	When I get board by ID <boardID>
	Then I should see board with ID <boardID> is <status>
	Examples:
	| boardID 		           | status   |
	| 59a6ceaa1522ccd3686ad4da | present  |
	| 59a6ceaa1522ccd3686ad4dA | absent   | 

@2
Scenario Outline: Verify that user is able to get all lists of the board
GET /boards/{id}/lists
	
	When I get lists of board with ID <boardID>
	Then I should see board has <listsAmount> lists
	Examples:
	| boardID 		           | listsAmount |
	| 59a6ceaa1522ccd3686ad4da | 2           |

@3
@PathParameter
Scenario Outline: Verify that user is able to get lists of the board with path parameter filter
GET /boards/{id}/lists/{filter}   
	When I get lists with filter = <filter> of board with ID <boardID>
	Then I should see board has <listsAmount> lists
	Examples:
	| boardID 		           | listsAmount | filter |
	| 59a6ceaa1522ccd3686ad4da | 1           | closed |
	| 59a6ceaa1522ccd3686ad4da | 3           | all    |
	| 59a6ceaa1522ccd3686ad4da | 0           | none   |
	| 59a6ceaa1522ccd3686ad4da | 2           | open   |

@4
@QueryParameter
Scenario Outline: Verify that user is able to get board list info by query parameter fields = name
GET boards/id/lists?fields=name	
	When I get lists by fields = name of a board with ID 59a6ceaa1522ccd3686ad4da
	Then I should see board has 2 lists
	Then I should see name of the list with id <listID> is <listName>
	Examples:
	| listID 				   | listName  | 
	| 59a6ceb74447c94cf1316864 | testList1 |
	| 59a6cebc673615f5f7e64960 | testList2 |

@5
@QueryParameter
Scenario Outline: Verify that user is able to get cards info of board list by query parameter cards
GET boards/id/lists?cards=value		
	When I get lists by cards = <cards> of the board with ID 59a6ceaa1522ccd3686ad4da
	Then I should see list with ID <listID> has <cardsAmount> cards
	Examples:
	| listID                   | cardsAmount | cards   |
	| 59a6ceb74447c94cf1316864 | 2           | all     |
	| 59a6cebc673615f5f7e64960 | 6           | all     |
	| 59a6ceb74447c94cf1316864 | 0           | closed  |
	| 59a6cebc673615f5f7e64960 | 3           | closed  |
	| 59a6ceb74447c94cf1316864 | 2           | open    | 
	| 59a6cebc673615f5f7e64960 | 3           | open    |
	| 59a6ceb74447c94cf1316864 | 2           | visible |
	| 59a6cebc673615f5f7e64960 | 3           | visible |
	| 59a6ceb74447c94cf1316864 | 0           | none    |
	| 59a6cebc673615f5f7e64960 | 0           | none    |

@6
Scenario Outline: Verify that user is able to get all cards of the board
GET /boards/{id}/cards
	When I get cards of board with ID <boardID>
	Then I should see board has <cardsAmount> cards
	Examples:
	| boardID 		           | cardsAmount |
	| 59a6ceaa1522ccd3686ad4da | 5           |

@7
@PathParameter
Scenario Outline: Verify that user is able to get card by ID of the board
GET /boards/{id}/cards/{cardId}
	
	When I get card by ID <cardID> of board with ID 59a6ceaa1522ccd3686ad4da
	Then I should see name of the card with ID <cardID> is <cardName>
	Examples:
	| cardID 		           | cardName    |
	| 59a6cec7b534421babb02388 | testCard1.1 |
	| 59a6cecddbc4fc7aa3d8113b | testCard1.2 |
	
@8
@PathParameter
Scenario Outline: Verify that user is able to get cards of the board with path parameter filter
GET /boards/{id}/cards/{filter}   
	When I get cards with filter = <filter> of board with ID <boardID>
	Then I should see board has <cardsAmount> cards
	Examples:
	| boardID 		           | cardsAmount | filter  |
	| 59a6ceaa1522ccd3686ad4da | 3           | closed  |
	| 59a6ceaa1522ccd3686ad4da | 8           | all     |
	| 59a6ceaa1522ccd3686ad4da | 0           | none    |
	| 59a6ceaa1522ccd3686ad4da | 5           | open    |
	| 59a6ceaa1522ccd3686ad4da | 5           | visible |

@9
Scenario Outline: Verify that user is able to get checklists of the board
GET /boards/{id}/checklists   
	When I get checklists of board with ID 59a6ceaa1522ccd3686ad4da
	Then I should see board has 2 checklists
	Then I should see name of the checklist with id <checklistID> is <checklistName>
	Examples:
	| checklistID 			   | checklistName   | 
	| 59a6d3cd67bbe6627bf104a2 | CheckList2.1.1  |
	| 59a6d3d7479280e4b44d5e73 | CheckList2.1.2  |
