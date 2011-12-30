@ECHO OFF

rem you know.
SET folderPath=C:\temp
SET finalArchiveName=archive.zip

"C:\Program Files\7-Zip\7z.exe" d %finalArchiveName%
"C:\Program Files\7-Zip\7z.exe" a -r -tzip -y -xr!.svn -xr!target -xr!target-eclipse -xr!.classpath -xr!.project -xr!.settings %finalArchiveName% %folderPath%\*

@PAUSE

