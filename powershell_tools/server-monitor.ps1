$processName = "java"
$serverStartScriptPath = ".\start.bat"
$serverAddress = ""
$serverPort = 25565

# Define the check interval in seconds
$checkInterval = 300
$serverstart = $false
$color = "White"
while ($true) {
	$color = "White"
	$serverstart = $false
    $process = Get-Process $processName -ErrorAction SilentlyContinue

    # Check if the process is not running
    if ($null -eq $process) {
        # Start the server
        Start-Process -FilePath $serverStartScriptPath
        $response = "Minecraft server was not running."
		$color = "Red"
		$serverstart = $true
    } else {
		$ping = Test-Netconnection -ComputerName $serverAddress -Port $serverPort
        if(Test-Connection -Computername $serverAddress)
		{
			if($ping.TcpTestSucceeded -eq $true)
			{
				$response = "Minecraft server is running properly."
			}
			else
			{
				$response = "Minecraft server was not responding."
				$color = "Red"
				$serverstart = $true
				stop-process $process
				stop-process (get-process cmd)
				Start-Process -FilePath $serverStartScriptPath
			}
		}
		else
		{
			$response = "Ping failed, something's wrong..."
			$color = "Red"
		}
    }
	Write-Host "`r$(Get-Date) | $($response)" -Foregroundcolor $color
	if($serverstart)
		{
			for ($i = 1800; $i -ge 0; $i--) {
					Write-Host "`rWaiting for server restart: $($i)    " -NoNewline
					Start-Sleep -Seconds 1
				}
			Write-Host "Server should be restarted.                    "
		}
	for ($i = $checkInterval; $i -ge 0; $i--) {
    Write-Host "`r$i seconds until next check     " -NoNewline
    Start-Sleep -Seconds 1
	}
}
