`JIconButton` is a Java Swing component designed to display an [Icon](https://docs.oracle.com/en/java/javase/24/docs/api/java.desktop/javax/swing/Icon.html)
and to automatically resize the Icon to fit within the button's bounds. It can be used with the following subclasses of
the Icon class:

* [FontIcon](https://github.com/kordamp/ikonli/blob/master/core/ikonli-swing/src/main/java/org/kordamp/ikonli/swing/FontIcon.java) from [ikonli](https://github.com/kordamp/ikonli)
* [ImageIcon](https://docs.oracle.com/en/java/javase/24/docs/api/java.desktop/javax/swing/ImageIcon.html)

## Table of Contents

* [Installation](https://github.com/Valkryst/JIconButton#installation)
    * [Gradle](https://github.com/Valkryst/JIconButton#-gradle)
    * [Maven](https://github.com/Valkryst/JIconButton#-maven)
    * [sbt](https://github.com/Valkryst/JIconButton#-scala-sbt)

## Installation

JIconButton is hosted on the [JitPack package repository](https://jitpack.io/#Valkryst/JIconButton)
which supports Gradle, Maven, and sbt.

### ![Gradle](https://i.imgur.com/qtc6bXq.png?1) Gradle

Add JitPack to your `build.gradle` at the end of repositories.

```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

Add JIconButton as a dependency.

```
dependencies {
	implementation 'com.github.Valkryst:JIconButton:2025.10.1'
}
```

### ![Maven](https://i.imgur.com/2TZzobp.png?1) Maven

Add JitPack as a repository.

``` xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
Add JIconButton as a dependency.

```xml
<dependency>
    <groupId>com.github.Valkryst</groupId>
    <artifactId>JIconButton</artifactId>
    <version>2025.10.1</version>
</dependency>
```

### ![Scala SBT](https://i.imgur.com/Nqv3mVd.png?1) Scala SBT

Add JitPack as a resolver.

```
resolvers += "jitpack" at "https://jitpack.io"
```

Add JIconButton as a dependency.

```
libraryDependencies += "com.github.Valkryst" % "JIconButton" % "2025.10.1"
```