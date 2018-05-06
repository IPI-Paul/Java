set shell = CreateObject("WScript.Shell")

shell.run "Cmd.exe /k " & """C:\Program Files (x86)\Microsoft Visual Studio\2017\Community\Common7\Tools\vsdevcmd.bat"""

WScript.sleep 1000
shell.sendkeys "CD " & shell.CurrentDirectory, true
shell.sendkeys "{Enter}", true

WScript.sleep 15000
a = inputbox("Please wait until the Command Prompt is in the current directopry." &vbcrlf & "Please give the file name you want to convert! If it is not in the currently displayed directory, please give the full path")

if a > "" then
  shell.sendkeys "cl /EHsc " & """" &  a & """", true
  shell.sendkeys "{Enter}", true
end if