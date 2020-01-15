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
        file << "8 More lines\n"
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
        when :
            FileExt.clearOwnerPermissions(file)
        then :
            assert ! FileExt.getPermissions(file).containsAll([
                    PosixFilePermission.OWNER_READ,
                    PosixFilePermission.OWNER_WRITE
            ])
    }
    def "Set individual permissions"() {
        setup:
            FileExt.clearPermissions(file)
            FileExt.setReadable(file, true, true, true)
            FileExt.setWritable(file, true, true, true)
            FileExt.setExecutable(file, true, true, true)
        expect:
            assert FileExt.getPermissions(file).containsAll([
                    PosixFilePermission.OWNER_READ,
                    PosixFilePermission.OWNER_WRITE,
                    PosixFilePermission.OWNER_EXECUTE,
                    PosixFilePermission.GROUP_READ,
                    PosixFilePermission.GROUP_WRITE,
                    PosixFilePermission.GROUP_EXECUTE,
                    PosixFilePermission.OTHERS_READ,
                    PosixFilePermission.OTHERS_WRITE,
                    PosixFilePermission.OTHERS_EXECUTE
            ])
    }
    
    def "Copy File"() {
        setup:
            File tmpFile = new File(file.parentFile, "file.ext.copy.test")
            if(tmpFile.exists()) {
                tmpFile.delete()
            }
            FileExt.copyTo(file, tmpFile)
        expect:
            assert tmpFile.exists()
        cleanup:
            tmpFile.delete()
    }
    
    def "Move File"() {
        setup:
            File tmpFile = new File(file.parentFile, "file.ext.copy.test")
            if(tmpFile.exists()) {
                tmpFile.delete()
            }
            FileExt.moveTo(file, tmpFile)
        expect:
            assert tmpFile.exists()
            assert ! file.exists()
        cleanup:
            tmpFile.delete()
    }
    
    def "Hard Link or copy"() {
        setup:
            File tmpFile = new File(file.parentFile, "file.ext.copy.test")
            if(tmpFile.exists()) {
                tmpFile.delete()
            }
            FileExt.hardLinkOrCopyTo(file, tmpFile)
        expect:
            assert tmpFile.exists()
            assert file.exists()
        cleanup:
            tmpFile.delete()
    }
    
    def "List files and delete using string filters"() {
        setup:
            File tmpDir = new File(System.getProperty("java.io.tmpdir"))
            (1..10).each {
                new File(tmpDir, "test-file-${it}.txt").text = "test"
            }
            (1..10).each {
                new File(tmpDir, "test-file-${it}.tmp").text = "test"
            }
        expect:
            assert FileExt.listFiles(tmpDir, "*.txt").size() == 10
            assert FileExt.listFiles(tmpDir, "*.tmp").size() == 10
        when:
            assert FileExt.deleteFiles(tmpDir, "*.txt")
        then:
            assert FileExt.listFiles(tmpDir, "*.txt").size() == 0
            assert FileExt.listFiles(tmpDir, "*.tmp").size() == 10
        cleanup:
            FileExt.deleteFiles(tmpDir, "*.tmp")
            tmpDir.deleteDir()
    }
    
    def "List files and delete using FileFilter"() {
        setup:
            File tmpDir = new File(System.getProperty("java.io.tmpdir"))
            (1..10).each {
                new File(tmpDir, "test-file-${it}.tmp").text = "test"
            }
            FileFilter filter = { File f -> f.name.endsWith(".tmp") } as FileFilter
        expect:
            assert FileExt.listFiles(tmpDir, "*.tmp").size() == 10
        when:
            int count = 0
            FileExt.eachFileMatchAsync(tmpDir, filter) {
                File item ->
                    println item
                    count++
            }
        then:
            assert count == FileExt.listFiles(tmpDir, "*.tmp").size()
        when:
            assert FileExt.deleteFiles(tmpDir, filter)
        then:
            assert FileExt.listFiles(tmpDir, "*.tmp").size() == 0
        cleanup:
            tmpDir.deleteDir()
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
