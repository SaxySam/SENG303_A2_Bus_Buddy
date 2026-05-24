# Bus Buddy 🚌

**Bus Buddy** is an Android companion app that makes public transport more entertaining, social, and useful during the ride. Riders join their bus lobby by scanning a QR code or entering a Bus ID, play a short side-scrolling bus game, contribute scores to route leaderboards, and get helpful route tools so they do not miss their stop.

> Designed hypothetically for Christchurch Metro riders as a SENG303 mobile app project.

---

## Overview

Bus Buddy turns the idle minutes of a bus trip into a shared ride-sized game session. Passengers can join the lobby for their current bus, compete individually, contribute points to their route, and compare scores across major Metro routes.

The app is built around three main ideas:

1. **Join quickly** using a bus QR code or manual Bus ID entry.
2. **Play lightly** with a simple side-scrolling runner game controlled by tilt or touch.
3. **Stay informed** with route stop lists, proximity alerts, and quick links to Metro services.

---

## Problem

Long public transport journeys can feel boring, disconnected, and easy to tune out from. Bus Buddy aims to make commuting more engaging by giving passengers something social and interactive to do while still helping them stay aware of their journey.

The goal is to increase enjoyment, encourage friendly competition, and make Metro rides feel more connected.

---

## Key Features

### Bus Lobby Entry

- Scan a QR code on the bus to join that bus lobby.
- Enter a Bus ID manually when QR scanning is unavailable.
- Join route-based lobbies for shared scoring.
- Connect with onboard Wi-Fi, mobile data, or use offline mode.

### Side-Scrolling Bus Game

- Play as the bus route you are currently riding.
- Avoid obstacles while travelling toward the next stop.
- Tilt controls using the gyroscope.
- Optional touch controls for accessibility and preference.
- Score based on distance travelled in metres.
- Game ends on collision and displays the final score plus all-time high score.

### Leaderboards

- Weekly route leaderboard for major Metro routes.
- Individual contributor leaderboard per route.
- All-time high scores for users and routes.
- Offline score storage with upload when a stable connection returns.
- Score sharing through Android share intents.

### Route Tools

- View major bus routes and stops in the app.
- Track proximity to the next stop while playing.
- Display current bus speed using device sensors where supported.
- Show a toast or alert when approaching a flagged stop.
- Open Metro route updates through a URL intent.
- Open Metro Card top-up through a URL intent.

---

## User Journey

1. The rider boards a bus and finds the QR code near the front.
2. They open Bus Buddy and scan the QR code.
3. If scanning is unavailable, they enter the Bus ID manually.
4. They connect using bus Wi-Fi, mobile data, or continue offline.
5. They play the side-scrolling bus game.
6. Their score contributes to the current bus route leaderboard.
7. They can view route rankings, personal highs, and weekly contributors.
8. They can check route stops, Metro updates, or top up their Metro Card.
9. The app alerts them when they are approaching their stop.

Tiny commute goblin defeated. Passenger morale restored.

---

## Supported Routes

Bus Buddy focuses on the major colour-coded Christchurch Metro routes.

| Route | Name / Direction | Colour | Hex |
|---|---|---:|---|
| Orbiter | Orbiter | Green / Lime | `#79BC43` |
| 1 | Rangiora / Cashmere | Light Blue | `#3EBCED` |
| 3 | Airport / Sumner | Purple | `#554588` |
| 5 | Rolleston / New Brighton | Yellow / Gold | `#FFC20E` |
| 7 | Halswell / Queenspark | Orange | `#F37021` |
| 8 | Port-to-Port, Airport / Lyttelton | Pink | `TBD` |


---

## Tech Stack

This project is intended for Android development.

- **Language:** Kotlin
- **Platform:** Android
- **UI:** Jetpack Compose / Material 3
- **Architecture:** MVVM with clean separation between UI, domain, and data layers
- **State:** Kotlin Flow / StateFlow
- **Storage:** Room or local persistence for usernames, high scores, offline route data, and pending score uploads
- **Networking:** Retrofit, OkHttp, Ktor, or equivalent HTTP client
- **Device APIs:** Camera, Location, Gyroscope, Accelerometer, Network state
- **Testing:** JUnit, AndroidX Test, Compose UI testing
- **Build:** Gradle Kotlin DSL

Adjust this list to match the exact libraries used in the final implementation.

---

## Permissions

Bus Buddy may request the following Android permissions:

```xml
<uses-permission android:name="android.permission.CAMERA" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
```

### Permission Purpose

| Permission | Used For |
|---|---|
| Camera | Scanning bus QR codes |
| Location | Showing proximity to the next stop and route progress |
| Internet | Syncing lobbies, leaderboards, and route information |
| Network State | Detecting offline mode and syncing later |
| Notifications | Optional stop approach alerts |
| Gyroscope / Accelerometer | Tilt controls and speed-related gameplay features |

Sensor access does not require manifest permissions, but availability should be checked at runtime.

---

## Getting Started

### Prerequisites

- Android Studio
- JDK 17 or newer
- Android SDK installed through Android Studio
- A physical Android device or emulator

### Clone the Repository

```bash
git clone <REPO_URL> bus-buddy
cd bus-buddy
```

### Open in Android Studio

1. Open Android Studio.
2. Select **Open**.
3. Choose the Bus Buddy project folder.
4. Let Gradle sync.
5. Select an emulator or connected Android device.
6. Run the app.
