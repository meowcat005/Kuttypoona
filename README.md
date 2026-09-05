# Kuttypoona — real-device screen-time app

Kuttypoona is a native Kotlin + Jetpack Compose Android app. This version removes the Phase-1 dummy usage layer and uses Android `UsageStatsManager` for real on-device app-usage data.

## What is implemented
- Real daily and 7-day usage statistics using `UsageStatsManager`
- User-app labels and top-used apps from installed launchable apps
- Screen-interactive event count as an unlock/session metric
- Usage-access permission onboarding and Settings shortcut
- Persistent Deep Time timer that survives app restarts using end-time state
- XP and Buddy level persistence
- Companion overlay permission flow and a lightweight floating Lumi companion
- Foreground service for the companion overlay on Android 8+
- Local settings persistence for goal, companion and Buddy state
- GitHub Actions debug APK build

## Important Android permissions
Android does not show `PACKAGE_USAGE_STATS` as a normal runtime dialog. The user must open **Settings → Usage access** and enable Kuttypoona. The app detects this state and provides a button.

The floating companion similarly requires **Display over other apps** permission. The app opens the correct system settings page; it does not try to bypass Android permission controls.

## Build from GitHub / Termux
This repository intentionally uses the same no-laptop workflow as the original prototype. GitHub Actions installs Gradle and builds the debug APK.

```bash
git add .
git commit -m "Build real Kuttypoona app"
git push
```

Then GitHub → Actions → **Build Kuttypoona APK** → successful run → Artifacts → `kuttypoona-debug-apk`.

## Notes
- Usage statistics are read locally and are not uploaded by this app.
- Android's usage APIs report foreground time; this is not the same as a perfect physical-screen-on measurement.
- The unlock metric is based on `SCREEN_INTERACTIVE` events and is intentionally labelled as an unlock/session metric rather than claiming to be an exact lock-screen unlock count.
- The companion is intentionally simple in this first production-oriented build. It is not an accessibility service and does not attempt to control other apps.
