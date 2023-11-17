An demo project of Kotlin Multiplatform Notes app with shared Android/Desktop/iOS Compose UI.

This project inspired by this [ToDo example project](https://github.com/IlyaGulya/TodoAppDecomposeMviKotlin) and reuse 
its idea and UI but with different technology stack.

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…

Libraries used:

- [Compose Multiplatform](https://jb.gg/compose) for UI
- [Voyager](https://github.com/adrielcafe/voyager) - Navigation & Screen Model(a.k.a ViewModel) library
- [Koin](https://github.com/InsertKoinIO/koin) - DI Framework
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Asynchronous programming.
- [UUID](https://github.com/benasher44/uuid) - Multiplatform UUID implementation
- [DateTime](https://github.com/Kotlin/kotlinx-datetime) - A multiplatform Kotlin library for working with date and time.

There are few modules:

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

* `/data` contains data layer of the application: core models & repositories.

### Run your application on Android

- Create an Android virtual device.
- In the list of run configurations, select composeApp.
- Choose your Android virtual device and click Run.

### Run your application on iOS

- Launch Xcode in a separate window. The first time, you may also need to accept its license terms and allow it to perform some necessary initial tasks.
- In the list of run configurations, select iosApp and click Run.
- If you don't have an available iOS configuration in the list, add a new iOS simulated device.

### Run your application on Desktop

You can run the application on the desktop as follows:

- Go to `composeApp/src/desktopMain/kotlin`.
- Open the `main.kt` file and find the `main()` function:
- Click the green run icon in the gutter next to the `main()` function:
- 
Once you run your desktop application, a new *Run Configuration* is created. You can use it from now on to run the desktop application.
