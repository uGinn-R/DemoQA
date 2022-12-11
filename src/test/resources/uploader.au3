Local $hWnd = WinWait("[CLASS:#32770]", "", 5);
Sleep(200);
ControlSetText($hWnd,"","[CLASS:Edit; INSTANCE:1]","c:\Users\User\IdeaProjects\DemoQA\src\test\resources\dummyUpload.png");
Sleep(200);
ControlClick($hWnd,"","[CLASS:Button; INSTANCE:1]");
