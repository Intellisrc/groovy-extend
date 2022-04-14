package com.intellisrc.groovy

import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.attribute.PosixFilePermission
/**
 * @since 2/17/18.
 */
class FileExtTest extends Specification {

    def "Counting number of lines"() {
        setup:
            File file = Files.createTempFile("test", "test").toFile()
            file << "1 Some content\n"
            file << "2 More lines\n"
            file << "3 More lines\n"
            file << "4 More lines\n"
            file << "5 More lines\n"
            file << "6 More lines\n"
            file << "7 More lines\n"
            file << "8 More lines\n"
        expect:
            assert FileExt.getLines(file) == 8
        cleanup:
            assert file.delete()
    }
    def "Testing clear permissions"() {
        setup :
            File file = Files.createTempFile("test", "test").toFile()
        when:
            FileExt.ownerPermissions(file, true, true, true)
        then:
            assert FileExt.getPermissions(file).containsAll([
                    PosixFilePermission.OWNER_READ,
                    PosixFilePermission.OWNER_WRITE,
                    PosixFilePermission.OWNER_EXECUTE
            ])
        when:
            FileExt.clearPermissions(file)
        then:
            assert FileExt.getPermissions(file).empty
        cleanup:
            assert file.delete()
    }
    def "Only owner 600"() {
        setup :
            File file = Files.createTempFile("test", "test").toFile()
            FileExt.ownerPermissions(file, true, true, false)
            FileExt.clearGroupPermissions(file)
            FileExt.clearOthersPermissions(file)
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
        cleanup:
            assert file.delete()
    }
    def "Set individual permissions"() {
        setup:
            File file = Files.createTempFile("test", "test").toFile()
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
        cleanup:
            assert file.delete()
    }
    
    def "Copy File"() {
        setup:
            File file = Files.createTempFile("test", "test").toFile()
            file.text = "Hello World"
            File tmpFile = new File(file.parentFile, "file.ext.copy.test")
            FileExt.copyTo(file, tmpFile)
        expect:
            assert tmpFile.exists()
            assert tmpFile.text == "Hello World"
        cleanup:
            assert tmpFile.delete()
            assert file.delete()
    }
    
    def "Move File"() {
        setup:
            File file = Files.createTempFile("test", "test").toFile()
            File tmpFile = new File(file.parentFile, "file.ext.copy.test")
            FileExt.moveTo(file, tmpFile)
        expect:
            assert tmpFile.exists()
            assert ! file.exists()
        cleanup:
            assert tmpFile.delete()
    }
    
    def "Hard Link or copy"() {
        setup:
            File file = Files.createTempFile("test", "test").toFile()
            file.text = "Hello World"
            File tmpFile = new File(file.parentFile, "file.ext.copy.test")
            FileExt.hardLinkOrCopyTo(file, tmpFile)
        expect:
            assert tmpFile.exists()
            assert file.exists()
            assert tmpFile.text == "Hello World"
        cleanup:
            assert tmpFile.delete()
            assert file.delete()
    }
    
    def "List files and delete using string filters"() {
        setup:
            File tmpDir = Files.createTempDirectory("test").toFile()
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
            assert FileExt.deleteFiles(tmpDir, "*.tmp")
            assert tmpDir.deleteDir()
    }
    
    def "List files and delete using FileFilter"() {
        setup:
            File tmpDir = Files.createTempDirectory("test").toFile()
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
            assert tmpDir.deleteDir()
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
            File tmpDir = Files.createTempDirectory("test").toFile()
            (1..10).each {
                new File(tmpDir, "test-file-${it}.txt").text = "test"
            }
            int x = 0
        when:
            FileExt.eachFileAsync(tmpDir, {
                println it.name
                x++
            })
        then:
            noExceptionThrown()
            assert x == 10
        cleanup:
            assert tmpDir.deleteDir()
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
            File tmpDir = Files.createTempDirectory("test").toFile()
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

    /**
     * File.get() must generate the same path:
     */
    def "All paths must match - User dir"() {
        setup:
            File f1 = new File(new File(FileStaticExt.getUserDir(null), "directory"), "file.txt")
            File f2 = FileStaticExt.get(null,"directory", "file.txt")
            File f3 = FileStaticExt.get(null,FileStaticExt.getUserDir(null), "directory", "file.txt")
        expect:
            assert f1.absolutePath == f2.absolutePath
            assert f2.absolutePath == f3.absolutePath
    }
    /**
     * File.get() must generate the same path:
     */
    def "All paths must match - Home dir"() {
        setup:
            File f1 = new File(new File(FileStaticExt.getHomeDir(null), "directory"), "file.txt")
            File f2 = FileStaticExt.get(null,"~/directory", "file.txt")
            File f3 = FileStaticExt.get(null,FileStaticExt.getHomeDir(null), "directory", "file.txt")
        expect:
            assert f1.absolutePath == f2.absolutePath
            assert f2.absolutePath == f3.absolutePath
    }

    def "Create file should create path and file"() {
        setup:
            File f = FileStaticExt.create(null, FileStaticExt.getTempDir(null), "some", "dir", "in", "temp", "path.txt")
        expect:
            assert f.exists()
        cleanup:
            if(f.exists()) {
                f.delete()
                new File(FileStaticExt.getTempDir(null), "some").deleteDir()
            }
    }
}
