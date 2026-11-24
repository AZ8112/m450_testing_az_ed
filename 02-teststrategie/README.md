# Teststrategie

# Exercise 1

## Abstract test cases

| ID | Input (purchase price P) | Expected discount |
|----|--------------------------|-------------------|
| 1  | P < 15,000               | 0%                |
| 2  | 15,000 <= P <= 20,000    | 5%                |
| 3  | 20,000 < P < 25,000      | 7%                |
| 4  | P >= 25,000              | 8.5%              |

## Concrete test cases

| ID | Purchase price (CHF) | Expected discount | Expected final price (CHF) | Why this value |
| :--- | :--- | :--- | :--- | :--- |
| 1 | 10,000 | 0% | 10,000.00 | Normal value (safely inside the 0% range) |
| 2 | 14,999 | 0% | 14,999.00 | Just below the 15,000 lower boundary |
| 3 | 15,000 | 5% | 14,250.00 | Exact lower boundary (15,000) |
| 4 | 17,500 | 5% | 16,625.00 | Normal value (safely inside the 5% range) |
| 5 | 20,000 | 5% | 19,000.00 | Exact upper boundary ("up to" = inclusive) |
| 6 | 20,001 | 7% | 18,600.93 | Just above the 20,000 boundary |
| 7 | 22,500 | 7% | 20,925.00 | Normal value (safely inside the 7% range) |
| 8 | 24,999 | 7% | 23,249.07 | Just below the 25,000 upper boundary |
| 9 | 25,000 | 8.5% | 22,875.00 | Exact upper boundary where highest discount starts |
| 10 | 30,000 | 8.5% | 27,450.00 | Normal value (safely inside the 8.5% range) |
---


# Exercise 2 – Car rental website (sixt.ch)

Functional black-box tests: only the website is used, no code.

| ID | Description | Expected Result | Actual Result | Status | Possible Cause |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 1 | Open the website (https://www.sixt.ch/) | The homepage loads completely and the car search form is shown. | The homepage loads completely, no loading delays, and the search form is visible at the top. | Passed | - |
| 2 | Car search: Return date is **before** the pick-up date | The search is blocked and a clear error message is shown (e.g., "Return date must be after pick-up date"). | The search is automatically replaced with the current date without informing the user. | Failed | The frontend validation does not catch the error. Instead, the system quietly fixes the input in the background (auto-correction). This can lead to unintended search results. |
| 3 | Only show cars at the selected location | The search results only show cars that are available at the chosen pick-up and return location. | After entering the location "Zürich", all listed cars show "Zürich" as the pick-up and return location. | Passed | - |
| 4 | Search for a car, select one and add extras (e.g., booster seat) | The total price is updated correctly and includes the cost of the selected extra. | After selecting the "child seat" extra (approx. 15 CHF/day), the total price in the summary increases by the correct amount. | Passed | - |
| 5 | Search for a vehicle at a location with no matching cars (e.g., a van) | The system shows alternative locations where the desired vehicle (e.g., a van) is available, instead of only showing the selected location. | When searching for a van at an unavailable location, a list of locations appears. Locations without availability are marked in red, and locations with availability are in normal black text. | Passed | - |