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

The GitHub Actions workflow verifies every pull request and every push to `main`
(unit tests, lint and debug build). After a successful `main` build, it creates a
signed AAB and deploys it to Google Play. The deployment is protected by the
`google-play-production` GitHub Environment; configure required reviewers there
before merging a production release.

### GitHub Actions secrets

Add these **repository secrets** in
`Settings` → `Secrets and variables` → `Actions` → `New repository secret`:

| Secret | Value |
| --- | --- |
| `ANDROID_KEYSTORE_BASE64` | Base64 of the upload keystore (`base64 -w 0 release.keystore` on Linux/macOS; `[Convert]::ToBase64String([IO.File]::ReadAllBytes('release.keystore'))` in PowerShell). |
| `ANDROID_KEYSTORE_PASSWORD` | Password of that keystore. |
| `ANDROID_KEY_ALIAS` | Alias of the upload key. |
| `ANDROID_KEY_PASSWORD` | Password of that alias. |
| `GOOGLE_PLAY_SERVICE_ACCOUNT_JSON` | Complete JSON key of the service account authorized in Google Play Console with release permissions. |

In `Settings` → `Environments`, create `google-play-production` and add the
production approvers. A manual run can choose the `internal` track first; pushes
to `main` deploy to `production` after the environment approval.

## Signed release

1. Copy `keystore.properties.example` to `keystore.properties`.
2. Set the keystore path and credentials in `keystore.properties`. This file and all keystores are ignored by Git.
3. Run:

```bash
./gradlew bundleRelease
```

Upload `app/build/outputs/bundle/release/app-release.aab` to Google Play. Do not publish an unsigned artifact; configure Play App Signing and increase `versionCode` for every subsequent release.
