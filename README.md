## WaterProgress

### screenshot

   <table align="center">
       <tr>
           <td><img src="screenshot/alter.gif"/></td>
           <td><img src="screenshot/water.gif"/></td>
       </tr>
   </table>


### How to
- gradle

   1.Add it in your root build.gradle at the end of repositories.
   ```bash
   allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
   ```
    Step 2. Add the dependency.
    ```bash
     dependencies {
        compile 'com.github.firefecker:WaterProgress:0.1.0'
     }

    ```

 - maven

    Step 1. Add the JitPack repository to your build file.
    ```bash
    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>
    ```
    Step 2. Add the dependency.
    ```bash
    	<dependency>
    	    <groupId>com.github.firefecker</groupId>
    	    <artifactId>WaterProgress</artifactId>
    	    <version>0.1.0</version>
    	</dependency>

    ```