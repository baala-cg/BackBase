Feature: Delete Article
  
    @CRUDArticles   
    Scenario: User deleting an Article
  	Given Scenario Name as "Delete_Article"
    And User navigates to Url
    And User logs into the application
    When User tries to delete an Article then Article should be deleted successfully     
    Then User logs out of the application
    
