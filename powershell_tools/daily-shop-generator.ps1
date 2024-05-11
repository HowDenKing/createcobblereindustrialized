# Import CSV file
$csvData = Import-Csv -Path .\daily-shop.csv

# Initialize an empty ordered dictionary for trades
$trades = [ordered]@{}

# Loop through each row in the CSV data
foreach ($row in $csvData) {
    # Create an ordered dictionary for each item
    $item = [ordered]@{
        "currency" = $row.currency
        "price"    = [int]$row.price
        "amount"   = [int]$row.amount
        "rarity"   = [int]$row.rarity
    }

    # Add the item ordered dictionary to the trades ordered dictionary
    $trades[$row.item] = $item
	Write-Host $row.item
}

# Create an ordered dictionary for the final JSON structure
$jsonData = [ordered]@{
    "trades-amount" = 7
    "trades"        = $trades
}

# Convert the ordered dictionary to a JSON string
$jsonString = $jsonData | ConvertTo-Json -Depth 3

# Write the JSON string to a file
$jsonString | Out-File -FilePath .\daily-shop.json
