set shell = CreateObject("WScript.Shell")
set wShell = CreateObject("Shell.Application")

shell.run "Cmd.exe"
wscript.sleep 1000

dim a, b, c, d, e
a = ""
while a = ""
	set oExec=shell.exec("mshta.exe ""about:<head><meta http-equiv=""x-ua-compatible"" content=""IE=9""><script language=""vbscript""type=""text/vbscript"">document.parentwindow.clipboardData.SetData ""text"", """": close()</script></head>""")
	wscript.sleep 3000
	set oExec=shell.exec("mshta.exe ""about:<head><meta http-equiv=""x-ua-compatible"" content=""IE=9""><script language=""vbscript""type=""text/vbscript"">window.setTimeout ""getFile()"", 10, ""vbscript"": Sub getFile() document.parentwindow.clipboardData.SetData ""text"",Dlg.OpenFileDlg(""C:\Users\Paul\Documents\Studies\Java\ISBN100134177290\nul"",,""Java%20(*.java)|*.java|All%20Files(*.*)|*.*"",""Select%20a%20file%20to%20convert"") End Sub </script></head><object id=""Dlg"" classid=""CLSID:3050F4E1-98B5-11CF-BB82-00AA00BDCE0B"" width=""0"" height=""0""></object>""")
	dim sFileSelected
	sFileSelected = ""
	while sFileSelected = ""
		wscript.sleep 4000
		sFileSelected = CreateObject("htmlfile").ParentWindow.ClipboardData.GetData("text")
	wend
	oExec.terminate
	a = sFileSelected
wend

b = Split(a, "\")
c = b(Ubound(b) - 1) & "." & Split(b(Ubound(b)),".")(0)

d = ""
for i = 0 to Ubound(b) -1
	d = d & b(i) & "\"
next 

e = ""
for i = 0 to Ubound(b) - 2
	e = e & b(i) & "\"
next 

set fld = nothing
while fld is nothing
  set fld = wShell.BrowseForFolder(0,"Choose a folder to store the C++ h file in:" , &H0001 + &H0004 + &H0010 + &H0020, d)
  if not fld is nothing then
    d = fld.self.path
  end if
wend

set fld = nothing
while fld is nothing
  set fld = wShell.BrowseForFolder(0,"Choose the parent folder where the package is located:" , &H0001 + &H0004 + &H0010 + &H0020, e)
  if not fld is nothing then
    e = fld.self.path
    e = replace(replace(e, "(", "{(}"), ")", "{)}")
  end if
wend

shell.sendkeys "CD " & e, true
shell.sendkeys "{Enter}", true

a = "javah -d """ & d & """ " & c
a = replace(replace(a, "(", "{(}"), ")", "{)}")
if a > "" then
  shell.sendkeys a, true
  shell.sendkeys "{Enter}", true
end if

set shell = nothing
set wShell = nothing
set oExec = nothing
set fld = nothing