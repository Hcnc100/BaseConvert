# BaseConvert

Android app to convert numbers between bases 2 and 36, including a step-by-step explanation.

## Requirements

- JDK 17
- Android SDK Platform 36 and Build Tools 36.0.0

## Verification

Run these commands before merging or releasing:

```bash
./gradlew test lint assembleDebug
```

The CI workflow runs the same checks for pull requests and pushes.

## Signed release

1. Copy `keystore.properties.example` to `keystore.properties`.
2. Set the keystore path and credentials in `keystore.properties`. This file and all keystores are ignored by Git.
3. Run:

```bash
./gradlew bundleRelease
```

Upload `app/build/outputs/bundle/release/app-release.aab` to Google Play. Do not publish an unsigned artifact; configure Play App Signing and increase `versionCode` for every subsequent release.
