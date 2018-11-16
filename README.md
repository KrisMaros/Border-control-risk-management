# Border-control-risk-management
Aplikacja java z interfejsem graficznym do przeprowadzania weryfikacji pasażerów linii lotniczej  w przypadku zagrożenia 

New  Project _ Requirements

GreenLine Passenger Targeting System (PTS)
The GreenLine Passenger Targeting System (PTS) extracts passenger and crew information from Advance Passenger Information and Passenger Name Records so that it may be vetted against internal border control holdings. 

•	The system provides a map layer, data grid, and navigation panel.

•	The dashboard is user configurable. To support workflow management, the application groups passenger data by flight and airport/terminal of arrival.

•	User screens present passenger data in summary view and display an overall risk indicator (red, yellow, green) in the “My Flights” Navigation pane as well as in the data grid below the map layer at the bottom of the screen. Passengers are force ranked in order of risk score from highest to lowest.

•	Queries can be made against either passenger or flight data.

  o	Searching by passenger displays a list of passengers in the grid that match all selected criteria across multiple flights; whereas
  
  o	Searching by flight displays a list of flights in the grid that match all selected criteria across multiple passengers, crew,           equipment, capacity factors or locations.
  
  o	Additional search constraints are included for more detailed queries.
  
•	Clicking on an individual name opens a new window containing the full passenger details with hyperlinks to additional data, including seating, flight, ticket and payment details.

•	Selecting the “View Risk Analysis Details” link shows the full risk scorecard with the details on how the risk assessment module determined this passenger’s score.  The Passenger Risk Assessment Module comes pre-loaded with risk indicator rules associated with terrorism, narcotics, smuggling, illegal immigration, and revenue related threats.

•	The PTS contains a rules editor so users or administrators can create new rules and risk profiles in the system.

•	In the View Manager, users can create and save user-specific rules and queries.  As new data are received, hits are automatically pushed to the user for review.

•	A user may also initiate workflows and case management activities with other users/analysts within their own agency or in partnership with other agencies. 

  o	Shared folders can be populated with either passengers or flights via manual point-and-click or drag-and-drop functionality or           automation (based on user-defined and organization-defined settings).
  
  o	Administrative controls can be created to restrict information only to those users with a “Need to Know”.

