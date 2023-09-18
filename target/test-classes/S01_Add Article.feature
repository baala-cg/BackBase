Feature: Add Aritcle        
    
    @CRUD_Articles
    @Test123
  	Scenario: User adding an Article
  	Given Scenario Name as "Add_Article"
    And User navigates to Url
    And User logs into the application
    And DataSheet as "Article_Data"
    When User tries to add an Article then Article should be added successfully 
    Then User logs out of the application

  