# Apigo Starter Project for Android #


A library that gives you access to the powerful Apigo cloud platform from your Android app. 
For more information about Apigo and its features, see [Apigo Website][apigo.id] and [Apigo Documentations][docs].

## Download
1. Download [the latest AAR][library] and copy it on `libs` directory.
2. Define in your `app` module `build.gradle` this code below before `dependencies`

```groovy
repositories{
    flatDir {
        dirs 'libs'
    }
}
```

Then add this code below on `dependencies` :

```groovy
implementation(name: 'Apigo-Android-1.0.0', ext: 'aar')
implementation 'com.squareup.okhttp3:okhttp:3.9.1'
```

## Setup
1. Register first to [Apigo Customer Portal][cloud]
2. Create an application to get `applicationId` and `clientKey`
3. Add this line below to your `Application` class within `onCreate` method to initialize Apigo SDK

```java 
Apigo.initialize(this,
                "YOUR_SERVER_URL",
                "YOUR_APPLICATION_ID",
                "YOUR_CLIENT_KEY");
```
Don't forget to initialize your application class to `AndroidManifest.xml`  

(Optional) You can add some custom setup :

* Enable Apigo SDK debug logging by calling `Apigo.setLogLevel(int);` before initialize SDK.
* Apigo Log Level Mode : `LOG_LEVEL_VERBOSE`, `LOG_LEVEL_DEBUG`, `LOG_LEVEL_INFO`, `LOG_LEVEL_WARNING`, `LOG_LEVEL_ERROR`, `LOG_LEVEL_NONE`

Everything is done!

## License
    Copyright (c) 2018, Apigo.
    All rights reserved.

[apigo.id]:https://apigo.id
[docs]:https://apigo.id/docs/
[cloud]:https://customer.apigo.id/
[library]:https://github.com/apigoid/Apigo-Android/releases/latest
