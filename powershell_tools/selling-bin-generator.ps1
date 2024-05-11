# Import CSV file
$csvData = Import-Csv -Path .\selling-bin.csv

# Initialize an empty ordered dictionary for trades
$trades = [ordered]@{}

# Loop through each row in the CSV data
foreach ($row in $csvData) {
    # Create an ordered dictionary for each item
    $item = [ordered]@{
        "currency"     = $row.currency
        "sellPrice"    = [int]$row.price
        "sellAmount"       = [int]$row.amount
    }

    # Add the item ordered dictionary to the trades ordered dictionary
    $trades[$row.item] = $item
	Write-Host $row.item
}

# Create an ordered dictionary for the final JSON structure
$jsonData = $trades

# Convert the ordered dictionary to a JSON string
$jsonString = $jsonData | ConvertTo-Json -Depth 3

# Write the JSON string to a file
$jsonString | Out-File -FilePath .\selling-bin.json
