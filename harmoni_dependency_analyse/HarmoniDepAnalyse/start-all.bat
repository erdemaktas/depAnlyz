echo TEST - STARTED
start cmd /c start-test.bat
echo TEST - FINISHED
echo UAT - STARTED
start cmd /c start-uat.bat
echo UAT - FINISHED
echo HOTFIX - STARTED
start cmd /c start-hotfix.bat
echo HOTFIX - FINISHED
pause