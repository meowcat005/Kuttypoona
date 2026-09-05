# Kuttypoona — Phase 1 (UI Prototype)

Native Android app, Kotlin + Jetpack Compose. This is Phase 1 from the dev brief:
5 screens (Today, Deep Time, Insights, My Buddy, Settings) wired together with
bottom navigation, using dummy/fake data. No real permissions requested yet.

Note: the app displays as "Kuttypoona" but the internal code package is still
`com.lumeo.app` (this is just an internal technical ID, invisible to users —
renaming it isn't necessary and was skipped to avoid touching every file).

## Setup — entirely from your Android phone

### 1. Install apps
- **Termux** (from F-Droid, not Play Store — Play Store version is outdated)
- **Acode** (Play Store) — code editor, optional but nicer than editing in Termux

### 2. In Termux, set up git and get this project onto your phone
```
pkg update && pkg upgrade
pkg install git unzip
```
Unzip this project folder wherever Termux can see it (e.g. `~/storage/downloads/kuttypoona`
after running `termux-setup-storage`), then:
```
cd ~/storage/downloads/kuttypoona
git init
git add .
git commit -m "Phase 1: UI prototype skeleton"
```

### 3. Create a GitHub repo
On github.com (mobile browser or GitHub app), create a new **empty** repo called `kuttypoona`.
Do NOT initialize it with a README (you already have one).

### 4. Push from Termux
```
git remote add origin https://github.com/YOUR_USERNAME/kuttypoona.git
git branch -M main
git push -u origin main
```
It'll ask for your GitHub username and a **Personal Access Token** (not your password —
GitHub removed password auth for git operations). Generate one at:
github.com → Settings → Developer settings → Personal access tokens → generate new token
(give it "repo" scope).

### 5. Watch it build
Go to your repo on GitHub → **Actions** tab. You'll see "Build Kuttypoona APK" running
automatically (triggered by your push). Takes ~2-4 minutes.

### 6. Get your APK
Once the Actions run finishes (green check ✅), click into it → scroll to **Artifacts** →
download `kuttypoona-debug-apk`. It's a zip containing `app-debug.apk`. Extract it, then
open the APK file on your phone to install (you may need to allow "install from unknown
sources" for your browser/files app once).

## Making changes after this

Edit files in Acode → in Termux run:
```
git add .
git commit -m "describe what you changed"
git push
```
Every push re-triggers the Actions build automatically. New APK appears in Artifacts
each time — no laptop, no Android Studio, ever needed.

## What's next (per the dev brief's phased plan)

- Phase 2: replace dummy data with real `UsageStatsManager` tracking
- Phase 3: app limits + warning notifications
- Phase 4: real Deep Time timer + XP persistence (Room database)
- Phase 5: floating companion overlay (hardest part — do this last)

Come back here once Phase 1 builds and looks right on your phone — we'll do Phase 2 next.
