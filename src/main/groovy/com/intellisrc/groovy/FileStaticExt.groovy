package com.intellisrc.groovy

import groovy.transform.CompileStatic

/**
 * Extending `File` static class
 * @since 2022/04/11.
 */
@CompileStatic
class FileStaticExt {
    /**
     * Return the User's home directory
     * @return
     */
    static File getHomeDir(final File self) {
        return new File(System.getProperty('user.home'))
    }
    /**
     * Returns the root path of the application
     * @return
     */
    static File getUserDir(final File self) {
        return new File(System.getProperty("user.dir"))
    }
    /**
     * Returns the temporally directory path
     * @return
     */
    static File getTempDir(final File self) {
        return new File(System.getProperty("java.io.tmpdir"))
    }
    /**
     * Alternative to `new File()` which is more flexible
     * Examples:
     *
     * Convert path to home directory:
     * SysInfo.getFile("~/system", "file.txt")
     *
     * Accepts multiple levels and automatically detect when reading from user directory:
     * SysInfo.getFile("resources", "images", "thumbs", "logo.jpg")
     *
     * It accepts File objects and String as well:
     * SysInfo.getFile(someDir, "directory", "file.txt")
     *
     * @param self
     * @param path
     * @return
     */
    static File get(final File self, Object... path) {
        File pathFile
        boolean isFirstInPath = true
        path.each {
            def part ->
                String pathPart
                switch (part) {
                    case File:
                        if (isFirstInPath) {
                            pathPart = (part as File).absolutePath
                        } else {
                            pathPart = (part as File).name
                        }
                        break
                    default:
                        pathPart = part.toString()
                        break
                }
                if (isFirstInPath) {
                    switch (pathPart) {
                        case ~/^\/.*$/:
                            pathFile = new File(pathPart)
                            break
                        case ~/^~.*$/:
                            pathFile = new File(getHomeDir(self), pathPart.replace('~/', ''))
                            break
                        default:
                            pathFile = new File(getUserDir(self), pathPart)
                    }
                } else {
                    pathFile = new File(pathFile, pathPart)
                }
                isFirstInPath = false
        }
        return pathFile
    }

    /**
     * It will create an empty file (creates directories if necessary)
     * @param self
     * @param path
     * @return
     */
    static File create(final File self, Object... path) {
        File file = get(self, path)
        if(! file.parentFile.exists() && file.parentFile.mkdirs()) {
            file.createNewFile()
        }
        return file
    }
}
