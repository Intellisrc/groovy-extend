package com.intellisrc.groovy

import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.attribute.PosixFilePermission
/**
 * @since 2/17/18.
 */
class FileExtTest extends Specification {
    File file
    def setup() {
        file = new File(System.getProperty("java.io.tmpdir") + File.separator + "file.ext.test")
        file << "1 Some content\n"
        file << "2 More lines\n"
        file << "3 More lines\n"
        file << "4 More lines\n"
        file << "5 More lines\n"
        file << "6 More lines\n"
        file << "7 More lines\n"
        file << "8 More lines"
    }
    def cleanup() {
        file.delete()
    }
    def "Counting number of lines"() {
        expect:
            assert FileExt.getLines(file) == 8
    }
    def "Testing clear permissions"() {
        setup :
            FileExt.clearPermissions(file)
        expect :
            assert FileExt.getPermissions(file).empty
    }
    def "Only owner 600"() {
        setup :
            FileExt.clearGroupPermissions(file)
            FileExt.clearOthersPermissions(file)
            FileExt.ownerPermissions(file, true, true, false)
        expect :
            assert FileExt.getPermissions(file).containsAll([
                    PosixFilePermission.OWNER_READ,
                    PosixFilePermission.OWNER_WRITE
            ]) &&! FileExt.getPermissions(file).contains(
                    PosixFilePermission.OWNER_EXECUTE
            ) &&! FileExt.getPermissions(file).contains(
                    PosixFilePermission.GROUP_READ
            )
    }
    /**
     * Read a directory asynchronously
     * Usage: new File("some/path").eachFileAsync {
     *     File file ->
     *
     * }
     */
    def "Test Async Dir"() {
        setup :
            File tmpDir = new File(System.getProperty("java.io.tmpdir"))
            int x = 0
        when:
            FileExt.eachFileAsync(tmpDir, {
                println it.name
                x++
            })
        then:
            noExceptionThrown()
            assert x > 0
    }

    /**
     * Read a directory asynchronously while matching a file
     * Usage: new File("some/path").eachFileMatchAsync("*.{jpg,png}") {
     *     File file ->
     *
     * }
     */
    def "Test Async Dir Match"() {
        setup :
            File tmpDir = new File(System.getProperty("java.io.tmpdir"))
            File matchFile = new File(tmpDir, "test.match")
            matchFile.text = "Just a test"
            int x = 0
        when:
            FileExt.eachFileMatchAsync(tmpDir, "*.match", {
                println it.name
                x++
            })
        then:
            noExceptionThrown()
            assert x > 0
        cleanup:
            matchFile.delete()
    }

    def "Exists not empty"() {
        setup:
            File tmpFile = Files.createTempFile("test", "empty").toFile()
        when:
            tmpFile.createNewFile()
        then:
            assert !FileExt.existsAndNotEmpty(tmpFile)
        when:
            tmpFile.text = "\n"
        then:
            assert FileExt.existsAndNotEmpty(tmpFile)
        when:
            tmpFile.text = "not empty"
        then:
            assert FileExt.existsAndNotEmpty(tmpFile)
        cleanup:
            tmpFile.delete()
    }

    def "is empty"() {
        setup:
            File tmpFile = Files.createTempFile("test", "empty").toFile()
        when:
            tmpFile.createNewFile()
        then:
            assert FileExt.isEmpty(tmpFile)
        when:
            tmpFile.text = "\n"
        then:
            assert !FileExt.isEmpty(tmpFile)
        when:
            tmpFile.text = "not empty"
        then:
            assert !FileExt.isEmpty(tmpFile)
        cleanup:
            tmpFile.delete()
    }
}
