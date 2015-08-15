# gk-transport

Installation for version 1.0
1. Checkout branch Version_1.0
2. import as gradle project in eclipse.
3. run gradle task : clean war
4. extract lib folder from generated war.
5. Make sure gk-transport-ui-1.0.jar, gk-transport-interface-1.0.jar, gk-transport-core-1.0.jar are in lib folder.
6. paste the lib folder in c:/gk-transport.
7. Now there are two ways to run the application.
	a)Run command : C:\Windows\System32\javaw.exe -cp "C:/gk-transport/lib/*" com.gk.enterprise.transport.desktop.DesktopApplication.
	b)	Create shortcut for javaw.exe;
		Now open properties of this newly created shortcut.
		Paste the command "C:\Windows\System32\javaw.exe -cp "C:/gk-transport/lib/*" com.gk.enterprise.transport.desktop.DesktopApplication" in Target textbox.
For reference the shortcut can be found in branch Version_1.0