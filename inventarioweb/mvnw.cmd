@echo off
setlocal

set "BASEDIR=%~dp0"
if "%BASEDIR:~-1%"=="\" set "BASEDIR=%BASEDIR:~0,-1%"

if "%JAVA_HOME%"=="" (
    set "JAVA_EXE=java"
) else (
    set "JAVA_EXE=%JAVA_HOME%\bin\java.exe"
)

set "WRAPPER_JAR=%BASEDIR%\.mvn\wrapper\maven-wrapper.jar"

if not exist "%WRAPPER_JAR%" (
    echo Error: no se encontro "%WRAPPER_JAR%". >&2
    exit /b 1
)

"%JAVA_EXE%" %MAVEN_OPTS% %MAVEN_DEBUG_OPTS% -classpath "%WRAPPER_JAR%" "-Dmaven.multiModuleProjectDirectory=%BASEDIR%" org.apache.maven.wrapper.MavenWrapperMain %*
exit /b %ERRORLEVEL%
