@ECHO OFF

rem you know.
SET folderPath=c:\temp\

java -classpath ../../../target/folder-compression-app.jar com.googlecode.usc.folder.compression.App2 -folderPath %folderPath% -compressionType ZIP -excludedWords ".svn | target | target-eclipse | .classpath | .project | .settings" -help 


@PAUSE

