set shell = CreateObject("WScript.Shell")
set wShell = CreateObject("Shell.Application")

shell.run "Cmd.exe /k " & """C:\Program Files (x86)\Microsoft Visual Studio\2017\Community\Common7\Tools\vsdevcmd.bat"""

WScript.sleep 15000
dim c, d
c = ""
d = ""

set fld = nothing
while fld is nothing
  set fld = wShell.BrowseForFolder(0,"Choose a folder to store the C++ Libraries in:" , &H0001 + &H0004 + &H0010 + &H0020,   "C:\Users\Paul\Documents\Studies\Java\ISBN100134177290\sourceFiles\libraries")
  if not fld is nothing then
    a = fld.self.path
    a = replace(replace(a, "(", "{(}"), ")", "{)}")
  end if
wend

shell.sendkeys "CD " & a, true
shell.sendkeys "{Enter}", true

set fld = nothing
while fld is nothing
  set fld = wShell.BrowseForFolder(0,"Choose the folder where the JDK\include is located:" , &H0001 + &H0004 + &H0010 + &H0020, "C:\Program Files\Java\jdk1.8.0_161\include")
  if not fld is nothing then
    a = fld.self.path
  end if
wend

set fld = nothing
while fld is nothing
  set fld = wShell.BrowseForFolder(0,"Choose the folder where the JDK\include\win32 is located:" , &H0001 + &H0004 + &H0010 + &H0020, "C:\Program Files\Java\jdk1.8.0_161\include\win32")
  if not fld is nothing then
    b = fld.self.path
  end if
wend

while c = ""
	set oExec=shell.exec("mshta.exe ""about:<head><meta http-equiv=""x-ua-compatible"" content=""IE=9""><script language=""vbscript""type=""text/vbscript"">document.parentwindow.clipboardData.SetData ""text"", """": close()</script></head>""")
	wscript.sleep 3000
	set oExec=shell.exec("mshta.exe ""about:<head><meta http-equiv=""x-ua-compatible"" content=""IE=9""><script language=""vbscript""type=""text/vbscript"">window.setTimeout ""getFile()"", 10, ""vbscript"": Sub getFile() document.parentwindow.clipboardData.SetData ""text"",Dlg.OpenFileDlg(""C:\Users\Paul\Documents\Studies\Java\ISBN100134177290\null"",,""C%20Files%20(*.c)|*.c|All%20Files(*.*)|*.*"",""Select%20a%20file%20to%20convert"") End Sub </script></head><object id=""Dlg"" classid=""CLSID:3050F4E1-98B5-11CF-BB82-00AA00BDCE0B"" width=""0"" height=""0""></object>""")
	dim sFileSelected
	sFileSelected = ""
	while sFileSelected = ""
		wscript.sleep 4000
		sFileSelected = CreateObject("htmlfile").ParentWindow.ClipboardData.GetData("text")
	wend
	oExec.terminate
	c = sFileSelected
wend

e = Split(c, "\")
f = Split(e(Ubound(e)),".")(0)

while d = ""
  d = inputbox("Please give the DLL name to use", "DLL Name", f & ".dll")
wend

a = "cl -I """ & a & """ -I """ & b & """ -LD """ & c & """ advapi32.lib -Fe" & d
a = replace(replace(a, "(", "{(}"), ")", "{)}")
if a > "" then
  shell.sendkeys a, true
  shell.sendkeys "{Enter}", true
end if

set shell = nothing
set wShell = nothing
set oExec = nothing
set fld = nothing