Feature: Update Article
  
    @CRUD_Articles
    Scenario: User updating an Article
  	Given Scenario Name as "Update_Article"
    And User navigates to Url
    And User logs into the application
    And DataSheet as "Article_Data"
    When User tries to update an Article then Article should be modified successfully     
    Then User logs out of the application
    
   