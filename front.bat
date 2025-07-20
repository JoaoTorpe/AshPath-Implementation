@echo off

:: Temporarily switch to the ashpath-frontend directory
pushd ashpath-frontend || (
    echo Directory 'ashpath-frontend' not found
    exit /b 1
)

:: Run the command
call npm start

:: Return to the original directory
popd
