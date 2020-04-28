# About
This project is to extend (Java/Groovy) core classes to improve productivity.

# Usage
How to use it in your project:

Maven:
```xml
<dependency>
  <groupId>com.intellisrc</groupId>
  <artifactId>groovy-extend</artifactId>
  <version>VERSION</version>
</dependency>
```
Gradle:
```groovy
dependecies {
    implementation 'com.intellisrc:groovy-extend:VERSION'
    // or extended annotation:
    implementation compile group: 'com.intellisrc', name: 'groovy-extend', version: 'VERSION'
}
```
In which `VERSION` is this package version, for example: `2.5.6.4`, `2.5.11.0`. The first 3 numbers in the version are
related to you groovy version target. So if you want to extend groovy `2.5.9`, you need to use `2.5.9.0`. The last 
number in the version is this library revision number. These are current versions:

* 2.5.6.4
* 2.5.7.0
* 2.5.8.0
* 2.5.9.0
* 2.5.10.0
* 2.5.11.0

## Date & Time

`Date` class should not longer be used. Because of that, we extended `Date` class to make it easier to change it to `LocalDate` and `LocalDateTime`:

### Date Methods

* `toZonedDateTime` : Using timezone
* `toLocalDateTime`
* `toLocalDate`
* `toLocalTime`

### LocalDate / LocalDateTime / LocalTime Methods

* `toDate` : Only used for external codes which still rely on Date
* `toDateTime` : Convert to DateTime
* `format` : use DateTimeFormatter in an easy way

#### LocalDateTime only:
* `YMDHms` : Return "yyyy-MM-dd HH:mm:ss"
* `getYMDHms("","")` : Return "yyyyMMddHHmmss"
* `YMDHmsS` : Return "yyyy-MM-dd HH:mm:ss.SSS"
* `YMDHm` : Return "yyyy-MM-dd HH:mm"
* `toMillis` : Return epoch milliseconds (long)
* `fromMillis` : (static) Convert epoch milliseconds into LocalDateTime

#### LocalDate only:
* `YMD` : Return "yyyy-MM-dd"
* `YY` : Return "yy"
* `MM` : Return "MM"
* `DD` : Return "dd"

#### LocalTime only:
* `HHmmss` : Return "HH:mm:ss"
* `getHHmmss("")` : Return "HHmmss"
* `HHmm` : Return "HH:mm"

## Files

These extensions provide faster ways to deal with files. Setting permissions and performing operations are made easier.

### File Methods

#### Performance

* `lines` : Get total lines in a file (faster way)
* `eachFileAsync` : Iterate through all files without having to read the whole directory first
* `eachFileMatchAsync` : Iterate files inside directory using glob search or FileFilter
* `listFiles` : Using glob search (Async) [@since 2.5.6.2]
* `deleteFiles` : Delete files using glob search or FileFilter (Async) [@since 2.5.6.2]

#### Permissions

* `permissions` : Will return PosixFilePermissions
* `ownerPermissions` : set owner permissions
* `groupPermissions` : set group permissions
* `othersPermissions` : set others permissions
* `clearPermissions`
* `clearOwnerPermissions`
* `clearGroupPermissions`
* `clearOthersPermissions`
* `setReadable`
* `setWritable`
* `setExecutable`
* `setOwnerReadable`
* `setOwnerWritable`
* `setOwnerExecutable`
* `setGroupReadable`
* `setGroupWritable`
* `setGroupExecutable`
* `setOthersReadable`
* `setOthersWritable`
* `setOthersExecutable`

#### Operations

* `copyTo`
* `moveTo`
* `linkTo`           : Create a link from a file. Its argument is the link to create. An additional argument is to specify if link is hard or not.
* `hardLinkOrCopyTo` : Will try to create a hardLink, but if its in another device (which is not allowed), it will copy it.

#### Network

These extensions help to deal with network interfaces.

### NetworkInterface Methods

* `localIP` : Get's the first IP address that is not 127.x.x.x
* `isLocalAddress` : Test if local device contains such IP address
* `isPortAvailable` : Test if port is available
* `getIPStartWith` : Retrieves the first IP address that starts with...
* `getIPinNetwork` : Retrieves the first IP address that is in Network X
* `getFreePort` : Retrieves an available port.
* `getInet4Addresses` : Retrieves all IP addresses registered in localhost

#### String

Extends String methods that are commonly used and easiest ways to convert them.

* `alphaNumeric` : returns only alphanumeric characters
* `padRight` : pad to the right with spaces
* `padLeft` : pad to the left with spaces
* `appendRandomNum` : Useful to create random strings, e.g: 'apple1234'

#### Conversion

* `toInet4Address`
* `toInet6Address`
* `toInetAddress`
* `getQueryMap` : Convert Query String to Map
* `toDateTime`  : Convert String into LocalDateTime (auto detect format)
* `toDate`      : Convert String into LocalDate (auto detect format)
* `toTime`      : Convert String into LocalTime (auto detect format)

## Other

### Map Methods

* `toQueryString` : Convert Map to Query String e.g : "a=1&b=2"

### Random Methods

* `range` : Specify a range to get a random number

### Collection Methods

* `random` : Get one or more random element from a collection (two implementations).