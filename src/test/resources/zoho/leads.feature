Feature: Search functionality

Background:
Given I am logged in zoho.com
And I click on "leads" in top menu
  
  @CreateLead
  Scenario Outline: Creation of a Lead
    When I go to create lead page
    And enter and submit lead details
    | FirstName  | LastName | Email  | Company |
    | <FirstName>| <LastName>| <Email>| <Company> |
    Then Lead Description Page should load
    And I verify lead details
    When I click on "leads" in top menu
    Then Lead '<LeadName>' should 'be present' inside the grid
    Examples: 
      | FirstName  | LastName | Email        | Company |
      | Alex       |  Jones   | xyz@gmail.com| MRF     |
      

    
    
  @DeleteLead
  Scenario Outline: Deletion  of the lead
    When I select the lead '<LeadName>'
    And I click on delete button
    Then Lead '<LeadName>' should 'not be present' inside the grid
    Examples:
      | LeadName  |
      | Alex Jones|

