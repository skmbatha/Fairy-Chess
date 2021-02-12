if not DEFINED IS_MINIMIZED set IS_MINIMIZED=1 && start "" /min "%~dpnx0" %* && exit
cd game_file_system\Eclipse_Version2 GUI\src
java FairyChess
exit