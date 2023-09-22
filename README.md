
# LittleBigPlanet Speedrun Utility

LBP-SRUtil (LittleBigPlanet Speedrun Utility) is a Java application designed for JRE11 and newer versions. 

It includes a game file mod that establishes a UDP server within LBP1, enabling fast data communication between the desktop app and the game. The utility primarily features two functions. Firstly, it provides an input viewer that generates a visual representation of the controller with real-time input information displayed. Secondly, it offers a work-in-progress tool for tool-assisted speedruns (TAS). 

Additionally, LBP-SRUtil includes a built-in eboot elf patcher for PS3 and an `imported_patch.yml` file for RPCS3.


## Warning: Risks associated with the mod

To enable this mod's functionality, the eboot patch grants unrestricted memory access and the ability to call any eboot functions from any script. While the mod itself is not malicious, there are risks associated with using script mods from untrustworthy sources or when in a P2P game party where someone might try to run a script.

The risks associated with the mod are extremely low, as only a few people are able to write scripts, let alone exploit this vulnerability. Not saying it can't happen, just very unlikely. 
## How to mitigate risks

These risks exist only when the eboot/game patch is active. To mitigate the risks, be careful when joining or allowing others to join your session, especially if you don't trust them. 

Replace the eboot with an unpatched version or disable the RPCS3 patch and restart the game or console before using script mods or joining players you don't trust.
## Installation

PC:
- If you don't already have JRE11 or up on your system, make sure to install that first. You can now start the .JAR file. If it asks what program to use, navigate to the JRE11 folder and select the `java.exe` file.
- The app uses the controller skin format from racman, so to get some basic skins go to https://github.com/MichaelRelaxen/racman/tree/master/controllerskins and get the ones you like. Place them in a folder named `controllerskins` in the same directory as the `LBP-SRUtil.jar` file. 
    
    *The file setup should look like this*
    ```bash
    LBP-SRUtil/
    ├── LBP-SRUtil.jar
    └── controllerskins/
        └── DS3 Black/
            ├── skin.txt
            └── skin.png
    ```
***
\
Game files:
- Place the `boot.map` file in `\dev_hdd0\game\*TITLEID*\USRDIR\gamedata\alear\boot.map`
- Place the `patch9.farc` file in `\dev_hdd0\game\*TITLEID*\USRDIR\patch9.farc`
***
\
PS3:
- Copy over the `EBOOT.BIN` file from `\dev_hdd0\game\*TITLEID*\USRDIR\EBOOT.BIN` to your computer, use RPCS3 or TrueAncestor Resigner to decrypt it to an `ELF` file.
- Use the `Patch .ELF file` option in the LBP-SRUtil app, to apply the patch.
- Use TrueAncestor Resigner to resign it once more to a .BIN file.
- Place the output .BIN file back into `\dev_hdd0\game\*TITLEID*\USRDIR\EBOOT.BIN`
- **Highly recommend keeping a backup of the original .BIN file**
***
\
RPCS3:
- Place the `imported_patch.yml` file in `\RPCS3\patches\imported_patch.yml` or merge it with any pre existing ones.
- In RPCS3 navigate to `Manage>Game Patches` and in the Patch Manager go to `LittleBigPlanet>*TITLEID* v.01.30`, check "Alear (Minimal)" then apply and save.
## Usage
- Launch LBP1, once in game press start and enter the settings menu, a new settings page should be present at the top, if the installation was done correctly. **"SRUtil Settings"**
- Under those settings you can configure the UDP server as well as turn it on and off.
- In the LBP-SRUtil app go over to the settings tab and make sure the port matches with the in game configuration. 
- Press the start button in the app, at the bottom it will show if its running or not.
- If it says "Running Active" the connection is established.
- Go back to the Input viewer tab and select a controller skin for the desired player, and start the viewer.

(Note: if doing this over the network on a real PS3 and not RPCS3, the ip must be changed from 0.0.0.0 to the IPV4 of the PS3 in the app, and to the IPV4 of your PC in the game)
## Credits

- [@ennuo](https://github.com/ennuo) providing the in game UDP server/listener as well as sharing much forbidden knowledge.
- [@bordplate](https://github.com/MichaelRelaxen) for giving the 'ok' on using some of the assets from the 'racman' project.
