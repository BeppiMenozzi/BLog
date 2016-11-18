# B-Log

Helper when debugging.
With a single code line can show any kind of datatype in the logcat window, replicate that data in a toast and/or in a dialog window.
Additionally, adds the running method name taken from the stack trace.

### Setup (Gradle)
Add **maven { url "https://jitpack.io" }** in project's build.gradle file.

Add **compile 'com.github.BeppiMenozzi:B-Log:0.0.2'** in module's build.gradle file.

### Usage
Simply add<br>

    BLog.d();
    
<br> to monitor your advancement as if it was a breakpoint.

Use <br>
    
    BLog.init();
    
<br> to configure it and pass the Context to show toasts and popups in a very easy and fast way.

Use <br>

    BLog.d(...);

<br> to write anything to Log.d, and/or toast, and/or popup dialog.

### Author

[Beppi Menozzi](http://www.beppi.it)

### License

Copyright 2016 Beppi's App Studios

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

