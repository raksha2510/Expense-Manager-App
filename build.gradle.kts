// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        mavenCentral()

    }
    dependencies {
        classpath ("io.realm:realm-gradle-plugin:10.15.1")


    }
}
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("com.google.gms.google-services")version "4.3.15" apply false
    id ("com.android.library") version "8.1.1" apply false

}