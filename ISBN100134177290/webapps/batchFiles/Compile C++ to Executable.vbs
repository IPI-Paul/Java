set shell = CreateObject("WScript.Shell")
set wShell = CreateObject("Shell.Application")

shell.run "Cmd.exe /k " & """C:\Program Files (x86)\Microsoft Visual Studio\2017\Community\Common7\Tools\vsdevcmd.bat"""

WScript.sleep 11000
dim a
set fld = nothing
while fld is nothing
  set fld = wShell.BrowseForFolder(0,"Choose a folder to compile the C++ files in:" , &H0001 + &H0004 + &H0010 + &H0020,   "C:\Users\Paul\Documents\Studies\Java\ISBN100134177290\sourceFiles\exe")
  if not fld is nothing then
    a = fld.self.path
    a = replace(replace(a, "(", "{(}"), ")", "{)}")
  end if
wend

shell.sendkeys "cd " & a, true
shell.sendkeys "{Enter}", true

a = ""
while a = ""
	set oExec=shell.exec("mshta.exe ""about:<head><meta http-equiv=""x-ua-compatible"" content=""IE=9""><script language=""vbscript""type=""text/vbscript"">document.parentwindow.clipboardData.SetData ""text"", """": close()</script></head>""")
	wscript.sleep 3000
	set oExec=shell.exec("mshta.exe ""about:<head><meta http-equiv=""x-ua-compatible"" content=""IE=9""><script language=""vbscript""type=""text/vbscript"">window.setTimeout ""getFile()"", 10, ""vbscript"": Sub getFile() document.parentwindow.clipboardData.SetData ""text"",Dlg.OpenFileDlg(""C:\Users\Paul\Documents\Studies\Java\ISBN100134177290\nul"",,""CPP%20Files(*.cpp)|*.cpp|All%20Files(*.*)|*.*"",""Select%20a%20file%20to%20convert"") End Sub </script></head><object id=""Dlg"" classid=""CLSID:3050F4E1-98B5-11CF-BB82-00AA00BDCE0B"" width=""0"" height=""0""></object>""")
	dim sFileSelected
	sFileSelected = ""
	while sFileSelected = ""
		wscript.sleep 4000
		sFileSelected = CreateObject("htmlfile").ParentWindow.ClipboardData.GetData("text")
	wend
	oExec.terminate
	a = sFileSelected
wend
a = replace(replace(a, "(", "{(}"), ")", "{)}")

WScript.sleep 2000
shell.sendkeys "cl /EHsc """ & a & """", true
shell.sendkeys "{Enter}", true

set shell = nothing
set wShell = nothing
set oExec = nothing
set fld = nothing