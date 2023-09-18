Feature: Fetch Article    
    
    @CRUD_Articles
    @Test123
    @ignore
    Scenario: User fetching an Article
  	Given Scenario Name as "Fetch_Article"
    And User navigates to Url
    And User logs into the application
    When User tries to fetch an Article then Article should be retrieved successfully 
    Then User logs out of the application
    