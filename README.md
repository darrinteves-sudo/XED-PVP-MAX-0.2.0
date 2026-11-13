# XED PvP MAX 0.2.0

Client-side Fabric mod project for Minecraft Java 1.21.11. The build uses Java 21, Fabric Loader 0.18.6, Fabric API 0.141.4+1.21.11, Yarn 1.21.11+build.6, and Fabric Loom 1.17.12.

## Implemented in 0.2.0

- In-game settings screen opened with **Right Shift** (rebindable under Options → Controls).
- FPS HUD toggle.
- Combat status HUD toggle (player health and armor points).
- Coordinates HUD toggle.
- Compact HUD toggle.
- Settings saved in `config/xed-pvp-max.properties`.
- Black/red interface styling.

These are client-side display/configuration features. This build does not yet implement custom particles, low-fire rendering, keystrokes, target tracking, or resource-pack assets.

## Build

Requires Java 21 and Gradle 9.2.1 or a compatible Gradle 9.x installation:

```sh
gradle build
```

The mod JAR is created at `build/libs/xed-pvp-max-0.2.0.jar`.

## Android build without Termux

The included GitHub Actions workflow can build the JAR in the cloud:
1. Create a GitHub repository and upload this project folder's contents.
2. Open the repository's **Actions** tab.
3. Select **Build XED PvP MAX** and choose **Run workflow**.
4. When the run completes, open it and download the `xed-pvp-max-0.2.0` artifact.
5. Extract the JAR and place it in the `mods` folder of your Zalith 1.21.11 Fabric instance.

Keep your 0.1.0 JAR backed up until you confirm 0.2.0 launches. Do not put both versions in `mods` at the same time.
