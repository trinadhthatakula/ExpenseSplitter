# **Expense Splitter - Kotlin Multiplatform app Targeting only web**

Completely build using kotlin and Compose Multiplatform.

**How to use**? 
(web app is now live at https://rupee-money-manager.web.app/)
1. start by adding persons
2. Add respective expenses to each person
3. Rest will be handled by the code you can see who owes who at the bottom

**Things under development**
1. Expense History
2. Delete past Expenses
3. Expense Description and other details

**Known issues:**
1. The app is not responsive and is not suitable for mobile devices as Soft keyboard is not supported in compose web yet and here is a [link to the issue](https://github.com/JetBrains/compose-multiplatform/issues/3943)
2. Certain elements on the page are not scrollable and are not visible on smaller screens, they are meant to be scrollable but right now scroll behaviour doesn't work.



**Development/Deployment Instructions**:

This is a Kotlin Multiplatform project targeting Web.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html),
[Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform),
[Kotlin/Wasm](https://kotl.in/wasm/)…

**Note:** Compose/Web is Experimental and may be changed at any time. Use it only for evaluation purposes.
We would appreciate your feedback on Compose/Web and Kotlin/Wasm in the public Slack channel [#compose-web](https://slack-chats.kotlinlang.org/c/compose-web).
If you face any issues, please report them on [GitHub](https://github.com/JetBrains/compose-multiplatform/issues).

You can open the web application by running the `:composeApp:wasmJsBrowserDevelopmentRun` Gradle task.
