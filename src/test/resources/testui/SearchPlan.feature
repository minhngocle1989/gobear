Feature: Search for insurance plans

    Scenario: Search single plans
        Given I am on travel page
        When I search for single plans
        Then There should be more than 3 plan cards

        When I click on element "SEE MORE" with xpath "//a[contains(text(), 'SEE MORE')]"
        And I select element "Malayan Insurance" with xpath "//div[@data-filter-name='Malayan Insurance']"
        Then There should be 3 plan cards

        And I move max value -50 pixels of element "Personal Accident" with xpath "//label[contains(text(), 'Personal Accident')]//following-sibling::div[@class='bootstrap-slider']//div[contains(@class,'max-slider-handle')]"
        Then There should be 3 plan cards

        And I click on element "Price: High to low" with xpath "//label[contains(text(), 'Price: High to low')]"
        Then There should be 3 plan cards

        And I click on element "Destination" with xpath "//div[contains(@class, 'destination-field')]//button"
        And I click on element "Thailand destination" with xpath "//div[contains(@class, 'destination-field')]//span[text()='Thailand']/ancestor::li"
        Then There should be more than 3 plan cards

        And I click on element "Travel End Date" with xpath "//div[@class='date-to']"
        And I click on element "Day" with xpath "//td[@class='day'][last()]"
        Then There should be more than 3 plan cards
