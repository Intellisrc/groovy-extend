# About
This project is to extend (Java/Groovy) core classes to improve productivity.

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
* `eachFileAsync`
* `eachFileMatchAsync`

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
* `linkTo`
* `hardLinkOrCopyTo`

#### Network

These extensions help to deal with network interfaces.

### NetworkInterface Methods

* `localIP` : Get's the first IP address that is not 127.x.x.x
* `isLocalAddress` : Test if local device contains such IP address
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
* `toDateTime`
* `toDate`
* `toTime`

## Other

### Map Methods

* `toQueryString` : Convert Map to Query String e.g : "a=1&b=2"

### Random Methods

* `range` : Specify a range to get a random number